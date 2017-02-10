package client.interfaces;

import shared.model_classes.Game;

/**
 * Created by rebeccaredd on 2/9/17.
 */

public interface IGameListView {

    public String getGameName();

    public int getNumberOfPlayers();

    public Game getSelectedGame();


}
