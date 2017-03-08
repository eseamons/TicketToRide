package shared.command_data_classes;

import java.util.ArrayList;
import java.util.List;

import shared.model_classes.DestinationCard;

/**
 * Created by erics on 3/6/2017.
 */

public class RemoveDestinationCardCommandData {
    List<DestinationCard> discardedCards;
    private String auth;
    int gameID;
    int playerID;

    public RemoveDestinationCardCommandData() {
        discardedCards = new ArrayList<>();
    }

    public List<DestinationCard> getDiscardedCards() {
        return discardedCards;
    }

    public void setDiscardedCards(List<DestinationCard> discardedCards) {
        this.discardedCards = discardedCards;
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

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
