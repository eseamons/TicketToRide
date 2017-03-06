package shared.command_classes;

import com.google.gson.JsonObject;

import server.ServerFacade;
import shared.Result;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawFaceUpCardCommand extends Command {

    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String auth = jsonObject.get("auth").getAsString();
        int faceUpCardID = Integer.parseInt(jsonObject.get("faceUpCardID").getAsString());
        boolean success = ServerFacade.getInstance().drawFaceUpCard(faceUpCardID, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
    }
}
