package core;

import analysis.AnalysisPhase;
import audiotext.AudioTextPhase;
import compliance.CompliancePhase;
import ingest.IngestPhase;
import packaging.PackagingPhase;
import visuals.VisualsPhase;

public class Orchestrator {

    public void process(Workflow wf) {
        try {
            while (wf.getState() != State.COMPLETED && wf.getState() != State.FAILED) {
                switch (wf.getState()) {
                    case UPLOADED:
                        handleEvent(wf, Event.INGEST);
                        break;
                    case INGESTED:
                        handleEvent(wf, Event.ANALYZE);
                        break;
                    case ANALYZED:
                        handleEvent(wf, Event.PROCESS_VISUALS);
                        break;
                    case VISUALS_PROCESSED:
                        handleEvent(wf, Event.PROCESS_AUDIO);
                        break;
                    case AUDIO_PROCESSED:
                        handleEvent(wf, Event.CHECK_COMPLIANCE);
                        break;
                    case COMPLIANCE_CHECKED:
                        handleEvent(wf, Event.PACKAGE);
                        break;
                    case PACKAGED:
                        handleEvent(wf, Event.FINISH);
                        break;
                    default:
                        return;
                }
            }
        } catch (Exception e) {
            System.err.println("Critical failure: " + e.getMessage());
            e.printStackTrace();
            handleEvent(wf, Event.FAIL);
        }
    }

    private void handleEvent(Workflow wf, Event event) {
        switch (event) {
            case INGEST:
                new IngestPhase().run(wf);
                wf.setState(State.INGESTED);
                break;
            case ANALYZE:
                new AnalysisPhase().run(wf);
                wf.setState(State.ANALYZED);
                break;
            case PROCESS_VISUALS:
                new VisualsPhase().run(wf);
                wf.setState(State.VISUALS_PROCESSED);
                break;
            case PROCESS_AUDIO:
                new AudioTextPhase().run(wf);
                wf.setState(State.AUDIO_PROCESSED);
                break;
            case CHECK_COMPLIANCE:
                new CompliancePhase().run(wf);
                wf.setState(State.COMPLIANCE_CHECKED);
                break;
            case PACKAGE:
                new PackagingPhase().run(wf);
                wf.setState(State.PACKAGED);
                break;
            case FINISH:
                wf.setState(State.COMPLETED);
                break;
            case FAIL:
                wf.setState(State.FAILED);
                break;
        }
    }
}
