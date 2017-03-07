package shared.command_classes;

import com.google.gson.JsonObject;

import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.RemoveDestinationCardCommandData;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class RemoveDestinationCardCommand extends Command {

    public Result execute()
    {
        String destinationCardName = ((RemoveDestinationCardCommandData) info).getDestinationCardName();
        String auth = ((RemoveDestinationCardCommandData) info).getAuth();
        boolean success = ServerFacade.getInstance().removeDestinationCard(destinationCardName, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        //TODO: has not been implemeted yet
    }
}
