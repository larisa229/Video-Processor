package audiotext;

import core.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SpeechToText implements PipelineStage {
    @Override
    public String getName() {
        return "Speech To Text";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        Path textDir = wf.getOutputDir().resolve(wf.getMovieId()).resolve("text");
        try {
            Files.createDirectories(textDir);
            String content = "00:00:07 - Hey, it's Marty Mauser.\n00:00:08 - I'm in the royal suite.\n00:00:10 - I saw you in the lobby yesterday.";
            Files.writeString(textDir.resolve("source_transcript.txt"), content);

            wf.metadata.put("transcript_path", "text/source_transcript.txt");
            System.out.println("Source transcript generated");
        } catch (IOException e) {
            throw new StageException(getName(), "Failed to write transcript", e);
        }
    }
}
