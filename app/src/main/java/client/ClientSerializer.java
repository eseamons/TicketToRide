package client;

import shared.Result;
import com.google.gson.Gson;

public class ClientSerializer {
    public static String serializeObject(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static Result deserializeResults(String str){
        Gson gson = new Gson();
        return gson.fromJson(str, Result.class);
    }
}
