package workflow;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import workflow.temporal.ToteCycleWorkflow;

import java.time.Duration;

public class ExecuteToteCycle {
    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("tote-cycle-queue")
                .setWorkflowId("tote-cycle-" + System.currentTimeMillis())
                .setWorkflowExecutionTimeout(Duration.ofSeconds(10))
                .build();
        WorkflowClient client = WorkflowClient.newInstance(service);

        ToteCycleWorkflow workflow = client.newWorkflowStub(ToteCycleWorkflow.class, options);
        WorkflowExecution execution = WorkflowClient.start(workflow::ToteCycle, 10, 5.0);
        System.out.printf("Workflow %s Execution Complete. Run ID %s", execution.getWorkflowId(), execution.getRunId());
    }
}
