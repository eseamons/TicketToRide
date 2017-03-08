package shared.command_data_classes;

import shared.model_classes.DestinationCard;

/**
 * Created by erics on 3/7/2017.
 */

public class DrawDestinationCardCommandData {
    private String destinationCardName;
    private DestinationCard destinationCard;
    private int gameID;
    private String auth;
    int PlayerID;

    public DrawDestinationCardCommandData() {

    }
    public DestinationCard getDestinationCard() {
        return destinationCard;
    }

    public void setDestinationCard(DestinationCard destinationCard) {
        this.destinationCard = destinationCard;
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
