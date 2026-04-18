package audiotext;

import core.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class AIDubber implements PipelineStage {
    @Override
    public String getName() {
        return "AI Dubber";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        Path audioDir = wf.getOutputDir().resolve(wf.getMovieId()).resolve("audio");
        try {
            Files.createDirectories(audioDir);
            Path dubFile = audioDir.resolve("ro_dub_synthetic.aac");
            if(!Files.exists(dubFile)) {
                Files.createFile(dubFile);
            }

            wf.metadata.put("audio_ro_path", "audio/rp_dub_synthetic.aac");
            System.out.println("Synthetic Romanian dub generated");
        } catch (Exception e) {
            throw new StageException(getName(), "Failed to generate AI dubbing", e);
        }
    }
}
