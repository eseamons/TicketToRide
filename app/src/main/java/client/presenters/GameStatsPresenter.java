package client.presenters;

import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.ClientModel;
import client.interfaces.IGameStatsPresenter;
import client.views.GameStatsView;
import shared.model_classes.Game;
import shared.model_classes.Player;

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
        switch(numberOfPlayers){
            case 5:
                Player player = game.getPlayerbyIndex(4);
                view.setPlayer5Name(player.getName());
                view.setPlayer5TrainCardsNum(player.getTrainCards().size());
                view.setPlayer5DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer5TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer5PointsNum(player.getPoints());
            case 4:
                player = game.getPlayerbyIndex(3);
                view.setPlayer4Name(player.getName());
                view.setPlayer4TrainCardsNum(player.getTrainCards().size());
                view.setPlayer4DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer4TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer4PointsNum(player.getPoints());
            case 3:
                player = game.getPlayerbyIndex(2);
                view.setPlayer3Name(player.getName());
                view.setPlayer3TrainCardsNum(player.getTrainCards().size());
                view.setPlayer3DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer3TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer3PointsNum(player.getPoints());
            case 2:
                player = game.getPlayerbyIndex(1);
                view.setPlayer2Name(player.getName());
                view.setPlayer2TrainCardsNum(player.getTrainCards().size());
                view.setPlayer2DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer2TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer2PointsNum(player.getPoints());
            case 1:
                player = game.getPlayerbyIndex(0);
                view.setPlayer1Name(player.getName());
                view.setPlayer1TrainCardsNum(player.getTrainCards().size());
                view.setPlayer1DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer1TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer1PointsNum(player.getPoints());
        }
    }
}
