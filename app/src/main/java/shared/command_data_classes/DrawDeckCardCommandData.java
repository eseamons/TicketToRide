package shared.command_data_classes;

import shared.CardColor;

/**
 * Created by erics on 3/7/2017.
 */

public class DrawDeckCardCommandData {
    private String auth;
    private int playerID;
    private int gameID;
    private CardColor card;

    public DrawDeckCardCommandData() {

    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public CardColor getCard() {
        return card;
    }

    public void setCard(CardColor card) {
        this.card = card;
    }
}
