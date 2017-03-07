package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.command_data_classes.GetNewCommandsCommandData;
import shared.model_classes.GameLobby;

/**
 * Created by Michaels on 2/12/2017.
 */

public class GetNewCommandsCommand extends Command
{
    public Result execute()
    {
        int gameID = ((GetNewCommandsCommandData) info).getGameLobbyID();
        String auth = ((GetNewCommandsCommandData) info).getAuth();
        List<Command> cmds = ServerFacade.getInstance().getNewCommands(gameID, auth);

        if(cmds.size() != 0) {
            System.out.println("Test");
        }

        Result result;
        if(cmds == null) {
            result = new Result(false,"");
        } else {
            result = new Result(true, cmds.toArray());
        }
        return result;
    }
}
