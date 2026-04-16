public class StageException extends RuntimeException {
    private final String stageName;

    public StageException(String stageName, String message) {
        super("[" + stageName + "] " + message);
        this.stageName = stageName;
    }

    public StageException(String stageName, String message, Throwable cause) {
        super("[" + stageName + "] " + message, cause);
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
    }
}
