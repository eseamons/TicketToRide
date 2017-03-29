package shared.command_data_classes;

import shared.CardColor;
import shared.model_classes.Route;

/**
 * Created by erics on 3/6/2017.
 */

public class ClaimRouteCommandData {
    private int gameID;
    private Route route;
    private String auth;
    private CardColor colorOfCardsUsed;
    private int numOfCardsUsed;

    public ClaimRouteCommandData() {
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public CardColor getColorOfCardsUsed() {
        return colorOfCardsUsed;
    }

    public void setColorOfCardsUsed(CardColor colorOfCardsUsed) {
        this.colorOfCardsUsed = colorOfCardsUsed;
    }

    public int getNumOfCardsUsed() {
        return numOfCardsUsed;
    }

    public void setNumOfCardsUsed(int numOfCardsUsed) {
        this.numOfCardsUsed = numOfCardsUsed;
    }
}
