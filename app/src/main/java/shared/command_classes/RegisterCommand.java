package shared.command_classes;

import server.ServerFacade;
import shared.Result;

public class RegisterCommand extends Command
{
    public Result execute()
    {
        String name = "";
        String password = "";
        boolean success = ServerFacade.getInstance().Register(name, password);
        return new Result(success, "");
    }

}
