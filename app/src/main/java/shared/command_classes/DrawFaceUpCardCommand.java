package shared.command_classes;

import com.google.gson.JsonObject;

import server.ServerFacade;
import shared.ColorNum;
import shared.Result;
import shared.command_data_classes.DrawFaceUpCardCommandData;

/**
 * Created by rebeccaredd on 2/22/17.
 */

public class DrawFaceUpCardCommand extends Command {

    public Result execute()
    {
        String auth = ((DrawFaceUpCardCommandData) info).getAuth();
        ColorNum faceUpCardID = ((DrawFaceUpCardCommandData) info).getFaceUpCardID();
        boolean success = ServerFacade.getInstance().drawFaceUpCard(faceUpCardID, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
    }
}
