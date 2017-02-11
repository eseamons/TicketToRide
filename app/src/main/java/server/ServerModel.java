package server;

import java.util.List;
import java.util.UUID;

import shared.ColorNum;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

import shared.interfaces.IServer;

public class ServerModel implements IServer{

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

    public Account Login(String name, String pass) {
        Account returnAccount = null;

        //set returnAccount if both username and password match an account
        for (Account account : accounts) {
            if(account.getPassword() == pass && account.getUsername() == name) {
                returnAccount = account;
            }
        }

        return returnAccount;
    }

    public boolean Register(String name, String pass) {
        boolean accountExists = false;
        boolean isRegisterSuccessful = false;

        //Check if accounts with same username already exists
        for (Account account : accounts) {
            if(account.getUsername() == name) {
                accountExists = true;
            }
        }

        if (accountExists == false) {
            Account newAccount = new Account();
            newAccount.setUsername(name);
            newAccount.setPassword(pass);

            //create authentication code
            String uuid = UUID.randomUUID().toString();
            newAccount.setAuthentication(uuid);
            accounts.add(newAccount);
        }

        return isRegisterSuccessful;

    }

    public List<GameLobby> getServerGameList(String auth) {
        List<GameLobby> returnLobbyList = null;

        //Checks for auth code in accounts. If valid auth code, sets returnLobbyList
        for (Account account : accounts) {
            if(account.getAuthentication() == auth) {
                returnLobbyList = lobbies;
            }
        }

        return returnLobbyList;
    }

    @Override
    public List<ICommand> getNewCommands(int ID, String auth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameLobby CreateGame(String name, int players, String auth) {
        GameLobby newGameLobby = null;
        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
        for (Account account : accounts) {
            if(account.getAuthentication() == auth) {
                newGameLobby = new GameLobby();
                newGameLobby.setName(name);

            }
        }

        return null;
    }

    @Override
    public GameLobby joinGame(int ID, String auth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean BeginGame(int ID, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addComment(String message, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

}
