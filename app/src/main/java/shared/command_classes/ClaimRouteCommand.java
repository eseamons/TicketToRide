package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.command_data_classes.ClaimRouteCommandData;
import shared.model_classes.Account;
import shared.model_classes.Route;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class ClaimRouteCommand extends Command {
    public Result execute() {
        String auth = ((ClaimRouteCommandData) info).getAuth();
        int gameID = ((ClaimRouteCommandData) info).getGameID();
        Route route = ((ClaimRouteCommandData) info).getRoute();

        boolean success = ServerFacade.getInstance().claimRoute(gameID, route,auth);
        return new Result(success, auth);
    }

    public void executeOnClient()
    {
        String auth = ((ClaimRouteCommandData) info).getAuth();
        int gameID = ((ClaimRouteCommandData) info).getGameID();
        Route route = ((ClaimRouteCommandData) info).getRoute();

        ClientFacade clientFacade = new ClientFacade();
        clientFacade.RouteClaimedbyPlayer(gameID,route,auth);


    }
}
