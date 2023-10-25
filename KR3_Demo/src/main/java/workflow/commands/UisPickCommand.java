package workflow.commands;

import com.google.gson.JsonObject;

public class UisPickCommand extends RobotCommand {
    private final int toteNum;
    private final double toteMass;

    public UisPickCommand(int toteNum, double toteMass) {
        this(null, toteNum, toteMass);
    }

    public UisPickCommand(String robotId, int toteNum, double toteMass) {
        this.command = "uis-pick";
        this.robotId = robotId;
        this.toteNum = toteNum;
        this.toteMass = toteMass;
    }

    @Override
    public JsonObject toJson() {
        JsonObject commandJson = toJsonNoParams();
        commandJson.addProperty("tote_num", toteNum);
        commandJson.addProperty("tote_mass", toteMass);
        return commandJson;
    }
}
