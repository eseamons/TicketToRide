package client.interfaces;

import java.util.List;

import shared.model_classes.Game;
import shared.model_classes.GameLobby;

/**
 * Created by rebeccaredd on 2/9/17.
 */

public interface IGameListView {

    String getGameName();

    int getNumberOfPlayers();

    GameLobby getSelectedGame();

    void setSelectedGame(GameLobby gameLobby);

    void setAvailableGames(List<GameLobby> games);
}
