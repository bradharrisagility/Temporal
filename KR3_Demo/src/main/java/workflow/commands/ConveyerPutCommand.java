package workflow.commands;

import com.google.gson.JsonObject;

public class ConveyerPutCommand extends RobotCommand {

    public ConveyerPutCommand() {
        this(null);
    }
    public ConveyerPutCommand(String robotId) {
        this.command = "uis-put";
        this.robotId = robotId;
    }
    @Override
    public JsonObject toJson() {
        return toJsonNoParams();
    }
}
