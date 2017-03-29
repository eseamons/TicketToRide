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
        return ServerFacade.getInstance().drawDestinationCards(gameID, auth);
    }

    public void executeOnClient() {
        DestinationCard[] destinationCards = ((DrawDestinationCardCommandData) info).getDestinationCards();


        int gameID = ((DrawDestinationCardCommandData) info).getGameID();
        int playerID = ((DrawDestinationCardCommandData) info).getPlayerID();
        String auth = ((DrawDestinationCardCommandData) info).getAuth();

        String playerAuth = ClientModel.getInstance().getAuthorization();
        if (auth.equals(playerAuth)) {
            ClientFacade clientFacade = new ClientFacade();
            clientFacade.playerDrewDestinationCards(gameID, playerID, destinationCards);
        }
    }
}
