package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;

import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;

public class LoginCommand extends Command
{
    @Override
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        Account account = ServerFacade.getInstance().login(username, password);
        Result result = null;
        if(account == null) {
            result = new Result(false,"");
        } else {
            try {
                result = new Result(true, Serializer.serialize(account));
            } catch(IOException e) {
                result = new Result(false,"");
            }
        }
        return result;
    }



}
