package ingest;

import core.*;

import java.util.Set;

public class FormatValidator implements PipelineStage {
    private static final Set<String> ALLOWED = Set.of("mp4", "mov", "mkv", "webm");

    @Override
    public String getName() {
        return "Format Validator";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        String name = wf.getInputFile().getFileName().toString();
        String ext = name.substring(name.lastIndexOf('.') + 1).toLowerCase();

        if (!ALLOWED.contains(ext))
            throw new StageException(getName(), "Unsupported format: " + ext);

        wf.metadata.put("format", ext);
        System.out.println("Format validated: " + ext);
    }
}
