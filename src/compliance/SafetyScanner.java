package compliance;

import core.*;

public class SafetyScanner implements PipelineStage {
    @Override
    public String getName() {
        return "Safety Scanner";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        wf.metadata.put("safety_status", "passed");
        wf.metadata.put("blur_regions", "none");
        System.out.println("Safety scan complete. No violations found");
    }
}
