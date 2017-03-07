package shared.command_classes;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.CreateGameCommandData;
import shared.model_classes.GameLobby;

public class CreateGameCommand extends Command
{
    @Override
    public Result execute()
    {
        String gameName = ((CreateGameCommandData) info).getGameName();
        int max_player_num = ((CreateGameCommandData) info).getMaxPlayerNum();
        String auth = ((CreateGameCommandData) info).getAuth();
        boolean gameCreatedSuccessful = ServerFacade.getInstance().createGameLobby(gameName, max_player_num, auth);
        if(gameCreatedSuccessful == true)
            return new Result(true, "");
        else
            return new Result(false, "");
    }

    @Override
    public void executeOnClient()
    {
        ClientFacade client = new ClientFacade();

        //get data from Command Data object
        String gameName = ((CreateGameCommandData) info).getGameName();
        int max_player_num = ((CreateGameCommandData) info).getMaxPlayerNum();
        String auth = ((CreateGameCommandData) info).getAuth();
        int gameLobbyID = ((CreateGameCommandData) info).getGameLobbyID();

        //creating game lobby and adding to game lobby list in client
        GameLobby gameLobby = new GameLobby();
        gameLobby.setName(gameName);
        gameLobby.setMax_players(max_player_num);
        gameLobby.setID(gameLobbyID);
        client.addGameToLobbyList(gameLobby);
    }
}
