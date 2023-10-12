package workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface KR3Workflow {

    @WorkflowMethod
    void replaceTote(Integer toteNum, Double toteMass);
}
