package compliance;

import core.*;

public class RegionalBranding implements PipelineStage {
    @Override
    public String getName() {
        return "Regional Branding";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        wf.metadata.put("applied_branding", "Global_Studio_Standard");
        System.out.println("Applied regional branding");
    }
}
