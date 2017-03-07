package shared.command_classes;

import shared.Result;
import shared.interfaces.ICommand;

/**
 * Created by sirla on 2/10/2017.
 */

public class Command implements ICommand {

    protected Object info;
    protected int cmdID;

    //setters
    public void setInfo(Object info) { this.info = info; }
    public void setCmdID(int cmdID) { this.cmdID = cmdID; }
    //getters
    public Object getInfo() {
        return info;
    }
    public int getCmdID() { return cmdID;}

    public void executeOnClient() {}

    @Override
    public Result execute() {
        return null;
    }

}
