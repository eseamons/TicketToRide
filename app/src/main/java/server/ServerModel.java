package server;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.CardColor;
import shared.ColorNum;
import shared.Serializer;
import shared.command_classes.*;
import shared.command_data_classes.AddCommentCommandData;
import shared.command_data_classes.BeginGameCommandData;
import shared.command_data_classes.CreateGameCommandData;
import shared.command_data_classes.DrawDeckCardCommandData;
import shared.command_data_classes.DrawDestinationCardCommandData;
import shared.command_data_classes.DrawFaceUpCardCommandData;
import shared.command_data_classes.EndTurnCommandData;
import shared.command_data_classes.JoinGameCommandData;
import shared.command_data_classes.RemoveDestinationCardCommandData;
import shared.command_data_classes.SetFaceUpCardCommandData;
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


            CreateGameCommandData cmdData = new CreateGameCommandData();
            cmdData.setGameName(gameLobbyName);
            cmdData.setGameLobbyID(gameLobbyID);
            cmdData.setAuth(auth);
            cmdData.setMaxPlayerNum(max_player_num);

            int currentCmdID = gameLobbyList.getCurrentLobbyCommandID();
            gameLobbyList.incrementCurrentLobbyCommandID();

            Command cmd = new CreateGameCommand();
            cmd.setInfo(cmdData);
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
        gameList.addGameCommand(cmd);
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

                Account acc = accountList.getAccountByAuthCode(auth);

                int playerIndex = returnGameLobby.addNewPlayers(acc);
                Player p = returnGameLobby.getPlayers().get(playerIndex);
                playerAuthMap.put(auth, p);


                int currentCmdID = gameLobbyList.getCurrentLobbyCommandID();
                gameLobbyList.incrementCurrentLobbyCommandID();
                Command cmd = new JoinGameCommand();

                JoinGameCommandData cmdData = new JoinGameCommandData();
                cmdData.setGameLobbyID(gameLobbyID);
                cmdData.setAccount(acc);
                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);

                gameLobbyList.addLobbyCommand(cmd);
                ///////////////////////////////////////////////
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
            GameLobby gameLobby = gameLobbyList.getGameLobbyByID(gameLobbyID);
            Game game = gameList.beginGame(gameLobby);
            setFaceUpCard(game.drawCard(),0,gameLobbyID);
            setFaceUpCard(game.drawCard(),1,gameLobbyID);
            setFaceUpCard(game.drawCard(),2,gameLobbyID);
            setFaceUpCard(game.drawCard(),3,gameLobbyID);
            setFaceUpCard(game.drawCard(),4,gameLobbyID);


            //remove game lobby
            gameLobbyList.removeLobby(gameLobbyID);



            int currentCmdID = gameLobbyList.getCurrentLobbyCommandID();
            gameLobbyList.incrementCurrentLobbyCommandID();
            Command cmd = new BeginGameCommand();

            BeginGameCommandData cmdData = new BeginGameCommandData();
            cmdData.setGameLobbyID(gameLobbyID);
            cmdData.setAuth(auth);

            cmd.setInfo(cmdData);
            cmd.setCmdID(currentCmdID);
            gameLobbyList.addLobbyCommand(cmd);
            beginGameSuccessful = true;

            //TODO: should create multiple commands that create the game...
            //everything past here should be on gameListCommands...

            List<Player> playersInGame = game.getPlayers();
            for( Player p: playersInGame)
            {
                String PlayerAuth = p.getPlayerAuthCode();
                int gameID = game.getGameID();
                for( int i = 0; i< 4; i++)
                {
                    drawDeckCard(PlayerAuth, gameID);
                }

                for(int j = 0; j < 3; j++) {
                    drawDestinationCard(gameID,PlayerAuth);
                }
            }
            //TODO:set the 5 face up cards.
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
        Game game = gameList.getGameByAuthCode(auth);

        if (game != null) {
            game.addComment(message);
            addCommentSuccessful = true;
            int gameID = game.getGameID();
            AddCommentCommandData cmdData = new AddCommentCommandData();
            cmdData.setGameID(gameID);
            cmdData.setAuth(auth);
            cmdData.setMessage(message);

            int currentCmdID = gameList.getCurrentGameCommandID();
            gameList.incrementCurrentGameCommandID();

            Command cmd = new AddCommentCommand();
            cmd.setInfo(cmdData);
            cmd.setCmdID(currentCmdID);
            gameList.addGameCommand(cmd);
        }
        return addCommentSuccessful;
    }

    /*
        These are the functions used after starting the game
    */
    //added endTurn for the end turn Command (2/28)
    public List<Command> getNewGameCommands(int commandID, String auth) {
        List<Command> newCommandList = new ArrayList<>();

        if(accountList.authCodeExists(auth))
        {
            newCommandList = gameList.getNewGameCommands(commandID);
        }

        return newCommandList;
    }

    @Override
    public boolean endTurn(int gameID, String auth) {
        boolean endTurnSuccessful = false;
        Game game = gameList.getGame(gameID);

        if (game != null)
        {
            endTurnSuccessful = true;
            game.endTurn();

            EndTurnCommandData cmdData = new EndTurnCommandData();
            cmdData.setGameID(gameID);

            int currentCmdId = gameList.getCurrentGameCommandID();
            gameList.incrementCurrentGameCommandID();

            Command cmd = new EndTurnCommand();
            cmd.setInfo(cmdData);
            cmd.setAuth(auth);
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


                routeString = Serializer.serialize(route);

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
    public boolean drawDestinationCard(int gameID, String auth) {
        boolean successful = false;
        Game currentGame = gameList.getGame(gameID);

        if(currentGame != null)
        {
            DestinationCard destinationCard = currentGame.drawDestinationCard(auth);
            Player player = playerAuthMap.get(auth);

            if(destinationCard != null && player != null)
            {
                successful = true;
                player.addDestinationCard(destinationCard);
                int playerID = player.getPlayerID();

                int currentCmdID = gameList.getCurrentGameCommandID();
                gameList.incrementCurrentGameCommandID();
                Command cmd = new DrawDestinationCardCommand();

                DrawDestinationCardCommandData cmdData = new DrawDestinationCardCommandData();
                cmdData.setAuth(auth);
                cmdData.setGameID(gameID);
                cmdData.setDestinationCard(destinationCard);
                cmdData.setPlayerID(playerID);

                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);
                gameList.addGameCommand(cmd);
            }
        }
        return successful;
    }

    @Override
    public boolean removeDestinationCard(DestinationCard destinationCard,int gameID, String auth) {
//        Game currentGame = null;
//        Player player = playerAuthMap.get(auth);
//        DestinationCard destinationCard = null;
//        destinationCard = currentGame.getDestinationCardByName(destinationCardName);
//        destinationCard.setOwnership(auth);
//        player.removeDestinationCard(destinationCardName);
        boolean successful = false;
        Game currentGame = gameList.getGame(gameID);

        if(currentGame != null)
        {
            Player player = playerAuthMap.get(auth);

            if( player != null)
            {
                successful = true;
                int playerID = player.getPlayerID();
                currentGame.returnDestinationCard(destinationCard, playerID);

                //TODO:below here change to be game commands not lobby commands
                int currentCmdID = gameList.getCurrentGameCommandID();
                gameList.incrementCurrentGameCommandID();
                Command cmd = new DrawDestinationCardCommand();

                RemoveDestinationCardCommandData cmdData = new RemoveDestinationCardCommandData();
                cmdData.setAuth(auth);
                cmdData.setGameID(gameID);
                cmdData.setPlayerID(playerID);
                cmdData.setDiscardedCard(destinationCard);

                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);
                gameList.addGameCommand(cmd);
            }
        }
        return successful;

    }

    @Override
    public boolean drawDeckCard(String auth, int gameID) {
        boolean successful = false;
        Game currentGame = gameList.getGame(gameID);

        if (currentGame != null) {
            CardColor cardColor = currentGame.drawCard();
            Player player = playerAuthMap.get(auth);

            if(cardColor != null && player != null)
            {
                successful = true;
                player.addTrainCard(cardColor);
                int playerID = player.getPlayerID();

                int currentCmdID = gameList.getCurrentGameCommandID();
                gameList.incrementCurrentGameCommandID();
                Command cmd = new DrawDeckCardCommand();

                DrawDeckCardCommandData cmdData = new DrawDeckCardCommandData();
                cmdData.setGameID(gameID);
                cmdData.setPlayerID(playerID);
                cmdData.setAuth(auth);
                cmdData.setCard(cardColor);

                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);
                gameList.addGameCommand(cmd);

            }
        }



        return successful;
    }

    @Override
    public boolean drawFaceUpCard(int faceUpCardID, String auth, int gameID) {
        boolean successful = false;
        Game currentGame = gameList.getGame(gameID);

        if (currentGame != null) {
            CardColor cardColor = currentGame.getFaceUpCard(faceUpCardID);
            Player player = playerAuthMap.get(auth);

            if(cardColor != null && player != null)
            {
                successful = true;
                player.addTrainCard(cardColor);

                int playerID = player.getPlayerID();

                int currentCmdID = gameList.getCurrentGameCommandID();
                gameList.incrementCurrentGameCommandID();
                Command cmd = new DrawFaceUpCardCommand();

                DrawFaceUpCardCommandData cmdData = new DrawFaceUpCardCommandData();
                cmdData.setGameID(gameID);
                cmdData.setAuth(auth);
                cmdData.setCardColor(cardColor);
                cmdData.setPlayerID(playerID);

                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);
                gameList.addGameCommand(cmd);

                //replaces the card that you drew.. hopefully
                setFaceUpCard(currentGame.drawCard(),faceUpCardID,gameID);

            }
        }



        return successful;
    }

    public void setFaceUpCard(CardColor card, int cardIndex, int gameID)
    {
        Game currentGame = gameList.getGame(gameID);
        currentGame.setFaceUpCard(cardIndex, card);

        int currentCmdID = gameList.getCurrentGameCommandID();
        gameList.incrementCurrentGameCommandID();
        Command cmd = new SetFaceUpCardCommand();

        SetFaceUpCardCommandData cmdData = new SetFaceUpCardCommandData();
        cmdData.setCard(card);
        cmdData.setCardIndex(cardIndex);
        cmdData.setGameID(gameID);

        cmd.setInfo(cmdData);
        cmd.setCmdID(currentCmdID);
        gameList.addGameCommand(cmd);

        //TODO: if 3 wilds are face up then all face up cards need to be discarded and replaced
    }


}
