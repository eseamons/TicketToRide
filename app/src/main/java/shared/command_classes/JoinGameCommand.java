package shared.command_classes;

import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;
import shared.model_classes.GameLobby;

public class JoinGameCommand extends Command
{
    public Result execute()
    {
        int ID = 0;
        String auth = "";
        GameLobby game = ServerFacade.getInstance().joinGame(ID, auth);
        if(game == null)
            return new Result(false,"");
        else
            return new Result(true, ServerSerializer.serializeObject(game));
    }
}
