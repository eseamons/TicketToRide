package shared.command_data_classes;

import java.util.ArrayList;
import java.util.List;

import shared.model_classes.DestinationCard;

/**
 * Created by erics on 3/6/2017.
 */

public class ConfirmDestinationCardCommandData {
    private String auth;
    private int gameID;
    private int playerID;
    private boolean [] confirmedCardsbool;
    private DestinationCard[] confirmedCards;


    public ConfirmDestinationCardCommandData() {

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

    public boolean[] getConfirmedCardsBools() {
        return confirmedCardsbool;
    }

    public void setConfirmedCardsBools(boolean[] confirmedCardsbool) {
        this.confirmedCardsbool = confirmedCardsbool;
    }

    public void setConfirmedCards(DestinationCard[] confirmedCards) {
        this.confirmedCards = confirmedCards;
    }

    public DestinationCard[] getConfirmedCards() {
        return this.confirmedCards;
    }

}
