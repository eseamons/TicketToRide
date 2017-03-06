package shared.command_classes;

import com.google.gson.JsonObject;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;

public class AddCommentCommand extends Command
{
    @Override
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String message = jsonObject.get("message").getAsString();
        String auth = jsonObject.get("auth").getAsString();
        boolean success = ServerFacade.getInstance().addComment(message, auth);

        return new Result(success, "");
    }

    public void executeOnClient()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        String message = jsonObject.get("message").getAsString();
        int gameID = jsonObject.get("gameID").getAsInt();
        ClientFacade clientFacade = new ClientFacade();
        clientFacade.addComment(gameID,message);
    }

}
