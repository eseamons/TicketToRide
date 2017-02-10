package shared.command_classes;

import server.ServerFacade;
import shared.Result;
import shared.model_classes.Player;

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

}
