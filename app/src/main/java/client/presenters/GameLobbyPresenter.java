package client.presenters;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import client.ClientFacade;
import client.interfaces.IGameLobbyPresenter;
import client.views.GameLobbyView;
import shared.model_classes.Player;

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
        Context context = GameLobbyView.getInstance().getApplicationContext();
        Toast.makeText(context,"Start Game!!",Toast.LENGTH_LONG);
        return true;
    }


}
