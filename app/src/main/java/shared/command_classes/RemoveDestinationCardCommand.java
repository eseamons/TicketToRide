package shared.command_classes;

import com.google.gson.JsonObject;

import java.util.List;

import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.RemoveDestinationCardCommandData;
import shared.model_classes.DestinationCard;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class RemoveDestinationCardCommand extends Command {

    public Result execute()
    {
        DestinationCard destinationCard = ((RemoveDestinationCardCommandData) info).getDiscardedCard();
        String auth = ((RemoveDestinationCardCommandData) info).getAuth();
        int gameID = ((RemoveDestinationCardCommandData)info).getGameID();
        boolean success = ServerFacade.getInstance().removeDestinationCard(destinationCard,gameID, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        //TODO: has not been implemeted yet
    }
}
