package shared.interfaces;

import java.util.List;

import shared.ColorNum;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

public interface IServer {

    public Account Login(String name, String pass);

    public boolean Register(String name, String pass);

    public List<GameLobby> getServerGameList(int ID, String auth);

    public List<ICommand> getNewCommands(int ID, String auth);

    public GameLobby CreateGame(String name, int players, String auth);

    public GameLobby joinGame(int ID, String auth);

    public boolean BeginGame(int ID, String auth);

    public boolean setPlayerColor(ColorNum color, String auth);

    public boolean addComment(Player player, String message, String auth);
}
