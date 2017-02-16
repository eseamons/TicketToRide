package server;

import com.google.gson.Gson;

import shared.Result;
import shared.command_classes.Command;
import shared.model_classes.GameLobby;

public class ServerSerializer {
    public static String serializeResults(Result r){
        Gson gson = new Gson();
        return gson.toJson(r);
    }

    public static Command deserializeCommand(String str){
        Gson gson = new Gson();
        return gson.fromJson(str, Command.class);
    }

    public static String serializeGameLobby(GameLobby game)
    {
        Gson gson = new Gson();
        return gson.toJson(game);
    }

    public static String serializeObject(Object obj)
    {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}
