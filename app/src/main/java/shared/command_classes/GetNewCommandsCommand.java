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
        int commandID = Integer.parseInt(jsonObject.get("commandID").getAsString());
        String auth = jsonObject.get("auth").getAsString();
        List<Command> cmds = ServerFacade.getInstance().getNewCommands(commandID, auth);

        if(cmds.size() != 0) {
            System.out.println("Test");
        }

        Result result = null;
        if(cmds == null) {
            result = new Result(false,"");
        } else {
            result = new Result(true, Serializer.serializeList(cmds));
        }
        return result;
    }
}
