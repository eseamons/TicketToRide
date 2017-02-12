package client.presenters;

import client.interfaces.IGameLobbyPresenter;

public class GameLobbyPresenter implements IGameLobbyPresenter{

    private static GameLobbyPresenter instance;

    public static GameLobbyPresenter getInstance()
    {
        if (instance == null)
            instance = new GameLobbyPresenter();
        return instance;
    }


    public GameLobbyPresenter()
    {

    }

    public boolean sendMessage()
    {

        return true;
    }

    public boolean changeColor()
    {

        return true;
    }

    public boolean beginGame()
    {

        return true;
    }


}
