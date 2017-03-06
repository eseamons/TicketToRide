package server;

import java.util.List;

import shared.ColorNum;
import shared.command_classes.Command;
import shared.interfaces.IServer;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;
import shared.model_classes.Route;

public class ServerFacade implements IServer{

    private static ServerFacade instance = null;

    public static ServerFacade getInstance()
    {
        if(instance == null) {
            instance =  new ServerFacade();
        }

        return instance;
    }

    /*
      Function used prior to starting game
    */
    @Override
    public Account login(String name, String pass) {
        ServerModel serverModel = ServerModel.getInstance();
        Account returnAccount = serverModel.login(name, pass);
        return returnAccount;
    }

    @Override
    public boolean register(String name, String pass) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.register(name, pass);
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
    public boolean createGameLobby(String name, int players, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.createGameLobby(name, players, auth);
    }

    @Override
    public GameLobby joinGame(int ID, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.joinGame(ID, auth);
    }

    @Override
    public boolean beginGame(int ID, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.beginGame(ID, auth);
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

    /*
        These are the functions used after starting the game
    */
    @Override
    public boolean endTurn(int gameID, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.endTurn(gameID,auth);
    }

    @Override
    public boolean claimRoute(int gameID, Route routeClaimed, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.claimRoute(gameID, routeClaimed, auth);

    }

    @Override
    public boolean drawDestinationCard(String destinationCardName, int playerID, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.drawDestinationCard(destinationCardName, playerID, auth);
    }

    @Override
    public boolean removeDestinationCard(String destinationCardName, String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        return serverModel.removeDestinationCard(destinationCardName, auth);
    }

    @Override
    public boolean drawDeckCard(String auth) {
        return false;
    }

    @Override
    public boolean drawFaceUpCard(ColorNum faceUpCardID, String auth) {
        return false;
    }

}
