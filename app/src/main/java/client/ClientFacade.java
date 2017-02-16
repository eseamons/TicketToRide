package client;

import java.util.ArrayList;
import java.util.List;

import client.interfaces.IClient;
import client.presenters.GameListPresenter;
import client.presenters.GameLobbyPresenter;
import client.presenters.LoginPresenter;
import shared.ColorNum;
import shared.command_classes.Command;
import shared.interfaces.ICommand;
import shared.model_classes.Account;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

public class ClientFacade implements IClient{

    private static ClientModel clientModel = ClientModel.getInstance();

    @Override
    public boolean sendMessage(String msg)
    {
        //TODO: Implement this plz
        return ServerProxy.getInstance().addComment(msg, clientModel.getAuthorization());
    }

    @Override
    public ArrayList<String> getChat()
    {
        //TODO: Implement this

        ArrayList<String> chatArray = new ArrayList<String>();

        return chatArray;
    }

    @Override
    public Player[] getPlayers()
    {
        //TODO: Implement this

        return null;
    }

    @Override
    public boolean changePlayerColor(ColorNum colorNum) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        String auth = clientModel.getAuthorization();

        boolean successful = serverProxy.setPlayerColor(colorNum, auth);
        return successful;
    }


    @Override
    public Account Login(String name, String pass) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        Account account = serverProxy.Login(name, pass);
        ClientModel.getInstance().setAccount(account);
        return account;
    }

    @Override
    public boolean Register(String name, String pass) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean registerSuccessful = serverProxy.Register(name, pass);
        return registerSuccessful;
    }

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
        if(list_of_commands == null)
            return;
        for(int i = 0; i < list_of_commands.size(); i++)
        {
            Command cmd = (Command) list_of_commands.get(i);
            cmd.executeOnClient();
            clientModel.getCommand_list().add(cmd);
        }
    }

    @Override
    public GameLobby joinGame(int gameID) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        return serverProxy.joinGame(gameID, clientModel.getAuthorization());
    }

    @Override
    public boolean createGame(String gameName, int maxPlayers) {
        String auth = clientModel.getAuthorization();
        ServerProxy serverProxy = ServerProxy.getInstance();
        return serverProxy.CreateGame(gameName, maxPlayers, auth);
    }

    @Override
    public Game beginGame(int ID) {

        String auth = clientModel.getAuthorization();

        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean beginGameBool = serverProxy.BeginGame(ID, auth);
        return null;
    }

    public void addGameToLobbyList(GameLobby game)
    {
        clientModel.addLobbyToList(game);
    }

    public void addComment(int gameID, String message)
    {
        if(clientModel.getCurrent_game_lobby().getID() == gameID)
        {
            clientModel.addCommentToCurrentGame(gameID, message);
        }
        //else do nothing
    }

    public void someoneJoinedGame(int gameID, String name)
    {
            clientModel.playerJoinsGame(gameID, name);
    }

    public void aGameStarted(int gameID)
    {
        clientModel.aGameStarted(gameID);
    }

    public void setGameListPrestenter(GameListPresenter gameListPresenter )
    { clientModel.setGameListPresenter(gameListPresenter);}

    public void setGameLobbyPrestenter(GameLobbyPresenter gameLobbyPresenter )
    { clientModel.setGameLobbyPresenter(gameLobbyPresenter);}
}
