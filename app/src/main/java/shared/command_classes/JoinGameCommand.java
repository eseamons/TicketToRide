package shared.command_classes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import client.ClientFacade;
import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;
import shared.model_classes.GameLobby;

public class JoinGameCommand extends Command
{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        int gameID = Integer.parseInt(jsonObject.get("gameID").getAsString());
        String auth = jsonObject.get("auth").getAsString();
        GameLobby game = ServerFacade.getInstance().joinGame(gameID, auth);
        if(game == null)
            return new Result(false,"");
        else
            return new Result(true, ServerSerializer.serializeObject(game));
    }

    public void executeOnClient()
    {
        String[] parts = info.split(" ");
        int ID = Integer.parseInt(parts[0]);
        String name = parts[1];
        ClientFacade facade = new ClientFacade();
        facade.someoneJoinedGame(ID, name);

    }
}
