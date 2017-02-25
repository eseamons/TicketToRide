package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.ColorNum;
import shared.command_classes.AddCommentCommand;
import shared.command_classes.BeginGameCommand;
import shared.command_classes.Command;
import shared.command_classes.CreateGameCommand;
import shared.command_classes.JoinGameCommand;
import shared.model_classes.Account;
import shared.model_classes.AccountList;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

import shared.interfaces.IServer;
import shared.model_classes.Player;

public class ServerModel implements IServer{

    private static ServerModel instance;
    private static int currentLobbyID;
    private AccountList accountList;
    private List<GameLobby> lobbies;
    private List<Game> games;
    private List<Command> lobby_commands;
    private Map<String, Player> playerMap;
    private List<String> gameNames;

    private ServerModel() {
        accountList = new AccountList();
        lobbies = new ArrayList<>();
        games = new ArrayList<>();
        lobby_commands = new ArrayList<>();
        currentLobbyID = 1;
        gameNames = new ArrayList<>();
        playerMap = new HashMap<>();
    }

    /**
     * Creates and returns a single instance of the ServerModel.
     * @return instance of ServerModel
     */
    public static ServerModel getInstance() {
        if (instance == null) {
            instance = new ServerModel();
        }
        return instance;
    }

    /**
     * This function registers a user in the system
     * @param username username of user
     * @param password password of user
     * @return boolean indicating if register action was successful
     */
    public boolean Register(String username, String password) {
        return accountList.registerAccount(username, password);
    }

    /**
     * This function logs a user into the system
     * @param username username of user
     * @param password password of user
     * @return boolean indicating if register action was successful
     */
    public Account Login(String username, String password) {
        return accountList.login(username, password);
    }

    /**
     * Creates a game lobby
     * @param name
     * @param max_player_num
     * @param auth
     * @return
     */
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

            Command cmd = new CreateGameCommand();
            cmd.setInfo(name + " " + max_player_num + " " + currentLobbyID);
            cmd.setcmdID(lobby_commands.size());
            lobby_commands.add(cmd);

            currentLobbyID++;
        }

        return newGameLobby != null;
    }

    /**
     * Returns list of all game lobbies
     * @param auth
     * @return
     */
    public List<GameLobby> getServerGameList(String auth) {
        List<GameLobby> returnLobbyList = null;
        if(accountList.authCodeExists(auth)) {
            returnLobbyList = lobbies;
        }
        return returnLobbyList;
    }

    /**
     * Adds new command to game lobby commands list
     * @param cmd
     * @return
     */
    public void addCommand(Command cmd)
    {
        lobby_commands.add(cmd);
    }

    int times = 0;

    @Override
    public List<Command> getNewCommands(int commandID, String auth) {
        //is the ID for the last command that the user has?
        times++;

        List<Command> newCommandList = new ArrayList<>();

        if(accountList.authCodeExists(auth))
        {
            newCommandList = lobby_commands.subList(commandID+1, lobby_commands.size());

        }
        System.out.println("SIZE: " + lobby_commands.size() + " CMDID " + commandID + ": this was called: " + times);

        return newCommandList;
    }

    @Override
    public GameLobby joinGame(int gameLobbyID, String auth) {

        GameLobby returnGameLobby = null;

        //Checks for auth code in accounts. If valid auth code, creates new Game lobby
        if(accountList.authCodeExists(auth)) {
            returnGameLobby = lobbies.get(gameLobbyID - 1);

            if(returnGameLobby.getPlayers().size() < returnGameLobby.getMax_players()) {
                Player p = new Player();
                Account acc = accountList.getAccountByAuthCode(auth);
                p.setAccount(acc);
                playerMap.put(auth, p);
                returnGameLobby.addNewPlayers(p);

                Command cmd = new JoinGameCommand();
                cmd.setInfo(gameLobbyID + "  " + acc.getUsername());
                cmd.setcmdID(lobby_commands.size());
                lobby_commands.add(cmd);
            }

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
                    cmd.setcmdID(lobby_commands.size());
                    addCommand(cmd);
                }



            }

        return addCommentSuccessful;
    }

}
