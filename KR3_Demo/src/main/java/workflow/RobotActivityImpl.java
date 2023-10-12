package workflow;

public class RobotActivityImpl implements RobotActivity {

    @Override
    public void uis_pick(Integer robotId, Integer toteNum, Double toteMass) {
        System.out.println("UIS Pick");
    }

    @Override
    public void conveyer_put(Integer robotId) {
        System.out.println("Conveyer Put");
    }

    @Override
    public void convery_pick(Integer robotId) {
        System.out.println("Conveyer Pick");
    }

    @Override
    public void uis_put(Integer robotId, Integer toteNum) {
        System.out.println("UIS Put");
    }
}
