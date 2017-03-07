package shared.command_data_classes;

import shared.model_classes.Account;

/**
 * Created by erics on 3/7/2017.
 */

public class JoinGameCommandData {
    private int gameLobbyID;
    private String auth;
    private Account account;

    public JoinGameCommandData() {

    }

    //getters
    public int getGameLobbyID() {
        return gameLobbyID;
    }

    public void setGameLobbyID(int gameLobbyID) {
        this.gameLobbyID = gameLobbyID;
    }

    public String getAuth() {
        return auth;
    }

    //setters
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
