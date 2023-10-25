package workflow;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import workflow.temporal.ActivitiesImpl;
import workflow.temporal.ToteCycleWorkflow;
import workflow.temporal.ToteCycleWorkflowImpl;

public class StartWorkers {

    public static void main(String[] args) {
        try {
            WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
            WorkflowClient client = WorkflowClient.newInstance(service);
            WorkerFactory factory = WorkerFactory.newInstance(client);
            Worker worker = factory.newWorker("tote-cycle-queue");

            worker.registerWorkflowImplementationTypes(ToteCycleWorkflowImpl.class);
            worker.registerActivitiesImplementations(new ActivitiesImpl());

            factory.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
