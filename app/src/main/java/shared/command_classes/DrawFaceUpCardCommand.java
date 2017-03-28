package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import server.ServerFacade;
import shared.CardColor;
import shared.ColorNum;
import shared.Result;
import shared.command_data_classes.DrawDeckCardCommandData;
import shared.command_data_classes.DrawFaceUpCardCommandData;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawFaceUpCardCommand extends Command {

    public Result execute()
    {
        DrawFaceUpCardCommandData data = (DrawFaceUpCardCommandData)info;
        String auth = data.getAuth();
        int faceUpCardID = data.getFaceUpCardID();
        int gameID = data.getGameID();
        boolean success = ServerFacade.getInstance().drawFaceUpCard(faceUpCardID, auth, gameID);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        DrawFaceUpCardCommandData data = (DrawFaceUpCardCommandData)info;
        int playerID = data.getPlayerID();
        int gameID = data.getGameID();
        CardColor card = data.getCardColor();

        ClientFacade clientFacade = new ClientFacade();
        clientFacade.playerDrewDeckCard(gameID,playerID ,card);
    }
}
