package shared.command_classes;

import com.google.gson.JsonObject;

import server.ServerFacade;
import shared.ColorNum;
import shared.Result;
import shared.command_data_classes.SetPlayerColorCommandData;

public class SetPlayerColorCommand extends Command
{
    @Override
    public Result execute()
    {
        ColorNum c = ((SetPlayerColorCommandData) info).getColorNum();
        String auth = ((SetPlayerColorCommandData) info).getAuth();
        boolean success = ServerFacade.getInstance().setPlayerColor(c, auth);
        return new Result(success, auth);
    }
}
