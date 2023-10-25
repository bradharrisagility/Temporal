package workflow.temporal;

import workflow.commands.CommandResult;
import workflow.services.AllocationService;
import workflow.services.AwsService;

public class ActivitiesImpl implements Activities{
    AllocationService allocationService;
    AwsService awsService;

    public ActivitiesImpl() {
        this.allocationService = new AllocationService();
        awsService = new AwsService(
                System.getenv("AWS_ACCESS_KEY"),
                System.getenv("AWS_SECRET_KEY")
        );
    }

    @Override
    public String selectRobot() {
        return allocationService.selectRobot();
    }

    @Override
    public void sendCommand(String commandJson) {
        awsService.AddToSqs(commandJson);
    }

    @Override
    public CommandResult getCommandStatus(String robotId) {
        return CommandResult.SUCCESS;
    }
}
