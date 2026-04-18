package packaging;

import core.*;

import java.util.List;

public class PackagingPhase {
    public void run(Workflow wf) throws StageException {
        List<PipelineStage> stages = List.of(
                new DRMWrapper(),
                new ManifestBuilder()
        );

        for(PipelineStage stage : stages) {
            stage.execute(wf);
        }

        System.out.println("Pipeline complete");
    }
}
