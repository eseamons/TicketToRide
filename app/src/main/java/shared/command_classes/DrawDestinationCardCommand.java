package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import client.ClientModel;
import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.DrawDestinationCardCommandData;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawDestinationCardCommand extends Command {
    public Result execute()
    {
        String destinationCardName = ((DrawDestinationCardCommandData) info).getDestinationCardName();
        int playerID = ((DrawDestinationCardCommandData) info).getPlayerID();
        String auth = ((DrawDestinationCardCommandData) info).getAuth();
        boolean success = ServerFacade.getInstance().drawDestinationCard(destinationCardName,playerID, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        String destinationCardName = ((DrawDestinationCardCommandData) info).getDestinationCardName();
        int playerID = ((DrawDestinationCardCommandData) info).getPlayerID();
        String auth = ((DrawDestinationCardCommandData) info).getAuth();


        ClientFacade clientFacade = new ClientFacade();

    }
}
