package client.presenters;

import android.content.Context;
import android.widget.Toast;

import client.ClientFacade;
import client.interfaces.IGameLobbyPresenter;
import client.views.GameLobbyView;

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
