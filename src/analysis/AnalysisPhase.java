package analysis;

import core.*;
import java.util.List;

public class AnalysisPhase {
    public void run(Workflow wf) throws StageException {
        List<PipelineStage> stages = List.of(
                new IntroOutroDetector(),
                new CreditRoller(),
                new SceneIndexer()
        );

        for(PipelineStage stage : stages) {
            stage.execute(wf);
        }

        System.out.println("Analysis phase complete -> " + wf.getState());
    }
}
