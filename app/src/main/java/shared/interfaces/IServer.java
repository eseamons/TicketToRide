package shared.interfaces;

import java.util.List;

import shared.ColorNum;
import shared.command_classes.Command;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

public interface IServer {

    public Account Login(String name, String pass);

    public boolean Register(String name, String pass);

    public List<GameLobby> getServerGameList(String auth);

    public List<Command> getNewCommands(int commandID, String auth);

    public boolean CreateGame(String name, int max_player_num, String auth);

    public GameLobby joinGame(int gameID, String auth);

    public boolean BeginGame(int gameID, String auth);

    public boolean setPlayerColor(ColorNum color, String auth);

    public boolean addComment(String message, String auth);
}
