package shared.command_classes;

import server.ServerFacade;
import shared.Result;

public class BeginGameCommand extends Command
{
    public Result execute()
    {
        String parts[] = info.split(" ");
        int ID = Integer.parseInt(parts[0]);
        String auth = parts[1];
        boolean success = ServerFacade.getInstance().BeginGame(ID, auth);
        return new Result(success, "");
    }
}
