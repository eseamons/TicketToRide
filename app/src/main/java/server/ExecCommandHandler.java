package server;


import shared.Result;
import shared.command_classes.Command;
import shared.interfaces.ICommand;

public class ExecCommandHandler extends HandlerBase {

    @Override
    public String execute(String s){
        ICommand command = ServerSerializer.deserializeCommand(s);
        command = command.getCommand();
        Result result = command.execute();
        return ServerSerializer.serializeResults(result);
    }
}
