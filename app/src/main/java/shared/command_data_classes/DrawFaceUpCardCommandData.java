package shared.command_data_classes;

import shared.ColorNum;

/**
 * Created by erics on 3/7/2017.
 */

public class DrawFaceUpCardCommandData {
    private ColorNum faceUpCardID;
    private String auth;

    public DrawFaceUpCardCommandData() {
    }

    public ColorNum getFaceUpCardID() {
        return faceUpCardID;
    }

    public void setFaceUpCardID(ColorNum faceUpCardID) {
        this.faceUpCardID = faceUpCardID;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
