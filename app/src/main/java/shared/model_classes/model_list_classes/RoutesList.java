package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.List;

import shared.CardColor;
import shared.model_classes.Route;

/**
 * Created by Maynson on 3/1/2017.
 */

public class RoutesList {
    private List<Route> routeList = new ArrayList<>();

    public RoutesList()
    {
        //TODO: Add every route individually, WILL DO THIS OK!!
    }

    public Route findRoute(String city1, String city2, CardColor color)
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
}
