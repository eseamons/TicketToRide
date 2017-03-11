package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.presenters.GameListPresenter;
import client.presenters.GameLobbyPresenter;
import client.presenters.LoginPresenter;
import shared.CardColor;
import shared.command_classes.Command;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.DestinationCard;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;
import shared.model_classes.Route;
import shared.model_classes.model_list_classes.GameLobbyList;
import shared.model_classes.model_list_classes.RoutesList;

public class ClientModel extends Observable
{

    private ClientModel(){

    };

    public static ClientModel getInstance()
    {
        return instance;
    }

    private Account account = null;
    private List<Command> command_list = new ArrayList<Command>();
    private List<Command> gameCommandList = new ArrayList<Command>();
    private GameLobbyList gameLobbyList = new GameLobbyList();
    private GameLobby currentGameLobby;
    private Game currentGame;
    private Player this_player;
    private int player_num;
    //private RoutesList routes = new RoutesList();


    public void gameSetPlayer_num()
    {
        player_num = currentGame.getPlayers().size() - 1;
    }

    public void lobbySetPlayer_num()
    {
        player_num = currentGameLobby.NumOfCurrentPlayers() - 1;
    }

    public void setThis_Player()
    {
        //Player p = new Player();
        //p.setAccount(account);
        //this_player = p;
        this_player = currentGame.getPlayerbyIndex(player_num);
    }

    public void loginSetThis_Player()
    {
        Player p = new Player();
        p.setAccount(account);
        this_player = p;
    }

    //Methods needed for all classes
    private static ClientModel instance = new ClientModel();

    public void update() {
        setChanged();
        notifyObservers();
    }

    public String getAuthorization()
    {
        return account.getAuthentication();
    }

    public RoutesList getRoutesList()
    {
        return currentGame.getRoutes();
    }
    //methods needed for the login/register view
    public void setAccount(Account account) {
        this.account = account;
    }
        //not being used...
    public Account getAccount() {
        return account;
    }

    //methods needed for the gameListView
    public void setGameLobbyList(List<GameLobby> games) {
        gameLobbyList.setGameLobbyList(games);
    }

    public List<GameLobby> getListOfLobbies(){return gameLobbyList.getGameLobbyList();}

    public int getLastCommand() {
        if(command_list.size() == 0)
            return -1;
        return ((Command) command_list.get(command_list.size()-1)).getCmdID();
    }

    public List<Command> getCommand_list() {
        return command_list;
    }

    public void addLobbyToList(GameLobby gameLobby) {
        gameLobbyList.addLobby(gameLobby);
    }

    public void setCurrent_game_lobby(GameLobby currentGameLobby) {
        this.currentGameLobby = currentGameLobby;
    }

    public void playerJoinsGame(int gameID, Account account) {
        gameLobbyList.getGameLobbyByID(gameID).addNewPlayers(account);
    }

    public GameLobby getCurrent_game_lobby() {
        return currentGameLobby;
    }


    //methods needed for GameLobby View
    public void addCommentToCurrentGame(int gameID, String message) {
        if(currentGame.getGameID() == gameID)
        {
            currentGame.addNewComment(message);
        }
    }

    public List<String> getGameComments(){
        return currentGame.getAllComments();
    }

    public void aGameStarted(int gameID) {

        if(gameID == currentGameLobby.getID())
        {
            //currentGame = new Game(currentGameLobby);
            //TODO: stop poller from getting game lobby commands and start get game commands
            //poller.stopGameLobbyListTimer();
            //poller.getGameCommands();

        }
        removeGameLobbyByID(gameID);
    }

    public void removeGameLobbyByID(int gameID) {
        gameLobbyList.removeLobby(gameID);
    }



    //methods needed for game play
    public int getLastGameCommand(){
        if(gameCommandList.size() == 0)
        {return -1;}
        return ((Command)command_list.get(command_list.size()-1)).getCmdID();
    }

    public List<Command> getGameCommandList(){
        return gameCommandList;
    }

    public void endTurn(int gameID) {
        if(currentGame.getGameID() == gameID)
        {currentGame.endTurn();}
    }

    public List<CardColor> getFaceUpCards()
    {
        return currentGame.getFaceUpCards();
    }
    public List<Player> getPlayers() {return currentGame.getPlayers();}

    public void claimRoute(Route route, String auth) {
        currentGame.claimRoute(route,auth);
    }

    public void drawDeckCard(int PlayerID, CardColor card) {
        Player p =currentGame.getPlayerbyIndex(PlayerID);
        p.addTrainCard(card);
    }

    public void drawDestinationCard(int gameID, int playerID, DestinationCard destinationCard) {
        if(gameID == currentGame.getGameID())
        {
            Player p = currentGame.getPlayerbyIndex(playerID);
            p.addDestinationCard(destinationCard);
        }
    }

    public void removeDestinationCard(int gameID, int playerID, DestinationCard destinationCard) {
        if(gameID == currentGame.getGameID())
        {
            Player p = currentGame.getPlayerbyIndex(playerID);
            p.removeDestinationCard(destinationCard);
        }
    }

        //NOT SURE WHERE THESE GO YET
    public Game getCurrent_game() {
        return currentGame;
    }

    public void setCurrent_game(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void setCommand_list(List<Command> command_list) {
        this.command_list = command_list;
    }


    public Player getThis_player() {
        return this_player;
    }

    public void setThis_player(Player this_player) {
        this.this_player = this_player;
    }

    public int ThisPlayersTurn()
    {
        return currentGame.ThisPlayersTurn();
    }


    public void SetFaceUpCard(CardColor card, int cardIndex) {
        currentGame.setFaceUpCard(cardIndex,card);
    }
}
