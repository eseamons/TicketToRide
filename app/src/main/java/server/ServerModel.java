package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shared.ColorNum;
import shared.command_classes.AddCommentCommand;
import shared.command_classes.BeginGameCommand;
import shared.command_classes.Command;
import shared.command_classes.CreateGameCommand;
import shared.command_classes.JoinGameCommand;
import shared.command_classes.SetPlayerColorCommand;
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
    private AccountList accountList;
    private List<GameLobby> lobbies;
    private List<Game> games;
    private List<ICommand> lobby_commands;
    private Map<String, Player> playerMap;
    private List<String> gameNames;

    private ServerModel() {
        accountList = new AccountList();
        lobbies = new ArrayList<>();
        games = new ArrayList<>();
        lobby_commands = new ArrayList<>();
        currentLobbyID = 1;
        gameNames = new ArrayList<>();
    }

    public static ServerModel getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new ServerModel();
        }
        return SINGLETON;
    }

    public boolean Register(String name, String pass) {
        boolean isRegisterSuccessful = false;

        if (!accountList.usernameExists(name)) {
            isRegisterSuccessful = accountList.registerAccount(name, pass);
        }

        return isRegisterSuccessful;

    }

    public Account Login(String name, String pass) {
        return accountList.login(name, pass);
    }

    public List<GameLobby> getServerGameList(String auth) {
        List<GameLobby> returnLobbyList = null;
        if(accountList.authCodeExists(auth)) {
            returnLobbyList = lobbies;
        }

        return returnLobbyList;
    }

    public void addCommand(ICommand cmd)
    {
        lobby_commands.add(cmd);
    }

    int times = 0;
    @Override
    public List<ICommand> getNewCommands(int commandID, String auth) {
        //is the ID for the last command that the user has?
        List<ICommand> fullCommandList = null;
        List<ICommand> newCommandList = new ArrayList<>();

        if(accountList.authCodeExists(auth)) {
            GameLobby lobby = lobbies.get(lobbies.size());
            fullCommandList = lobby_commands;
            newCommandList = lobby_commands.subList(commandID-1, lobby_commands.size());

        }
        System.out.println("this was called: " + times);
        times++;
        return newCommandList;
    }

    @Override
    public boolean CreateGame(String name, int max_player_num, String auth) {
        GameLobby newGameLobby = null;

        if(accountList.authCodeExists(auth) && !gameNames.contains(name)) {
            newGameLobby = new GameLobby();
            newGameLobby.setName(name);
            newGameLobby.setMax_players(max_player_num);
            newGameLobby.setID(currentLobbyID);
            gameNames.add(name);
            lobbies.add(newGameLobby);
            currentLobbyID++;
        }

        return newGameLobby != null;
    }

    @Override
    public GameLobby joinGame(int gameLobbyID, String auth) {

        GameLobby returnGameLobby = null;

        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
        if(accountList.authCodeExists(auth) == true) {
            returnGameLobby = lobbies.get(gameLobbyID - 1);
            Player p = new Player();
            Account acc = accountList.getAccountByAuthCode(auth);
            p.setAccount(acc);
            playerMap.put(auth, p);
            returnGameLobby.addNewPlayers(p);

            Command cmd = new JoinGameCommand();
            cmd.setInfo(gameLobbyID + "  " + acc.getUsername());
            cmd.setcmdID(lobby_commands.size());
            cmd.setType("joingame");
            lobby_commands.add(cmd);

        }


        return returnGameLobby;
    }

    @Override
    public boolean BeginGame(int gameLobbyID, String auth) {
        boolean authcodeValid = false;

        if(accountList.authCodeExists(auth))
        {
            //create game
            Game newGame = new Game();
            //delete game lobby
            lobbies.remove(gameLobbyID - 1);
            authcodeValid = true;

            Command cmd = new BeginGameCommand();
            cmd.setInfo("" + gameLobbyID);
            cmd.setType("begingame");
            cmd.setcmdID(lobby_commands.size());
            addCommand(cmd);

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
        boolean addCommentSuccessful = false;
            for(GameLobby lobby : lobbies) {
                if(lobby.authCodeExistsInLobby(auth)) {

                    lobby.addNewComment(message);
                    addCommentSuccessful = true;

                    int ID = lobby.getID();
                    Command cmd = new AddCommentCommand();
                    cmd.setInfo(ID + " " + message);
                    cmd.setType("addcomment");
                    cmd.setcmdID(lobby_commands.size());
                    addCommand(cmd);
                }



            }

        return addCommentSuccessful;
    }

}
