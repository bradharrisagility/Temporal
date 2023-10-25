package workflow.temporal;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import workflow.commands.*;

import java.time.Duration;

public class ToteCycleWorkflowImpl implements ToteCycleWorkflow {
    private final RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(60))
            .setMaximumAttempts(2)
            .build();
    private final ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(60))
            .setRetryOptions(retryOptions)
            .build();

    private final Activities activities = Workflow.newActivityStub(Activities.class, activityOptions);

    @Override
    public void ToteCycle(Integer toteNum, Double toteMass) {
        Promise<CommandResult> commandResultPromise;

        commandResultPromise = executeRobotCommand(new UisPickCommand(toteNum, toteMass));
        if( commandResultPromise.get() == CommandResult.FAILED ) {
            return;
        }

        commandResultPromise = executeRobotCommand(new ConveyerPutCommand());
        if( commandResultPromise.get() == CommandResult.FAILED ) {
            return;
        }

        commandResultPromise = executeRobotCommand(new ConveyerPickCommand());
        if( commandResultPromise.get() == CommandResult.FAILED ) {
            return;
        }

        commandResultPromise = executeRobotCommand(new UisPutCommand());
        if(commandResultPromise.get() == CommandResult.SUCCESS) {
            System.out.println("Workflow Completed Successfully");
        }
    }

    private Promise<CommandResult> executeRobotCommand(RobotCommand command) {
        String robotId = activities.selectRobot();
        command.setRobotId(robotId);
        activities.sendCommand(command.toJson().toString());
        return Async.function(activities::getCommandStatus, robotId);
    }
}
