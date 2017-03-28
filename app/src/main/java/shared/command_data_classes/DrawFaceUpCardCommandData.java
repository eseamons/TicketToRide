package shared.command_data_classes;

import shared.CardColor;
import shared.ColorNum;

/**
 * Created by erics on 3/7/2017.
 */

public class DrawFaceUpCardCommandData {
    private int faceUpCardID;
    private String auth;
    private int gameID;
    private CardColor cardColor;
    private int playerID;

    public DrawFaceUpCardCommandData() {
    }

    public int getFaceUpCardID() {
        return faceUpCardID;
    }

    public void setFaceUpCardID(int faceUpCardID) {
        this.faceUpCardID = faceUpCardID;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
