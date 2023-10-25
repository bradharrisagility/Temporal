package workflow.temporal;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ToteCycleWorkflow {
    @WorkflowMethod
    void ToteCycle(Integer toteNum, Double toteMass);
}
