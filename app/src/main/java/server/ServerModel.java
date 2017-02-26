package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.ColorNum;
import shared.command_classes.*;
import shared.model_classes.*;
import shared.interfaces.IServer;
import shared.model_classes.model_list_classes.*;

public class ServerModel implements IServer{

    private static ServerModel instance;
    private AccountList accountList;
    private GameLobbyList gameLobbyList;
    private GameList gameList;


    private List<Command> lobbyCommands;
    private Map<String, Player> playerAuthMap;

    private ServerModel() {
        accountList = new AccountList();
        gameLobbyList = new GameLobbyList();
        lobbyCommands = new ArrayList<>();


        playerAuthMap = new HashMap<>();
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
    public boolean register(String username, String password) {
        return accountList.registerAccount(username, password);
    }

    /**
     * This function logs a user into the system
     * @param username username of user
     * @param password password of user
     * @return boolean indicating if register action was successful
     */
    public Account login(String username, String password) {
        return accountList.login(username, password);
    }

    /**
     * Creates a game lobby
     * @param gameLobbyName
     * @param max_player_num
     * @param auth
     * @return
     */
    @Override
    public boolean createGameLobby(String gameLobbyName, int max_player_num, String auth) {
        GameLobby newGameLobby = null;

        if(accountList.authCodeExists(auth) && !gameLobbyList.gameLobbyNameExists(gameLobbyName)) {

            newGameLobby = gameLobbyList.createGameLobby(gameLobbyName, max_player_num);
            int gameLobbyID = newGameLobby.getID();

            Command cmd = new CreateGameCommand();
            cmd.setInfo(gameLobbyName + " " + max_player_num + " " + gameLobbyID);
            cmd.setCmdID(lobbyCommands.size());
            lobbyCommands.add(cmd);

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
            returnLobbyList = gameLobbyList.getGameLobbyList();
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
        lobbyCommands.add(cmd);
    }

    int times = 0;

    @Override
    public List<Command> getNewCommands(int commandID, String auth) {
        //is the ID for the last command that the user has?
        times++;

        List<Command> newCommandList = new ArrayList<>();

        if(accountList.authCodeExists(auth))
        {
            newCommandList = lobbyCommands.subList(commandID+1, lobbyCommands.size());

        }
        System.out.println("SIZE: " + lobbyCommands.size() + " CMDID " + commandID + ": this was called: " + times);

        return newCommandList;
    }

    @Override
    public GameLobby joinGame(int gameLobbyID, String auth) {

        GameLobby returnGameLobby = null;

        //Checks for auth code in accounts. If valid auth code, gets Game lobby
        if(accountList.authCodeExists(auth)) {
            returnGameLobby = gameLobbyList.getGameLobbyByID(gameLobbyID);

            if(returnGameLobby.getPlayers().size() < returnGameLobby.getMaxPlayers()) {
                Player p = new Player();
                Account acc = accountList.getAccountByAuthCode(auth);
                p.setAccount(acc);
                playerAuthMap.put(auth, p);
                returnGameLobby.addNewPlayers(p);

                Command cmd = new JoinGameCommand();
                cmd.setInfo(gameLobbyID + "  " + acc.getUsername());
                cmd.setCmdID(lobbyCommands.size());
                lobbyCommands.add(cmd);
            }

        }


        return returnGameLobby;
    }

    @Override
    public boolean beginGame(int gameLobbyID, String auth) {
        boolean authcodeValid = false;

        if(accountList.authCodeExists(auth))
        {

            gameList.beginGame();

            //remove game lobby
            gameLobbyList.removeLobby(gameLobbyID);

            authcodeValid = true;

            Command cmd = new BeginGameCommand();
            cmd.setInfo("" + gameLobbyID);
            cmd.setCmdID(lobbyCommands.size());
            addCommand(cmd);

        }

        return authcodeValid;
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        Player p = playerAuthMap.get(auth);
        p.setColor(color);

        return true;
    }

    @Override
    public boolean addComment(String message, String auth) {
        boolean addCommentSuccessful = false;
        GameLobby lobby = gameLobbyList.addCommentToGameLobby(message,auth);

        if (lobby != null) {
            addCommentSuccessful = true;
            int gameLobbyID = lobby.getID();
            Command cmd = new AddCommentCommand();
            cmd.setInfo(gameLobbyID + " " + message);
            cmd.setCmdID(lobbyCommands.size());
            addCommand(cmd);
        }





        return addCommentSuccessful;
    }

}
