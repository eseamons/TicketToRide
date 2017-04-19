package client.presenters;

import android.graphics.Point;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.ClientFacade;
import client.Poller;
import client.StateClasses.ClientState;
import client.StateClasses.DrawDestinationCardState;
import client.interfaces.IMapPresenter;
import client.views.MapViewActivity;
import shared.CardColor;
import shared.model_classes.DestinationCard;
import shared.model_classes.Route;
import shared.model_classes.model_list_classes.RoutesList;

/**
 * Created by Michaels on 3/7/2017.
 */

public class MapViewPresenter implements Observer, IMapPresenter
{

    MapViewActivity view;
    ClientFacade client;

    public static boolean not_restoring_game = true;
    public MapViewPresenter(MapViewActivity act)
    {
        view = act;
        client = new ClientFacade();
        client.setObserver(this);
        Poller.getInstance().runGetGameCommands();
    }

    public void setDesiredToUseCardColor(CardColor color)
    {
        client.setToUseColor(color);
    }

    public void update(Observable o, Object arg)
    {
        setDestinationBox();
        setPlayerCardViews();
        drawRoutes();
        setDestinationCardVisiblity();
        isGameOver();

        view.update();

        view.setStupidButtonText(client.next_cmd);
    }

    boolean firstGameOverPoll = true;
    public void isGameOver()
    {

        if ( client.isGameOver() )
        {
            if(firstGameOverPoll)
            {
                client.performEndGameCalculations();
                view.goToGameOverView();
                firstGameOverPoll = false;
            }
            view.setGameOverMapView();
        }
    }

    public void setDestinationCardVisiblity()
    {
        boolean visible = client.shouldShowDestinationCard();
        view.setDestinationCardsAcceptanceVisibility(visible);
        if(visible)
        {
            boolean[] acceptance = client.getDestinationCardsAcceptance();
            DestinationCard[] dests = client.getChoosableDestinationCards();
            view.setDestinationCardText(dests, acceptance);
        }
    }

    public void drawRoutes()
    {
        RoutesList routes = client.getRoutesList();
        view.drawRoutes(routes);
    }

    public void setPlayerCardViews()
    {
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

    public void onTouch(Point click)
    {
        Route selected_route = client.getRouteByClick(click);
        view.setSelectedRoute(selected_route);
        boolean canClaim = client.canClaimRoute(selected_route);
        view.setCanClaimSelected(canClaim);
        update(null,null);
    }

    public void claimRouteButtonPressed()
    {
        Route selected = view.getSelectedRoute();
        if(selected == null  || selected.ownership != 0)
        {
            printToast("No route selected");
            return;
        }
        ClientState state = client.getCurrentState();
        String text = state.ClaimRouteButtonClicked(selected);
        if(text != "")
        {
            printToast(text);
        }

    }

    public void drawDestinationCardButtonPressed()
    {
        ClientState state = client.getCurrentState();
        String text = state.DrawDestinationCardButtonClicked();
        if(text != "")
        {
            printToast(text);
        }
        // piggyback command
        setDestinationCardVisiblity();
    }

    public void destinationCard1ButtonClicked()
    {
        templateDestinationCardButtonClicked(1);
    }

    public void destinationCard2ButtonClicked()
    {
        templateDestinationCardButtonClicked(2);
    }

    public void destinationCard3ButtonClicked()
    {
        templateDestinationCardButtonClicked(3);
    }

    public void templateDestinationCardButtonClicked(int n)
    {
        ClientState state = client.getCurrentState();
        switch(n)
        {
            case 1: state.DestinationCard1Clicked(); break;
            case 2: state.DestinationCard2Clicked(); break;
            case 3: state.DestinationCard3Clicked(); break;
        }
        setDestinationCardVisiblity();
    }

    public void destinationConfirmButtonClicked()
    {
        ClientState state = client.getCurrentState();
        String text = state.DestinationConfirmedClicked();
        if(text != "")
        {
            printToast(text);
        }
        setDestinationCardVisiblity();
    }

    public void printToast(String text)
    {
        Toast.makeText(view.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
