package client.presenters;

import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.ClientModel;
import client.interfaces.IGameStatsPresenter;
import client.views.GameStatsView;
import shared.model_classes.Game;

/**
 * Created by sirla on 3/3/2017.
 */

public class GameStatsPresenter implements IGameStatsPresenter,Observer{

    GameStatsView view;
    Game game;

    public GameStatsPresenter(GameStatsView gameStatsView){
        view = gameStatsView;
        game = ClientModel.getInstance().getCurrent_game();
        ClientFacade client = new ClientFacade();
        client.setObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        int numberOfPlayers = game.getPlayers().size();
        //TODO: fix design, trains remaining, and turn marker
        switch(numberOfPlayers){
            case 5: view.setPlayer5Name(game.getPlayerbyIndex(4).getAccount().getUsername());
                view.setPlayer5TrainCardsNum(game.getPlayerbyIndex(4).getTrainCards().size());
                view.setPlayer5DestinationCardsNum(game.getPlayerbyIndex(4).getDestinationCards().size());
                view.setPlayer5TrainsRemainingNum(0);
                view.setPlayer5PointsNum(game.getPlayerbyIndex(4).getPoints());
            case 4: view.setPlayer4Name(game.getPlayerbyIndex(3).getAccount().getUsername());
                view.setPlayer4TrainCardsNum(game.getPlayerbyIndex(3).getTrainCards().size());
                view.setPlayer4DestinationCardsNum(game.getPlayerbyIndex(3).getDestinationCards().size());
                view.setPlayer4TrainsRemainingNum(0);
                view.setPlayer4PointsNum(game.getPlayerbyIndex(3).getPoints());
            case 3: view.setPlayer3Name(game.getPlayerbyIndex(2).getAccount().getUsername());
                view.setPlayer3TrainCardsNum(game.getPlayerbyIndex(2).getTrainCards().size());
                view.setPlayer3DestinationCardsNum(game.getPlayerbyIndex(2).getDestinationCards().size());
                view.setPlayer3TrainsRemainingNum(0);
                view.setPlayer3PointsNum(game.getPlayerbyIndex(2).getPoints());
            case 2: view.setPlayer2Name(game.getPlayerbyIndex(1).getAccount().getUsername());
                view.setPlayer2TrainCardsNum(game.getPlayerbyIndex(1).getTrainCards().size());
                view.setPlayer2DestinationCardsNum(game.getPlayerbyIndex(1).getDestinationCards().size());
                view.setPlayer2TrainsRemainingNum(0);
                view.setPlayer2PointsNum(game.getPlayerbyIndex(1).getPoints());
            case 1: view.setPlayer1Name(game.getPlayerbyIndex(0).getAccount().getUsername());
                view.setPlayer1TrainCardsNum(game.getPlayerbyIndex(0).getTrainCards().size());
                view.setPlayer1DestinationCardsNum(game.getPlayerbyIndex(0).getDestinationCards().size());
                view.setPlayer1TrainsRemainingNum(0);
                view.setPlayer1PointsNum(game.getPlayerbyIndex(0).getPoints());
        }
    }
}
