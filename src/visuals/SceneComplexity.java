package visuals;

import core.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class SceneComplexity implements PipelineStage {
    @Override
    public String getName() {
        return "Scene Complexity";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "ffprobe", "-v", "error", "-select_streams", "v:0",
                    "-show_entries", "stream=width,height,avg_frame_rate",
                    "-of", "default=noprint_wrappers=1",
                    wf.getInputFile().toString()
            );

            Process proc = pb.start();
            try (BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                String line;
                while((line = input.readLine()) != null) {
                    String[] parts = line.split("=");
                    wf.metadata.put("probe_" + parts[0], parts[1]);
                }
            }

            if(proc.waitFor() != 0) throw new Exception("ffprobe failed");

            Path metaDir = wf.getOutputDir().resolve(wf.getMovieId()).resolve("metadata");
            Files.createDirectories(metaDir);

            StringBuilder json = new StringBuilder("{\n");
            for (var entry : wf.metadata.entrySet()) {
                if (entry.getKey().startsWith("probe_")) {
                    json.append("  \"")
                            .append(entry.getKey()).append("\": \"")
                            .append(entry.getValue()).append("\",\n");
                }
            }
            json.append("  \"classifier\": \"ffprobe\"\n}");
            Files.writeString(metaDir.resolve("scene_analysis.json"), json.toString());

            System.out.println("ffprobe analysis complete");

        } catch (Exception e) {
            throw new StageException(getName(), "Failed to analyze scene complexity", e);
        }
    }
}
