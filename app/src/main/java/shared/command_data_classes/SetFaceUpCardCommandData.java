package shared.command_data_classes;

import shared.CardColor;

/**
 * Created by rebeccaredd on 3/8/17.
 */

public class SetFaceUpCardCommandData {
    int gameID;
    int cardIndex;
    String auth;
    CardColor Card;


    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public CardColor getCard() {
        return Card;
    }

    public void setCard(CardColor card) {
        Card = card;
    }
}
