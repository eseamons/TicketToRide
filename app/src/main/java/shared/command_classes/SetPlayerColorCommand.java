package shared.command_classes;

import com.google.gson.JsonObject;

import server.ServerFacade;
import shared.ColorNum;
import shared.Result;

public class SetPlayerColorCommand extends Command
{
    @Override
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        ColorNum c = ColorNum.valueOf(jsonObject.get("ColorNum").getAsString());
        String auth = jsonObject.get("auth").getAsString();
        boolean success = ServerFacade.getInstance().setPlayerColor(c, auth);
        return new Result(success, auth);
    }
}
