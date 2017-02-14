package client.presenters;

import java.util.ArrayList;
import java.util.List;

import client.interfaces.IGameListPresenter;

import client.ClientFacade;
import client.views.GameListView;
import shared.model_classes.GameLobby;

public class GameListPresenter implements IGameListPresenter {


    ClientFacade clientFacade = new ClientFacade();

    @Override
    public Boolean joinGame() {
        GameListView gameListView = new GameListView();
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
        GameListView gameListView = new GameListView();
        String gameName = gameListView.getGameName();
        int maxPlayers = gameListView.getNumberOfPlayers();
        GameLobby gameLobby = clientFacade.createGame(gameName, maxPlayers);

        if (gameLobby == null)
        {return false;}

        return true;
    }

    @Override
    public List<GameLobby> getAvaliableGames() {


        //List<GameLobby> gameLobbies =  clientFacade.getServerGamesList("0");
        //return gameLobbies;
        GameLobby g = new GameLobby();
        g.setName("becca's game");
        g.setMax_players(3);

        ArrayList<GameLobby> games = new ArrayList<>();
        games.add(g);

        return games;
    }


}
