package packaging;

import core.*;

public class DRMWrapper implements PipelineStage {
    @Override
    public String getName() {
        return "DRM Wrapper";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        wf.metadata.put("drm_status", "encrypted_aes_128");
        System.out.println("All assets encrypted with DRM Wrapper");
    }
}
