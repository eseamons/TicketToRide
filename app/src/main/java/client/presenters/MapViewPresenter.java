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
        RoutesList routes = client.getRouteList();
        view.setDestinationBox(destinations);
        view.setPlayerCardViews(cards);
        view.drawRoutes(routes);
    }
}
