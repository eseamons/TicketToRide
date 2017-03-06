package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.model_classes.Account;
import shared.model_classes.Route;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class ClaimRouteCommand extends Command {
    public Result execute() {
        try {
            JsonObject jsonObject = convertStringToJsonObject(info);
            String auth = jsonObject.get("auth").getAsString();
            int gameID = jsonObject.get("gameID").getAsInt();
            String routeString = jsonObject.get("route").getAsString();
            Route route = (Route)Serializer.deserialize(routeString);

            boolean success = ServerFacade.getInstance().claimRoute(gameID, route,auth);
            return new Result(success, auth);
        }catch (Exception e) {e.printStackTrace();}
        return null;
    }

    public void executeOnClient()
    {
        try {
            JsonObject jsonObject = convertStringToJsonObject(info);
            String auth = jsonObject.get("auth").getAsString();
            int gameID = jsonObject.get("gameID").getAsInt();
            String routeString = jsonObject.get("route").getAsString();
            Route route = (Route)Serializer.deserialize(routeString);

            ClientFacade clientFacade = new ClientFacade();
            clientFacade.RouteClaimedbyPlayer(gameID,route,auth);

        } catch (Exception e)
        {e.printStackTrace();}


    }
}
