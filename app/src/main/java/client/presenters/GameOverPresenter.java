package client.presenters;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.interfaces.IGameOverPresenter;
import client.views.GameOverView;
import client.views.GameStatsView;
import client.views.MapViewActivity;
import shared.model_classes.Player;

/**
 * Created by Maynson on 3/24/2017.
 */

public class GameOverPresenter implements IGameOverPresenter, Observer {

    private GameOverView view;
    private boolean updated = false;

    private static GameOverPresenter instance;

    public static GameOverPresenter getInstance()
    {
        if (instance == null)
            instance = new GameOverPresenter();
        return instance;
    }

    @Override
    public void Quit() {

    }

    @Override
    public void ToFinishedMap() {

    }

    @Override
    public void update(Observable o, Object arg) {

        if (!updated)
        {
            view = GameOverView.getInstance();

            ClientFacade cf = new ClientFacade();
            Player thisPlayer = cf.getThisPlayer();
            List<Player> players = cf.getPlayers();

            if (players.size() < 5)
                view.player5Invis();
            if (players.size() < 4)
                view.player4Invis();
            if (players.size() < 3)
                view.player3Invis();

            int playerCount = players.size();
            List<Player> playersCopy = new ArrayList<Player>(players);
            List<Player> sortedPlayers = new ArrayList<Player>();
            int nextHighest;

            for (int i = 0; i < players.size(); i++) {
                nextHighest = findNextHighest(playersCopy);
                sortedPlayers.add(playersCopy.get(nextHighest));
                playersCopy.remove(nextHighest);
            }

            for (int i = 0; i < sortedPlayers.size(); i++)
            {
                switch(i)
                {
                    case 0:
                        Player first = sortedPlayers.get(i);
                        view.setPlayer1Name(first.getName());
                        view.setPlayer1Score(first.getPoints());

                        if (first.equals(thisPlayer))
                            view.setRibbon1();
                        break;
                    case 1:
                        Player second = sortedPlayers.get(i);
                        view.setPlayer2Name(second.getName());
                        view.setPlayer2Score(second.getPoints());

                        if (second.equals(thisPlayer))
                            view.setRibbon2();
                        break;
                    case 2:
                        Player third = sortedPlayers.get(i);
                        view.setPlayer3Name(third.getName());
                        view.setPlayer3Score(third.getPoints());

                        if (third.equals(thisPlayer))
                            view.setRibbon3();
                        break;
                    case 3:
                        Player fourth = sortedPlayers.get(i);
                        view.setPlayer4Name(fourth.getName());
                        view.setPlayer4Score(fourth.getPoints());

                        if (fourth.equals(thisPlayer))
                            view.setRibbon4();
                        break;
                    case 4:
                        Player fifth = sortedPlayers.get(i);
                        view.setPlayer5Name(fifth.getName());
                        view.setPlayer5Score(fifth.getPoints());

                        if (fifth.equals(thisPlayer))
                            view.setRibbon5();
                        break;
                }

            }

            if (sortedPlayers.get(0).equals(thisPlayer))
                view.setVictory();
            else
                view.setDefeat();



        }
        updated = true;
    }

    public int findNextHighest(List<Player> players)
    {
        int high = 0;
        int highIndex = -1;
        Player currentPlayer;
        for (int i = 0; i < players.size(); i++)
        {
            currentPlayer = players.get(i);
            if (currentPlayer.getPoints() > high)
            {
                high = currentPlayer.getPoints();
                highIndex = i;
            }
        }

        return highIndex;

    }
}
