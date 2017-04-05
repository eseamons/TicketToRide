package shared.command_classes;

import client.ClientModel;
import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.ConfirmDestinationCardCommandData;
import shared.model_classes.DestinationCard;
import shared.model_classes.Player;

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
        ClientModel model = ClientModel.getInstance();
        boolean[] confirmedCardsBools = ((ConfirmDestinationCardCommandData) info).getConfirmedCardsBools();
        DestinationCard[] confirmedCards = ((ConfirmDestinationCardCommandData) info).getConfirmedCards();
        int gameID = ((ConfirmDestinationCardCommandData) info).getGameID();
        int playerID = ((ConfirmDestinationCardCommandData) info).getPlayerID();
        Player thisPlayer = model.getThis_player();

        if(playerID != thisPlayer.getPlayerID()) {

        }

    }
}
