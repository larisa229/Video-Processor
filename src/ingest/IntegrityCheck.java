package ingest;

import core.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

public class IntegrityCheck implements PipelineStage {

    @Override
    public String getName() {
        return "Integrity Check";
    }

    @Override
    public void execute(Workflow wf) throws StageException {
        Path file = wf.getInputFile();
        if(!Files.exists(file))
            throw new StageException(getName(), "File not found: " + wf.getInputFile());

        try {
            byte[] bytes = Files.readAllBytes(file);
            if(bytes.length < 4) {
                throw new StageException(getName(), "File header missing");
            }
            System.out.println("Header check passed");

            byte[] hash = MessageDigest.getInstance("SHA-256").digest(bytes);
            StringBuilder sb = new StringBuilder();
            for(byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            wf.metadata.put("checksum", sb.toString());
            System.out.println("Integrity check succeeded: " + sb);
        } catch (StageException e) {
            throw e;
        } catch (Exception e) {
            throw new StageException(getName(), "Integrity check failed", e);
        }
    }
}
