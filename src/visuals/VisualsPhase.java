package visuals;

import core.*;

import java.util.List;

public class VisualsPhase {
    public void run(Workflow wf) throws StageException {
        List<PipelineStage> stages = List.of(
                new SceneComplexity(),
                new Transcoder(),
                new SpriteGenerator()
        );

        for(PipelineStage stage : stages) {
            stage.execute(wf);
        }

        System.out.println("Visuals phase complete");
    }
}
