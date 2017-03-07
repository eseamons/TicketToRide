package shared.command_classes;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.BeginGameCommandData;

public class BeginGameCommand extends Command
{
    public Result execute()
    {
        int gameLobbyID = ((BeginGameCommandData) info).getGameLobbyID();
        String auth = ((BeginGameCommandData) info).getAuth();
        boolean success = ServerFacade.getInstance().beginGame(gameLobbyID, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        int gameLobbyID = ((BeginGameCommandData) info).getGameLobbyID();
        ClientFacade client = new ClientFacade();
        client.aGameStarted(gameLobbyID);
        //TODO:have this assign the players their cards

    }

}
