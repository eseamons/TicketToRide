package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private Map<String, Player> playerMap;

    private ServerModel() {
        accountList = new AccountList();
        lobbies = new ArrayList<>();
        games = new ArrayList<>();
        lobby_commands = new ArrayList<>();
    }

    public void addCommand(ICommand cmd)
    {
        lobby_commands.add(cmd);
    }
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
    public List<ICommand> getNewCommands(int commandID, String auth) {
        //is the ID for the last command that the user has?
        List<ICommand> fullCommandList = null;
        List<ICommand> newCommandList = null;

        if(accountList.isAuthCodeValid(auth)) {
            GameLobby lobby = lobbies.get(lobbies.size());
            fullCommandList = lobby.getCommand_list();
            newCommandList = fullCommandList.subList(commandID-1, fullCommandList.size());

        }

        return newCommandList;
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
    public GameLobby joinGame(int gameLobbyID, String auth) {

        GameLobby returnGameLobby = null;

        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
        if(accountList.isAuthCodeValid(auth) == true) {
            returnGameLobby = lobbies.get(gameLobbyID - 1);
            Player p = new Player();
            Account acc = accountList.getAccountByAuthCode(auth);
            p.setAccount(acc);
            playerMap.put(auth, p);
            returnGameLobby.addNewPlayers(p);

        }


        return returnGameLobby;
    }

    @Override
    public boolean BeginGame(int gameLobbyID, String auth) {
        boolean authcodeValid = false;

        if(accountList.isAuthCodeValid(auth)) {
            //create game
            Game newGame = new Game();
            //delete game lobby
            lobbies.remove(gameLobbyID - 1);
            authcodeValid = true;
        }

        return authcodeValid;
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        Player p = playerMap.get(auth);
        p.setColor(color);
        return true;
    }

    @Override
    public boolean addComment(String message, String auth) {

        return false;
    }

}
