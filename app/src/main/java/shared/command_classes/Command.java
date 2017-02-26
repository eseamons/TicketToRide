package shared.command_classes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import shared.Result;
import shared.interfaces.ICommand;

/**
 * Created by sirla on 2/10/2017.
 */

public class Command implements ICommand {

    private String info;
    private int cmdID;

    //setters
    public void setInfo(String info) { this.info = info; }
    public void setCmdID(int cmdID) { this.cmdID = cmdID; }
    //getters
    public String getInfo() {
        return info;
    }
    public int getCmdID() { return cmdID;}

    public void executeOnClient() {}

    @Override
    public Result execute() {
        return null;
    }

    public JsonObject convertStringToJsonObject(String info) {
        return (new JsonParser()).parse(info).getAsJsonObject();
    }

}
