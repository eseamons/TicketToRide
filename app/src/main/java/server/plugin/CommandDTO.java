package server.plugin;

import shared.command_classes.Command;

/**
 * Created by rredd94 on 4/11/17.
 */

public class CommandDTO {
    Command command;
    int gameID;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
