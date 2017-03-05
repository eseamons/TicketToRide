package client.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import client.presenters.GameListPresenter;
import client.presenters.GameLobbyPresenter;
import shared.ColorNum;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

public interface IClient {

    //methods needed for the login/register view
    void setObserver(Observer o);

    Account login(String name, String pass);

    boolean register(String name, String pass);

    void getServerGamesList(String auth);


    //methods needed for the gameListView
    List<GameLobby> getClientGamesList();

    void getNewCommands();

    GameLobby joinGame(int gameID);

    boolean createGameLobby(String gameName, int maxPlayers);

    Game beginGame();

    boolean sendMessage(String message);

    ArrayList<String> getChat();

    List<Player> getPlayers();

    public void someoneJoinedGame(int gameID, Account account);

    public void addGameToLobbyList(GameLobby game);


    //methods needed for GameLobby View
    boolean changePlayerColor(ColorNum colorNum);

    public void aGameStarted(int gameID);

    public void addComment(int gameID, String message);

    boolean endTurn();

}
