package shared.command_classes;

import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;
import shared.model_classes.Account;

public class LoginCommand extends Command
{
    public Result execute()
    {
        String name = "";
        String pass = "";
        Account acnt = ServerFacade.getInstance().Login(name, pass);

        if(acnt == null)
            return new Result(false,"");
        else
            return new Result(true, ServerSerializer.serializeObject(acnt));
    }


}
