package shared.command_data_classes;

import java.util.List;

import shared.command_classes.Command;

/**
 * Created by erics on 3/6/2017.
 */

public class GetNewCommandsCommandData {

    private int gameLobbyID;
    private String auth;
    private Command[] cmds;

    public GetNewCommandsCommandData() {

    }

    //getters
    public int getGameLobbyID() {
        return this.gameLobbyID;
    }

    public String getAuth() {
        return this.auth;
    }

    public Command[] getCmds() {
        return this.cmds;
    }

    //setters
    public void setGameLobbyID(int gameLobbyID) {
        this.gameLobbyID = gameLobbyID;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public void setCmds(Command[] cmds) {
        this.cmds = cmds;
    }
}
