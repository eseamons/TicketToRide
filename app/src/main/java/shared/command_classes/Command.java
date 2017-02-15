package shared.command_classes;

import android.speech.RecognizerIntent;

import server.ServerFacade;
import shared.Result;
import shared.interfaces.ICommand;

/**
 * Created by sirla on 2/10/2017.
 */

public class Command implements ICommand {

    String type;
    String info;
    int cmdID;
    public static int nextCmdID = 0;
    public void setInfo(String info)
    {
        this.info = info;
    }

    public void setType(String info)
    {
        this.type = info;
    }

    public void setcmdID(int i)
    {
        this.cmdID = i;
        nextCmdID++;
    }

    public int getID()
    {
        return cmdID;
    }

    public int getCmdID() { return cmdID;}

    public void executeOnClient()
    {

    }

    @Override
    public Result execute() {
        return null;
    }

    @Override
    public String getType() {
        return type;
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
        cmd.setcmdID(nextCmdID);
        cmd.setType(type);
        cmd.setInfo(info);
        //ServerFacade.getInstance().addCommand(cmd);
        return cmd;
    }
}
