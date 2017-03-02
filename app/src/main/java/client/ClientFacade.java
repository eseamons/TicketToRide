package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

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


    //methods needed for the login/register view
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
        return account;
    }





    //methods needed for the gameListView
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
            //clientModel.update();
        }
    }

    @Override
    public boolean createGameLobby(String gameName, int maxPlayers) {
        String auth = clientModel.getAuthorization();
        ServerProxy serverProxy = ServerProxy.getInstance();
        return serverProxy.createGameLobby(gameName, maxPlayers, auth);
    }

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

    public void someoneJoinedGame(int gameID, Account account) {
        clientModel.playerJoinsGame(gameID, account);
    }






    //methods needed for GameLobby View
    @Override
    public boolean changePlayerColor(ColorNum colorNum) {
        ServerProxy serverProxy = ServerProxy.getInstance();
        String auth = clientModel.getAuthorization();

        boolean successful = serverProxy.setPlayerColor(colorNum, auth);
        return successful;
    }

    @Override
    public ArrayList<String> getChat() {
        //TODO: Implement this

        ArrayList<String> chatArray = new ArrayList<String>();

        return chatArray;
    }

    @Override
    public boolean sendMessage(String msg) {
        //TODO: Implement this plz
        return ServerProxy.getInstance().addComment(msg, clientModel.getAuthorization());
    }

    public void addComment(int gameID, String message) {
        if(clientModel.getCurrent_game_lobby().getID() == gameID)
        {
            clientModel.addCommentToCurrentGame(gameID, message);
        }
        //else do nothing
    }

    public void aGameStarted(int gameID){
        clientModel.aGameStarted(gameID);
    }

    @Override
    public Game beginGame() {

        //TODO: this should either return the boolean or have a way to get the game?

        String auth = clientModel.getAuthorization();
        int ID = clientModel.getCurrent_game_lobby().getID();
        ServerProxy serverProxy = ServerProxy.getInstance();
        boolean beginGameBool = serverProxy.beginGame(ID, auth);
        return null;
    }




    //methods needed for game play
    @Override
    public Player[] getPlayers() {
        //TODO: Implement this

        return null;
    }

    @Override
    public boolean endTurn() {
        //TODO: implement this
        return true;
    }




}
