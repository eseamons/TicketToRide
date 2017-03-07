package shared.command_data_classes;

import shared.command_classes.CreateGameCommand;

/**
 * Created by erics on 3/6/2017.
 */

public class CreateGameCommandData {

    private String gameName;
    private int max_player_num;
    private int gameLobbyID;
    private String auth;

    public CreateGameCommandData() {

    }

    //setters
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setMaxPlayerNum(int max_player_num) {
        this.max_player_num = max_player_num;
    }

    public void setGameLobbyID(int gameLobbyID) {
        this.gameLobbyID = gameLobbyID;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    //getters
    public String getGameName() {
        return this.gameName;
    }

    public int getMaxPlayerNum() {
        return this.max_player_num;
    }

    public int getGameLobbyID() {
        return this.gameLobbyID;
    }


    public String getAuth() {
        return this.auth;
    }

}
