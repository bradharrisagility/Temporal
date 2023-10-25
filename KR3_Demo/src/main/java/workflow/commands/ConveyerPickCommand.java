package workflow.commands;

import com.google.gson.JsonObject;

public class ConveyerPickCommand extends RobotCommand {

    public ConveyerPickCommand() {
        this(null);
    }
    public ConveyerPickCommand(String robotId) {
        this.command = "uis-put";
        this.robotId = robotId;
    }
    @Override
    public JsonObject toJson() {
        return toJsonNoParams();
    }
}
