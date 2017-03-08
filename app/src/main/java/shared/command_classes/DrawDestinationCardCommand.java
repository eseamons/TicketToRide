package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import client.ClientModel;
import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.DrawDestinationCardCommandData;
import shared.model_classes.DestinationCard;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawDestinationCardCommand extends Command {
    public Result execute()
    {
        int gameID = ((DrawDestinationCardCommandData) info).getGameID();
        String auth = ((DrawDestinationCardCommandData) info).getAuth();
        boolean success = ServerFacade.getInstance().drawDestinationCard(gameID, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        DestinationCard destinationCard = ((DrawDestinationCardCommandData) info).getDestinationCard();
        int gameID = ((DrawDestinationCardCommandData) info).getGameID();
        int playerID = ((DrawDestinationCardCommandData) info).getPlayerID();

        ClientFacade clientFacade = new ClientFacade();
        clientFacade.playerDrewDestinationCard(gameID, playerID, destinationCard);
    }
}
