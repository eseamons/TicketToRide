package shared.command_classes;

import server.ServerFacade;
import shared.Result;
import shared.model_classes.Player;

public class AddCommentCommand extends Command
{
    @Override
    public Result execute()
    {
        Player player = new Player();
        String message = "";
        String auth = "";
        boolean success = ServerFacade.getInstance().addComment(player, message, auth);

        return new Result(success, "");
    }

}
