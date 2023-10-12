package workflow;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class StartWorkers {

    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("replace-tote-task-queue");

        worker.registerWorkflowImplementationTypes(KR3WorkflowImpl.class);
        worker.registerActivitiesImplementations(new RobotActivityImpl());

        factory.start();
    }
}
