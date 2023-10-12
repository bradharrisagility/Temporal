package workflow;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.util.Map;

@ActivityInterface
public interface RobotActivity {

    @ActivityMethod
    void uis_pick(Integer robotId, Integer toteNum, Double toteMass);

    @ActivityMethod
    void conveyer_put(Integer robotId);

    @ActivityMethod
    void convery_pick(Integer robotId);

    @ActivityMethod
    void uis_put(Integer robotId, Integer toteNum);
}
