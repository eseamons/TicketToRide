package client.presenters;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.interfaces.IMapPresenter;
import client.views.MapViewActivity;
import shared.CardColor;
import shared.model_classes.DestinationCard;
import shared.model_classes.model_list_classes.RoutesList;

/**
 * Created by Michaels on 3/7/2017.
 */

public class MapViewPresenter implements Observer, IMapPresenter
{

    MapViewActivity view;

    public MapViewPresenter(MapViewActivity act)
    {
        view = act;
        ClientFacade client = new ClientFacade();
        client.setObserver(this);

    }
    public void update(Observable o, Object arg)
    {
        setDestinationBox();
        setPlayerCardViews();
        drawRoutes();

        ClientFacade client = new ClientFacade();
        view.setStupidButtonText(client.next_cmd);
    }


    public void drawRoutes()
    {
        ClientFacade client = new ClientFacade();
        RoutesList routes = client.getRoutesList();
        view.drawRoutes(routes);
    }



    public void setPlayerCardViews()
    {
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

    public void setDestinationBox()
    {
        ClientFacade client = new ClientFacade();
        client.setThis_player();
        List<DestinationCard> destinationCards = client.getDestinationList();

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
