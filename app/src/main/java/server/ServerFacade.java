package server;

import java.util.List;
import java.util.UUID;

import shared.ColorNum;
import shared.interfaces.ICommand;
import shared.interfaces.IServer;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

public class ServerFacade implements IServer{

    public static ServerFacade SINGLETON = null;

    public static ServerFacade getInstance()
    {
        if(SINGLETON == null) {
            SINGLETON =  new ServerFacade();
        }

        return SINGLETON;
    }
    @Override
    public Account Login(String name, String pass) {
        ServerModel serverModel = ServerModel.getInstance();
        List<Account> accountList = serverModel.getAccounts();
        Account returnAccount = null;

        //set returnAccount if both username and password match an account
        for (Account account : accountList) {
            if(account.getPassword() == pass && account.getUsername() == name) {
                returnAccount = account;
            }
        }

        return returnAccount;
    }

    @Override
    public boolean Register(String name, String pass) {
        ServerModel serverModel = ServerModel.getInstance();
        List<Account> accountList = serverModel.getAccounts();
        boolean accountExists = false;
        boolean isRegisterSuccessful = false;

        //Check if accounts with same username already exists
        for (Account account : accountList) {
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
        }

        return isRegisterSuccessful;
    }

    @Override
    public List<GameLobby> getServerGameList(String auth) {
        ServerModel serverModel = ServerModel.getInstance();
        List<Account> accountList = serverModel.getAccounts();
        List<GameLobby> returnLobbyList = null;

        //Checks for auth code in accounts. If valid auth code, sets returnLobbyList
        for (Account account : accountList) {
            if(account.getAuthentication() == auth) {
                returnLobbyList = serverModel.getLobbies();
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
        // TODO Auto-generated method stub
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
    public boolean addComment(Player player, String message, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

}
