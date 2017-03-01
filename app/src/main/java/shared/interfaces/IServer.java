package shared.interfaces;

import java.util.List;

import shared.ColorNum;
import shared.command_classes.Command;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;
import shared.model_classes.Route;

public interface IServer {

    /*
      Function used prior to starting game
    */
    Account login(String name, String pass);

    boolean register(String name, String pass);

    List<GameLobby> getServerGameList(String auth);

    List<Command> getNewCommands(int commandID, String auth);

    boolean createGameLobby(String name, int max_player_num, String auth);

    GameLobby joinGame(int gameID, String auth);

    boolean beginGame(int gameID, String auth);

    boolean setPlayerColor(ColorNum color, String auth);

    boolean addComment(String message, String auth);


    /*
        These are the functions used after starting the game
    */
    //added endTurn for the end turn Command (2/28)
    boolean endTurn(int gameID, String auth);

    boolean claimRoute(Route routeClaimed, int pointValue, String auth);

    boolean drawDestinationCard(String destinationCardName, String auth);

    boolean removeDestinationCard(String destinationCardName, String auth);

    boolean drawDeckCard(String auth);

    boolean drawFaceUpCard(int faceUpCardID, String auth);

}
