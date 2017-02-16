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

    public Account Login(String name, String pass);

    public boolean Register(String name, String pass);

    public void getServerGamesList(String auth);

    public List<GameLobby> getClientGamesList();

    public void getNewCommands();

    public GameLobby joinGame(int gameID);

    public boolean createGame(String gameName, int maxPlayers);

    public Game beginGame(int ID);

    public boolean sendMessage(String message);

    public ArrayList<String> getChat();

    public Player[] getPlayers();

    public boolean changePlayerColor(ColorNum colorNum);

    public void setGameListPrestenter(GameListPresenter gameListPresenter );

    public void setGameLobbyPrestenter(GameLobbyPresenter gameLobbyPresenter );

}
