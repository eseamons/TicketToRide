package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.CardColor;
import shared.model_classes.Player;
import shared.model_classes.Route;

/**
 * Created by Maynson on 3/1/2017.
 */

public class RoutesList {
    private List<Route> availableRouteList = new ArrayList<>();
    private Map<String,Route> playersClaimedRoutes = new HashMap<>();

    //added just so will compile
    List<Route> routeList;
    public int cur_done = 41+1; //this is for Michael to help harcoding his view
    public RoutesList()
    {
        availableRouteList.add(new Route("Vancouver", "Calgary", CardColor.WILD, 3));
        getRoute(0).setCoords(304,200,567,176);
        availableRouteList.add(new Route("Vancouver", "Seattle", CardColor.WILD, 1));
        getRoute(1).setCoords(253,231,253,293);
        availableRouteList.add(new Route("Vancouver", "Seattle", CardColor.WILD, 1));
        getRoute(2).setCoords(284,231,284,293);
        availableRouteList.add(new Route("Calgary", "Seattle", CardColor.WILD, 4));
        getRoute(3).setCoords(298,317,595,199);
        availableRouteList.add(new Route("Helena", "Seattle", CardColor.YELLOW, 6));
        getRoute(4).setCoords(292,346,813,438);
        availableRouteList.add(new Route("Calgary", "Helena", CardColor.WILD, 4));
        getRoute(5).setCoords(620,197,844,412);
        availableRouteList.add(new Route("Portland", "Salt Lake City", CardColor.BLUE, 6));
        getRoute(6).setCoords(242,421,658,658);
        availableRouteList.add(new Route("Portland", "San Francisco", CardColor.GREEN, 5));
        getRoute(7).setCoords(168,434,148,784);
        availableRouteList.add(new Route("Portland", "San Francisco", CardColor.PURPLE, 5));
        getRoute(8).setCoords(215,449,191,781);
        availableRouteList.add(new Route("Salt Lake City", "San Francisco", CardColor.ORANGE, 5));
        getRoute(9).setCoords(214,792,626,681);
        availableRouteList.add(new Route("Salt Lake City", "San Francisco", CardColor.WHITE, 5));
        getRoute(10).setCoords(224,814,638,705);
        availableRouteList.add(new Route("Los Angeles", "San Francisco", CardColor.PURPLE, 3));
        getRoute(11).setCoords(172,845,323,1014);
        availableRouteList.add(new Route("Los Angeles", "San Francisco", CardColor.YELLOW, 3));
        getRoute(12).setCoords(203,830,346,1000);
        availableRouteList.add(new Route("Los Angeles", "Las Vegas", CardColor.WILD, 2));
        getRoute(13).setCoords(375,983,501,907);
        availableRouteList.add(new Route("Los Angeles", "Phoenix", CardColor.WILD, 3));
        getRoute(14).setCoords(392,1011,652,1016);
        availableRouteList.add(new Route("Los Angeles", "El Paso", CardColor.BLACK, 6));
        getRoute(15).setCoords(393,1042,903,1116);
        availableRouteList.add(new Route("Salt Lake City", "Las Vegas", CardColor.ORANGE, 3));
        getRoute(16).setCoords(559,894,669,710);
        availableRouteList.add(new Route("Calgary", "Winnipeg", CardColor.WHITE, 6));
        getRoute(17).setCoords(627,167,1142,176);
        availableRouteList.add(new Route("Helena", "Winnipeg", CardColor.BLUE, 4));
        getRoute(18).setCoords(885,408,1132,210);
        availableRouteList.add(new Route("Helena", "Duluth", CardColor.ORANGE, 6));
        getRoute(19).setCoords(882,438,1414,430);
        availableRouteList.add(new Route("Helena", "Omaha", CardColor.RED, 5));
        getRoute(20).setCoords(908,461,1316,593);
        availableRouteList.add(new Route("Helena", "Denver", CardColor.GREEN, 4));
        getRoute(21).setCoords(863,459,994,716);
        availableRouteList.add(new Route("Helena", "Salt Lake City", CardColor.PURPLE, 3));
        getRoute(22).setCoords(832,461,700,636);
        availableRouteList.add(new Route("Denver", "Salt Lake City", CardColor.RED, 3));
        getRoute(23).setCoords(702,673,954,713);
        availableRouteList.add(new Route("Denver", "Salt Lake City", CardColor.YELLOW, 3));
        getRoute(24).setCoords(698,698,951,735);
        availableRouteList.add(new Route("Phoenix", "Denver", CardColor.WHITE, 5));
        getRoute(25).setCoords(676,1008,957,759);
        availableRouteList.add(new Route("Phoenix", "Santa Fe", CardColor.WILD, 3));
        getRoute(26).setCoords(711,1020,950,934);
        availableRouteList.add(new Route("Phoenix", "El Paso", CardColor.WILD, 3));
        getRoute(27).setCoords(693,1043,941,1102);
        availableRouteList.add(new Route("Denver", "Santa Fe", CardColor.WILD, 2));
        getRoute(28).setCoords(986,775,979,906);
        availableRouteList.add(new Route("Denver", "Omaha", CardColor.PURPLE, 4));
        getRoute(29).setCoords(1016,723,1337,618);
        availableRouteList.add(new Route("Denver", "Kansas City", CardColor.BLACK, 4));
        getRoute(30).setCoords(1044,746,1380,708);
        availableRouteList.add(new Route("Denver", "Kansas City", CardColor.ORANGE, 4));
        getRoute(31).setCoords(1042,774,1384,735);
        availableRouteList.add(new Route("Denver", "Oklahoma City", CardColor.RED, 4));
        getRoute(32).setCoords(1010,787,1330,875);
        availableRouteList.add(new Route("Santa Fe", "Oklahoma City", CardColor.BLUE, 3));
        getRoute(33).setCoords(1010,930,1271,905);
        availableRouteList.add(new Route("Santa Fe", "El Paso", CardColor.WILD, 2));
        getRoute(34).setCoords(975,950,965,1085);
        availableRouteList.add(new Route("Oklahoma City", "El Paso", CardColor.YELLOW, 5));
        getRoute(35).setCoords(997,1098,1348,903);
        availableRouteList.add(new Route("Dallas", "El Paso", CardColor.RED, 4));
        getRoute(36).setCoords(1035,1115,1381,1072);
        availableRouteList.add(new Route("Houston", "El Paso", CardColor.GREEN, 6));
        getRoute(37).setCoords(982,1128,1498,1163);
        availableRouteList.add(new Route("Winnipeg", "Sault St Marie", CardColor.WILD, 6));
        getRoute(38).setCoords(1210,192,1732,275);
        availableRouteList.add(new Route("Winnipeg", "Duluth", CardColor.BLACK, 4));
        getRoute(39).setCoords(1178,216,1425,406);
        availableRouteList.add(new Route("Omaha", "Duluth", CardColor.WILD, 2));
        getRoute(40).setCoords(1413,452,1357,577);
        availableRouteList.add(new Route("Omaha", "Duluth", CardColor.WILD, 2));
        getRoute(41).setCoords(1445,462,1394,585);
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

    public Route getRoute(String city1, String city2, CardColor color)
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

    public Route getRoute(int index)
    {
        return availableRouteList.get(index);
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
