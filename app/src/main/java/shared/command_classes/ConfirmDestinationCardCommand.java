package shared.command_classes;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.DrawDestinationCardCommandData;
import shared.command_data_classes.ConfirmDestinationCardCommandData;
import shared.model_classes.DestinationCard;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class ConfirmDestinationCardCommand extends Command {

    public Result execute()
    {
        boolean[] confirmedCards = ((ConfirmDestinationCardCommandData) info).getConfirmedCards();
        String auth = ((ConfirmDestinationCardCommandData) info).getAuth();
        int gameID = ((ConfirmDestinationCardCommandData)info).getGameID();
        int playerID = ((ConfirmDestinationCardCommandData) info).getPlayerID();
        boolean success = ServerFacade.getInstance().removeDestinationCard(gameID, playerID, confirmedCards, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        boolean[] confirmedCards = ((ConfirmDestinationCardCommandData) info).getConfirmedCards();
        int gameID = ((ConfirmDestinationCardCommandData) info).getGameID();
        int playerID = ((ConfirmDestinationCardCommandData) info).getPlayerID();


    }
}
