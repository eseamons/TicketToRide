package shared.command_classes;

import client.ClientFacade;
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

    public void executeOnClient()
    {
        ClientFacade client = new ClientFacade();
        String parts[] = info.split(" ");
        String name = parts[0];
        int players = Integer.parseInt(parts[1]);
        String auth = parts[2];
        int ID = Integer.parseInt(parts[3]);
        GameLobby game = new GameLobby();
        game.setName(name);
        game.setMax_players(players);
        game.setID(ID);
        client.addGameToLobbyList(game);
    }
}
