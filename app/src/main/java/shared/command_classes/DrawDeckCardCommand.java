package shared.command_classes;

import com.google.gson.JsonObject;

import server.ServerFacade;
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
    }

}
