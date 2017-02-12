package client;

import shared.Result;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class ClientSerializer {
    public static String serializeObject(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static Result deserializeResults(String str){
        Gson gson = new Gson();
        return gson.fromJson(str, Result.class);
    }

    public static Account deserializeAccount(String str)
    {
        Gson gson = new Gson();
        return gson.fromJson(str, Account.class);
    }

    public static List<GameLobby> deserializeGameLobbyList(String str)
    {
        Gson gson = new Gson();
        GameLobby[] games = gson.fromJson(str, GameLobby[].class);
        return Arrays.asList(games);
    }

    public static GameLobby deserializeGameLobby(String str)
    {
        Gson gson = new Gson();
        return gson.fromJson(str, GameLobby.class);
    }

    public static List<ICommand> deserializeCommandList(String str)
    {
        Gson gson = new Gson();
        ICommand[] cmds = gson.fromJson(str, ICommand[].class);
        return Arrays.asList(cmds);
    }
}
