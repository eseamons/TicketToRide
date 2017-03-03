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
    @Override
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String username = jsonObject.get("username").getAsString();
        int max_player_num = Integer.parseInt(jsonObject.get("max_player_num").getAsString());
        String auth = jsonObject.get("auth").getAsString();
        boolean gameCreatedSuccessful = ServerFacade.getInstance().createGameLobby(username, max_player_num, auth);
        if(gameCreatedSuccessful == true)
            return new Result(true, "");
        else
            return new Result(false, "");
    }

    @Override
    public void executeOnClient()
    {
        ClientFacade client = new ClientFacade();
        JsonObject jsonObject = convertStringToJsonObject(info);
        String username = jsonObject.get("gameLobbyName").getAsString();
        int max_player_num = Integer.parseInt(jsonObject.get("max_player_num").getAsString());
        int gameLobbyID = Integer.parseInt(jsonObject.get("gameLobbyID").getAsString());
        GameLobby game = new GameLobby();
        game.setName(username);
        game.setMax_players(max_player_num);
        game.setID(gameLobbyID);
        client.addGameToLobbyList(game);
    }
}
