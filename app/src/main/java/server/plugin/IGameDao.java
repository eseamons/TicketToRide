package server.plugin;

import java.util.Set;

import shared.Result;
import shared.model_classes.Game;

/**
 * Created by eseamons on 4/11/2017.
 */

public interface IGameDao {

    Set<GameDTO> getAll();

    Result addGame(GameDTO gameDTO);

    Result updateGame(GameDTO gameDTO);

    Result deleteGame(GameDTO gameDTO);

    Result clearAllGames();

    GameDTO selectByGameID(int gameID);

}
