package client;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import client.StateClasses.ClientState;
import client.StateClasses.NotMyTurnState;
import client.interfaces.IClient;
import shared.CardColor;
import shared.ColorNum;
import shared.Result;
import shared.command_classes.Command;
import shared.model_classes.Account;
import shared.model_classes.DestinationCard;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;
import shared.model_classes.Route;
import shared.model_classes.model_list_classes.RoutesList;

public class ClientFacade implements IClient{

    private static ClientModel clientModel = ClientModel.getInstance();


/*
    methods needed for the login/register view
*/
    @Override
    public void setObserver(Observer o) {
        clientModel.addObserver(o);
    }

    @Override
    public boolean register(String name, String pass) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean registerSuccessful = serverProxy.register(name, pass);
        return registerSuccessful;
    }

    @Override
    public Account login(String name, String pass) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        Account account = serverProxy.login(name, pass);
        ClientModel.getInstance().setAccount(account);
        ClientModel.getInstance().loginSetThis_Player();
        return account;
    }


/*
    methods needed for gameListView
*/
    @Override
    public void getServerGamesList(String auth) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        List<GameLobby> games = serverProxy.getServerGameList(clientModel.getAuthorization());
        clientModel.setGameLobbyList(games);

    }

    @Override
    public List<GameLobby> getClientGamesList() {
        return clientModel.getListOfLobbies();

    }

    @Override
    public int getNewCommands() {
        ServerProxy serverProxy = ServerProxy.getInstance();
        int last_cmd = clientModel.getLastCommand();
        String auth = clientModel.getAuthorization();

        List<Command> list_of_commands = serverProxy.getNewCommands(last_cmd, auth);

        //Return was not in brackets... did this fix it?
        if(list_of_commands == null)
        {return 0;}


        for(int i = 0; i < list_of_commands.size(); i++)
        {
            Command cmd = (Command) list_of_commands.get(i);
            cmd.executeOnClient();
            clientModel.getCommand_list().add(cmd);
        }
        return list_of_commands.size();
    }

    @Override
    public boolean createGameLobby(String gameName, int maxPlayers) {
        String auth = clientModel.getAuthorization();
        ServerProxy serverProxy = ServerProxy.getInstance();
        return serverProxy.createGameLobby(gameName, maxPlayers, auth);
    }

    @Override
    public void addGameToLobbyList(GameLobby game) {
        clientModel.addLobbyToList(game);
    }

    @Override
    public GameLobby joinGame(int gameID) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        GameLobby current_game_lobby = serverProxy.joinGame(gameID, clientModel.getAuthorization());
        clientModel.setCurrent_game_lobby(current_game_lobby);
        clientModel.setCurrentGameByID(gameID);
        clientModel.lobbySetPlayer_num();
        return current_game_lobby;
    }

    @Override
    public void someoneJoinedGame(int gameID, Account account) {
        clientModel.playerJoinsGame(gameID, account);
    }


/*
    methods needed for GameLobby View
*/


    @Override
    public List<Player> getPlayers() {
        return clientModel.getCurrent_game_lobby().getPlayers();
    }

    @Override
    public boolean changePlayerColor(ColorNum colorNum) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        String auth = clientModel.getAuthorization();

        boolean successful = serverProxy.setPlayerColor(colorNum, auth);
        return successful;
    }

    @Override
    public ArrayList<String> getLobbyChat() {
        return ClientModel.getInstance().getCurrent_game_lobby().getComment_list();
    }

    @Override
    public boolean sendMessage(String msg) {
        String message = clientModel.getAccount().getUsername() + ": "+msg;
        return ServerProxy.getInstance().addComment(message, clientModel.getAuthorization());
    }

    @Override
    public void addComment(int gameID, String message) {
        clientModel.addCommentToCurrentGame(gameID, message);
    }

    public List<String> getGameComments(){
        return clientModel.getGameComments();
    }

    @Override
    public Result beginGame() {

        //Testing if FIRST player pressed beginGame.
        Account account = clientModel.getAccount();
        List<Player> players = getPlayers();
        if (!players.get(0).getAccount().getAuthentication().equals(account.getAuthentication()))
        {
            return new Result(false, "Not first player");
        }

        //Testing if there are at least 2 players
        if (players.size() < 0)
        {
            return new Result(false, "Must have 2 players to start the game");
        }


        String auth = clientModel.getAuthorization();
        int gameLobbyID = clientModel.getCurrent_game_lobby().getID();
        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean beginGameBool = serverProxy.beginGame(gameLobbyID, auth);

        Game game = new Game(clientModel.getInstance().getCurrent_game_lobby());
        clientModel.setCurrent_game(game);
        //clientModel.gameSetPlayer_num();
        //TODO: is this supposed to always return null?
        return new Result(beginGameBool, "");
    }

    @Override
    public void aGameStarted(int gameID){
        clientModel.aGameStarted(gameID);


    }



/*
    methods needed for game play
    client methods are followed by their counterpart needed for receiving
*/


    public void calculateTurn()
    {clientModel.calculateTurn();}

    @Override
    public Player getThisPlayer()
    {
        return clientModel.getThis_player();
    }


    @Override
    public ArrayList<String> getChat() {
        return (ArrayList) clientModel.getCurrent_game().getChat();
    }


    @Override
    public List<Player> getGamePlayers() {
        return clientModel.getCurrent_game().getPlayers();
    }

    public void getNewGameCommands() {
        ServerProxy serverProxy = ServerProxy.getInstance();
        int lastCommand = clientModel.getLastGameCommand();
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getGameID();

        List<Command> listOfCommands = serverProxy.getNewGameCommands(gameID, lastCommand, auth);

        //Return was not in brackets... did this fix it?
        if(listOfCommands == null)
        {return;}

        System.out.println(lastCommand);
        for(int i = 0; i < listOfCommands.size(); i++)
        {
            Command cmd = (Command) listOfCommands.get(i);
            cmd.executeOnClient();
            clientModel.getGameCommandList().add(cmd);
        }
    }

    @Override
    public boolean endTurn() {
        setClientState(new NotMyTurnState());
        ServerProxy serverProxy = ServerProxy.getInstance();
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();

        boolean successful = serverProxy.endTurn(gameID,auth);
        return successful;
    }

    @Override
    public void aTurnEnded(int gameID, String auth) {
        clientModel.endTurn(gameID, auth);
    }

    @Override
    public boolean ClaimRoute(Route route) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();
        CardColor colorOfCardsUsed = clientModel.getDesiredToUseColor();

        boolean successful = serverProxy.claimRoute(gameID, route,auth, colorOfCardsUsed);
        return successful;
    }

    @Override
    public void RouteClaimedbyPlayer(int gameID, Route route, String auth, CardColor colorOfCardUsed) {
        Game currentGame = clientModel.getCurrent_game();
        if(currentGame.getGameID() == gameID)
        {clientModel.claimRoute(route, auth, colorOfCardUsed);}
    }

    //train deck commands
    public boolean drawDeckCard(){
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();
        ServerProxy serverProxy = ServerProxy.getInstance();

        boolean variable = serverProxy.drawDeckCard(auth, gameID);
        getNewGameCommands();
        return variable;
    }

    public void playerDrewDeckCard(int gameID, int playerID, CardColor card) {
        Game currentGame = clientModel.getCurrent_game();
        if(currentGame.getGameID() == gameID)
        {
            clientModel.drawDeckCard(playerID, card);
        }
    }

    public Route getRouteByClick(Point click)
    {
        return getRoutesList().getClickedRoute(click);
    }

    //get current player's list of trainCards
    public List<CardColor> getPlayerCards()
    {
        return ClientModel.getInstance().getThis_player().getTrainCards();
    }

    public int getRemainingTrains()
    {
        return clientModel.getThis_player().getTrainsRemaining();
    }


    //destination card commands
    public boolean retrieve3DestinationCards(){
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();
        ServerProxy serverProxy = ServerProxy.getInstance();
        Result r = serverProxy.drawDestinationCards(gameID,auth);
        Command cmd = (Command) r.getInfo();
        //clientModel.getGameCommandList().add(cmd);
        //cmd.executeOnClient();
        getNewGameCommands();
        return r.isSuccess();
    }

    //check if player drew destination card
    public void playerDrewDestinationCards(int gameID, int playerID, DestinationCard[] destinationCards){
        clientModel.drawDestinationCards(gameID, playerID, destinationCards);
    }

    //current player's destinationCards
    public List<DestinationCard> getDestinationList()
    {
        return clientModel.getInstance().getThis_player().getDestinationCards();
    }

//    public boolean discardDestinationCards(DestinationCard discardedDestCard)
//    {
//        ServerProxy serverProxy = ServerProxy.getInstance();
//        String auth = clientModel.getAuthorization();
//        int gameID = clientModel.getCurrent_game().getGameID();
//
//        return serverProxy.removeDestinationCard(discardedDestCard, gameID,auth);
//    }
//    public void playerRemovedDestinationCard(int gameID, int playerID, DestinationCard destinationCard) {
//        clientModel.removeDestinationCard(gameID,playerID, destinationCard);
//    }

    public RoutesList getRoutesList()
    {
      return clientModel.getInstance().getRoutesList();
    }


    public static String next_cmd = "STUPID BUTTON";
    public static int time = -1;
    public void runAnimation()
    {
        //only to be used while initialization works
        if(time == -1)
           initializeAnimation();
        // ^ ^ ^

        Game game = clientModel.getCurrent_game();
        Player current = clientModel.getThis_player();
        switch(time)
        {
            case -1: next_cmd = "Claim Route for Player 1"; break;
            case 0: game.stupidClaimRoute(11, 1); next_cmd = "Claim Route for Player 2 "; break;
            case 1: game.stupidClaimRoute(20, 2); next_cmd = "Claim Route for Player 3 "; break;
            case 2: game.stupidClaimRoute(24, 3); next_cmd = "Claim Route for Player 4 "; break;
            case 3: game.stupidClaimRoute(26, 4); next_cmd = "Claim Route for Player 5 "; break;
            case 4: game.stupidClaimRoute(12, 5); next_cmd = "Draw PURPLE for Current Player "; break;
            case 5: current.addTrainCard(CardColor.PURPLE); next_cmd = "Draw WHITE for Current Player "; break;
            case 6: current.addTrainCard(CardColor.WHITE); next_cmd = "Draw BLUE for Current Player "; break;
            case 7: current.addTrainCard(CardColor.BLUE); next_cmd = "Draw YELLOW for Current Player "; break;
            case 8: current.addTrainCard(CardColor.YELLOW); next_cmd = "Draw ORANGE for Current Player "; break;
            case 9: current.addTrainCard(CardColor.ORANGE); next_cmd = "Draw BLACK for Current Player "; break;
            case 10: current.addTrainCard(CardColor.BLACK); next_cmd = "Draw RED for Current Player "; break;
            case 11: current.addTrainCard(CardColor.RED); next_cmd = "Draw GREEN for Current Player "; break;
            case 12: current.addTrainCard(CardColor.GREEN); next_cmd = "Draw WILD for Current Player "; break;
            case 13: current.addTrainCard(CardColor.WILD); next_cmd = "Add Dest Card to Current Player (Duluth-Houston) "; break;
            case 14: current.addDestinationCard(new DestinationCard("Duluth", "Houston", 8)); next_cmd = "Add Red Card to Player 1"; break;
            case 15: game.stupidAddCard(CardColor.RED, 1); next_cmd = "Add Red Card to Player 2 "; break;
            case 16: game.stupidAddCard(CardColor.RED, 2); next_cmd = "Add Red Card to Player 3 "; break;
            case 17: game.stupidAddCard(CardColor.RED, 3); next_cmd = "Add Red Card to Player 4 "; break;
            case 18: game.stupidAddCard(CardColor.RED, 4); next_cmd = "Add Red Card to Player 5 "; break;
            case 19: game.stupidAddCard(CardColor.RED, 5); next_cmd = "Add Destination card to player 1"; break;
            case 20: game.stupidAddDestinationCard((new DestinationCard("Toronto", "Miami", 10)),1); next_cmd = "Add Destination card to player 2 "; break;
            case 21: game.stupidAddDestinationCard((new DestinationCard("Toronto", "Miami", 10)),2); next_cmd = "Add Destination card to player 3 "; break;
            case 22: game.stupidAddDestinationCard((new DestinationCard("Toronto", "Miami", 10)),3); next_cmd = "Add Destination card to player 4 "; break;
            case 23: game.stupidAddDestinationCard((new DestinationCard("Toronto", "Miami", 10)),4); next_cmd = "Add Destination card to player 5 "; break;
            case 24: game.stupidAddDestinationCard((new DestinationCard("Toronto", "Miami", 10)),5); next_cmd = "Turn goes to next player "; break;
            /*case 25: game.endTurn(); next_cmd = "Turn goes to next player"; break;
            case 26: game.endTurn(); next_cmd = "Turn goes to next player"; break;
            case 27: game.endTurn(); next_cmd = "Turn goes to next player"; break;
            case 28: game.endTurn(); next_cmd = "Turn goes to next player"; break;
            case 29: game.endTurn(); next_cmd = "Add new message"; break;
//            case 30: game.stupidAddComment("DUMMY1","Hello"); next_cmd = "Add new message"; break;
//            case 31: game.stupidAddComment("DUMMY2","Greetings"); next_cmd = "Add new message"; break;
//            case 32: game.stupidAddComment("DUMMY3","Good Evening, old chap"); next_cmd = "DONE"; break;*/
        }
        time++;
        clientModel.update();
    }

    public void initializeAnimation()
    {
        GameLobby gamelobby = new GameLobby();
        Account acnt = new Account();
        acnt.setUsername("DUMMY1");
        gamelobby.stupidAddPlayer(acnt);
        acnt = new Account();
        acnt.setUsername("DUMMY2");
        gamelobby.stupidAddPlayer(acnt);
        acnt = new Account();
        acnt.setUsername("DUMMY3");
        gamelobby.stupidAddPlayer(acnt);
        acnt = new Account();
        acnt.setUsername("DUMMY4");
        gamelobby.stupidAddPlayer(acnt);
        acnt = new Account();
        acnt.setUsername("DUMMY5");
        gamelobby.stupidAddPlayer(acnt);
        gamelobby.setID(1);
        Game game = new Game(gamelobby);
        game.setFaceUpCard(0, CardColor.RED);
        game.setFaceUpCard(1, CardColor.BLACK);
        game.setFaceUpCard(2, CardColor.BLUE);
        game.setFaceUpCard(3, CardColor.WILD);
        game.setFaceUpCard(4, CardColor.GREEN);
        clientModel.setCurrent_game(game);
        clientModel.setThis_player(game.getPlayerbyIndex(0));


    }

    public int getPlayerTurnItIs()
    {
        return clientModel.ThisPlayersTurn();
    }

    public void setThis_player()
    {
        clientModel.setPlayerThroughAuthCode();
    }

    public List<CardColor> getFaceUpCards()
    {
        return clientModel.getFaceUpCards();
    }

    public boolean drawFaceUpCard(int cardIndex)
    {
        //draws a face up card by index
        //sends a drawFaceUpCard command to the server
        Game currentGame = clientModel.getCurrent_game();
        String auth = clientModel.getAuthorization();
        int gameID = currentGame.getGameID();

        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean variable = serverProxy.drawFaceUpCard(cardIndex, auth, gameID);
        getNewGameCommands();
        return variable;
    }

    public void setFaceUpCard(int gameID, CardColor card, int cardIndex) {
        clientModel.SetFaceUpCard(card, cardIndex);
    }

    public CardColor getFaceUpCard(int cardIndex)
    {
        return clientModel.getFaceUpCard(cardIndex);
    }


    public boolean replaceFaceUpCards() {
        Game currentGame = clientModel.getCurrent_game();
        int gameID = currentGame.getGameID();
        List<CardColor> discaredFaceUpCards = currentGame.getFaceUpCards();

        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean successful = serverProxy.replaceFaceUpCards(gameID);

        if (successful)
        {currentGame.addCardsToDiscard(discaredFaceUpCards);}

        return successful;
    }

    public void replaceAllFaceUpCards(int gameID, List<CardColor> newCards) {
        if(gameID == clientModel.getCurrent_game().getGameID())
        {
            clientModel.ReplaceAllFaceUpCards(newCards);
        }
    }


    public ClientState getCurrentState()
    {
        return clientModel.getCurrentState();
    }

    public void setClientState(ClientState state)
    {
        clientModel.setClientState(state);
    }

    public boolean shouldShowDestinationCard()
    {
        return clientModel.shouldShowDestinationCard();
    }

    public boolean canClaimRoute(Route desiredRoute)
    {
        return clientModel.canClaimRoute(desiredRoute);
    }

    public void setToUseColor(CardColor color)
    {
        clientModel.setDesiredToUseColor(color);
    }

    public boolean[] getDestinationCardsAcceptance()
    {
        return clientModel.getDestinationCardsAcceptance();
    }

    public void intializeDestinationCardsAcceptance()
    {
        clientModel.intializeDestinationCardsAcceptance();
    }

    public void toggleDestinationCardsAcceptance(int n)
    {
        clientModel.toggleDestinationCardsAcceptance(n);
    }

    //this function returns the 3 destination cards stored in the model which the player can
    //either keep or discard  this array (or list) is created when the draw destination card command is used
    public DestinationCard[] getChoosableDestinationCards()
    {
        Player thisPlayer = ClientModel.getInstance().getThis_player();
        DestinationCard[] dests = thisPlayer.getAllChooseableDestinationCards();
        return dests;
    }

    //TODO this function is called when the destination card choices are confirmed.  The 3 destination Cards should be stored
    //in the model as well as a boolean array of which cards are to be kept (true) and discarded (false)
    public void confirmDestinationCards()
    {
        DestinationCard[]  acceptedCards = clientModel.getConfirmedCards();
        boolean[] acceptedCardsBools = clientModel.getDestinationCardsAcceptance();
        int gameID = clientModel.getCurrent_game().getGameID();
        int playerID = clientModel.getThis_player().getPlayerID();
        String auth = clientModel.getAuthorization();
        ServerProxy proxy = ServerProxy.getInstance();
        proxy.removeDestinationCard(gameID, playerID, acceptedCards, acceptedCardsBools, auth);
        clientModel.setAllChoosableDestinationCardsToNull();
    }

    public boolean canConfirmDestinationCards()
    {
        return clientModel.canConfirmDestinationCards();
    }

    public boolean isGameOver()
    {
        return clientModel.isGameOver();
    }

    public void performEndGameCalculations()
    {
        clientModel.getCurrent_game().performEndGameCalculations();
    }

    public int getAmountOfPlayersInCurrentGame()
    {
        return getPlayers().size();
    }


    public void addDestinationCardToPlayerIndex(int playerIndex, DestinationCard dc)
    {
        clientModel.addDestinationCardToPlayerIndex(playerIndex,dc);
    }

    public void clearDestinationCardsOfPlayerIndex(int playerIndex)
    {
        clientModel.clearDestinationCardsOfPlayerIndex(playerIndex);
    }

    public boolean ifIsInGameSetGameInMode()
    {
        String auth = clientModel.getAuthorization();
        Game current_game = ServerProxy.getInstance().getCurrentGame(auth);
        return clientModel.restoreGame(current_game);
    }


}
