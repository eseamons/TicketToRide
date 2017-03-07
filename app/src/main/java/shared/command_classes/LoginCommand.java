package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;

import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.command_data_classes.LoginCommandData;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;

public class LoginCommand extends Command
{
    @Override
    public Result execute()
    {
        String username = ((LoginCommandData) info).getUsername();
        String password = ((LoginCommandData) info).getPassword();
        Account account = ServerFacade.getInstance().login(username, password);
        Result result = null;
        if(account == null) {
            result = new Result(false,"");
        } else {
            result = new Result(true, account);
        }
        return result;
    }



}
