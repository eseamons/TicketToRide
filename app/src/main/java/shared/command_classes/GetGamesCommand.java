package shared.command_classes;

import java.util.List;

import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.GetGamesCommandData;
import shared.model_classes.GameLobby;

public class GetGamesCommand extends Command
{
    public Result execute()
    {
        String auth = ((GetGamesCommandData) info).getAuth();
        List<GameLobby> gameLobbies = ServerFacade.getInstance().getServerGameList(auth);

        GameLobby[] gameLobbiesArray = new GameLobby[gameLobbies.size()];

        for(int i = 0; i < gameLobbies.size(); i++) {
            gameLobbiesArray[i] = gameLobbies.get(i);
        }

        Result result;
        if(gameLobbies == null) {
            result = new Result(false,"");
        } else {
            GetGamesCommandData cmdData = new GetGamesCommandData();
            cmdData.setGameLobbies(gameLobbiesArray);
            result = new Result(true, cmdData);
        }
        return result;
    }

}
