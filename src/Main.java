import java.nio.file.Paths;
import core.*;

public class Main {
    public static void main(String[] args) {
        Workflow movieWorkflow = new Workflow("movie_101", Paths.get("src/resources", "test.mp4"), Paths.get("output"));
        Orchestrator orchestrator = new Orchestrator();
        orchestrator.process(movieWorkflow);
    }
}