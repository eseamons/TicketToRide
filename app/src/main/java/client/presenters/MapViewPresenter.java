package client.presenters;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.views.MapViewActivity;
import shared.CardColor;
import shared.model_classes.DestinationCard;
import shared.model_classes.model_list_classes.RoutesList;

/**
 * Created by Michaels on 3/7/2017.
 */

public class MapViewPresenter implements Observer {

    MapViewActivity view;

    public MapViewPresenter(MapViewActivity act)
    {
        view = act;
        ClientFacade client = new ClientFacade();
        client.setObserver(this);
    }
    public void update(Observable o, Object arg)
    {
        ClientFacade client = new ClientFacade();
        List<CardColor> cards = client.getPlayerCards();
        List<DestinationCard> destinations = client.getDestinationList();
        RoutesList routes = client.getRoutesList();
        setDestinationBox(destinations);
        setPlayerCardViews(cards);
        view.drawRoutes(routes);
        view.setStupidButtonText(client.next_cmd);
    }

    public void setPlayerCardViews(List<CardColor> playerCards)
    {
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


        view.setPurpleNumText("" + purpleCards);
        view.setWhiteNumText("" + whiteCards);
        view.setBlueNumText("" + blueCards);
        view.setYellowNumText("" + yellowCards);
        view.setOrangeNumText("" + orangeCards);
        view.setBlackNumText("" + blackCards);
        view.setRedNumText("" + redCards);
        view.setGreenNumText("" + greenCards);
        view.setWildNumText("" + wildCards);

    }

    public void setDestinationBox(List<DestinationCard> destinationCards)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < destinationCards.size(); i++)
        {
            DestinationCard dc = destinationCards.get(i);
            sb.append(dc.toString() + "\n");
        }
        view.setDestViewText(sb.toString());
        //view.setDestViewText("HI\nNO\nLANCE\nPLEASE\nSAVEme\nNO316ENGLISHPLS");
    }

    public void stupidButtonPressed()
    {
        ClientFacade client = new ClientFacade();
        client.runAnimation();

    }
}
