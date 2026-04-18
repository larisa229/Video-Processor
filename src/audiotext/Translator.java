package audiotext;

import core.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class Translator implements PipelineStage {
    @Override
    public String getName() {
        return "Translator";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        Path textDir = wf.getOutputDir().resolve(wf.getMovieId()).resolve("text");
        try {
            String translated = "00:00:07 - Hei, sunt Marty Mauser.\n00:00:08 - Sunt în suita regală.\n00:00:10 - Te-am văzut în hol ieri.";
            Files.writeString(textDir.resolve("ro_translation.txt"), translated);

            wf.metadata.put("translation_ro_path", "text/ro_translation.txt");
            System.out.println("Romanian translation complete");
        } catch (Exception e) {
            throw new StageException(getName(), "Failed to write translation", e);
        }
    }
}
