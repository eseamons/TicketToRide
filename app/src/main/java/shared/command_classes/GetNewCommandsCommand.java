package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.model_classes.GameLobby;

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
       Command[] cmdArray = (Command[]) cmds.toArray();
        Result result = null;
        if(cmds == null) {
            result = new Result(false,"");
        } else {
            try {
                result = new Result(true, Serializer.serialize(cmdArray));
            } catch(IOException e) {
                result = new Result(false,"");
            }
        }
        return result;
    }
}
