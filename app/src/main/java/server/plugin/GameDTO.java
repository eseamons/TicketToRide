package server.plugin;

import shared.model_classes.Game;

/**
 * Created by rredd94 on 4/11/17.
 */

public class GameDTO {
    Game game;
    int gameID;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
