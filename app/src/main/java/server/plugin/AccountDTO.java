package server.plugin;

import shared.model_classes.Account;

/**
 * Created by rredd94 on 4/11/17.
 */

public class AccountDTO {
    Account account;

    String auth;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
