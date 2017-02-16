package shared.command_classes;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;

public class AddCommentCommand extends Command
{
    @Override
    public Result execute()
    {
        String parts[] = info.split(" ");
        String message = parts[0];
        String auth = parts[1];
        boolean success = ServerFacade.getInstance().addComment(message, auth);

        return new Result(success, "");
    }

    public void executeOnClient()
    {
        String parts[] = info.split(" ");
        int ID = Integer.parseInt(parts[0]);
        String message = info.substring(parts[0].length());
        ClientFacade client = new ClientFacade();
        client.addComment(ID, message);
    }

}
