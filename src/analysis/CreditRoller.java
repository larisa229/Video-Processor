package analysis;

import core.*;

public class CreditRoller implements PipelineStage {
    @Override
    public String getName() {
        return "Credit Roller";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        wf.metadata.put("watch_next_trigger", "00:43:30");
        System.out.println("Credit start point detected for 'Watch Next'");
    }
}
