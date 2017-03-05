package server;

import java.io.IOException;

import shared.Result;
import shared.Serializer;
import shared.command_classes.Command;
import shared.interfaces.ICommand;

public class ExecCommandHandler extends HandlerBase {

    @Override
    public String execute(String json){

        Result result = null;
        Command command = (Command) Serializer.deserialize(json);
        result = command.execute();
        json = Serializer.serialize(result);
        return json;
    }
}
