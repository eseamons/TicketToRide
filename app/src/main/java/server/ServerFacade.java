package server;

import java.util.List;

import shared.ColorNum;
import shared.command_classes.Command;
import shared.interfaces.IServer;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;

public class ServerFacade implements IServer{

    private static ServerFacade instance = null;

    public static ServerFacade getInstance()
    {
        if(instance == null) {
            instance =  new ServerFacade();
        }

        return instance;
    }
    @Override
    public Account Login(String name, String pass) {
        ServerModel serverModel = ServerModel.getInstance();
        Account returnAccount = serverModel.Login(name, pass);
        return returnAccount;
    }

    @Override
    public boolean Register(String name, String pass) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.Register(name, pass);
    }

    @Override
    public List<GameLobby> getServerGameList(String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.getServerGameList(auth);
    }

    @Override
    public List<Command> getNewCommands(int ID, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.getNewCommands(ID, auth);
    }

    @Override
    public boolean CreateGame(String name, int players, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.CreateGame(name, players, auth);
    }

    @Override
    public GameLobby joinGame(int ID, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.joinGame(ID, auth);
    }

    @Override
    public boolean BeginGame(int ID, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.BeginGame(ID, auth);
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.setPlayerColor(color, auth);
    }

    @Override
    public boolean addComment(String message, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.addComment(message, auth);
    }

}
