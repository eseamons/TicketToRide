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
        boolean success = ServerFacade.getInstance().drawDeckCard(auth);
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
