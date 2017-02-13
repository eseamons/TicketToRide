package server;

import java.util.List;
import java.util.UUID;

import shared.ColorNum;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

import shared.interfaces.IServer;
import shared.model_classes.Player;

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
            accounts.add(newAccount);

            //create authentication code
            String uuid = UUID.randomUUID().toString();
            newAccount.setAuthentication(uuid);

            //mark register action as successful
            isRegisterSuccessful = true;
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
        //is the ID for the last command that the user has?
        List<ICommand> commandList = null;
        //how do we know which game we are looking for
        GameLobby newGameLobby = null;
        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
        for (Account account : accounts) {
            if(account.getAuthentication() == auth) {
                GameLobby lobby = lobbies.get(lobbies.size()+1);
                commandList = lobby.getCommand_list();

            }
        }
        return commandList;
    }

    @Override
    public GameLobby CreateGame(String name, int max_player_num, String auth) {
        GameLobby newGameLobby = null;
        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
        for (Account account : accounts) {
            if(account.getAuthentication() == auth) {
                newGameLobby = new GameLobby();
                newGameLobby.setName(name);
                newGameLobby.setMax_players(max_player_num);
                newGameLobby.setID(lobbies.size()+1);
                lobbies.add(newGameLobby);
            }
        }

        return newGameLobby;
    }

    @Override
    public GameLobby joinGame(int ID, String auth) {

        GameLobby returnGameLobby = null;

        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
        for (Account account : accounts) {
            if(account.getAuthentication() == auth) {
                returnGameLobby = lobbies.get(ID - 1);
            }
        }


        return returnGameLobby;
    }

    @Override
    public boolean BeginGame(int ID, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        Player player = null;
        boolean setPlayerColorSuccess = true;

        player.setColor(color);
        return setPlayerColorSuccess;


        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
//        for (Account account : accounts) {
//            if(account.getAuthentication() == auth) {
//                returnGameLobby = lobbies.get(ID - 1);
//            }
//        }

    }

    @Override
    public boolean addComment(String message, String auth) {
        // TODO Auto-generated method stub
        return false;
    }

}
