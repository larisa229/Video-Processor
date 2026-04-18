package visuals;

import core.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class Transcoder implements PipelineStage {
    @Override
    public String getName() {
        return "Transcoder";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        String[] codecs = {"h264", "vp9", "hevc"};
        String[] res = {"4k", "1080p", "720p"};
        Path h264Dir = wf.getOutputDir().resolve(wf.getMovieId()).resolve("video/h264");

        try {
            Files.createDirectories(h264Dir);

            for (String c : codecs) {
                Path dir = wf.getOutputDir().resolve(wf.getMovieId()).resolve("video/" + c);
                Files.createDirectories(dir);
                for (String r : res) {
                    Path file = dir.resolve(r + "_" + c + (c.equals("vp9") ? ".webm" : (c.equals("hevc") ? ".mkv" : ".mp4")));
                    if (!Files.exists(file)) Files.createFile(file);
                }
            }

            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg", "-loglevel", "quiet", "-y", "-i", wf.getInputFile().toString(),
                    "-s", "1920x1080", "-c:v", "libx264", "-crf", "23",
                    h264Dir.resolve("1080p_h264.mp4").toString()
            );

            System.out.println("Transcoding in progress...");
            Process p = pb.start();

            int exitCode = p.waitFor();
            if (exitCode != 0) {
                throw new Exception("ffmpeg failed with exit code " + exitCode);
            }

            wf.metadata.put("video_1080p_path", "video/h264/1080p_h264.mp4");
            System.out.println("Transcoding finished");

        } catch (Exception e) {
            throw new StageException(getName(), "Transcoding failed", e);
        }
    }
}
