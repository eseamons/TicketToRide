package client.presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.interfaces.IGameStatsPresenter;
import client.views.GameStatsView;
import shared.model_classes.Player;

/**
 * Created by sirla on 3/3/2017.
 */

public class GameStatsPresenter implements IGameStatsPresenter,Observer{

    GameStatsView view;
    List<Player> players;

    public GameStatsPresenter(GameStatsView gameStatsView){
        view = gameStatsView;
        ClientFacade client = new ClientFacade();
        client.setObserver(this);
        players =  client.getGamePlayers();
        update(null,null);
    }

    public void sendMessage(String message) {
        ClientFacade facade = new ClientFacade();
        facade.sendMessage(message);
        view.setChatMessage(message);
    }

    @Override
    public void update(Observable observable, Object o) {
        int numberOfPlayers = players.size();
        ClientFacade client = new ClientFacade();
        switch(numberOfPlayers){
            case 5:
                Player player = players.get(4);
                view.setPlayer5Name(player.getName());
                view.setPlayer5TrainCardsNum(player.getTrainCards().size());
                view.setPlayer5DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer5TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer5PointsNum(player.getPoints());
                view.setPlayer5TurnMarker(client.getPlayerTurnItIs() == 4);
            case 4:
                player = players.get(3);
                view.setPlayer4Name(player.getName());
                view.setPlayer4TrainCardsNum(player.getTrainCards().size());
                view.setPlayer4DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer4TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer4PointsNum(player.getPoints());
                view.setPlayer4TurnMarker(client.getPlayerTurnItIs() == 3);
            case 3:
                player = players.get(2);
                view.setPlayer3Name(player.getName());
                view.setPlayer3TrainCardsNum(player.getTrainCards().size());
                view.setPlayer3DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer3TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer3PointsNum(player.getPoints());
                view.setPlayer3TurnMarker(client.getPlayerTurnItIs() == 2);
            case 2:
                player = players.get(1);
                view.setPlayer2Name(player.getName());
                view.setPlayer2TrainCardsNum(player.getTrainCards().size());
                view.setPlayer2DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer2TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer2PointsNum(player.getPoints());
                view.setPlayer2TurnMarker(client.getPlayerTurnItIs() == 1);
            case 1:
                player = players.get(0);
                view.setPlayer1Name(player.getName());
                view.setPlayer1TrainCardsNum(player.getTrainCards().size());
                view.setPlayer1DestinationCardsNum(player.getDestinationCards().size());
                view.setPlayer1TrainsRemainingNum(player.getTrainsRemaining());
                view.setPlayer1PointsNum(player.getPoints());
                view.setPlayer1TurnMarker(client.getPlayerTurnItIs() == 0);
        }

        ArrayList<String> chat = client.getChat();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < chat.size(); i++)
        {
            sb.append(chat.get(i));
            sb.append("\n\n");
        }
        view.setChatMessage(sb.toString());

    }
}
