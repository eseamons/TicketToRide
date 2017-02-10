package shared.command_classes;

import client.ClientSerializer;
import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;
import shared.model_classes.GameLobby;

public class CreateGameCommand extends Command
{
    public Result execute()
    {
        String parts[] = info.split(" ");
        String name = parts[0];
        int players = Integer.parseInt(parts[1]);
        String auth = parts[2];
        GameLobby game = ServerFacade.getInstance().CreateGame(name, players, auth);
        if(game == null)
            return new Result(false, "");
        else
            return new Result(true, ServerSerializer.serializeGameLobby(game));
    }

}
