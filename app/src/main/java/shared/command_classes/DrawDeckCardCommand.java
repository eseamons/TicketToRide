package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import server.ServerFacade;
import shared.CardColor;
import shared.Result;
import shared.Serializer;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawDeckCardCommand extends Command {

    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String auth = jsonObject.get("auth").getAsString();
        int gameID = jsonObject.get("gameID").getAsInt();
        boolean success = ServerFacade.getInstance().drawDeckCard(auth, gameID);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        int playerID = jsonObject.get("playerID").getAsInt();
        int gameID = jsonObject.get("gameID").getAsInt();
        String cardString = jsonObject.get("card").getAsString();

        CardColor card = (CardColor) Serializer.deserialize(cardString);

        ClientFacade clientFacade = new ClientFacade();
        clientFacade.playerDrewDeckCard(gameID,playerID ,card);
    }

}
