package client;

import java.util.List;

import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

public class ClientModel {

    private Account account;
    private GameLobby current_game_lobby;
    private Game current_game;
    private List<ICommand> command_list;

    public int getLastCommand() {
        return 0;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public GameLobby getCurrent_game_lobby() {
        return current_game_lobby;
    }

    public void setCurrent_game_lobby(GameLobby current_game_lobby) {
        this.current_game_lobby = current_game_lobby;
    }

    public Game getCurrent_game() {
        return current_game;
    }

    public void setCurrent_game(Game current_game) {
        this.current_game = current_game;
    }

    public List<ICommand> getCommand_list() {
        return command_list;
    }

    public void setCommand_list(List<ICommand> command_list) {
        this.command_list = command_list;
    }
}
