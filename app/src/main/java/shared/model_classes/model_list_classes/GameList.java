package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.List;

import shared.model_classes.Game;

/**
 * Created by erics on 2/25/2017.
 */

public class GameList {

    private List<Game> games;

    public GameList() {
        games = new ArrayList<>();
    }

    public boolean beginGame() {
        //create game
        Game newGame = new Game();
        games.add(newGame);

        return true;
    }



}
