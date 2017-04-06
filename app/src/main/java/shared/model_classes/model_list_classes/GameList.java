package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.command_classes.Command;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

/**
 * Created by erics on 2/25/2017.
 */

public class GameList {

    private List<Game> games;
    private Map<Integer,Game> gameIdMap;

    private int currentGameCommandID;

    public GameList() {
        games = new ArrayList<>();
    }

    public Game beginGame(GameLobby gameLobby) {
        //create game
        Game newGame = new Game(gameLobby);
        games.add(newGame);
        return newGame;
    }

    //added everything below while working on EndTurnCommand (2/28)
    public Game getGame(int gameID) {
        //reset the map to the list of games incase any games have been added
        gameIdMap = new HashMap<Integer,Game>();
        for (Game g : games)
        {gameIdMap.put(g.getGameID(),g);}

       return gameIdMap.get(gameID);
    }

    public int getCurrentGameCommandID() {
        return currentGameCommandID;
    }

    public void incrementCurrentGameCommandID() {
        currentGameCommandID++;
    }

    public void addGameCommand(int gameID, Command cmd) {
        Game game = gameIdMap.get(gameID);
        game.addGameCommand(cmd);
    }

    public List<Command> getNewGameCommands(int gameID, int commandID) {
        Game game = gameIdMap.get(gameID);
        return game.getNewGameCommands(commandID);
    }

    public Game getGameByAuthCode(String auth){
        Game returnGame = null;

        for(Game game : games) {
            if(game.playerInGame(auth)) {
                returnGame = game;
            }
        }
        return returnGame;
    }

}
