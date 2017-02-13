package client.presenters;

import java.util.ArrayList;
import java.util.List;

import client.interfaces.IGameListPresenter;

import client.ClientFacade;
import client.views.GameListView;
import shared.model_classes.GameLobby;

public class GameListPresenter implements IGameListPresenter {


    ClientFacade clientFacade = new ClientFacade();
    GameListView gameListView = new GameListView();

    @Override
    public Boolean joinGame() {

        GameLobby game = gameListView.getSelectedGame();
        int id = game.getID();
        GameLobby gameLobby  = clientFacade.joinGame(id);

        if (gameLobby == null)
        {return false;}

        return true;
    }

    @Override
    public Boolean createGame()
    {
        String gameName = gameListView.getGameName();
        int maxPlayers = gameListView.getNumberOfPlayers();
        GameLobby gameLobby = clientFacade.createGame(gameName, maxPlayers);

        if (gameLobby == null)
        {return false;}

        return true;
    }

    @Override
    public List<GameLobby> getAvaliableGames() {
        //TODO: clientfacade should be getting the authorization. presenter doesn't have access to this.
        return clientFacade.getServerGamesList("0");
    }


}
