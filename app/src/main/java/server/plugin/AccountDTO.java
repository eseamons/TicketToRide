package server.plugin;

import shared.model_classes.Account;

/**
 * Created by rredd94 on 4/11/17.
 */

public class AccountDTO {
    Account account;
    int gameID;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
