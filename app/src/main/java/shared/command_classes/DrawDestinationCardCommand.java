package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import client.ClientModel;
import server.ServerFacade;
import shared.Result;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawDestinationCardCommand extends Command {
    public Result execute()
    {

        JsonObject jsonObject = convertStringToJsonObject(info);
        String destinationCardName = jsonObject.get("destinationCardName").getAsString();
        int playerID = jsonObject.get("playerID").getAsInt();
        String auth = jsonObject.get("auth").getAsString();
        boolean success = ServerFacade.getInstance().drawDestinationCard(destinationCardName,playerID, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        ClientModel clientModel = ClientModel.getInstance();
        JsonObject jsonObject = convertStringToJsonObject(info);
        String destinationCardName = jsonObject.get("destinationCardName").getAsString();
        int playerID = jsonObject.get("playerID").getAsInt();
        String auth = jsonObject.get("auth").getAsString();
        ClientFacade clientFacade = new ClientFacade();

    }
}
