package shared.command_classes;

import java.util.List;

import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;

/**
 * Created by Michaels on 2/12/2017.
 */

public class GetNewCommandsCommand extends Command
{
    public Result execute()
    {
        String parts[] = info.split(" ");
        int ID = Integer.parseInt(parts[0]);
        String auth = parts[1];
        List<Command> cmds = ServerFacade.getInstance().getNewCommands(ID, auth);
        if(cmds == null)
        {
            return new Result(false, "");
        }
        else
            return new Result(true,ServerSerializer.serializeObject(cmds));
    }

}
