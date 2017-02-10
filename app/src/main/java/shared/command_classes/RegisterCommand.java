package shared.command_classes;

import server.ServerFacade;
import shared.Result;

public class RegisterCommand extends Command
{
    public Result execute()
    {
        String[] parts = info.split(" ");
        String name = parts[0];
        String password = parts[1];
        boolean success = ServerFacade.getInstance().Register(name, password);
        return new Result(success, "");
    }

}
