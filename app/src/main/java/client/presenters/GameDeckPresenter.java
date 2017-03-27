package client.presenters;

import android.graphics.Color;
import android.os.Build;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.StateClasses.ClientState;
import client.interfaces.IGameDeckPresenter;
import client.views.GameDeckView;
import shared.CardColor;
import shared.model_classes.DestinationCard;

/**
 * Created by sirla on 3/3/2017.
 */

public class GameDeckPresenter implements IGameDeckPresenter,Observer {

    GameDeckView view;
    ClientFacade client = new ClientFacade();

    public GameDeckPresenter(GameDeckView gameDeckView){
        view = gameDeckView;
        ClientFacade client = new ClientFacade();
        client.setObserver(this);
        update(null,null);
    }

    @Override
    public void update(Observable observable, Object o) {
        setColors();
        setDestinations();
        setFaceUpCards();
        setTrainsRemaining();
    }

    public void setTrainsRemaining()
    {
        ClientFacade client = new ClientFacade();
        int rem = client.getRemainingTrains();
        view.setTrainsRemainingNum(rem);
    }
    public void setFaceUpCards()
    {
        ClientFacade client = new ClientFacade();
        List<CardColor> cards = client.getFaceUpCards();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setFaceUpCard0(cards.get(0));
            view.setFaceUpCard1(cards.get(1));
            view.setFaceUpCard2(cards.get(2));
            view.setFaceUpCard3(cards.get(3));
            view.setFaceUpCard4(cards.get(4));

        }
    }

    @Override
    public void setColors() {
        ClientFacade client = new ClientFacade();
        List<CardColor> playerCards = client.getPlayerCards();
        int redCards = 0;
        int orangeCards = 0;
        int yellowCards = 0;
        int greenCards = 0;
        int blueCards = 0;
        int purpleCards = 0;
        int whiteCards = 0;
        int blackCards = 0;
        int wildCards = 0;

        for (int i = 0; i < playerCards.size(); i++) {
            switch (playerCards.get(i)) {
                case RED:
                    redCards++;
                    break;
                case ORANGE:
                    orangeCards++;
                    break;
                case YELLOW:
                    yellowCards++;
                    break;
                case GREEN:
                    greenCards++;
                    break;
                case BLUE:
                    blueCards++;
                    break;
                case PURPLE:
                    purpleCards++;
                    break;
                case WHITE:
                    whiteCards++;
                    break;
                case BLACK:
                    blackCards++;
                    break;
                case WILD:
                    wildCards++;
                    break;
            }
        }
        view.setPurpleNum(purpleCards);
        view.setWhiteNum(whiteCards);
        view.setBlueNum(blueCards);
        view.setYellowNum(yellowCards);
        view.setOrangeNum(orangeCards);
        view.setBlackNum(blackCards);
        view.setRedNum(redCards);
        view.setGreenNum(greenCards);
        view.setWildNum(wildCards);

    }

    @Override
    public void setDestinations() {
        ClientFacade client = new ClientFacade();
        List<DestinationCard> destinations = client.getDestinationList();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < destinations.size(); i++)
        {
            DestinationCard dc = destinations.get(i);
            sb.append(dc.toString() + "\n");
        }
        view.setDestinationTickets(sb.toString());
    }

    public void DeckCardClicked()
    {
        ClientState state = client.getCurrentState();
        String text = state.DeckCardClicked();
        if(text != "")
        {
            printToast(text);
        }
    }


    public void FaceUp0Clicked()
    {
        templateFaceUpClick(0);
    }

    public void FaceUp1Clicked()
    {
        templateFaceUpClick(1);
    }

    public void FaceUp2Clicked()
    {
        templateFaceUpClick(2);
    }

    public void FaceUp3Clicked()
    {
        templateFaceUpClick(3);
    }

    public void FaceUp4Clicked()
    {
        templateFaceUpClick(4);
    }

    public void templateFaceUpClick(int n)
    {
        ClientState state = client.getCurrentState();
        String text = "";
        switch(n)
        {
            case 0: text = state.FaceUp0Clicked(); break;
            case 1: text = state.FaceUp1Clicked(); break;
            case 2: text = state.FaceUp2Clicked(); break;
            case 3: text = state.FaceUp3Clicked(); break;
            case 4: text = state.FaceUp4Clicked(); break;
        }
        if(text != "")
        {
            printToast(text);
        }
    }

    public void printToast(String text)
    {
        Toast.makeText(view.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

}