package shared.command_classes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;
import shared.model_classes.Account;

public class LoginCommand extends Command
{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        Account account = ServerFacade.getInstance().Login(username, password);

        if(account == null)
            return new Result(false,"");
        else
            return new Result(true, ServerSerializer.serializeObject(account));
    }



}
