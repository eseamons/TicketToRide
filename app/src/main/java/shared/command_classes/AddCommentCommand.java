package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.command_data_classes.AddCommentCommandData;

public class AddCommentCommand extends Command
{
    @Override
    public Result execute()
    {
        String message = ((AddCommentCommandData) info).getMessage();
        String auth = ((AddCommentCommandData) info).getAuth();
        boolean success = ServerFacade.getInstance().addComment(message, auth);

        return new Result(success, "");
    }

    public void executeOnClient()
    {
        String message = ((AddCommentCommandData) info).getMessage();
        int gameID = ((AddCommentCommandData) info).getGameLobbyID();
        ClientFacade clientFacade = new ClientFacade();
        clientFacade.addComment(gameID,message);
    }

}
