package workflow.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import workflow.commands.CommandResult;

@ActivityInterface
public interface Activities {
    @ActivityMethod
    String selectRobot();

    @ActivityMethod
    void sendCommand(String commandJson);

    @ActivityMethod
    CommandResult getCommandStatus(String robotId);
}
