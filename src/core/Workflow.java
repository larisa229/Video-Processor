package core;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Workflow {
    private final String movieId;
    private final Path inputFile;
    private final Path outputDir;

    private State state;
    public final Map<String, Object> metadata = new HashMap<>();

    public Workflow(String movieId, Path inputFile, Path outputDir) {
        this.movieId = movieId;
        this.inputFile = inputFile;
        this.outputDir = outputDir;
        this.state = State.UPLOADED;
    }

    public String getMovieId()       { return movieId; }
    public Path getInputFile()       { return inputFile; }
    public Path getOutputDir()       { return outputDir; }
    public State getState()          { return state; }
    public void setState(State s)    { this.state = s; }
}
