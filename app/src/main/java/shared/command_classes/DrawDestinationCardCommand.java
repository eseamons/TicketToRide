package shared.command_classes;

import com.google.gson.JsonObject;

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
        String auth = jsonObject.get("auth").getAsString();
        boolean success = ServerFacade.getInstance().drawDestinationCard(destinationCardName,auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
    }
}
