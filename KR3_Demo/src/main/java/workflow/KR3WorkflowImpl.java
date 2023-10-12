package workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class KR3WorkflowImpl implements KR3Workflow{
    private final RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(60))
            .setMaximumAttempts(2)
            .build();
    private final ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(10))
            .setRetryOptions(retryOptions)
            .build();

    private final RobotActivity robot = Workflow.newActivityStub(RobotActivity.class, activityOptions);
    private final AllocationActivity allocator = Workflow.newActivityStub(AllocationActivity.class, activityOptions);

    @Override
    public void replaceTote(Integer toteNum, Double toteMass) {
        System.out.println("Starting Workflow");

        Integer allocatedRobot = allocator.selectRobot();
        robot.uis_pick(allocatedRobot, toteNum, toteMass);
        // Send to Robot
        // Wait for status - listen to queue

        allocatedRobot = allocator.selectRobot();
        robot.conveyer_put(allocatedRobot);
        // Send to Robot
        // Wait for status

        allocatedRobot = allocator.selectRobot();
        robot.convery_pick(allocatedRobot);
        // Send to robot
        // Wait for status

        allocatedRobot = allocator.selectRobot();
        robot.uis_put(allocatedRobot, toteNum);
        // Send to robot
        // Wait for status
    }
}
