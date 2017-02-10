package shared.command_classes;

import android.speech.RecognizerIntent;

import shared.Result;
import shared.interfaces.ICommand;

/**
 * Created by sirla on 2/10/2017.
 */

public class Command implements ICommand {

    String type;
    String info;

    public void setInfo(String info)
    {
        this.info = info;
    }

    public void setType(String info)
    {
        this.type = type;
    }

    @Override
    public Result execute() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public ICommand getCommand()
    {
        Command cmd;
        switch(type)
        {
            case "addcomment": cmd = new AddCommentCommand(); break;
            case "begingame": cmd = new BeginGameCommand(); break;
            case "creategame": cmd = new CreateGameCommand(); break;
            case "getgames": cmd = new GetGamesCommand(); break;
            case "joingame": cmd = new JoinGameCommand(); break;
            case "login": cmd = new LoginCommand(); break;
            case "register": cmd = new RegisterCommand(); break;
            case "setplayercolor": cmd = new SetPlayerColorCommand(); break;
            default: cmd = null;

        }
        cmd.setType(type);
        cmd.setInfo(info);
        return cmd;
    }
}
