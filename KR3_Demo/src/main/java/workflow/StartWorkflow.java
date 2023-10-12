package workflow;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class StartWorkflow {

    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowOptions options1 = WorkflowOptions.newBuilder()
            .setTaskQueue("replace-tote-task-queue")
            .setWorkflowId("replace-tote-workflow-1")
            .build();
        WorkflowOptions options2 = WorkflowOptions.newBuilder()
                .setTaskQueue("replace-tote-task-queue")
                .setWorkflowId("replace-tote-workflow-2")
                .build();

        WorkflowClient client = WorkflowClient.newInstance(service);

        KR3Workflow workflow1 = client.newWorkflowStub(KR3Workflow.class, options1);
        KR3Workflow workflow2 = client.newWorkflowStub(KR3Workflow.class, options2);

        WorkflowExecution execution1 = WorkflowClient.start(workflow1::replaceTote, 0, 0.0);
        WorkflowExecution execution2 = WorkflowClient.start(workflow2::replaceTote, 3, 5.0);

        System.out.printf("Workflow %s Execution Complete. Run ID %s", execution1.getWorkflowId(), execution1.getRunId());
        System.out.printf("Workflow %s Execution Complete. Run ID %s", execution2.getWorkflowId(), execution2.getRunId());

        System.exit(0);
    }
}
