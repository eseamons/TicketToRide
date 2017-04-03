package client.presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.interfaces.IGameListPresenter;

import client.ClientFacade;
import client.views.GameListView;
import shared.model_classes.Game;
import shared.model_classes.GameLobby;

public class GameListPresenter implements IGameListPresenter,Observer {


    private GameListPresenter()
    {
        clientFacade.setObserver(this);
    }

    private static GameListPresenter instance = new GameListPresenter();
    ClientFacade clientFacade = new ClientFacade();

    public static GameListPresenter getInstance()
    {
        return instance;
    }

    @Override
    public Boolean joinGame() {
        GameListView gameListView = GameListView.getInstance();
        GameLobby game = gameListView.getSelectedGame();

        if(game == null)
        {return false;}

        if(game.getPlayers().size() == game.getMaxPlayers())
        {return false;}

        int id = game.getID();
        GameLobby gameLobby  = clientFacade.joinGame(id);

        if (gameLobby == null)
        {return false;}

        return true;
    }

    @Override
    public Boolean createGame()
    {
        GameListView gameListView = GameListView.getInstance();
        String gameName = gameListView.getGameName();
        int maxPlayers = gameListView.getNumberOfPlayers();

        if(maxPlayers < 2 ||maxPlayers > 5 ||gameName == null||gameName.equals("")) {
            return false;
        }

        boolean createGameSuccessful = clientFacade.createGameLobby(gameName, maxPlayers);

        if (createGameSuccessful == false) {
            return false;
        }

        return true;
    }

    @Override
    public void getServerGames() {
         clientFacade.getServerGamesList("0");
    }

    @Override
    public List<GameLobby> getClientGames() {

        List<GameLobby> games = clientFacade.getClientGamesList();
        return games;
    }


    @Override
    public void update(Observable observable, Object o) {
        List<GameLobby> gamesList = getClientGames();
        GameListView gameListView = GameListView.getInstance();
        gameListView.setAvailableGames(gamesList);
    }
}
