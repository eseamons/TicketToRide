package client.interfaces;

import shared.model_classes.GameLobby;

/**
 * Created by rebeccaredd on 2/9/17.
 */

public interface IGameListView {

    String getGameName();

    int getNumberOfPlayers();

    GameLobby getSelectedGame();


}
