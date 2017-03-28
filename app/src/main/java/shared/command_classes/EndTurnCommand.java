package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.EndTurnCommandData;


/**
 * Created by rebeccaredd on 2/22/17.
 */

public class EndTurnCommand extends Command{
    public Result execute()
    {
        int gameID = ((EndTurnCommandData) info).getGameID();
        String auth = ((EndTurnCommandData)info).getAuth();
        boolean success = ServerFacade.getInstance().endTurn(gameID, auth);
        return new Result(success, auth);
    }

    public void executeOnClient()
    {
        int gameID = ((EndTurnCommandData) info).getGameID();
        ClientFacade client = new ClientFacade();
        client.aTurnEnded(gameID);
        //TODO: the asterisk needs to change when the players turn ends.
    }
}
