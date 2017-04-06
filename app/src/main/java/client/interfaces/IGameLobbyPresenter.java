package client.interfaces;

import shared.Result;
import shared.model_classes.Player;

public interface IGameLobbyPresenter {

    public boolean sendMessage();

    public boolean changeColor();

    public Result beginGame();

    public Player[] getPlayers();

}
