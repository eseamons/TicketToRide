package shared.command_classes;

import com.google.gson.JsonObject;

import server.ServerFacade;
import shared.Result;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawDeckCardCommand extends Command {

    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String auth = jsonObject.get("auth").getAsString();
        boolean success = ServerFacade.getInstance().drawDeckCard(auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
    }

}
