package shared.command_classes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerFacade;
import shared.Result;

public class RegisterCommand extends Command
{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        boolean success = ServerFacade.getInstance().Register(username, password);
        return new Result(success, "");
    }

}
