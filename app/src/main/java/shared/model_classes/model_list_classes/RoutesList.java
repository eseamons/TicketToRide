package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.CardColor;
import shared.model_classes.Route;

/**
 * Created by Maynson on 3/1/2017.
 */

public class RoutesList {
    private List<Route> availableRouteList = new ArrayList<>();
    private Map<String,Route> playersClaimedRoutes = new HashMap<>();
    
    public RoutesList()
    {
        //TODO: Add every route individually, WILL DO THIS OK!!
        availableRouteList.add(new Route("Vancouver", "Calgary", CardColor.WILD, 3));
        availableRouteList.add(new Route("Vancouver", "Seattle", CardColor.WILD, 1));
        availableRouteList.add(new Route("Vancouver", "Seattle", CardColor.WILD, 1));
        availableRouteList.add(new Route("Calgary", "Seattle", CardColor.WILD, 4));
        availableRouteList.add(new Route("Helena", "Seattle", CardColor.YELLOW, 6));
        availableRouteList.add(new Route("Calgary", "Helena", CardColor.WILD, 4));
        availableRouteList.add(new Route("Portland", "Salt Lake City", CardColor.BLUE, 6));
        availableRouteList.add(new Route("Portland", "San Francisco", CardColor.GREEN, 5));
        availableRouteList.add(new Route("Portland", "San Francisco", CardColor.PURPLE, 5));
        availableRouteList.add(new Route("Salt Lake City", "San Francisco", CardColor.ORANGE, 5));
        availableRouteList.add(new Route("Salt Lake City", "San Francisco", CardColor.WHITE, 5));
        availableRouteList.add(new Route("Los Angeles", "San Francisco", CardColor.PURPLE, 3));
        availableRouteList.add(new Route("Los Angeles", "San Francisco", CardColor.YELLOW, 3));
        availableRouteList.add(new Route("Los Angeles", "Las Vegas", CardColor.WILD, 2));
        availableRouteList.add(new Route("Los Angeles", "Phoenix", CardColor.WILD, 3));
        availableRouteList.add(new Route("Los Angeles", "El Paso", CardColor.BLACK, 6));
        availableRouteList.add(new Route("Salt Lake City", "Las Vegas", CardColor.ORANGE, 3));
        availableRouteList.add(new Route("Calgary", "Winnipeg", CardColor.WHITE, 6));
        availableRouteList.add(new Route("Helena", "Winnipeg", CardColor.BLUE, 4));
        availableRouteList.add(new Route("Helena", "Duluth", CardColor.ORANGE, 6));
        availableRouteList.add(new Route("Helena", "Omaha", CardColor.RED, 5));
        availableRouteList.add(new Route("Helena", "Denver", CardColor.GREEN, 4));
        availableRouteList.add(new Route("Helena", "Salt Lake City", CardColor.PURPLE, 3));
        availableRouteList.add(new Route("Denver", "Salt Lake City", CardColor.RED, 3));
        availableRouteList.add(new Route("Denver", "Salt Lake City", CardColor.YELLOW, 3));
        availableRouteList.add(new Route("Phoenix", "Denver", CardColor.WHITE, 5));
        availableRouteList.add(new Route("Phoenix", "Santa Fe", CardColor.WILD, 3));
        availableRouteList.add(new Route("Phoenix", "El Paso", CardColor.WILD, 3));
        availableRouteList.add(new Route("Denver", "Santa Fe", CardColor.WILD, 2));
        availableRouteList.add(new Route("Denver", "Omaha", CardColor.PURPLE, 4));
        availableRouteList.add(new Route("Denver", "Kansas City", CardColor.BLACK, 4));
        availableRouteList.add(new Route("Denver", "Kansas City", CardColor.ORANGE, 4));
        availableRouteList.add(new Route("Denver", "Oklahoma City", CardColor.RED, 4));
        availableRouteList.add(new Route("Santa Fe", "Oklahoma City", CardColor.BLUE, 3));
        availableRouteList.add(new Route("Santa Fe", "El Paso", CardColor.WILD, 2));
        availableRouteList.add(new Route("Oklahoma City", "El Paso", CardColor.YELLOW, 5));
        availableRouteList.add(new Route("Dallas", "El Paso", CardColor.RED, 4));
        availableRouteList.add(new Route("Houston", "El Paso", CardColor.GREEN, 6));
        availableRouteList.add(new Route("Winnipeg", "Sault St Marie", CardColor.WILD, 6));
        availableRouteList.add(new Route("Winnipeg", "Duluth", CardColor.BLACK, 4));
        availableRouteList.add(new Route("Omaha", "Duluth", CardColor.WILD, 2));
        availableRouteList.add(new Route("Omaha", "Duluth", CardColor.WILD, 2));
        availableRouteList.add(new Route("Sault St Marie", "Duluth", CardColor.WILD, 3));
        availableRouteList.add(new Route("Toronto", "Duluth", CardColor.PURPLE, 6));
        availableRouteList.add(new Route("Chicago", "Duluth", CardColor.RED, 3));
        availableRouteList.add(new Route("Omaha", "Chicago", CardColor.BLUE, 4));
        availableRouteList.add(new Route("Omaha", "Kansas City", CardColor.WILD, 1));
        availableRouteList.add(new Route("Omaha", "Kansas City", CardColor.WILD, 1));
        availableRouteList.add(new Route("Saint Louis", "Kansas City", CardColor.BLUE, 2));
        availableRouteList.add(new Route("Saint Louis", "Kansas City", CardColor.PURPLE, 2));
        availableRouteList.add(new Route("Oklahoma City", "Kansas City", CardColor.WILD, 2));
        availableRouteList.add(new Route("Oklahoma City", "Kansas City", CardColor.WILD, 2));
        availableRouteList.add(new Route("Oklahoma City", "Little Rock", CardColor.WILD, 2));
        availableRouteList.add(new Route("Oklahoma City", "Dallas", CardColor.WILD, 2));
        availableRouteList.add(new Route("Oklahoma City", "Dallas", CardColor.WILD, 2));
        availableRouteList.add(new Route("Dallas", "Little Rock", CardColor.WILD, 2));
        availableRouteList.add(new Route("Dallas", "Houston", CardColor.WILD, 1));
        availableRouteList.add(new Route("Dallas", "Houston", CardColor.WILD, 1));
        availableRouteList.add(new Route("New Orleans", "Houston", CardColor.WILD, 2));
        availableRouteList.add(new Route("Chicago", "Toronto", CardColor.WHITE, 4));
        availableRouteList.add(new Route("Chicago", "Pittsburgh", CardColor.ORANGE, 3));
        availableRouteList.add(new Route("Chicago", "Pittsburgh", CardColor.BLACK, 3));
        availableRouteList.add(new Route("Saint Louis", "Chicago", CardColor.GREEN, 2));
        availableRouteList.add(new Route("Saint Louis", "Chicago", CardColor.WHITE, 2));
        availableRouteList.add(new Route("Saint Louis", "Pittsburgh", CardColor.GREEN, 5));
        availableRouteList.add(new Route("Saint Louis", "Nashville", CardColor.WILD, 2));
        availableRouteList.add(new Route("Saint Louis", "Little Rock", CardColor.WILD, 2));
        availableRouteList.add(new Route("Nashville", "Little Rock", CardColor.WHITE, 3));
        availableRouteList.add(new Route("New Orleans", "Little Rock", CardColor.GREEN, 3));
        availableRouteList.add(new Route("Nashville", "Pittsburgh", CardColor.YELLOW, 4));
        availableRouteList.add(new Route("Nashville", "Raleigh", CardColor.BLACK, 3));
        availableRouteList.add(new Route("Nashville", "Atlanta", CardColor.WILD, 1));
        availableRouteList.add(new Route("New Orleans", "Atlanta", CardColor.YELLOW, 4));
        availableRouteList.add(new Route("New Orleans", "Atlanta", CardColor.ORANGE, 4));
        availableRouteList.add(new Route("New Orleans", "Miami", CardColor.RED, 4));
        availableRouteList.add(new Route("Atlanta", "Raleigh", CardColor.WILD, 2));
        availableRouteList.add(new Route("Atlanta", "Raleigh", CardColor.WILD, 2));
        availableRouteList.add(new Route("Atlanta", "Charleston", CardColor.WILD, 2));
        availableRouteList.add(new Route("Atlanta", "Miami", CardColor.BLUE, 5));
        availableRouteList.add(new Route("Charleston", "Miami", CardColor.PURPLE, 4));
        availableRouteList.add(new Route("Charleston", "Raleigh", CardColor.WILD, 2));
        availableRouteList.add(new Route("Raleigh", "Washington", CardColor.WILD, 2));
        availableRouteList.add(new Route("Raleigh", "Washington", CardColor.WILD, 2));
        availableRouteList.add(new Route("Raleigh", "Pittsburgh", CardColor.WILD, 2));
        availableRouteList.add(new Route("Pittsburgh", "Washington", CardColor.WILD, 2));
        availableRouteList.add(new Route("Pittsburgh", "New York", CardColor.WHITE, 2));
        availableRouteList.add(new Route("Pittsburgh", "New York", CardColor.GREEN, 2));
        availableRouteList.add(new Route("Pittsburgh", "Toronto", CardColor.WILD, 2));
        availableRouteList.add(new Route("Toronto", "Sault St Marie", CardColor.WILD, 2));
        availableRouteList.add(new Route("Toronto", "Montreal", CardColor.WILD, 3));
        availableRouteList.add(new Route("Montreal", "Sault St Marie", CardColor.BLACK, 5));
        availableRouteList.add(new Route("Montreal", "New York", CardColor.BLUE, 3));
        availableRouteList.add(new Route("Montreal", "Boston", CardColor.WILD, 2));
        availableRouteList.add(new Route("Montreal", "Boston", CardColor.WILD, 2));
        availableRouteList.add(new Route("Boston", "New York", CardColor.RED, 2));
        availableRouteList.add(new Route("Boston", "New York", CardColor.YELLOW, 2));
        availableRouteList.add(new Route("Washington", "New York", CardColor.BLACK, 2));
        availableRouteList.add(new Route("Washington", "New York", CardColor.ORANGE, 2));

    }

    public Route findRoute(String city1, String city2, CardColor color)
    {
        Route currentRoute = availableRouteList.get(0);
        for (int i = 0; i < availableRouteList.size(); i++)
        {
            currentRoute = availableRouteList.get(i);
            if (city1 == currentRoute.city1 || city1 == currentRoute.city2)         //if city1 is one of the cities
                if (city2 == currentRoute.city1 || city2 == currentRoute.city2)     //if city2 is also one of the cities
                    if (color == currentRoute.color)                                //if route is the color
                    {
                        //if found route, exit
                        i = availableRouteList.size();
                    }
        }
        return currentRoute;
    }
    
    public boolean claimRoute(Route routeToClaim, String auth)
    {
        for(int i = 0; i <availableRouteList.size(); i++)
        {
            Route availableRoute = availableRouteList.get(i);
            if (availableRoute.city1.equals(routeToClaim.city1))
            {
                if(availableRoute.city2.equals(routeToClaim.city2))
                {
                    availableRouteList.remove(i);
                    playersClaimedRoutes.put(auth, availableRoute);
                    return true;
                }
            }
        }
        return false;
    }

}
