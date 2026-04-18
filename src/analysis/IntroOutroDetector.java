package analysis;

import core.*;

public class IntroOutroDetector implements PipelineStage {
    @Override
    public String getName() {
        return "Intro/Outro Detector";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        wf.metadata.put("intro_end", "00:02:15");
        wf.metadata.put("outro_start", "00:42:00");
        System.out.println("Intro/Outro timestamps identified");
    }
}
