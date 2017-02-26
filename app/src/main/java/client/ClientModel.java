package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import client.presenters.GameListPresenter;
import client.presenters.GameLobbyPresenter;
import client.presenters.LoginPresenter;
import shared.command_classes.Command;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

public class ClientModel extends Observable
{

    private ClientModel(){

    };

    public static ClientModel getInstance()
    {
        return instance;
    }

    private Account account = null;
    private GameLobby current_game_lobby;
    private Game current_game;
    private List<ICommand> command_list = new ArrayList<>();
    private List<GameLobby> list_of_lobbies = new ArrayList<>();
    private static ClientModel instance = new ClientModel();

    private LoginPresenter loginPresenter = null;
    private GameListPresenter gameListPresenter = null;
    private GameLobbyPresenter gameLobbyPresenter = null;

    public void addLobbyToList(GameLobby game)
    {
        list_of_lobbies.add(game);
        update();
    }

    public GameLobby getGameLobbyByID(int gameID){
     for(int i = 0; i< list_of_lobbies.size(); i++)
     {
         GameLobby game = list_of_lobbies.get(i);
         if(game.getID() == gameID)
         {
             return game;
         }
     }
        return null;
    }

    public int getLastCommand() {
        if(command_list.size() == 0)
            return -1;
        return ((Command) command_list.get(command_list.size()-1)).getCmdID();
    }

    public Account getAccount() {
        return account;
    }

    public List<GameLobby> getListOfLobbies(){return list_of_lobbies;}
    public void setAccount(Account account) {
        this.account = account;
    }

    public GameLobby getCurrent_game_lobby() {
        return current_game_lobby;
    }

    public void setCurrent_game_lobby(GameLobby current_game_lobby) {
        this.current_game_lobby = current_game_lobby;
        update();
    }

    public Game getCurrent_game() {
        return current_game;
    }

    public void setCurrent_game(Game current_game) {
        this.current_game = current_game;
    }

    public List<ICommand> getCommand_list() {
        return command_list;
    }

    public void setCommand_list(List<ICommand> command_list) {
        this.command_list = command_list;
    }

    public String getAuthorization()
    {
        return account.getAuthentication();
    }

    public void update()
    {
        setChanged();
        notifyObservers();
    }

    public void addCommentToCurrentGame(int gameID, String message)
    {
        if(current_game_lobby.getID() == gameID)
        {
            current_game_lobby.addNewComment(message);
            update();
        }
    }

    public void playerJoinsGame(int gameID, String name)
    {
        GameLobby game = getGameLobbyByID(gameID);
        if(game != null)
        {
            game.playerJoined();
        }
        if(game == current_game_lobby)
        {
            //if its the current game then add player to list so name pops up on screen
        }
        update();
    }

    public void aGameStarted(int gameID)
    {
        if(gameID == current_game_lobby.getID())
        {
            current_game = current_game_lobby.changeIntoGame();
            removeGameLobbyByID(current_game_lobby.getID());
            current_game_lobby = null;

        }
        else
        {
            removeGameLobbyByID(gameID);
        }

        //update();
    }

    public void removeGameLobbyByID(int gameID)
    {
        for(int i = 0; i < list_of_lobbies.size(); i++)
        {
            GameLobby game = list_of_lobbies.get(i);
            if(game.getID() == gameID)
            {
                list_of_lobbies.remove(i);
            }
        }
    }

    public void setGameLobbyList(List<GameLobby> games)
    {
        list_of_lobbies = games;
        update();
    }


    public void setGameListPresenter(GameListPresenter gameListPresenter)
    {
        this.gameListPresenter = gameListPresenter;
        addObserver(gameListPresenter);
    }

    public void setGameLobbyPresenter(GameLobbyPresenter gameLobbyPresenter)
    {
        this.gameLobbyPresenter = gameLobbyPresenter;
        addObserver(gameLobbyPresenter);
    }
}
