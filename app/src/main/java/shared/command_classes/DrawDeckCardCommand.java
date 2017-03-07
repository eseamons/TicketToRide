package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import server.ServerFacade;
import shared.CardColor;
import shared.Result;
import shared.command_data_classes.DrawDeckCardCommandData;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawDeckCardCommand extends Command {

    public Result execute()
    {
        String auth = ((DrawDeckCardCommandData) info).getAuth();
        int playerID = ((DrawDeckCardCommandData) info).getPlayerID();
        boolean success = ServerFacade.getInstance().drawDeckCard(auth, playerID);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        String auth = ((DrawDeckCardCommandData) info).getAuth();
        int playerID = ((DrawDeckCardCommandData) info).getPlayerID();
        int gameID = ((DrawDeckCardCommandData) info).getGameID();
        CardColor card = ((DrawDeckCardCommandData) info).getCard();

        ClientFacade clientFacade = new ClientFacade();
        clientFacade.playerDrewDeckCard(gameID,playerID ,card);
    }

}
