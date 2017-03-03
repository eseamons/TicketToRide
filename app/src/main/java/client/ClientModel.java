package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.presenters.GameListPresenter;
import client.presenters.GameLobbyPresenter;
import client.presenters.LoginPresenter;
import shared.command_classes.Command;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;
import shared.model_classes.model_list_classes.GameLobbyList;

public class ClientModel extends Observable
{

    private ClientModel(){

    };

    public static ClientModel getInstance()
    {
        return instance;
    }

    private Account account = null;
    private List<ICommand> command_list = new ArrayList<>();
    private GameLobbyList gameLobbyList = new GameLobbyList();
    private GameLobby currentGameLobby;
    private Game currentGame;



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
//        update();
    }

    public List<GameLobby> getListOfLobbies(){return gameLobbyList.getGameLobbyList();}

    public int getLastCommand() {
        if(command_list.size() == 0)
            return -1;
        return ((Command) command_list.get(command_list.size()-1)).getCmdID();
    }

    public List<ICommand> getCommand_list() {
        return command_list;
    }

    public void addLobbyToList(GameLobby gameLobby) {
        gameLobbyList.addLobby(gameLobby);
        //update();
    }

    public void setCurrent_game_lobby(GameLobby currentGameLobby) {
        this.currentGameLobby = currentGameLobby;
       // update();
    }

    public void playerJoinsGame(int gameID, Account account) {
        GameLobby gameLobby = gameLobbyList.getGameLobbyByID(gameID);
        if(gameLobby != null)
        {
            gameLobby.addNewPlayers(account);
        }
       // update();
    }

    public GameLobby getCurrent_game_lobby() {
        return currentGameLobby;
    }




    //methods needed for GameLobby View
    public void addCommentToCurrentGame(int gameID, String message) {
        if(currentGameLobby.getID() == gameID)
        {
            currentGameLobby.addNewComment(message);
            //update();
        }
    }

    public void aGameStarted(int gameID) {
        if(gameID == currentGameLobby.getID())
        {
            currentGame = new Game(currentGameLobby);
            //TODO: change view...
            //TODO: stop poller from getting game lobby commands and start get game commands
        }
        removeGameLobbyByID(gameID);
    }

    public void removeGameLobbyByID(int gameID) {
        gameLobbyList.removeLobby(gameID);
    }



    //methods needed for game play
    public void endTurn(int gameID) {
        if(currentGame.getGameID() == gameID)
        {currentGame.endTurn();}
    }

    public List<Player> getPlayers()
    {return currentGame.getPlayers();}


        //NOT SURE WHERE THESE GO YET
    public Game getCurrent_game() {
        return currentGame;
    }

    public void setCurrent_game(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void setCommand_list(List<ICommand> command_list) {
        this.command_list = command_list;
    }



}
