package server;

import java.util.List;

import shared.CardColor;
import shared.ColorNum;
import shared.Result;
import shared.command_classes.Command;
import shared.interfaces.IServer;
import shared.model_classes.Account;
import shared.model_classes.DestinationCard;
import shared.model_classes.GameLobby;
import shared.model_classes.Route;

public class ServerFacade implements IServer{

    private static ServerFacade instance = null;
    private static ServerModel serverModel = ServerModel.getInstance();

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
        Account returnAccount = serverModel.login(name, pass);
        return returnAccount;
    }

    @Override
    public boolean register(String name, String pass) {
        return serverModel.register(name, pass);
    }

    @Override
    public List<GameLobby> getServerGameList(String auth) {
        return serverModel.getServerGameList(auth);
    }

    @Override
    public List<Command> getNewCommands(int commandID, String auth) {
        return serverModel.getNewCommands(commandID, auth);
    }

    @Override
    public boolean createGameLobby(String name, int players, String auth) {
        return serverModel.createGameLobby(name, players, auth);
    }

    @Override
    public GameLobby joinGame(int ID, String auth) {
        return serverModel.joinGame(ID, auth);
    }

    @Override
    public boolean beginGame(int gameLobbyID, String auth) {
        return serverModel.beginGame(gameLobbyID, auth);
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        return serverModel.setPlayerColor(color, auth);
    }

    @Override
    public boolean addComment(String message, String auth) {
        return serverModel.addComment(message, auth);
    }

    /*
        These are the functions used after starting the game
    */
    public List<Command> getNewGameCommands(int commandID, String auth) {
        return serverModel.getNewGameCommands(commandID, auth);

    }

    @Override
    public boolean endTurn(int gameID, String auth) {
        return serverModel.endTurn(gameID,auth);
    }

    @Override
    public boolean claimRoute(int gameID, Route routeClaimed, String auth, CardColor colorOfCardsUsed) {
        return serverModel.claimRoute(gameID, routeClaimed, auth, colorOfCardsUsed);

    }

//    @Override
//    public boolean drawDestinationCard(String destinationCardName, int playerID, String auth) {
//        return serverModel.drawDestinationCard(destinationCardName, playerID, auth);
//    }
    @Override
    public Result drawDestinationCards(int gameID, String auth) {
    return serverModel.drawDestinationCards(gameID, auth);
}

    @Override
    public boolean removeDestinationCard(int gameID, int playerID, DestinationCard[] acceptedCards, boolean[] acceptedCardsBools, String auth) {
        return serverModel.removeDestinationCard(gameID, playerID, acceptedCards, acceptedCardsBools, auth);
    }

    @Override
    public boolean drawDeckCard(String auth, int gameID) {
        return serverModel.drawDeckCard(auth, gameID);
    }

    @Override
    public boolean drawFaceUpCard(int faceUpCardID, String auth, int gameID) {
        return serverModel.drawFaceUpCard(faceUpCardID, auth, gameID);
    }


    public boolean replaceAllFaceUpCards(int gameID) {
        return serverModel.replaceAllFaceUpCards(gameID);
    }

}
