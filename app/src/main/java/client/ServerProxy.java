package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import shared.ColorNum;
import shared.Result;
import shared.Serializer;
import shared.command_classes.AddCommentCommand;
import shared.command_classes.ClaimRouteCommand;
import shared.command_classes.Command;
import shared.command_classes.CreateGameCommand;
import shared.command_classes.DrawDeckCardCommand;
import shared.command_classes.EndTurnCommand;
import shared.command_classes.GetGamesCommand;
import shared.command_classes.GetNewCommandsCommand;
import shared.command_classes.JoinGameCommand;
import shared.command_classes.LoginCommand;
import shared.command_classes.RegisterCommand;
import shared.command_classes.SetPlayerColorCommand;
import shared.command_data_classes.AddCommentCommandData;
import shared.command_data_classes.BeginGameCommandData;
import shared.command_data_classes.ClaimRouteCommandData;
import shared.command_data_classes.CreateGameCommandData;
import shared.command_data_classes.GetGamesCommandData;
import shared.command_data_classes.GetNewCommandsCommandData;
import shared.command_data_classes.JoinGameCommandData;
import shared.command_data_classes.LoginCommandData;
import shared.command_data_classes.RegisterCommandData;
import shared.command_data_classes.SetPlayerColorCommandData;
import shared.interfaces.IServer;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;
import shared.model_classes.Route;

public class ServerProxy implements IServer{

    private static ServerProxy instance = null;
    String urlpath = "http://10.0.2.2:8080/command";

    private ServerProxy() {

    }

    public static ServerProxy getInstance()
    {
        if(instance == null) {
            instance = new ServerProxy();
        }
        return instance;
    }

    /*
      Function used prior to starting game
    */

    @Override
    public Account login(String username, String password) {

        LoginCommandData cmdData = new LoginCommandData();
        cmdData.setUsername(username);
        cmdData.setPassword(password);

        Command cmd = new LoginCommand();
        cmd.setInfo(cmdData);
        Result r =  ClientCommunicator.getInstance().send(urlpath, cmd);
        Account account = null;
        if(r.isSuccess())
        {
            account = (Account) r.getInfo();
            return account;
        }
        else
        {
            return account;
        }
    }

    @Override
    public boolean register(String username, String password)
    {


        RegisterCommandData cmdData = new RegisterCommandData();
        cmdData.setUsername(username);
        cmdData.setPassword(password);

        Command cmd = new RegisterCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    @Override
    public boolean createGameLobby(String gameName, int max_player_num, String auth)
    {
        //create command data object
        CreateGameCommandData cmdData = new CreateGameCommandData();
        cmdData.setGameName(gameName);
        cmdData.setMaxPlayerNum(max_player_num);
        cmdData.setAuth(auth);

        // create command object
        Command cmd = new CreateGameCommand();
        cmd.setInfo(cmdData);

        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    @Override
    public GameLobby joinGame(int gameLobbyID, String auth)
    {
        JoinGameCommandData cmdData = new JoinGameCommandData();
        cmdData.setGameLobbyID(gameLobbyID);
        cmdData.setAuth(auth);

        Command cmd = new JoinGameCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return (GameLobby) r.getInfo();
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<GameLobby> getServerGameList(String auth)
    {
        GetGamesCommandData cmdData = new GetGamesCommandData();
        cmdData.setAuth(auth);

        Command cmd = new GetGamesCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return new ArrayList<GameLobby>(Arrays.asList((GameLobby[]) r.getInfo()));
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Command> getNewCommands(int gameLobbyID, String auth)
    {
        GetNewCommandsCommandData cmdData = new GetNewCommandsCommandData();
        cmdData.setGameLobbyID(gameLobbyID);
        cmdData.setAuth(auth);

        Command cmd = new GetNewCommandsCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return new ArrayList<>(Arrays.asList((Command[]) r.getInfo()));
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean beginGame(int gameLobbyID, String auth)
    {
        BeginGameCommandData cmdData = new BeginGameCommandData();
        cmdData.setGameLobbyID(gameLobbyID);
        cmdData.setAuth(auth);

        Command cmd = new GetGamesCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);

        return r.isSuccess();
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        SetPlayerColorCommandData cmdData = new SetPlayerColorCommandData();
        cmdData.setColorNum(color);
        cmdData.setAuth(auth);

        Command cmd = new SetPlayerColorCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();

    }

    @Override
    public boolean addComment(String message, String auth) {
        AddCommentCommandData cmdData = new AddCommentCommandData();
        cmdData.setMessage(message);
        cmdData.setAuth(auth);
        Command cmd = new AddCommentCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    /*
        These are the functions used after starting the game
    */

    @Override
    public boolean endTurn(int gameID, String auth) {
        String json = "{\"gameID\": \""+gameID+"\", \"auth\":\""+auth+"\"}";
        Command cmd = new EndTurnCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    @Override
    public boolean claimRoute(int gameID, Route routeClaimed, String auth) {
        ClaimRouteCommandData cmdData = new ClaimRouteCommandData();
        cmdData.setGameID(gameID);
        cmdData.setRoute(routeClaimed);
        cmdData.setAuth(auth);


        Command cmd = new ClaimRouteCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath,cmd);
        return r.isSuccess();
    }

    @Override
    public boolean drawDestinationCard(String destinationCardName, int playerID, String auth) {
        return false;
    }

    @Override
    public boolean removeDestinationCard(String destinationCardName, String auth) {
        return false;
    }

    @Override
    public boolean drawDeckCard(String auth) {
        return false;
    }

    @Override
    public boolean drawFaceUpCard(ColorNum faceUpCardID, String auth) {
        return false;
    }

}
