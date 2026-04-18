package compliance;

import core.*;

import java.util.List;

public class CompliancePhase {
    public void run(Workflow wf) throws StageException {
        List<PipelineStage> stages = List.of(
                new SafetyScanner(),
                new RegionalBranding()
        );

        for(PipelineStage stage : stages) {
            stage.execute(wf);
        }

        System.out.println("Compliance phase complete");
    }
}
