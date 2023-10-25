package workflow.commands;

import com.google.gson.JsonObject;

import java.util.Map;

public abstract class RobotCommand {
    protected String command;
    protected String robotId;

    public String getCommand() {
        return command;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    protected JsonObject toJsonNoParams() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("command", command);
        if(robotId != null) {
            jsonObject.addProperty("robot_id", robotId);
        }
        return jsonObject;
    }

    public abstract JsonObject toJson();
}
