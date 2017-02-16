package client.presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.interfaces.IGameListPresenter;

import client.ClientFacade;
import client.views.GameListView;
import shared.model_classes.GameLobby;

public class GameListPresenter implements IGameListPresenter,Observer {


    private GameListPresenter()
    {
        clientFacade.setGameListPrestenter(this);
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
        if(game == null){return false;}
        int id = game.getID();
        GameLobby gameLobby  = clientFacade.joinGame(id);

        if (gameLobby == null) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean createGame()
    {
        GameListView gameListView = GameListView.getInstance();
        String gameName = gameListView.getGameName();
        int maxPlayers = gameListView.getNumberOfPlayers();

        if(maxPlayers <0 ||maxPlayers > 5 ||gameName == null||gameName.equals("")) {
            return false;
        }

        boolean createGameSuccessful = clientFacade.createGame(gameName, maxPlayers);

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
        if(games == null)
        {
            games = new ArrayList<>();
        }

        return games;
    }


    @Override
    public void update(Observable observable, Object o) {
        List<GameLobby> gamesList = getClientGames();
        GameListView gameListView = GameListView.getInstance();
        gameListView.setAvaliableGames(gamesList);
        gameListView.getAdapter().setNewItems(gamesList);
        //gameListView.populateGamesList();
    }
}
