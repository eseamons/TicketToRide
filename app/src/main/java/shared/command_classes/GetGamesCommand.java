package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.model_classes.GameLobby;

public class GetGamesCommand extends Command
{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String auth = jsonObject.get("auth").getAsString();
        List<GameLobby> games = ServerFacade.getInstance().getServerGameList(auth);
        Result result = null;
        if(games == null) {
            result = new Result(false,"");
        } else {
            result = new Result(true, Serializer.serialize(games));
        }
        return result;
    }

}
