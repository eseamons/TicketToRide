package shared.command_classes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.model_classes.GameLobby;

public class CreateGameCommand extends Command
{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String username = jsonObject.get("username").getAsString();
        int max_player_num = Integer.parseInt(jsonObject.get("max_player_num").getAsString());
        String auth = jsonObject.get("auth").getAsString();
        boolean gameCreatedSuccessful = ServerFacade.getInstance().CreateGame(username, max_player_num, auth);
        if(gameCreatedSuccessful == true)
            return new Result(true, "");
        else
            return new Result(false, "");
    }

    public void executeOnClient()
    {
        ClientFacade client = new ClientFacade();
        String parts[] = info.split(" ");
        String name = parts[0];
        int players = Integer.parseInt(parts[1]);
        int ID = Integer.parseInt(parts[2]);
        GameLobby game = new GameLobby();
        game.setName(name);
        game.setMax_players(players);
        game.setID(ID);
        client.addGameToLobbyList(game);
    }
}
