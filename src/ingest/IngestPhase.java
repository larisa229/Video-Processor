package ingest;

import core.*;

import java.util.List;

public class IngestPhase {
    public void run(Workflow wf) throws StageException {
        List<PipelineStage> stages = List.of(
                new IntegrityCheck(),
                new FormatValidator()
        );

        for (PipelineStage stage : stages) {
            stage.execute(wf);
        }
        System.out.println("Ingest phase complete");
    }
}
