package workflow.commands;

import com.google.gson.JsonObject;

public class UisPutCommand extends RobotCommand {

    public UisPutCommand() {
        this(null);
    }
    public UisPutCommand (String robotId) {
        this.command = "uis-put";
        this.robotId = robotId;
    }
    @Override
    public JsonObject toJson() {
        return toJsonNoParams();
    }
}
