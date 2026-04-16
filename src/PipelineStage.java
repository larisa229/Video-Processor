public interface PipelineStage {
    String getName();
    void execute(Workflow wf) throws StageException;
}
