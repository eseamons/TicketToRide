package shared.model_classes;

import shared.ColorNum;

public class Player {

    public Account account;
    public ColorNum color;

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public ColorNum getColor() {
        return color;
    }
    public void setColor(ColorNum color) {
        this.color = color;
    }

    public boolean authCodeMatchesAccount(String auth) {
        return account.getAuthentication() == auth;
    }

}
