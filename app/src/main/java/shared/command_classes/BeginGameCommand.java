package shared.command_classes;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;

public class BeginGameCommand extends Command
{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        int gameID = Integer.parseInt(jsonObject.get("gameID").getAsString());
        String auth = jsonObject.get("auth").getAsString();
        boolean success = ServerFacade.getInstance().beginGame(gameID, auth);
        return new Result(success, "");
    }

    public void executeOnClient()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        int gameID = Integer.parseInt(jsonObject.get("gameID").getAsString());
        ClientFacade client = new ClientFacade();
        client.aGameStarted(gameID);
        //TODO:have this assign the players their cards

    }

}
