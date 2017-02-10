package client.views;

import android.widget.Button;

import java.util.List;

import client.interfaces.IGameListView;
import shared.model_classes.Game;

public class GameListView implements IGameListView {

    List<String> AvaliableGames;
    String GameName;
    int NumPlayers;
    Button JoinGame;
    Button CreateGame;


    @Override
    public String getGameName() {
        return null;
    }

    @Override
    public int getNumberOfPlayers() {
        return 0;
    }

    @Override
    public Game getSelectedGame() {
        return null;
    }


}
