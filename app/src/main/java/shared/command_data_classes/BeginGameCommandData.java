package shared.command_data_classes;

/**
 * Created by erics on 3/6/2017.
 */

public class BeginGameCommandData {
    private int gameLobbyID;
    private String auth;

    public BeginGameCommandData() {

    }

    public int getGameLobbyID() {
        return gameLobbyID;
    }

    public void setGameLobbyID(int gameLobbyID) {
        this.gameLobbyID = gameLobbyID;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
