package client;

import java.util.List;

import server.ServerFacade;
import server.ServerSerializer;
import shared.ColorNum;
import shared.Result;
import shared.command_classes.AddCommentCommand;
import shared.command_classes.Command;
import shared.command_classes.CreateGameCommand;
import shared.command_classes.GetGamesCommand;
import shared.command_classes.JoinGameCommand;
import shared.command_classes.LoginCommand;
import shared.command_classes.RegisterCommand;
import shared.command_classes.SetPlayerColorCommand;
import shared.interfaces.ICommand;
import shared.interfaces.IServer;
import shared.model_classes.Account;
import shared.model_classes.GameLobby;
import shared.model_classes.Player;

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

    @Override
    public Account Login(String username, String password) {

        String json = "{\"username\": \""+username+"\", \"password\":\""+password+"\"}";
        Command cmd = new LoginCommand();
        cmd.setInfo(json);
        cmd.setType("login");
        Result r =  ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return ClientSerializer.deserializeAccount(r.getInfo());
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean Register(String username, String password)
    {
        String json = "{\"username\": \""+username+"\", \"password\":\""+password+"\"}";
        Command cmd = new RegisterCommand();
        cmd.setInfo(json);
        cmd.setType("register");
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    @Override
    public List<GameLobby> getServerGameList(String auth)
    {
        String message = auth;
        Command cmd = new GetGamesCommand();
        cmd.setInfo(message);
        cmd.setType("getgames");
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return ClientSerializer.deserializeGameLobbyList(r.getInfo());
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Command> getNewCommands(int ID, String auth)
    {
        StringBuilder message = new StringBuilder();
        message.append(ID);
        message.append(" " + auth);
        Command cmd = new GetGamesCommand();
        cmd.setInfo(message.toString());
        cmd.setType("getcommands");
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return ClientSerializer.deserializeCommandList(r.getInfo());
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean CreateGame(String username, int max_player_num, String auth)
    {
        String json = "{\"username\": \""+username+"\", \"max_player_num\":"+max_player_num+", \"auth\": \""+auth+"\"}";
        Command cmd = new CreateGameCommand();
        cmd.setInfo(json);
        cmd.setType("creategame");
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    @Override
    public GameLobby joinGame(int ID, String auth)
    {
        StringBuilder message = new StringBuilder();
        message.append(ID);
        message.append(" " + auth);
        Command cmd = new JoinGameCommand();
        cmd.setInfo(message.toString());
        cmd.setType("joingame");
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r.isSuccess())
        {
            return ClientSerializer.deserializeGameLobby(r.getInfo());
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean BeginGame(int ID, String auth)
    {
        StringBuilder message = new StringBuilder();
        message.append(ID);
        message.append(" " + auth);
        Command cmd = new GetGamesCommand();
        cmd.setInfo(message.toString());
        cmd.setType("begingame");
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);

        return r.isSuccess();
    }

    @Override
    public boolean setPlayerColor(ColorNum color, String auth) {
        StringBuilder message = new StringBuilder();
        message.append(color.toString());
        message.append(" " + auth);
        Command cmd = new SetPlayerColorCommand();
        cmd.setInfo(message.toString());
        cmd.setType("setplayercolor");
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();

    }

    @Override
    public boolean addComment(String messagetosend, String auth) {

        StringBuilder message = new StringBuilder();
        message.append(messagetosend + " ");
        message.append(auth);
        Command cmd = new AddCommentCommand();
        cmd.setInfo(message.toString());
        cmd.setType("addcomment");
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

}
