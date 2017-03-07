package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.command_data_classes.GetGamesCommandData;
import shared.model_classes.GameLobby;

public class GetGamesCommand extends Command
{
    public Result execute()
    {
        String auth = ((GetGamesCommandData) info).getAuth();
        List<GameLobby> gameLobbies = ServerFacade.getInstance().getServerGameList(auth);
        Result result = null;
        if(gameLobbies == null) {
            result = new Result(false,"");
        } else {
            result = new Result(true, gameLobbies.toArray());
        }
        return result;
    }

}
