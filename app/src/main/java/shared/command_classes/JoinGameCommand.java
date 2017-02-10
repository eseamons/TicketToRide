package shared.command_classes;

import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;
import shared.model_classes.GameLobby;

public class JoinGameCommand extends Command
{
    public Result execute()
    {
        String[] parts = info.split(" ");
        int ID = Integer.parseInt(parts[0]);
        String auth = parts[1];
        GameLobby game = ServerFacade.getInstance().joinGame(ID, auth);
        if(game == null)
            return new Result(false,"");
        else
            return new Result(true, ServerSerializer.serializeObject(game));
    }
}
