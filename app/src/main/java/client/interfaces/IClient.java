package client.interfaces;

import java.util.List;

import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

public interface IClient {

    public Account Login(String name, String pass);

    public boolean Register(String name, String pass);

    public List<GameLobby> getServerGamesList(String auth);

    public List<GameLobby> getClientGamesList(String auth);

    public List<ICommand> getNewCommands(int ID, String auth);

    public GameLobby joinGame(int gameID, String auth);

    public Game strartGame(String auth);
}
