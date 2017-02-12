package client.interfaces;

import android.content.Context;

import shared.model_classes.Player;

public interface IGameLobbyPresenter {

    public boolean sendMessage();

    public boolean changeColor();

    public boolean beginGame();

    public Player[] getPlayers();

}
