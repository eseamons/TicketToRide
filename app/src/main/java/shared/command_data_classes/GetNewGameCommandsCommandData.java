package shared.command_data_classes;

import shared.command_classes.Command;

/**
 * Created by rebeccaredd on 3/8/17.
 */

public class GetNewGameCommandsCommandData {

    private int commandID;
    private String auth;
    private Command[] cmds;
    private int gameID;

    public GetNewGameCommandsCommandData() {

    }

    //getters
    public int getCommandID() {
        return this.commandID;
    }

    public String getAuth() {
        return this.auth;
    }

    public Command[] getCmds() {
        return this.cmds;
    }

    //setters
    public void setCommandID(int commandID) {
        this.commandID = commandID;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public void setCmds(Command[] cmds) {
        this.cmds = cmds;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }
}
