package client;

import java.util.List;

import server.ServerFacade;
import shared.ColorNum;
import shared.interfaces.ICommand;
import shared.interfaces.IServer;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

public class ServerProxy implements IServer{

    private static ServerProxy SINGLETON = null;
    public static ServerProxy getInstance()
    {
        if(SINGLETON == null) {
            SINGLETON = new ServerProxy();
        }

        return SINGLETON;
    }

    @Override
    public Account Login(String name, String pass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean Register(String name, String pass) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<GameLobby> getServerGameList(String auth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ICommand> getNewCommands(int ID, String auth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameLobby CreateGame(String name, int players, String auth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameLobby joinGame(int ID, String auth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean BeginGame(int ID, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addComment(String message, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

}
