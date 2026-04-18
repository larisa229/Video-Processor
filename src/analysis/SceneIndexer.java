package analysis;

import core.*;

import java.util.List;
import java.util.Map;

public class SceneIndexer implements PipelineStage {
    @Override
    public String getName() {
        return "Scene Indexer";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        Map<String, String> scenes = Map.of(
                "establishing_shot", "00:00:00 - 00:02:15",
                "dialogue",          "00:02:15 - 00:10:00",
                "action",            "00:10:00 - 00:42:00"
        );
        wf.metadata.put("scene_categories", scenes);
        System.out.println("Scene indexed by category");
    }
}
