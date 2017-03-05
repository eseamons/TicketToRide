package server;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.ColorNum;
import shared.Serializer;
import shared.command_classes.*;
import shared.model_classes.*;
import shared.interfaces.IServer;
import shared.model_classes.model_list_classes.*;

public class ServerModel implements IServer{

    private static ServerModel instance;
    private AccountList accountList;
    private GameLobbyList gameLobbyList;
    private GameList gameList;



    private Map<String, Player> playerAuthMap;

    private ServerModel() {
        accountList = new AccountList();
        gameLobbyList = new GameLobbyList();
        gameList = new GameList();
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
            String json = "{\"gameLobbyName\":\""+gameLobbyName+"\", \"max_player_num\":\""+max_player_num+"\", \"gameLobbyID\":\""+gameLobbyID+"\"}";

            int currentCmdID = gameLobbyList.getCurrentLobbyCommandID();
            gameLobbyList.incrementCurrentLobbyCommandID();

            Command cmd = new CreateGameCommand();
            cmd.setInfo(json);
            cmd.setCmdID(currentCmdID);
            gameLobbyList.addLobbyCommand(cmd);

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
        gameLobbyList.addLobbyCommand(cmd);
    }

    @Override
    public List<Command> getNewCommands(int commandID, String auth) {

        List<Command> newCommandList = new ArrayList<>();

        if(accountList.authCodeExists(auth))
        {
            newCommandList = gameLobbyList.getNewLobbyCommands(commandID);

        }

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

                int currentCmdID = gameLobbyList.getCurrentLobbyCommandID();
                gameLobbyList.incrementCurrentLobbyCommandID();
                Command cmd = new JoinGameCommand();

                String accountString = "";
                try {
                    accountString = Serializer.serialize(acc);
                } catch (IOException e) {e.printStackTrace();}

                String info = "{\"gameLobbyID\": \""+gameLobbyID+"\", \"acc\":\""+accountString+"\"}";
                cmd.setInfo(info);
                cmd.setCmdID(currentCmdID);
                gameLobbyList.addLobbyCommand(cmd);
            }

        }


        return returnGameLobby;
    }

    @Override
    public boolean beginGame(int gameLobbyID, String auth) {
        boolean beginGameSuccessful = false;

        if(accountList.authCodeExists(auth))
        {

            //Begin game
            gameList.beginGame(gameLobbyList.getGameLobbyByID(gameLobbyID));

            //remove game lobby
            gameLobbyList.removeLobby(gameLobbyID);



            int currentCmdID = gameLobbyList.getCurrentLobbyCommandID();
            gameLobbyList.incrementCurrentLobbyCommandID();
            Command cmd = new BeginGameCommand();
            cmd.setInfo("" + gameLobbyID);
            cmd.setCmdID(currentCmdID);
            gameLobbyList.addLobbyCommand(cmd);
            beginGameSuccessful = true;
        }

        return beginGameSuccessful;
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
            String json = "{\"message\": \""+message+"\", \"gameLobbyID\":\""+gameLobbyID+"\"}";

            int currentCmdID = gameLobbyList.getCurrentLobbyCommandID();
            gameLobbyList.incrementCurrentLobbyCommandID();

            Command cmd = new AddCommentCommand();
            cmd.setInfo(json);
            cmd.setCmdID(currentCmdID);
            gameLobbyList.addLobbyCommand(cmd);
        }
        return addCommentSuccessful;
    }

    /*
        These are the functions used after starting the game
    */
    //added endTurn for the end turn Command (2/28)
    @Override
    public boolean endTurn(int gameID, String auth) {
        boolean endTurnSuccessful = false;
        Game game = gameList.getGame(gameID);

        if (game != null)
        {
            endTurnSuccessful = true;
            game.endTurn();

            String json = "{ \"gameID\":\""+gameID+"\"}";

            int currentCmdId = gameList.getCurrentGameCommandID();
            gameList.incrementCurrentGameCommandID();

            Command cmd = new EndTurnCommand();
            cmd.setInfo(json);
            cmd.setCmdID(currentCmdId);
            gameList.addGameCommand(cmd);
        }
        return endTurnSuccessful;
    }

    @Override
    public boolean claimRoute(int gameID, Route route, String auth) {
        boolean routeClaimed = false;
        Game game = gameList.getGame(gameID);

        if (game != null)
        {
            routeClaimed = game.claimRoute(route, auth);

            if(routeClaimed == true) {

                String routeString = null;

                try {
                    routeString = Serializer.serialize(route);
                } catch (IOException e) {e.printStackTrace();}

                String json = "{ \"gameID\":\"" + gameID + "\", \"route\":\"" + routeString + "\", \"auth\":\"" +auth+ "\"}";

                int currentCmdId = gameList.getCurrentGameCommandID();
                gameList.incrementCurrentGameCommandID();

                Command cmd = new ClaimRouteCommand();
                cmd.setInfo(json);
                cmd.setCmdID(currentCmdId);
                gameList.addGameCommand(cmd);
            }
        }
        return routeClaimed;

    }

    @Override
    public boolean drawDestinationCard(String destinationCardName, String auth) {
        return false;
    }

    @Override
    public boolean removeDestinationCard(String destinationCardName, String auth) {
        return false;
    }

    @Override
    public boolean drawDeckCard(String auth) {
        return false;
    }

    @Override
    public boolean drawFaceUpCard(int faceUpCardID, String auth) {
        return false;
    }

}
