package shared.command_data_classes;

import shared.model_classes.Route;

/**
 * Created by erics on 3/6/2017.
 */

public class ClaimRouteCommandData {
    private int gameID;
    private Route route;
    private String auth;

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
}
