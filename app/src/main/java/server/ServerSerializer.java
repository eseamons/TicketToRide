package server;

import com.google.gson.Gson;

import shared.Result;
import shared.interfaces.ICommand;

public class ServerSerializer {
    public static String serializeResults(Result r){
        Gson gson = new Gson();
        return gson.toJson(r);
    }

    public static ICommand deserializeCommand(String str){
        Gson gson = new Gson();
        return gson.fromJson(str, ICommand.class);
    }

}
