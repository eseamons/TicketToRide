package shared.command_classes;

import java.util.List;

import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.GetNewCommandsCommandData;
import shared.command_data_classes.GetNewGameCommandsCommandData;

/**
 * Created by rebeccaredd on 3/8/17.
 */

public class GetNewGameCommandsCommand extends Command {
    public Result execute()
    {
        int commandID = ((GetNewGameCommandsCommandData) info).getCommandID();
        String auth = ((GetNewGameCommandsCommandData) info).getAuth();
        int gameID = ((GetNewGameCommandsCommandData) info).getGameID();
        List<Command> cmds = ServerFacade.getInstance().getNewGameCommands(gameID, commandID, auth);

        GetNewGameCommandsCommandData cmdData = new GetNewGameCommandsCommandData();
        Command[] cmdArray = new Command[cmds.size()];

        for(int i = 0; i < cmds.size(); i++) {
            cmdArray[i] = cmds.get(i);
        }

        cmdData.setCmds(cmdArray);


        Result result;
        if(cmds == null) {
            result = new Result(false,"");
        } else {
            result = new Result(true,cmdData);
        }
        return result;
    }
}
