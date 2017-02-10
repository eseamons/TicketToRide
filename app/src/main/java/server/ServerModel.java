package server;

import java.util.List;

import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

public class ServerModel {

    private static ServerModel SINGLETON;

    private ServerModel() {}

    public static ServerModel getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new ServerModel();
        }
        return SINGLETON;
    }

    private List<Account> accounts;
    private List<GameLobby> lobbies;
    private List<Game> games;
    private List<ICommand> lobby_commands;
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    public List<GameLobby> getLobbies() {
        return lobbies;
    }
    public void setLobbies(List<GameLobby> lobbies) {
        this.lobbies = lobbies;
    }
    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }
    public List<ICommand> getLobby_commands() {
        return lobby_commands;
    }
    public void setLobby_commands(List<ICommand> lobby_commands) {
        this.lobby_commands = lobby_commands;
    }

}
