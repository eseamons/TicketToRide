package server.plugin;

import shared.model_classes.Game;

/**
 * Created by eseamons on 4/11/2017.
 */

public interface IGameDao {
    void insertGame(Game game);
    void clearData();
}
