package shared.command_classes;

import server.ServerFacade;
import shared.ColorNum;
import shared.Result;

public class SetPlayerColorCommand extends Command
{
    public Result execute()
    {
        ColorNum c = ColorNum.BLACK;
        String auth = "";
        boolean success = ServerFacade.getInstance().setPlayerColor(c, auth);
        return new Result(success, auth);
    }
}
