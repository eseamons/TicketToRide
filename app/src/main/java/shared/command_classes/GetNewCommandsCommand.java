package shared.command_classes;

import com.google.gson.JsonObject;

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
        JsonObject jsonObject = convertStringToJsonObject(info);
        int gameID = Integer.parseInt(jsonObject.get("gameID").getAsString());
        String auth = jsonObject.get("auth").getAsString();
        List<Command> cmds = ServerFacade.getInstance().getNewCommands(gameID, auth);
        if(cmds == null)
        {
            return new Result(false, "");
        }
        else
            return new Result(true,ServerSerializer.serializeObject(cmds));
    }

}
