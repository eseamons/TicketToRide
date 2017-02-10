package client.presenters;

import client.interfaces.IGameListPresenter;

import client.ClientFacade;
import client.views.GameListView;
import shared.model_classes.GameLobby;

public class GameListPresenter implements IGameListPresenter {


   // ClientFacade clientFacade = ClientFacade.getInstance();
    GameListView gameListView = new GameListView();

    @Override
    public Boolean joinGame() {

        GameLobby game = gameListView.getSelectedGame();
        int id = game.getID();
       // boolean retValue  = clientFacade.joinGame(id);
        //return retValue;
        return null;
    }

    @Override
    public Boolean createGame()
    {
        String gameName = gameListView.getGameName();
        int maxPlayers = gameListView.getNumberOfPlayers();
        //boolean retValue = clientFacade.createGame(gameName, maxPlayers);
        //return retValue;
        return null;
    }


}
