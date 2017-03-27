package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shared.ColorNum;
import shared.Result;
import shared.Serializer;
import shared.command_classes.AddCommentCommand;
import shared.command_classes.BeginGameCommand;
import shared.command_classes.ClaimRouteCommand;
import shared.command_classes.Command;
import shared.command_classes.CreateGameCommand;
import shared.command_classes.DrawDeckCardCommand;
import shared.command_classes.DrawDestinationCardCommand;
import shared.command_classes.EndTurnCommand;
import shared.command_classes.GetGamesCommand;
import shared.command_classes.GetNewCommandsCommand;
import shared.command_classes.GetNewGameCommandsCommand;
import shared.command_classes.JoinGameCommand;
import shared.command_classes.LoginCommand;
import shared.command_classes.RegisterCommand;
import shared.command_classes.RemoveDestinationCardCommand;
import shared.command_classes.SetPlayerColorCommand;
import shared.command_data_classes.AddCommentCommandData;
import shared.command_data_classes.BeginGameCommandData;
import shared.command_data_classes.ClaimRouteCommandData;
import shared.command_data_classes.CreateGameCommandData;
import shared.command_data_classes.DrawDestinationCardCommandData;
import shared.command_data_classes.GetGamesCommandData;
import shared.command_data_classes.GetNewCommandsCommandData;
import shared.command_data_classes.GetNewGameCommandsCommandData;
import shared.command_data_classes.JoinGameCommandData;
import shared.command_data_classes.LoginCommandData;
import shared.command_data_classes.RegisterCommandData;
import shared.command_data_classes.RemoveDestinationCardCommandData;
import shared.command_data_classes.SetPlayerColorCommandData;
import shared.interfaces.IServer;
import shared.model_classes.Account;
import shared.model_classes.DestinationCard;
import shared.model_classes.GameLobby;
import shared.model_classes.Route;

public class ServerProxy implements IServer{

    //CLASS INVARIANT - auth should be a valid authorization code
    private static ServerProxy instance = null;
    String urlpath = "http://10.0.2.2:8080/command";
    //String urlpath = "http://192.168.1.111:8080/command";

    //precondition - none
    //postcondition - none
    private ServerProxy() {

    }

    //precondition - none
    //return reutrns an instance of the server proxy
    //postcondition - if instance is null, instance is set to a new ServerProxy instance
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

    //@param username - the username of the desired account to be logged in
    //@param password - the password of the desired account to be logged in
    //@pre-condition - username must not be null, and must be valid username
    //@pre-condition - password must not be null, and must correct password
    //@return - The appropriate account according to the username and password, has a new authorization code
    //@post-condition - no data should be changed, returns desired account
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

    //@param username - the desired username for a new account
    //@param password - the desired password for a new account
    //@pre-condition - username must not be null, must not be a username that exists in the server
    //@pre-condition - password must not be null
    //return - returns true if sucessful, false if not
    //post-condition - sends command to server to create a new username/password combination
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

    //@param gameName - the name of the Game to be created
    //@param max_player_num - the maximum number of players allowed to join
    //@param auth - the authentication code of the account performing the creation
    //@precondition - gameName must not be null
    //@precondition - max_player_num should be an in between 2-5
    //@precondition - auth should be a valid authorization code
    //return - true if game successfull created, false if otherwise
    //post-condition - sends a command to the server to create a game on the server
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

    //@param gameLobbyID - the ID of the desired lobby to join
    //@param auth - The authorization code of the account joining the game
    //@precondition - gameLobbyID must be a valid ID (a game exists with that ID)
    //@precondition - auth should be a valid authorization code
    //@return returns the GameLobby of the joined game
    //@postcondition - sends a command to the server to add the current account to desired game lobby if sucessful
    @Override
    public GameLobby joinGame(int gameLobbyID, String auth)
    {
        JoinGameCommandData cmdData = new JoinGameCommandData();
        cmdData.setGameLobbyID(gameLobbyID);
        cmdData.setAuth(auth);

        Command cmd = new JoinGameCommand();
        cmd.setInfo(cmdData);
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

    //@param auth - the authorization code of the client retrieving the games
    //@precondition - auth should be a valid authorization code
    //@return returns the list of gamelobbies located on the server
    //@postcondition - sends a command to the server to retrieve the gamess and returns that list, no data members in models should be changed
    @Override
    public List<GameLobby> getServerGameList(String auth)
    {
        GetGamesCommandData cmdData = new GetGamesCommandData();
        cmdData.setAuth(auth);

        Command cmd = new GetGamesCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r != null && r.isSuccess())
        {
            GetGamesCommandData newCmdData = (GetGamesCommandData) r.getInfo();
            List<GameLobby> gameLobbies = Arrays.asList(newCmdData.getGameLobbies());
            return gameLobbies;
        }
        else
        {
            return null;
        }
    }

    //@param commandID - the ID of the last command received from that specefic client
    //@param auth - the authorization code of the account asking for the new commands
    //@precondition - commandID must be less than or equal to the ID of the last command stored in the server
    //@precondition - auth should be a valid authorization code
    //@return returns a list of Commands to be executed on the client
    //postcondition - no data members in models should be changed; a command is created that retrieves a list of new commands
    @Override
    public List<Command> getNewCommands(int commandID, String auth)
    {
        GetNewCommandsCommandData cmdData = new GetNewCommandsCommandData();
        cmdData.setCommandID(commandID);
        cmdData.setAuth(auth);

        Command cmd = new GetNewCommandsCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r != null && r.isSuccess())
        {
            GetNewCommandsCommandData newCmdData = (GetNewCommandsCommandData) r.getInfo();
            return Arrays.asList(newCmdData.getCmds());
        }
        else
        {
            return null;
        }
    }

    //@param gameLobbyID - the ID of the game Lobby that is starting
    //@param auth - the authorization code of the client starting the game
    //@precondition - gameLobbyID must be an ID that correlates to an existing gameLobby
    //@precondition - auth should be a valid authorization code
    //@return returns true if sucessful, false if not
    //@postcondition - a beginGame command is sent, executed, and stored on the server
    @Override
    public boolean beginGame(int gameLobbyID, String auth)
    {
        BeginGameCommandData cmdData = new BeginGameCommandData();
        cmdData.setGameLobbyID(gameLobbyID);
        cmdData.setAuth(auth);

        Command cmd = new BeginGameCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);

        return r.isSuccess();
    }

    //@param color - desired color to be set to the player
    //@param auth - authorization code of the player who's color is to be set
    //@precondition - color must be a valid ColorNum
    //@precondition - auth should be a valid authorization code
    //@return returns true if successful
    //@postcondition - a setPlayerColorCommand is sent to, executed, and stored on the server
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

    //@param message - desired message to be displayed in the chat, starts with the players name
    //@param auth - authorization code the the sender of the message
    //@precondition - message must not be null
    //@precondition - auth should be a valid authorization code
    //return returns true if sucessful, otherwise false
    //postcondition - AddCommentCommand message should be sent to, executed, and stored on the server
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

    //@param lastCommand - ID of the last game command received by the client
    //@param auth - authorization of the client asking for new game commands
    //precondition - lastCommand must be less than or equal to the newest stored command in the game
    //@precondition - auth should be a valid authorization code
    //@return returns a list of commands to be executed on the client
    //@postcondition - a getNewGameComman is sent to the server and executed; thus returning the list of commands
    public List<Command> getNewGameCommands(int lastCommand, String auth) {
        GetNewGameCommandsCommandData cmdData = new GetNewGameCommandsCommandData();
        cmdData.setCommandID(lastCommand);
        cmdData.setAuth(auth);

        Command cmd = new GetNewGameCommandsCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        if(r != null && r.isSuccess())
        {
            GetNewGameCommandsCommandData newCmdData = (GetNewGameCommandsCommandData) r.getInfo();
            return Arrays.asList(newCmdData.getCmds());
        }
        else
        {
            return null;
        }
    }

    //@param gameID - ID of the game that the client is playing
    //@param auth - authorization code of the client ending turn
    //precondition gameID - must be an existing gameID
    //@precondition - auth should be a valid authorization code
    //@return returns true if sucessful, otherwise false
    //@postcondition - an EndTurnCommand should be sent to the server and then executed and stored
    @Override
    public boolean endTurn(int gameID, String auth) {
        String json = "{\"gameID\": \""+gameID+"\", \"auth\":\""+auth+"\"}";
        Command cmd = new EndTurnCommand();
        cmd.setInfo(json);
        Result r = ClientCommunicator.getInstance().send(urlpath, cmd);
        return r.isSuccess();
    }

    //@param gameID - ID of the game the client is playing
    //@param routeClaimed - the route to be claimed
    //@param auth - authorization code of the client claiming the route
    //@precondition - gameID must be the valid ID of an existing game
    //@precondtion routeClaimed must be a valid route and must not already be claimed
    //@precondition - auth should be a valid authorization code
    //@return returns true if sucessful, false if not
    //@postcondition - a claimRouteCommand is sent to the server and then executed and stored
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

    //@param gameID - the ID of the client drawing a destination card
    //@param auth - the authorization code of the client drawing the destination card
    //@precondition - gameID must be a valid ID of an existing game which the client account is a part of
    //@precondition - auth should be a valid authorization code
    //@return  return true if sucessful, false otherwise
    //@postcondition - a drawDestinationCardCommand is sent to the server and then executed and stored
    @Override
    public boolean drawDestinationCard(int gameID, String auth) {
        DrawDestinationCardCommandData cmdData = new DrawDestinationCardCommandData();
        cmdData.setAuth(auth);
        cmdData.setGameID(gameID);

        Command cmd = new DrawDestinationCardCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath,cmd);
        return r.isSuccess();
    }

    //@param destinationCard - the desired destination card to be removed
    //@param gameID - the ID of the client removing a destination card
    //@param auth - the authorization code of the client removing a destination card
    //@precondition - must be a valid destination card found in the destinationCardList
    //precondition - gameID must be a valid ID of an existing game that the account is a part of
    //@precondition - auth should be a valid authorization code
    //@return returns true if sucessful, false otherwise
    //@postcondition - a removeDestinationCard comand is sent to the server and then executed and stored
    @Override
    public boolean removeDestinationCard(DestinationCard destinationCard, int gameID, String auth) {
        RemoveDestinationCardCommandData cmdData = new RemoveDestinationCardCommandData();
        cmdData.setDiscardedCard(destinationCard);
        cmdData.setAuth(auth);
        cmdData.setGameID(gameID);

        Command cmd = new RemoveDestinationCardCommand();
        cmd.setInfo(cmdData);
        Result r = ClientCommunicator.getInstance().send(urlpath,cmd);
        return r.isSuccess();
    }

    //@param auth - the authorization code of the client drawing a card
    //@param playerID - the id of the player drawing the card
    //@precondition - auth should be a valid authorization code
    //@precondition - playerID should be a number between 0-4 and be the playerID of the client sending the command
    //@return returns true if successful, false otherwise
    //@postcondition - a drawDeckCommand is sent to the server, and then executed and stored
    @Override
    public boolean drawDeckCard(String auth, int playerID) {
        return false;
    }

    //param faceUpCardID - the index of the card being drawn
    //param auth - the authorization code of the client drawing the face up card
    //precondition - faceUpCardID must be an int between 0 and 4
    //@precondition - auth should be a valid authorization code
    //@return returns true if sucessful, false otherwise
    @Override
    public boolean drawFaceUpCard(ColorNum faceUpCardID, String auth) {
        return false;
    }

}
