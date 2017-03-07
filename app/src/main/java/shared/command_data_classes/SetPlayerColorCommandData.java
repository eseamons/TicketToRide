package shared.command_data_classes;

import shared.ColorNum;

/**
 * Created by erics on 3/6/2017.
 */

public class SetPlayerColorCommandData {

    private ColorNum colorNum;
    private String auth;

    public SetPlayerColorCommandData() {

    }

    public ColorNum getColorNum() {
        return colorNum;
    }

    public void setColorNum(ColorNum colorNum) {
        this.colorNum = colorNum;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
