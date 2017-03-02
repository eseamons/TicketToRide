package server.testObjects;

import java.util.ArrayList;
import java.util.List;

import server.ServerModel;
import shared.command_classes.Command;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;

/**
 * Created by erics on 2/15/2017.
 */

public class ServerModelTestObject {

    private List<Account> testAccounts;

    public ServerModelTestObject() {
        testAccounts = new ArrayList<>();
    }

    public boolean register(String username, String password) {
        ServerModel model = ServerModel.getInstance();
        boolean registerSuccessful = model.register(username, password);
        return registerSuccessful;
    }

    public Account login(String username, String password) {
        ServerModel model = ServerModel.getInstance();
        Account account = model.login(username, password);
        return account;
    }

    public boolean createGame(String name, int max_player_num, String auth) {
        ServerModel model = ServerModel.getInstance();
        boolean successful = model.createGameLobby(name, max_player_num,auth);
        return successful;
    }

    public GameLobby joinGame(int gameLobbyID, String auth) {
        ServerModel model = ServerModel.getInstance();
        return model.joinGame(gameLobbyID, auth);
    }

    public List<Command> getNewCommands(int commandID, String auth) {
        ServerModel model = ServerModel.getInstance();
        List<Command> commandList = model.getNewCommands(commandID, auth);
        return commandList;
    }

    public Account getAccount(int index) {
        return testAccounts.get(index);
    }

}

