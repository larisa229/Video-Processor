package audiotext;

import core.*;

import java.util.List;

public class AudioTextPhase {
    public void run(Workflow wf) throws StageException {
        List<PipelineStage> stages = List.of(
                new SpeechToText(),
                new Translator(),
                new AIDubber()
        );

        for(PipelineStage stage : stages) {
            stage.execute(wf);
        }

        System.out.println("Audio phase complete");
    }
}
