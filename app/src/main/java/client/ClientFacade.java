package client;

import java.util.ArrayList;
import java.util.List;

import client.interfaces.IClient;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

public class ClientFacade implements IClient{

    @Override
    public boolean sendMessage(String msg)
    {
        //TODO: Implement this plz


        return true;
    }

    @Override
    public ArrayList<String> getChat()
    {
        //TODO: Implement this

        ArrayList<String> chatArray = new ArrayList<String>();

        return chatArray;
    }


    @Override
    public Account Login(String name, String pass) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        Account account = serverProxy.Login(name, pass);
        return account;
    }

    @Override
    public boolean Register(String name, String pass) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        return false;
    }

    @Override
    public List<GameLobby> getServerGamesList(String auth) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        List<GameLobby> gameLobbyList = serverProxy.getServerGameList(auth);
        return gameLobbyList;
    }

    @Override
    public List<GameLobby> getClientGamesList(String auth) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        return null;
    }

    @Override
    public List<ICommand> getNewCommands(int ID, String auth) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        return null;
    }

    @Override
    public GameLobby joinGame(int gameID) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        return null;
    }

    @Override
    public GameLobby createGame(String gameName, int maxPlayers) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        return null;
    }

    @Override
    public Game beginGame(int ID, String auth) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean beginGameBool = serverProxy.BeginGame(ID, auth);
        return null;
    }
}
