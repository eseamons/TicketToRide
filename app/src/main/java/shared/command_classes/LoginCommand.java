package shared.command_classes;

import client.ClientFacade;
import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;
import shared.model_classes.Account;

public class LoginCommand extends Command
{
    public Result execute()
    {
        String[] parts = info.split(" ");
        String name = parts[0];
        String pass = parts[1];
        Account acnt = ServerFacade.getInstance().Login(name, pass);

        if(acnt == null)
            return new Result(false,"");
        else
            return new Result(true, ServerSerializer.serializeObject(acnt));
    }



}
