package shared.command_classes;

import server.ServerFacade;
import shared.ColorNum;
import shared.Result;

public class SetPlayerColorCommand extends Command
{
    public Result execute()
    {
        String[] parts = info.split(" ");
        ColorNum c = ColorNum.valueOf(parts[0]);
        String auth = parts[1];
        boolean success = ServerFacade.getInstance().setPlayerColor(c, auth);
        return new Result(success, auth);
    }
}
