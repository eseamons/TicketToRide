package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import server.plugin.AccountDTO;
import server.plugin.CommandDTO;
import server.plugin.GameDTO;
import server.plugin.IAccountDao;
import server.plugin.ICommandDao;
import server.plugin.IDaoFactory;
import server.plugin.IGameDao;
import server.plugin.Plugin;
import shared.CardColor;
import shared.ColorNum;
import shared.Result;
import shared.command_classes.*;
import shared.command_data_classes.AddCommentCommandData;
import shared.command_data_classes.BeginGameCommandData;
import shared.command_data_classes.ClaimRouteCommandData;
import shared.command_data_classes.CreateGameCommandData;
import shared.command_data_classes.DrawDeckCardCommandData;
import shared.command_data_classes.DrawDestinationCardCommandData;
import shared.command_data_classes.DrawFaceUpCardCommandData;
import shared.command_data_classes.EndTurnCommandData;
import shared.command_data_classes.JoinGameCommandData;
import shared.command_data_classes.ConfirmDestinationCardCommandData;
import shared.command_data_classes.ReplaceAllFaceUpCardsCommandData;
import shared.command_data_classes.SetFaceUpCardCommandData;
import shared.interfaces.ICommand;
import shared.model_classes.*;
import shared.interfaces.IServer;
import shared.model_classes.model_list_classes.*;

public class ServerModel implements IServer{

    private static ServerModel instance;
    private AccountList accountList;
    private GameLobbyList gameLobbyList;
    private GameList gameList;
    private Map<String, Player> playerAuthMap;
    private Plugin plugin;
    private int checkpoint;
    private int deltas = 0;

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
        boolean registered = accountList.registerAccount(username, password);
        if (registered)
        {
            Account account = accountList.getAccountByUserName(username);
            addAccountToDatabase(account);
        }
        return registered;
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


//    public void addCommand(int gameID, Command cmd)
//    {
//        gameList.addGameCommand(gameID, cmd);
//    }

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

            if(returnGameLobby.getPlayers().size() < returnGameLobby.getMaxPlayers()
                    && !returnGameLobby.authCodeExistsInLobby(auth)) {


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
            //gameLobbyList.removeLobby(gameLobbyID);



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

            //Add to database (maybe? idk man)
            addCommandToDatabase(cmd, game.getGameID());
            addGameToDatabase(game);

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

            int currentCmdID = getCurrentGameCmdId(auth);

            Command cmd = new AddCommentCommand();
            cmd.setInfo(cmdData);
            cmd.setCmdID(currentCmdID);
            gameList.addGameCommand(gameID, cmd);
        }
        return addCommentSuccessful;
    }

    /*
        These are the functions used after starting the game
    */
    //added endTurn for the end turn Command (2/28)
    public List<Command> getNewGameCommands(int gameID, int commandID, String auth) {
        List<Command> newCommandList = new ArrayList<>();

        if(accountList.authCodeExists(auth))
        {
            newCommandList = gameList.getNewGameCommands(gameID, commandID);
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
            game.endTurn(auth);

            EndTurnCommandData cmdData = new EndTurnCommandData();
            cmdData.setGameID(gameID);

            int currentCmdID = getCurrentGameCmdIbWITHGAMEID(gameID);

            Command cmd = new EndTurnCommand();
            cmd.setInfo(cmdData);
            cmd.setAuth(auth);
            cmd.setCmdID(currentCmdID);
            gameList.addGameCommand(gameID, cmd);

            //Add to database
            addCommandToDatabase(cmd, gameID);


        }
        return endTurnSuccessful;
    }

    @Override
    public boolean claimRoute(int gameID, Route route, String auth, CardColor colorOfCardsUsed) {
        boolean routeClaimed = false;
        Game game = gameList.getGame(gameID);

        if (game != null)
        {
            routeClaimed = game.claimRoute(route, auth, colorOfCardsUsed);

            if(routeClaimed == true) {

                ClaimRouteCommandData cmdData = new ClaimRouteCommandData();
                cmdData.setGameID(gameID);
                cmdData.setAuth(auth);
                cmdData.setRoute(route);
                cmdData.setColorOfCardsUsed(colorOfCardsUsed);

                int currentCmdId = getCurrentGameCmdId(auth);

                Command cmd = new ClaimRouteCommand();
                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdId);
                gameList.addGameCommand(gameID, cmd);

                //Add to database
                addCommandToDatabase(cmd, gameID);

                for(int i = 0; i < 5; i ++)
                {
                    if(game.getFaceUpCards().get(i).equals(CardColor.EMPTY) )//&& game.getDiscardPileSize() != 0)
                    {
                        setFaceUpCard(game.drawCard(), i, gameID);
                    }
                }

            }
        }
        return routeClaimed;

    }

    @Override
    public Result drawDestinationCards(int gameID, String auth) {
        boolean successful = false;
        Game currentGame = gameList.getGame(gameID);
        Command cmd = null;

        if(currentGame != null)
        {
            DestinationCard cardOne = currentGame.drawDestinationCard(auth);
            DestinationCard cardTwo = currentGame.drawDestinationCard(auth);
            DestinationCard cardThree = currentGame.drawDestinationCard(auth);
            Player player = playerAuthMap.get(auth);

            if(cardOne != null && cardTwo != null && cardThree != null && player != null)
            {
                successful = true;
                player.setChoosableDestinationCard(cardOne, 0);
                player.setChoosableDestinationCard(cardOne, 1);
                player.setChoosableDestinationCard(cardOne, 2);
                int playerID = player.getPlayerID();

                int currentCmdID = getCurrentGameCmdIbWITHGAMEID(gameID);
                cmd = new DrawDestinationCardCommand();

                DrawDestinationCardCommandData cmdData = new DrawDestinationCardCommandData();
                cmdData.setAuth(auth);
                cmdData.setGameID(gameID);
                cmdData.setDestinationCard(cardOne,0);
                cmdData.setDestinationCard(cardTwo,1);
                cmdData.setDestinationCard(cardThree,2);
                cmdData.setPlayerID(playerID);

                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);
                cmd.setAuth(auth);
                gameList.addGameCommand(gameID, cmd);

                //Add to database
                addCommandToDatabase(cmd, gameID);
            }
        }

        Result result = new Result(successful, cmd);



        return result;
    }

    @Override
    public boolean removeDestinationCard(int gameID, int playerID, DestinationCard[] acceptedCards, boolean[] acceptedCardsBools, String auth) {
        Game currentGame = null;
        boolean successful = false;
       currentGame = gameList.getGame(gameID);

        if(currentGame != null)
        {
            Player player = playerAuthMap.get(auth);

            if( player != null)
            {
                successful = true;
                int currentCmdID = getCurrentGameCmdIbWITHGAMEID(gameID);
                Command cmd = new ConfirmDestinationCardCommand();

                ConfirmDestinationCardCommandData cmdData = new ConfirmDestinationCardCommandData();
                cmdData.setAuth(auth);
                cmdData.setGameID(gameID);
                cmdData.setPlayerID(playerID);
                cmdData.setConfirmedCardsBools(acceptedCardsBools);
                cmdData.setConfirmedCards(acceptedCards);

                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);
                gameList.addGameCommand(gameID, cmd);

                //Add to database
                addCommandToDatabase(cmd, gameID);
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

                int currentCmdID = getCurrentGameCmdIbWITHGAMEID(gameID);
                Command cmd = new DrawDeckCardCommand();

                DrawDeckCardCommandData cmdData = new DrawDeckCardCommandData();
                cmdData.setGameID(gameID);
                cmdData.setPlayerID(playerID);
                cmdData.setAuth(auth);
                cmdData.setCard(cardColor);

                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);
                gameList.addGameCommand(gameID, cmd);

                //Add to database
                addCommandToDatabase(cmd, gameID);

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

                int currentCmdID = getCurrentGameCmdIbWITHGAMEID(gameID);
                Command cmd = new DrawFaceUpCardCommand();

                DrawFaceUpCardCommandData cmdData = new DrawFaceUpCardCommandData();
                cmdData.setGameID(gameID);
                cmdData.setAuth(auth);
                cmdData.setCardColor(cardColor);
                cmdData.setPlayerID(playerID);

                cmd.setInfo(cmdData);
                cmd.setCmdID(currentCmdID);
                gameList.addGameCommand(gameID, cmd);

                //replaces the card that you drew.. hopefully
                setFaceUpCard(currentGame.drawCard(),faceUpCardID,gameID);

                //Add to database
                addCommandToDatabase(cmd, gameID);

            }
        }



        return successful;
    }

    public void setFaceUpCard(CardColor card, int cardIndex, int gameID)
    {
        Game currentGame = gameList.getGame(gameID);
        currentGame.setFaceUpCard(cardIndex, card);

        int currentCmdID = getCurrentGameCmdIbWITHGAMEID(gameID);
        Command cmd = new SetFaceUpCardCommand();

        SetFaceUpCardCommandData cmdData = new SetFaceUpCardCommandData();
        cmdData.setCard(card);
        cmdData.setCardIndex(cardIndex);
        cmdData.setGameID(gameID);

        cmd.setInfo(cmdData);
        cmd.setCmdID(currentCmdID);
        gameList.addGameCommand(gameID, cmd);

        //Add to database
        addCommandToDatabase(cmd, gameID);
    }


    public boolean replaceAllFaceUpCards(int gameID) {
        boolean successful = false;
        Game currentGame = gameList.getGame(gameID);
        if(currentGame != null)
        {
            successful = true;

            List<CardColor> discardedFaceUpCards = currentGame.getFaceUpCards();
            currentGame.addCardsToDiscard(discardedFaceUpCards);


            ArrayList<CardColor> newFaceUpCards = new ArrayList<>();
            for(int i = 0; i < 5; i++)
            {
                CardColor c = currentGame.drawCard();
                currentGame.setFaceUpCard(i, c);
                newFaceUpCards.add(c);
            }

            ReplaceAllFaceUpCardsCommandData cmdData = new ReplaceAllFaceUpCardsCommandData();
            cmdData.setNewFaceUpCards(newFaceUpCards);
            cmdData.setGameID(gameID);


            int currentCmdID = getCurrentGameCmdIbWITHGAMEID(gameID);
            Command cmd = new ReplaceAllFaceUpCardsCommand();
            cmd.setInfo(cmdData);
            cmd.setCmdID(currentCmdID);
            gameList.addGameCommand(gameID, cmd);

            //Add to database
            addCommandToDatabase(cmd, gameID);
        }
        return successful;
    }


    public int getCurrentGameCmdIbWITHGAMEID(int id)
    {
        Game g = gameList.getGame(id);
        int currentCmdID = g.getCurrentGameCommandID();
        g.incrementCurrentGameCommandID();
        return currentCmdID;
    }
    public int getCurrentGameCmdId(String auth)
    {
        Game g= gameList.RealgetGameByAuthCode(auth);
        int currentCmdID = g.getCurrentGameCommandID();
        g.incrementCurrentGameCommandID();
        return currentCmdID;
    }


    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;

    }

    public void setCheckpoint(int checkpoint)
    {
        this.checkpoint = checkpoint;
    }

    public void setWipe(boolean wipe)
    {
        if (wipe)
        {
            IDaoFactory f = plugin.createDaoFactory();

            IAccountDao accountDao = f.createAccountDao();
            ICommandDao commandDao = f.createCommandDao();
            IGameDao gameDao = f.createGameDao();
            accountDao.clearData();
            commandDao.clearData();
            gameDao.clearAllGames();

        }
    }

    public void addCommandToDatabase(Command c, int gameID)
    {
        CommandDTO commDTO = new CommandDTO();
        commDTO.setCommand(c);
        commDTO.setGameID(gameID);

        IDaoFactory f = plugin.createDaoFactory();

        ICommandDao commandDao = f.createCommandDao();
        commandDao.addCommand(commDTO);
        deltas++;

        if (deltas >= checkpoint)
        {
            updateGameInDatabase(gameList.getGame(gameID));
            deltas = 0;
        }

        return;
    }

    public void updateGameInDatabase(Game game)
    {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setGameID(game.getGameID());
        gameDTO.setGame(game);

        IDaoFactory f = plugin.createDaoFactory();

        IGameDao gameDao = f.createGameDao();
        gameDao.updateGame(gameDTO);
        return;
    }

    public void addAccountToDatabase(Account acc)
    {

        AccountDTO accDTO = new AccountDTO();
        accDTO.setAccount(acc);

        IDaoFactory f = plugin.createDaoFactory();

        IAccountDao accountDao = f.createAccountDao();
        accountDao.addAccount(accDTO);
        return;
    }

    public void addGameToDatabase(Game game)
    {

        GameDTO gameDTO = new GameDTO();
        gameDTO.setGameID(game.getGameID());
        gameDTO.setGame(game);

        IDaoFactory f = plugin.createDaoFactory();

        IGameDao gameDao = f.createGameDao();
        gameDao.addGame(gameDTO);
        return;
    }

}
