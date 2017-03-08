package shared.command_data_classes;

import shared.command_classes.Command;
import shared.model_classes.GameLobby;

/**
 * Created by erics on 3/6/2017.
 */

public class GetGamesCommandData {
    private String auth;
    private GameLobby[] lobbies;

    public GetGamesCommandData() {

    }

    //getters
    public String getAuth() {
        return auth;
    }

    //setters
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public void setGameLobbies(GameLobby[] lobbies) {
        this.lobbies = lobbies;
    }


    public GameLobby[] getGameLobbies() {
        return lobbies;
    }
}
