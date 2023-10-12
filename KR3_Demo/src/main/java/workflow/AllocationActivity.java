package workflow;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AllocationActivity {
    @ActivityMethod
    public Integer selectRobot();
}
