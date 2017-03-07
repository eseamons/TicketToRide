package shared.command_classes;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.command_data_classes.JoinGameCommandData;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;

public class JoinGameCommand extends Command
{
    public Result execute()
    {
        int gameLobbyID = ((JoinGameCommandData) info).getGameLobbyID();
        String auth = ((JoinGameCommandData) info).getAuth();
        GameLobby gameLobby = ServerFacade.getInstance().joinGame(gameLobbyID, auth);
        Result result = null;
        if(gameLobby == null) {
            result = new Result(false,"");
        } else {
            result = new Result(true, gameLobby);
        }
        return result;
    }

    public void executeOnClient()
    {
        int gameLobbyID = ((JoinGameCommandData) info).getGameLobbyID();
        Account account = ((JoinGameCommandData) info).getAccount();
        ClientFacade clientFacade = new ClientFacade();
        clientFacade.someoneJoinedGame(gameLobbyID, account);
    }
}
