package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.List;

import shared.CardColor;
import shared.model_classes.Player;
import shared.model_classes.Route;

/**
 * Created by Maynson on 3/1/2017.
 */

public class RoutesList {
    private List<Route> routeList = new ArrayList<>();

    public RoutesList()
    {
        //TODO: Add every route individually, WILL DO THIS OK!!
        routeList.add(new Route("Vancouver", "Calgary", CardColor.WILD, 3));
        routeList.add(new Route("Vancouver", "Seattle", CardColor.WILD, 1));
        routeList.add(new Route("Vancouver", "Seattle", CardColor.WILD, 1));
        routeList.add(new Route("Calgary", "Seattle", CardColor.WILD, 4));
        routeList.add(new Route("Helena", "Seattle", CardColor.YELLOW, 6));
        routeList.add(new Route("Calgary", "Helena", CardColor.WILD, 4));
        routeList.add(new Route("Portland", "Salt Lake City", CardColor.BLUE, 6));
        routeList.add(new Route("Portland", "San Francisco", CardColor.GREEN, 5));
        routeList.add(new Route("Portland", "San Francisco", CardColor.PURPLE, 5));
        routeList.add(new Route("Salt Lake City", "San Francisco", CardColor.ORANGE, 5));
        routeList.add(new Route("Salt Lake City", "San Francisco", CardColor.WHITE, 5));
        routeList.add(new Route("Los Angeles", "San Francisco", CardColor.PURPLE, 3));
        routeList.add(new Route("Los Angeles", "San Francisco", CardColor.YELLOW, 3));
        routeList.add(new Route("Los Angeles", "Las Vegas", CardColor.WILD, 2));
        routeList.add(new Route("Los Angeles", "Phoenix", CardColor.WILD, 3));
        routeList.add(new Route("Los Angeles", "El Paso", CardColor.BLACK, 6));
        routeList.add(new Route("Salt Lake City", "Las Vegas", CardColor.ORANGE, 3));
        routeList.add(new Route("Calgary", "Winnipeg", CardColor.WHITE, 6));
        routeList.add(new Route("Helena", "Winnipeg", CardColor.BLUE, 4));
        routeList.add(new Route("Helena", "Duluth", CardColor.ORANGE, 6));
        routeList.add(new Route("Helena", "Omaha", CardColor.RED, 5));
        routeList.add(new Route("Helena", "Denver", CardColor.GREEN, 4));
        routeList.add(new Route("Helena", "Salt Lake City", CardColor.PURPLE, 3));
        routeList.add(new Route("Denver", "Salt Lake City", CardColor.RED, 3));
        routeList.add(new Route("Denver", "Salt Lake City", CardColor.YELLOW, 3));
        routeList.add(new Route("Phoenix", "Denver", CardColor.WHITE, 5));
        routeList.add(new Route("Phoenix", "Santa Fe", CardColor.WILD, 3));
        routeList.add(new Route("Phoenix", "El Paso", CardColor.WILD, 3));
        routeList.add(new Route("Denver", "Santa Fe", CardColor.WILD, 2));
        routeList.add(new Route("Denver", "Omaha", CardColor.PURPLE, 4));
        routeList.add(new Route("Denver", "Kansas City", CardColor.BLACK, 4));
        routeList.add(new Route("Denver", "Kansas City", CardColor.ORANGE, 4));
        routeList.add(new Route("Denver", "Oklahoma City", CardColor.RED, 4));
        routeList.add(new Route("Santa Fe", "Oklahoma City", CardColor.BLUE, 3));
        routeList.add(new Route("Santa Fe", "El Paso", CardColor.WILD, 2));
        routeList.add(new Route("Oklahoma City", "El Paso", CardColor.YELLOW, 5));
        routeList.add(new Route("Dallas", "El Paso", CardColor.RED, 4));
        routeList.add(new Route("Houston", "El Paso", CardColor.GREEN, 6));
        routeList.add(new Route("Winnipeg", "Sault St Marie", CardColor.WILD, 6));
        routeList.add(new Route("Winnipeg", "Duluth", CardColor.BLACK, 4));
        routeList.add(new Route("Omaha", "Duluth", CardColor.WILD, 2));
        routeList.add(new Route("Omaha", "Duluth", CardColor.WILD, 2));
        routeList.add(new Route("Sault St Marie", "Duluth", CardColor.WILD, 3));
        routeList.add(new Route("Toronto", "Duluth", CardColor.PURPLE, 6));
        routeList.add(new Route("Chicago", "Duluth", CardColor.RED, 3));
        routeList.add(new Route("Omaha", "Chicago", CardColor.BLUE, 4));
        routeList.add(new Route("Omaha", "Kansas City", CardColor.WILD, 1));
        routeList.add(new Route("Omaha", "Kansas City", CardColor.WILD, 1));
        routeList.add(new Route("Saint Louis", "Kansas City", CardColor.BLUE, 2));
        routeList.add(new Route("Saint Louis", "Kansas City", CardColor.PURPLE, 2));
        routeList.add(new Route("Oklahoma City", "Kansas City", CardColor.WILD, 2));
        routeList.add(new Route("Oklahoma City", "Kansas City", CardColor.WILD, 2));
        routeList.add(new Route("Oklahoma City", "Little Rock", CardColor.WILD, 2));
        routeList.add(new Route("Oklahoma City", "Dallas", CardColor.WILD, 2));
        routeList.add(new Route("Oklahoma City", "Dallas", CardColor.WILD, 2));
        routeList.add(new Route("Dallas", "Little Rock", CardColor.WILD, 2));
        routeList.add(new Route("Dallas", "Houston", CardColor.WILD, 1));
        routeList.add(new Route("Dallas", "Houston", CardColor.WILD, 1));
        routeList.add(new Route("New Orleans", "Houston", CardColor.WILD, 2));
        routeList.add(new Route("Chicago", "Toronto", CardColor.WHITE, 4));
        routeList.add(new Route("Chicago", "Pittsburgh", CardColor.ORANGE, 3));
        routeList.add(new Route("Chicago", "Pittsburgh", CardColor.BLACK, 3));
        routeList.add(new Route("Saint Louis", "Chicago", CardColor.GREEN, 2));
        routeList.add(new Route("Saint Louis", "Chicago", CardColor.WHITE, 2));
        routeList.add(new Route("Saint Louis", "Pittsburgh", CardColor.GREEN, 5));
        routeList.add(new Route("Saint Louis", "Nashville", CardColor.WILD, 2));
        routeList.add(new Route("Saint Louis", "Little Rock", CardColor.WILD, 2));
        routeList.add(new Route("Nashville", "Little Rock", CardColor.WHITE, 3));
        routeList.add(new Route("New Orleans", "Little Rock", CardColor.GREEN, 3));
        routeList.add(new Route("Nashville", "Pittsburgh", CardColor.YELLOW, 4));
        routeList.add(new Route("Nashville", "Raleigh", CardColor.BLACK, 3));
        routeList.add(new Route("Nashville", "Atlanta", CardColor.WILD, 1));
        routeList.add(new Route("New Orleans", "Atlanta", CardColor.YELLOW, 4));
        routeList.add(new Route("New Orleans", "Atlanta", CardColor.ORANGE, 4));
        routeList.add(new Route("New Orleans", "Miami", CardColor.RED, 4));
        routeList.add(new Route("Atlanta", "Raleigh", CardColor.WILD, 2));
        routeList.add(new Route("Atlanta", "Raleigh", CardColor.WILD, 2));
        routeList.add(new Route("Atlanta", "Charleston", CardColor.WILD, 2));
        routeList.add(new Route("Atlanta", "Miami", CardColor.BLUE, 5));
        routeList.add(new Route("Charleston", "Miami", CardColor.PURPLE, 4));
        routeList.add(new Route("Charleston", "Raleigh", CardColor.WILD, 2));
        routeList.add(new Route("Raleigh", "Washington", CardColor.WILD, 2));
        routeList.add(new Route("Raleigh", "Washington", CardColor.WILD, 2));
        routeList.add(new Route("Raleigh", "Pittsburgh", CardColor.WILD, 2));
        routeList.add(new Route("Pittsburgh", "Washington", CardColor.WILD, 2));
        routeList.add(new Route("Pittsburgh", "New York", CardColor.WHITE, 2));
        routeList.add(new Route("Pittsburgh", "New York", CardColor.GREEN, 2));
        routeList.add(new Route("Pittsburgh", "Toronto", CardColor.WILD, 2));
        routeList.add(new Route("Toronto", "Sault St Marie", CardColor.WILD, 2));
        routeList.add(new Route("Toronto", "Montreal", CardColor.WILD, 3));
        routeList.add(new Route("Montreal", "Sault St Marie", CardColor.BLACK, 5));
        routeList.add(new Route("Montreal", "New York", CardColor.BLUE, 3));
        routeList.add(new Route("Montreal", "Boston", CardColor.WILD, 2));
        routeList.add(new Route("Montreal", "Boston", CardColor.WILD, 2));
        routeList.add(new Route("Boston", "New York", CardColor.RED, 2));
        routeList.add(new Route("Boston", "New York", CardColor.YELLOW, 2));
        routeList.add(new Route("Washington", "New York", CardColor.BLACK, 2));
        routeList.add(new Route("Washington", "New York", CardColor.ORANGE, 2));

    }

    public Route getRoute(String city1, String city2, CardColor color)
    {
        Route currentRoute = routeList.get(0);
        for (int i = 0; i < routeList.size(); i++)
        {
            currentRoute = routeList.get(i);
            if (city1 == currentRoute.city1 || city1 == currentRoute.city2)         //if city1 is one of the cities
                if (city2 == currentRoute.city1 || city2 == currentRoute.city2)     //if city2 is also one of the cities
                    if (color == currentRoute.color)                                //if route is the color
                    {
                        //if found route, exit
                        i = routeList.size();
                    }
        }
        return currentRoute;
    }

    public Route getRoute(int index)
    {
        return routeList.get(index);
    }

    public List<Route> getClaimableRoutes(Player p)
    {
        List<CardColor> playerCards = p.getTrainCards();
        int redCards = 0;
        int orangeCards = 0;
        int yellowCards = 0;
        int greenCards = 0;
        int blueCards = 0;
        int purpleCards = 0;
        int whiteCards = 0;
        int blackCards = 0;
        int wildCards = 0;

        for (int i = 0; i < playerCards.size(); i++)
        {
            switch(playerCards.get(i))
            {
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

        Route currentRoute;
        List<Route> claimableRoutes = new ArrayList<>();
        for(int i = 0; i < routeList.size(); i++)
        {
            currentRoute = routeList.get(i);
            // if route not owned
            if (currentRoute.ownership == 0)
            {
                switch(currentRoute.color)
                {
                    case RED:
                        if ((redCards + wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);
                        break;
                    case ORANGE:
                        if ((orangeCards + wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);                        break;
                    case YELLOW:
                        if ((yellowCards + wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);                        break;
                    case GREEN:
                        if ((greenCards + wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);                        break;
                    case BLUE:
                        if ((blueCards + wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);                        break;
                    case PURPLE:
                        if ((purpleCards + wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);                        break;
                    case WHITE:
                        if ((whiteCards + wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);                        break;
                    case BLACK:
                        if ((blackCards + wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);                        break;
                    case WILD:
                        if ((redCards+orangeCards+yellowCards+greenCards+blueCards+purpleCards+whiteCards+blackCards+wildCards) >= currentRoute.length)
                            claimableRoutes.add(currentRoute);                        break;
                }
            }
        }

        return claimableRoutes;

    }



}
