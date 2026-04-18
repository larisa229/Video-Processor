package packaging;

import core.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ManifestBuilder implements PipelineStage {
    @Override
    public String getName() {
        return "Manifest Builder";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        Path manifestPath = wf.getOutputDir().resolve(wf.getMovieId()).resolve("manifest.json");

        try {
            String json = "{\n" +
                    "  \"movieId\": \"" + wf.getMovieId() + "\",\n" +
                    "  \"checksum\": \"" + wf.metadata.get("checksum") + "\",\n" +
                    "  \"format\": \"" + wf.metadata.get("format") + "\",\n" +
                    "  \"safety_status\": \"" + wf.metadata.get("safety_status") + "\",\n" +
                    "  \"drm_status\": \"" + wf.metadata.get("drm_status") + "\",\n" +
                    "  \"branding\": \"" + wf.metadata.get("applied_branding") + "\",\n" +
                    "  \"timestamps\": {\n" +
                    "    \"intro_end\": \"" + wf.metadata.get("intro_end") + "\",\n" +
                    "    \"outro_start\": \"" + wf.metadata.get("outro_start") + "\",\n" +
                    "    \"watch_next_trigger\": \"" + wf.metadata.get("watch_next_trigger") + "\"\n" +
                    "  },\n" +
                    "  \"assets\": {\n" +
                    "    \"video\": \"video/h264/1080p_h264.mp4\",\n" +
                    "    \"sprite\": \"images/sprite_map.jpg\",\n" +
                    "    \"transcript\": \"" + wf.metadata.get("transcript_path") + "\",\n" +
                    "    \"translation\": \"" + wf.metadata.get("translation_ro_path") + "\",\n" +
                    "    \"dub\": \"" + wf.metadata.get("audio_ro_path") + "\"\n" +
                    "  }\n" +
                    "}";

            Files.writeString(manifestPath, json);
            System.out.println("Manifest.json has been created");
        } catch (IOException e) {
            throw new StageException(getName(), "Failed to build manifest", e);
        }
    }
}
