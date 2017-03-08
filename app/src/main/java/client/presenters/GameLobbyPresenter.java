package client.presenters;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.interfaces.IGameLobbyPresenter;
import client.views.GameLobbyView;
import shared.model_classes.Player;

public class GameLobbyPresenter implements IGameLobbyPresenter,Observer{

    private static GameLobbyPresenter instance;

    public static GameLobbyPresenter getInstance()
    {
        if (instance == null)
            instance = new GameLobbyPresenter();
        return instance;
    }

    @Override
    public void update(Observable observable, Object o) {

        GameLobbyView.getInstance().update(observable, o);
    }

    public GameLobbyPresenter()
    {
        ClientFacade clientFacade = new ClientFacade();
        clientFacade.setObserver(this);
    }

    public Player[] getPlayers()
    {
        //TODO:
        //ClientFacade cf = ClientFacade.getInstance();
        ClientFacade cf = new ClientFacade();

        Player[] playerArray = new Player[0];

        //TODO:
        //playerArray = cf.getPlayers();

        return playerArray;
    }

    public boolean sendMessage()
    {
        //TODO:
        //ClientFacade cf = ClientFacade.getInstance();

        ClientFacade cf = new ClientFacade();

        String message = "";

        //TODO:
        //message += cf.getPlayerName;

        message += GameLobbyView.getInstance().getMessage();

        return cf.sendMessage(message);
    }

    public boolean changeColor()
    {
        return true;
    }

    public boolean beginGame()
    {
        ClientFacade client = new ClientFacade();
        return client.beginGame();
    }

}
