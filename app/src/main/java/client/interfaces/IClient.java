package client.interfaces;

import java.util.ArrayList;
import java.util.List;

import client.presenters.GameListPresenter;
import client.presenters.GameLobbyPresenter;
import shared.ColorNum;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

public interface IClient {

    Account login(String name, String pass);

    boolean register(String name, String pass);

    void getServerGamesList(String auth);

    List<GameLobby> getClientGamesList();

    void getNewCommands();

    GameLobby joinGame(int gameID);

    boolean createGameLobby(String gameName, int maxPlayers);

    Game beginGame();

    boolean sendMessage(String message);

    ArrayList<String> getChat();

    Player[] getPlayers();

    boolean changePlayerColor(ColorNum colorNum);

    void setGameListPresenter(GameListPresenter gameListPresenter );

    void setGameLobbyPresenter(GameLobbyPresenter gameLobbyPresenter );

}
