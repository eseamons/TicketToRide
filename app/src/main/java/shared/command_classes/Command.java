package shared.command_classes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    public void setcmdID(int i)
    {
        this.cmdID = i;
        nextCmdID++;
    }

    public String getInfo() {
        return info;
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

    public JsonObject convertStringToJsonObject(String info) {
        return (new JsonParser()).parse(info).getAsJsonObject();
    }

}
