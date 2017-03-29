package shared.command_data_classes;

import shared.model_classes.DestinationCard;

/**
 * Created by erics on 3/7/2017.
 */

public class DrawDestinationCardCommandData {
    private String destinationCardName;
    private DestinationCard[] destinationCards;
    private int gameID;
    private String auth;
    int PlayerID;

    public DrawDestinationCardCommandData() {
        destinationCards = new DestinationCard[3];
    }
    public DestinationCard[] getDestinationCards() {
        return destinationCards;
    }

    public void setDestinationCard(DestinationCard destinationCard, int index) {
        this.destinationCards[index] = destinationCard;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public void setPlayerID(int playerID) {
        PlayerID = playerID;
    }
}
