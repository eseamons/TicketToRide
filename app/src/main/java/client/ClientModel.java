package client;

import java.util.List;
import java.util.Observable;

import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

public class ClientModel extends Observable
{

    private Account account = null;
    private GameLobby current_game_lobby;
    private Game current_game;
    private List<ICommand> command_list;
    private List<GameLobby> list_of_lobbies;
    private static ClientModel instance = new ClientModel();

    public void addLobbyToList(GameLobby game)
    {
        list_of_lobbies.add(game);
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


    private ClientModel(){};

    public static ClientModel getInstance()
    {return instance;}

    public int getLastCommand() {
        return 0;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public GameLobby getCurrent_game_lobby() {
        return current_game_lobby;
    }

    public void setCurrent_game_lobby(GameLobby current_game_lobby) {
        this.current_game_lobby = current_game_lobby;
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
    {return account.getAuthentication();}

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
            //if its the current game
        }
    }

    public void aGameStarted(int gameID)
    {
        if(gameID == current_game_lobby.getID())
        {

        }
        else
        {
            removeGameLobbyByID(gameID);
        }

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



}
