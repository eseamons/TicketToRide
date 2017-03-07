package shared.command_classes;

import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.RegisterCommandData;

public class RegisterCommand extends Command
{
    @Override
    public Result execute()
    {
        String username = ((RegisterCommandData) info).getUsername();
        String password = ((RegisterCommandData) info).getPassword();
        boolean success = ServerFacade.getInstance().register(username, password);
        return new Result(success, "");
    }

}
