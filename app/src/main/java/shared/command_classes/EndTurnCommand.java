package shared.command_classes;

import com.google.gson.JsonObject;

import server.ServerFacade;
import shared.Result;


/**
 * Created by rebeccaredd on 2/22/17.
 */

public class EndTurnCommand extends Command{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        int gameID = jsonObject.get("gameID").getAsInt();
        String auth = jsonObject.get("auth").getAsString();
        boolean success = ServerFacade.getInstance().endTurn(gameID, auth);
        return new Result(success, auth);
    }

    public void executeOnClient()
    {
        //TODO: implement this and connect it to the client side.
    }
}
