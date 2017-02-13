package server;

import java.util.List;

import shared.ColorNum;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.AccountList;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

import shared.interfaces.IServer;
import shared.model_classes.Player;

public class ServerModel implements IServer{

    private static ServerModel SINGLETON;

    private static int currentLobbyID;

    private ServerModel() {}

    public static ServerModel getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new ServerModel();
        }
        return SINGLETON;
    }

    private AccountList accountList;
    private List<GameLobby> lobbies;
    private List<Game> games;
    private List<ICommand> lobby_commands;

    public Account Login(String name, String pass) {

        return accountList.login(name, pass);
    }

    public boolean Register(String name, String pass) {
        boolean isRegisterSuccessful = false;

        if (accountList.doesAccountExist(name)) {
            isRegisterSuccessful = accountList.createAccount(name, pass);
        }

        return isRegisterSuccessful;

    }

    public List<GameLobby> getServerGameList(String auth) {
        List<GameLobby> returnLobbyList = null;
        if(accountList.isAuthCodeValid(auth)) {
            returnLobbyList = lobbies;
        }

        return returnLobbyList;
    }

    @Override
    public List<ICommand> getNewCommands(int ID, String auth) {
        //is the ID for the last command that the user has?
        List<ICommand> commandList = null;

        if(accountList.isAuthCodeValid(auth)) {
            GameLobby lobby = lobbies.get(lobbies.size()+1);
            commandList = lobby.getCommand_list();
        }

        return commandList;
    }

    @Override
    public GameLobby CreateGame(String name, int max_player_num, String auth) {
        GameLobby newGameLobby = null;

        if(accountList.isAuthCodeValid(auth)) {
            newGameLobby = new GameLobby();
            newGameLobby.setName(name);
            newGameLobby.setMax_players(max_player_num);
            newGameLobby.setID(lobbies.size()+1);
            lobbies.add(newGameLobby);
        }

        return newGameLobby;
    }

    @Override
    public GameLobby joinGame(int ID, String auth) {

        GameLobby returnGameLobby = null;

        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
        if(accountList.isAuthCodeValid(auth) == true) {
            returnGameLobby = lobbies.get(ID - 1);
        }


        return returnGameLobby;
    }

    @Override
    public boolean BeginGame(int ID, String auth) {
        // TODO Auto-generated method stub

        //create game
        //delete game lobby
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
