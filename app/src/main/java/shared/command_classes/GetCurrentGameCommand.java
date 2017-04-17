package shared.command_classes;

import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.GetCurrentGameCommandData;
import shared.command_data_classes.JoinGameCommandData;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

/**
 * Created by mepeter on 4/17/17.
 */

public class GetCurrentGameCommand extends Command
{
    public Result execute()
    {
        String auth = ((GetCurrentGameCommandData) info).getAuth();
        Game current_game = ServerFacade.getInstance().getCurrentGame(auth);
        Result result;
        if(current_game == null) {
            result = new Result(false,"");
        } else {
            result = new Result(true, current_game);
        }
        return result;
    }
}
