package client.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import client.presenters.GameListPresenter;
import client.presenters.GameLobbyPresenter;
import shared.CardColor;
import shared.ColorNum;
import shared.Result;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;
import shared.model_classes.Route;

public interface IClient {

/*
    methods needed for the login/register view
*/
    void setObserver(Observer o);

    Account login(String name, String pass);

    boolean register(String name, String pass);

/*
    methods needed for gameListView
*/
    void getServerGamesList(String auth);

    List<GameLobby> getClientGamesList();

    int getNewCommands();

    boolean createGameLobby(String gameName, int maxPlayers);

    public void addGameToLobbyList(GameLobby game);

    GameLobby joinGame(int gameID);

    public void someoneJoinedGame(int gameID, Account account);



/*
    methods needed for GameLobby View
*/
    List<Player> getPlayers();

    boolean changePlayerColor(ColorNum colorNum);

    ArrayList<String> getChat();

    ArrayList<String> getLobbyChat();

    boolean sendMessage(String message);

    public void addComment(int gameID, String message);

    Result beginGame();

    public void aGameStarted(int gameID);


/*
    methods needed for game play
    client methods are followed by thier counterpart needed for receiving
*/
    public Player getThisPlayer();

    boolean endTurn();

    public void aTurnEnded(int gameID);

    public boolean ClaimRoute(Route route);

    void RouteClaimedbyPlayer(int gameID, Route route, String auth, CardColor colorOfCardUsed);

    List<Player> getGamePlayers();

}
