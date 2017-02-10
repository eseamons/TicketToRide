package shared.command_classes;

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
        switch(type)
        {
            case "addcomment": return new AddCommentCommand();
            case "begingame": return new BeginGameCommand();
        }
        return new Command();
    }
}
