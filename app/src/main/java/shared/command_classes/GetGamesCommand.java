package shared.command_classes;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import server.ServerFacade;
import server.ServerSerializer;
import shared.Result;
import shared.model_classes.GameLobby;

public class GetGamesCommand extends Command
{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String auth = jsonObject.get("auth").getAsString();
        List<GameLobby> games = ServerFacade.getInstance().getServerGameList(auth);
        if(games == null)
            return new Result(false,"");
        else
            return new Result(true, ServerSerializer.serializeObject(games));
    }

}
