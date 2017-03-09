package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.List;

import shared.command_classes.AddCommentCommand;
import shared.command_classes.Command;
import shared.model_classes.GameLobby;

/**
 * Created by erics on 2/25/2017.
 */

public class GameLobbyList {

    private List<GameLobby> lobbies;
    private static int currentLobbyID;
    private static int currentLobbyCommandID;
    private List<String> gameLobbyNames;
    private List<Command> lobbyCommands;

    public GameLobbyList() {
        lobbies = new ArrayList<>();
        currentLobbyID = 1;
        currentLobbyCommandID = 1;
        gameLobbyNames = new ArrayList<>();
        lobbyCommands = new ArrayList<>();
    }

    /**
     *
     * @param gameLobbyName
     * @param max_player_num
     * @return New Game Lobby
     */
    public GameLobby createGameLobby(String gameLobbyName, int max_player_num) {

        //Create New Game Lobby
        GameLobby newGameLobby = new GameLobby();

        //Set New Game Lobby data fields
        newGameLobby.setName(gameLobbyName);
        newGameLobby.setMax_players(max_player_num);
        newGameLobby.setID(currentLobbyID);

        //Add Game Lobby Name to List of Names. This helps keep the names unique
        gameLobbyNames.add(gameLobbyName);

        //Add Game Lobby to Game Lobby List
        lobbies.add(newGameLobby);

        //Increment the Game Lobby ID Counter
        incrementCurrentLobbyID();

        return newGameLobby;
    }

    /**
     *
     * @param gameLobbyName
     * @return Boolean indicating if the Game Lobby Name already exists
     */
    public boolean gameLobbyNameExists(String gameLobbyName) {
        return gameLobbyNames.contains(gameLobbyName);
    }

    public GameLobby getGameLobbyByID(int gameLobbyID) {
        return lobbies.get(gameLobbyID - 1);
    }

    public void addLobby(GameLobby gameLobby){
        lobbies.add(gameLobby);
    }

    /**
     *
     * @param gameLobbyID
     */
    public void removeLobby(int gameLobbyID) {
        lobbies.remove(gameLobbyID - 1);
    }

    /**
     *
     * @param message
     * @param auth
     * @return Game lobby that has the player with the correct auth code
     */
    public GameLobby addCommentToGameLobby(String message, String auth) {
        GameLobby returnLobby = null;
        for(GameLobby lobby : lobbies) {
            if(lobby.authCodeExistsInLobby(auth)) {
                lobby.addNewComment(message);
                returnLobby = lobby;
            }
        }
        return returnLobby;
    }

    public void setGameLobbyList(List<GameLobby> gameLobbyList) {
        lobbies = gameLobbyList;
    }

    public List<GameLobby> getGameLobbyList() {
        return lobbies;
    }

    private void incrementCurrentLobbyID() {
        currentLobbyID++;
    }

    public int getCurrentLobbyCommandID()
    {
        return lobbyCommands.size()+1;
        /*int ret = currentLobbyCommandID;
        incrementCurrentLobbyCommandID();
        return ret;*/
    }

    public void incrementCurrentLobbyCommandID() {
        currentLobbyCommandID++;
    }

    public void addLobbyCommand(Command cmd) {
        lobbyCommands.add(cmd);
    }

    public List<Command> getNewLobbyCommands(int commandID) {

        List<Command> returnLobbyList = new ArrayList<>();

        if(commandID == -1) {
            return lobbyCommands;
        }
        else {
            return lobbyCommands.subList(commandID, lobbyCommands.size());
        }

    }

}
