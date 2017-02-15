package client.interfaces;

import java.util.List;

import shared.model_classes.GameLobby;

/**
 * Created by rebeccaredd on 2/9/17.
 */

public interface IGameListPresenter {

    Boolean joinGame();

    Boolean createGame();

    void getAvaliableGames();
}
