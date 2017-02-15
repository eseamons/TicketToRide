package client.interfaces;

import java.util.ArrayList;
import java.util.List;

import shared.ColorNum;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

public interface IClient {

    public Account Login(String name, String pass);

    public boolean Register(String name, String pass);

    public List<GameLobby> getServerGamesList(String auth);

    public List<GameLobby> getClientGamesList(String auth);

    public List<ICommand> getNewCommands();

    public GameLobby joinGame(int gameID);

    public boolean createGame(String gameName, int maxPlayers);

    public Game beginGame(int ID);

    public boolean sendMessage(String message);

    public ArrayList<String> getChat();

    public Player[] getPlayers();

    public boolean changePlayerColor(ColorNum colorNum);



}
