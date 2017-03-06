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
import shared.command_classes.EndTurnCommand;
import shared.command_classes.GetGamesCommand;
import shared.command_classes.GetNewCommandsCommand;
import shared.command_classes.JoinGameCommand;
import shared.command_classes.LoginCommand;
import shared.command_classes.RegisterCommand;
import shared.command_classes.SetPlayerColorCommand;
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

        String json = "{\"username\": \""+username+"\", \"password\":\""+password+"\"}";
        Command cmd = new LoginCommand();
        cmd.setInfo(json);
        Result r =  ClientCommunicator.getInstance().send(urlpath, cmd);
        Account account = null;
        if(r.isSuccess())
        {
            account = (Account) Serializer.deserialize(r.getInfo());
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
        String json = "{\"username\": \""+username+"\", \"password\":\""+password+"\"}";
        Command cmd = new RegisterCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    @Override
    public boolean createGameLobby(String username, int max_player_num, String auth)
    {
        String json = "{\"username\": \""+username+"\", \"max_player_num\":"+max_player_num+", \"auth\": \""+auth+"\"}";
        Command cmd = new CreateGameCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    @Override
    public GameLobby joinGame(int gameID, String auth)
    {
        String json = "{\"gameID\": \""+gameID+"\", \"auth\":\""+auth+"\"}";
        Command cmd = new JoinGameCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return (GameLobby) Serializer.deserialize(r.getInfo());
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<GameLobby> getServerGameList(String auth)
    {
        String json = "{\"auth\":\""+auth+"\"}";
        Command cmd = new GetGamesCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return (List<GameLobby>) Serializer.deserialize(r.getInfo());
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Command> getNewCommands(int gameID, String auth)
    {
        String json = "{\"gameID\": \""+gameID+"\", \"auth\":\""+auth+"\"}";
        Command cmd = new GetNewCommandsCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return (List<Command>) Serializer.deserializeList(r.getInfo());
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean beginGame(int gameID, String auth)
    {
        String json = "{\"gameID\": \""+gameID+"\", \"auth\":\""+auth+"\"}";
        Command cmd = new GetGamesCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);

        return r.isSuccess();
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        String json = "{\"ColorNum\": \""+color.toString()+"\", \"auth\":\""+auth+"\"}";
        Command cmd = new SetPlayerColorCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();

    }

    @Override
    public boolean addComment(String message, String auth) {
        String json = "{\"message\": \""+message+"\", \"auth\":\""+auth+"\"}";
        Command cmd = new AddCommentCommand();
        cmd.setInfo(json);
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
        String json = "{\"gameID\": \""+gameID+"\", \"route\":\""+routeClaimed+"\", \"auth\":\""+auth+"\"}";
        Command cmd = new ClaimRouteCommand();
        cmd.setInfo(json);
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
