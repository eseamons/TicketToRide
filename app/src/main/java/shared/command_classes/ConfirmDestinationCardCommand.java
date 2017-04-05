package shared.command_classes;

import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.ConfirmDestinationCardCommandData;
import shared.model_classes.DestinationCard;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class ConfirmDestinationCardCommand extends Command {

    public Result execute()
    {
        boolean[] confirmedCardsBools = ((ConfirmDestinationCardCommandData) info).getConfirmedCardsBools();
        DestinationCard[] acceptedCards = ((ConfirmDestinationCardCommandData) info).getConfirmedCards();
        String auth = ((ConfirmDestinationCardCommandData) info).getAuth();
        int gameID = ((ConfirmDestinationCardCommandData)info).getGameID();
        int playerID = ((ConfirmDestinationCardCommandData) info).getPlayerID();
        boolean success = ServerFacade.getInstance().removeDestinationCard(gameID, playerID, acceptedCards, confirmedCardsBools, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        boolean[] confirmedCards = ((ConfirmDestinationCardCommandData) info).getConfirmedCardsBools();
        int gameID = ((ConfirmDestinationCardCommandData) info).getGameID();
        int playerID = ((ConfirmDestinationCardCommandData) info).getPlayerID();

    }
}
