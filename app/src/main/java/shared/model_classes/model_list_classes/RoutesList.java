package shared.model_classes.model_list_classes;

import android.graphics.Point;

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
    /**
     * availableRouteList
     * List of all unclaimed routes in Ticket To Ride Game.
     */
    private List<Route> availableRouteList = new ArrayList<>();
    /**
     * playersClaimedRoutes
     * Map that links a Player's authorization token to the Route(s)
     * they own.
     */
    private Map<String,List<Route>> playersClaimedRoutes = new HashMap<>();

    //added just so will compile
    //List<Route> routeList;

    /**
     * CUR_DONE
     * Current number of routes added to the list.
     * (this is for Michael to help hard-coding his view)
     */
    public int CUR_DONE = 99+1;

    /**
     * Constructor for RoutesList.
     *
     * Includes hard-coding in all of the Routes available in Ticket To Ride,
     * a total of 100 routes.
     * Also adds the coordinates for the two connected cities in each route.
     * These coordinates are used within the MapView for drawing claimed routes.
     *
     * @post availableRouteList.size() = 100
     * @post availableRouteList contains all Routes on initial TTR gameboard.
     */
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
        getRoute(42).setCoords(1492,394,1731,314);
        availableRouteList.add(new Route("Toronto", "Duluth", CardColor.PURPLE, 6));
        getRoute(43).setCoords(1470,425,1995,351);
        availableRouteList.add(new Route("Chicago", "Duluth", CardColor.RED, 3));
        getRoute(44).setCoords(1461,446,1700,517);
        availableRouteList.add(new Route("Omaha", "Chicago", CardColor.BLUE, 4));
        getRoute(45).setCoords(1394,609,1715,555);
        availableRouteList.add(new Route("Omaha", "Kansas City", CardColor.WILD, 1));
        getRoute(46).setCoords(1366,639,1397,695);
        availableRouteList.add(new Route("Omaha", "Kansas City", CardColor.WILD, 1));
        getRoute(47).setCoords(1394,627,1430,679);
        availableRouteList.add(new Route("Saint Louis", "Kansas City", CardColor.BLUE, 2));
        getRoute(48).setCoords(1443,698,1609,696);
        availableRouteList.add(new Route("Saint Louis", "Kansas City", CardColor.PURPLE, 2));
        getRoute(49).setCoords(1443,721,1609,718);
        availableRouteList.add(new Route("Oklahoma City", "Kansas City", CardColor.WILD, 2));
        getRoute(50).setCoords(1413,735,1370,860);
        availableRouteList.add(new Route("Oklahoma City", "Kansas City", CardColor.WILD, 2));
        getRoute(51).setCoords(1443,739,1395,867);
        availableRouteList.add(new Route("Oklahoma City", "Little Rock", CardColor.WILD, 2));
        getRoute(52).setCoords(1403,889,1571,883);
        availableRouteList.add(new Route("Oklahoma City", "Dallas", CardColor.WILD, 2));
        getRoute(53).setCoords(1377,907,1397,1039);
        availableRouteList.add(new Route("Oklahoma City", "Dallas", CardColor.WILD, 2));
        getRoute(54).setCoords(1411,905,1430,1038);
        availableRouteList.add(new Route("Dallas", "Little Rock", CardColor.WILD, 2));
        getRoute(55).setCoords(1463,1020,1565,913);
        availableRouteList.add(new Route("Dallas", "Houston", CardColor.WILD, 1));
        getRoute(56).setCoords(1430,1085,1479,1131);
        availableRouteList.add(new Route("Dallas", "Houston", CardColor.WILD, 1));
        getRoute(57).setCoords(1455,1067,1504,1112);
        availableRouteList.add(new Route("New Orleans", "Houston", CardColor.WILD, 2));
        getRoute(58).setCoords(1550,1140,1718,1118);
        availableRouteList.add(new Route("Chicago", "Toronto", CardColor.WHITE, 4));
        getRoute(59).setCoords(1734,514,2028,360);
        availableRouteList.add(new Route("Chicago", "Pittsburgh", CardColor.ORANGE, 3));
        getRoute(60).setCoords(1768,520,2029,492);
        availableRouteList.add(new Route("Chicago", "Pittsburgh", CardColor.BLACK, 3));
        getRoute(61).setCoords(1781,548,2034,520);
        availableRouteList.add(new Route("Saint Louis", "Chicago", CardColor.GREEN, 2));
        getRoute(62).setCoords(1620,685,1711,570);
        availableRouteList.add(new Route("Saint Louis", "Chicago", CardColor.WHITE, 2));
        getRoute(63).setCoords(1652,698,1740,578);
        availableRouteList.add(new Route("Saint Louis", "Pittsburgh", CardColor.GREEN, 5));
        getRoute(64).setCoords(1665,716,2046,547);
        availableRouteList.add(new Route("Saint Louis", "Nashville", CardColor.WILD, 2));
        getRoute(65).setCoords(1661,750,1826,790);
        availableRouteList.add(new Route("Saint Louis", "Little Rock", CardColor.WILD, 2));
        getRoute(66).setCoords(1636,735,1595,867);
        availableRouteList.add(new Route("Nashville", "Little Rock", CardColor.WHITE, 3));
        getRoute(67).setCoords(1624,890,1862,812);
        availableRouteList.add(new Route("New Orleans", "Little Rock", CardColor.GREEN, 3));
        getRoute(68).setCoords(1610,913,1728,1094);
        availableRouteList.add(new Route("Nashville", "Pittsburgh", CardColor.YELLOW, 4));
        getRoute(69).setCoords(1845,775,2073,560);
        availableRouteList.add(new Route("Nashville", "Raleigh", CardColor.BLACK, 3));
        getRoute(70).setCoords(1890,775,2140,727);
        availableRouteList.add(new Route("Nashville", "Atlanta", CardColor.WILD, 1));
        getRoute(71).setCoords(1890,805,1963,843);
        availableRouteList.add(new Route("New Orleans", "Atlanta", CardColor.YELLOW, 4));
        getRoute(72).setCoords(1765,1093,1971,864);
        availableRouteList.add(new Route("New Orleans", "Atlanta", CardColor.ORANGE, 4));
        getRoute(73).setCoords(1790,1109,1990,888);
        availableRouteList.add(new Route("New Orleans", "Miami", CardColor.RED, 4));
        getRoute(74).setCoords(1812,1121,2271,1198);
        availableRouteList.add(new Route("Atlanta", "Raleigh", CardColor.WILD, 2));
        getRoute(75).setCoords(2007,840,2138,754);
        availableRouteList.add(new Route("Atlanta", "Raleigh", CardColor.WILD, 2));
        getRoute(76).setCoords(2024,860,2162,769);
        availableRouteList.add(new Route("Atlanta", "Charleston", CardColor.WILD, 2));
        getRoute(77).setCoords(2037,882,2207,890);
        availableRouteList.add(new Route("Atlanta", "Miami", CardColor.BLUE, 5));
        getRoute(78).setCoords(2010,898,2286,1173);
        availableRouteList.add(new Route("Charleston", "Miami", CardColor.PURPLE, 4));
        getRoute(79).setCoords(2232,898,2319,1169);
        availableRouteList.add(new Route("Charleston", "Raleigh", CardColor.WILD, 2));
        getRoute(80).setCoords(2185,759,2248,857);
        availableRouteList.add(new Route("Raleigh", "Washington", CardColor.WILD, 2));
        getRoute(81).setCoords(2173,722,2285,618);
        availableRouteList.add(new Route("Raleigh", "Washington", CardColor.WILD, 2));
        getRoute(82).setCoords(2195,741,2310,633);
        availableRouteList.add(new Route("Raleigh", "Pittsburgh", CardColor.WILD, 2));
        getRoute(83).setCoords(2100,565,2139,702);
        availableRouteList.add(new Route("Pittsburgh", "Washington", CardColor.WILD, 2));
        getRoute(84).setCoords(2118,540,2268,603);
        availableRouteList.add(new Route("Pittsburgh", "New York", CardColor.WHITE, 2));
        getRoute(85).setCoords(2095,492,2239,421);
        availableRouteList.add(new Route("Pittsburgh", "New York", CardColor.GREEN, 2));
        getRoute(86).setCoords(2109,512,2254,435);
        availableRouteList.add(new Route("Pittsburgh", "Toronto", CardColor.WILD, 2));
        getRoute(87).setCoords(2055,357,2064,494);
        availableRouteList.add(new Route("Toronto", "Sault St Marie", CardColor.WILD, 2));
        getRoute(88).setCoords(1800,296,1967,324);
        availableRouteList.add(new Route("Toronto", "Montreal", CardColor.WILD, 3));
        getRoute(89).setCoords(2018,310,2205,174);
        availableRouteList.add(new Route("Montreal", "Sault St Marie", CardColor.BLACK, 5));
        getRoute(90).setCoords(1780,270,2183,154);
        availableRouteList.add(new Route("Montreal", "New York", CardColor.BLUE, 3));
        getRoute(91).setCoords(2225,197,2265,398);
        availableRouteList.add(new Route("Montreal", "Boston", CardColor.WILD, 2));
        getRoute(92).setCoords(2271,171,2404,256);
        availableRouteList.add(new Route("Montreal", "Boston", CardColor.WILD, 2));
        getRoute(93).setCoords(2256,185,2384,276);
        availableRouteList.add(new Route("Boston", "New York", CardColor.RED, 2));
        getRoute(94).setCoords(2302,412,2393,296);
        availableRouteList.add(new Route("Boston", "New York", CardColor.YELLOW, 2));
        getRoute(95).setCoords(2327,427,2418,310);
        availableRouteList.add(new Route("Washington", "New York", CardColor.BLACK, 2));
        getRoute(96).setCoords(2287,455,2295,590);
        availableRouteList.add(new Route("Washington", "New York", CardColor.ORANGE, 2));
        getRoute(97).setCoords(2315,455,2325,590);
        availableRouteList.add(new Route("Seattle", "Portland", CardColor.ORANGE, 1));
        getRoute(98).setCoords(233,330,202,390);
        availableRouteList.add(new Route("Seattle", "Portland", CardColor.ORANGE, 1));
        getRoute(99).setCoords(261,341,236,398);


    }

    /**
     * Returns the number of unclaimed routes.
     *
     * @pre none
     *
     * @return the number of Routes not yet claimed.
     */
    public int getSize()
    {
        return availableRouteList.size();
    }

    /**
     * Returns the route that is specified by connecting city1, city2,
     * with the same Route color as the 'color' param.
     *
     * @pre city1 must be a city in TTR
     * @pre city2 must be a city in TTR, and be connected to city1 through a route.
     * @pre color must be a possible Route color in TTR.
     *
     * @param city1 One of the cities connected by the Route.
     * @param city2 The other city that isn't city1 connected by the Route.
     * @param color The color of the Route
     * @return The route specified by the cities and color.
     */
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

    /**
     * Returns the route that is at the specified index in the List of Unclaimed Routes.
     *
     * @pre index >= 0 and index < number of unclaimed routes.
     * @param index The index of the desired route in the List of Unclaimed Routes
     * @return the Route specified by the index parameter
     */
    public Route getRoute(int index)
    {
        return availableRouteList.get(index);
    }


    /**
     * Returns a List of Routes for the specified player that he/she is able
     * to claim at the time this method is called.
     *
     * @pre player 'p' must be a player within the current Game.
     * @post none.
     * @param p The player that is desired to find all claimable routes
     * @return A list of Routes that the player 'p' is able to claim at the time
     *          of the method call.
     */
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
        for(int i = 0; i < availableRouteList.size(); i++)
        {
            currentRoute = availableRouteList.get(i);
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

    /**
     * Attempts to claim a Route by the Player that matches the given authorization token.
     *
     * @pre routeToClaim must be an unclaimed route in the current TTR Game.
     * @pre auth must be a player's authorization token of a player in the current TTR Game.
     *
     * @post availableRouteList.size()= oldsize - 1
     * @post if returns true, player now has claimed the Route given in the param.
     *
     * @param routeToClaim The Route to be claimed
     * @param auth The player's authorization token of the player claiming the Route.
     * @return boolean true if player was able to claim route, or boolean false if not able.
     */
    public boolean claimRoute(Route routeToClaim, String auth, int playerID)
    {
        for(int i = 0; i <availableRouteList.size(); i++)
        {
            Route availableRoute = availableRouteList.get(i);
            if (availableRoute.city1.equals(routeToClaim.city1))
            {
                if(availableRoute.city2.equals(routeToClaim.city2))
                {
//                    availableRouteList.remove(i);
                    List<Route> routeList = playersClaimedRoutes.get(auth);
                    if(routeList == null)
                    {routeList = new ArrayList<>();}
//
//                    playersClaimedRoutes.remove(auth);
                    routeList.add(availableRoute);
//                    playersClaimedRoutes.put(auth, routeList);

                    availableRoute.ownership = playerID+1;
                    return true;
                }
            }
        }
        return false;
    }


    //returns the route clicked according to its hitboxes, if no route was clicked then returns null
    public Route getClickedRoute(Point click)
    {
        for(Route r : availableRouteList)
        {
            Point start = new Point((int)r.start_x,(int)r.start_y);
            Point end = new Point((int) r.end_x, (int) r.end_y);
            boolean isIn = contains(start,end,click);
            //if(contains(start,end,click))
            if(isIn)
            {
                return r;
            }
        }
        return null;

    }

    //uses an algorithm to determine if a point is within tolerance (8) units of a line
    public boolean contains(Point start, Point end, Point click)
    {
        double tolerance = 8;

        double width = Math.abs( start.x - end.x );
        double height = Math.abs( start.y - end.y );
        double UL_y, UL_x, UR_x, UR_y;
        if(start.x < end.x)
            UL_x = start.x;
        else
            UL_x = end.x;
        if(start.y < end.y)
            UL_y = start.y;
        else
            UL_y = end.y;

        //finds the edges of the box aroudn the route
        UR_x = UL_x + width + tolerance;
        UR_y = UL_y + height + tolerance;
        UL_x -= tolerance;
        UL_y -= tolerance;
        if(click.x < UL_x || click.x > UR_x || click.y < UL_y || click.y > UR_y) //make sure its in the box
            return false;




        double x2 = end.x;
        double x1 = start.x;
        double x0 = click.x;

        double y2 = end.y;
        double y1 = start.y;
        double y0 = click.y;

        double top = Math.abs( (x2 - x1)*(y1 - y0) - (x1-x0)*(y2-y1));
        double bot = Math.pow(Math.pow(x2-x1,2)+Math.pow(y2-y1,2),.5);
        double d = top/bot;

        return d <= tolerance; //line algorithm is within tolerance
    }
}
