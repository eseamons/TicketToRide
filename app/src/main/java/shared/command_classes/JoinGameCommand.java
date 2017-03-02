package shared.command_classes;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import client.ClientFacade;
import server.ServerFacade;
import shared.Result;
import shared.Serializer;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;

public class JoinGameCommand extends Command
{
    public Result execute()
    {
        JsonObject jsonObject = convertStringToJsonObject(info);
        int gameID = Integer.parseInt(jsonObject.get("gameID").getAsString());
        String auth = jsonObject.get("auth").getAsString();
        GameLobby game = ServerFacade.getInstance().joinGame(gameID, auth);
        Result result = null;
        if(game == null) {
            result = new Result(false,"");
        } else {
            try {
                result = new Result(true, Serializer.serialize(game));
            } catch(IOException e) {
                result = new Result(false,"");
            }
        }
        return result;
    }

    public void executeOnClient()
    {
        //THIS SHOULD WORK?
//        String[] parts = info.split(" ");
//        int ID = Integer.parseInt(parts[0]);
//        String name = parts[1];
//        ClientFacade facade = new ClientFacade();
//        facade.someoneJoinedGame(ID, name);

        try {
            JsonObject jsonObject = convertStringToJsonObject(info);
            int gameLobbyID = Integer.parseInt(jsonObject.get("gameLobbyID").getAsString());
            String acc = jsonObject.get("acc").getAsString();
            Account account = (Account)Serializer.deserialize(acc);
            ClientFacade clientFacade = new ClientFacade();
            clientFacade.someoneJoinedGame(gameLobbyID, account);



        } catch (IOException e)
        {e.printStackTrace();}


    }
}
