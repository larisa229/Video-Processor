package visuals;

import core.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class SpriteGenerator implements PipelineStage {
    @Override
    public String getName() {
        return "Sprite Generator";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        Path imageDir = wf.getOutputDir().resolve(wf.getMovieId()).resolve("images");
        Path spritePath = imageDir.resolve("sprite_map.jpg");
        Path thumbDir = imageDir.resolve("thumbnails");

        try {
            Files.createDirectories(imageDir);
            Files.createDirectories(thumbDir);

            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg", "-loglevel", "quiet", "-y", "-i", wf.getInputFile().toString(),
                    "-vf", "fps=1,scale=160:-1,tile=3x3",
                    imageDir.resolve("sprite_map.jpg").toString()
            );

            Process proc = pb.start();
            int exitCode = proc.waitFor();

            if (exitCode != 0 && !Files.exists(spritePath)) {
                throw new Exception("ffmpeg failed and no image was created");
            }
            System.out.println("Sprite map generated");
        } catch (Exception e) {
            throw new StageException(getName(), "Sprite generation failed", e);
        }
    }
}
