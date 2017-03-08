package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import client.interfaces.IClient;
import shared.CardColor;
import shared.ColorNum;
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
        ClientModel.getInstance().setThis_Player();
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
    public void getNewCommands() {
        ServerProxy serverProxy = ServerProxy.getInstance();
        int last_cmd = clientModel.getLastCommand();
        String auth = clientModel.getAuthorization();

        List<Command> list_of_commands = serverProxy.getNewCommands(last_cmd, auth);

        //Return was not in brackets... did this fix it?
        if(list_of_commands == null)
        {return;}


        for(int i = 0; i < list_of_commands.size(); i++)
        {
            Command cmd = (Command) list_of_commands.get(i);
            cmd.executeOnClient();
            clientModel.getCommand_list().add(cmd);
        }
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
    public ArrayList<String> getChat() {
        ArrayList<String> chatArray = new ArrayList<String>();
        return chatArray;
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
    public boolean beginGame() {

        String auth = clientModel.getAuthorization();
        int gameLobbyID = clientModel.getCurrent_game_lobby().getID();
        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean beginGameBool = serverProxy.beginGame(gameLobbyID, auth);
        //TODO: is this supposed to always return null?
        return beginGameBool;
    }

    @Override
    public void aGameStarted(int gameID){
        clientModel.aGameStarted(gameID);
    }



/*
    methods needed for game play
    client methods are followed by their counterpart needed for receiving
*/


    @Override
    public List<Player> getGamePlayers() {
        return clientModel.getCurrent_game().getPlayers();
    }

    @Override
    public boolean endTurn() {
        ServerProxy serverProxy = ServerProxy.getInstance();
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();

        boolean successful = serverProxy.endTurn(gameID,auth);
        return successful;
    }

    @Override
    public void aTurnEnded(int gameID) {
        clientModel.endTurn(gameID);
    }

    @Override
    public boolean ClaimRoute(Route route) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();

        boolean successful = serverProxy.claimRoute(gameID, route,auth);
        return successful;
    }

    @Override
    public void RouteClaimedbyPlayer(int gameID, Route route, String auth) {
        Game currentGame = clientModel.getCurrent_game();
        if(currentGame.getGameID() == gameID)
        {clientModel.claimRoute(route, auth);}
    }

    //train deck commands
    public boolean drawDeckCard(){
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();
        ServerProxy serverProxy = ServerProxy.getInstance();
        return serverProxy.drawDeckCard(auth, gameID);
    }

    public void playerDrewDeckCard(int gameID, int playerID, CardColor card) {
        Game currentGame = clientModel.getCurrent_game();
        if(currentGame.getGameID() == gameID)
        {
            clientModel.drawDeckCard(playerID, card);
        }
    }

    public boolean drawFaceUpCard( CardColor card) {
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();
        ServerProxy serverProxy = ServerProxy.getInstance();
        //return serverProxy.drawFaceUpCard(card, auth, gameID);
        //TODO: all of the drawFaceUpCards on the server side need to have a gameID parameter so the server knows which game to add the info to.
        return false;
    }

    //get current player's list of trainCards
    public List<CardColor> getPlayerCards()
    {
        return ClientModel.getInstance().getThis_player().getTrainCards();
    }


    //destination card commands
    public boolean drawDestinationCard(){
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();
        ServerProxy serverProxy = ServerProxy.getInstance();
        return serverProxy.drawDestinationCard(gameID,auth);
    }

    public void playerDrewDestinationCard(int gameID, int playerID, DestinationCard destinationCard){
        clientModel.drawDestinationCard(gameID, playerID, destinationCard);
    }

    //current player's destinationCards
    public List<DestinationCard> getDestinationList()
    {
        return clientModel.getInstance().getThis_player().getDestinationCards();
    }

    public boolean discardDestinationCards(DestinationCard discardedDestCard)
    {
        ServerProxy serverProxy = ServerProxy.getInstance();
        String auth = clientModel.getAuthorization();
        int gameID = clientModel.getCurrent_game().getGameID();

        return serverProxy.removeDestinationCard(discardedDestCard, gameID,auth);
    }
    public void playerRemovedDestinationCard(int gameID, int playerID, DestinationCard destinationCard) {
        clientModel.removeDestinationCard(gameID,playerID, destinationCard);
    }

    public RoutesList getRoutesList()
    {
      return clientModel.getInstance().getRoutesList();
    }

    public void runAnimation()
    {
        int time = 0;
        switch(time)
        {
            //case 0: clientModel.
        }
    }

    public void initializeAnimation()
    {
        GameLobby fakeLobby = new GameLobby();
        fakeLobby.setID(1);
        fakeLobby.setMax_players(2);
        fakeLobby.setName("ok");
        fakeLobby.addNewPlayers(new Account());
        fakeLobby.addNewPlayers(new Account());
        Game game = new Game(fakeLobby);
    }



}
