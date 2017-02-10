package shared.command_classes;

import server.ServerFacade;
import shared.Result;

public class BeginGameCommand extends Command
{
    public Result execute()
    {
        int ID = 0;
        String auth = "";
        boolean success = ServerFacade.getInstance().BeginGame(ID, auth);
        return new Result(success, "");
    }
}
