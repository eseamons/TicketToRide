package shared.model_classes;

import java.util.ArrayList;

import shared.model_classes.model_list_classes.RoutesList;

/**
 * Created by Michaels on 4/4/2017.
 */

public class RouteTesterMain
{
    static ArrayList<Player> stupidPlayers;
    static RoutesList routes;
    public static void main(String[] args) {
        routes = new RoutesList();
        stupidPlayers = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            stupidPlayers.add(new Player());
        }

        addTestDestinationCards();
        routes.setStupidPlayers(stupidPlayers);
        addTestClaimRoutes();

        routes.performEndGameCalculations();

        printPoints();

    }

    public static void printPoints()
    {
        for(int i = 0; i<stupidPlayers.size(); i++)
        {
            Player p = stupidPlayers.get(i);
            int playerNumber = i+1;
            System.out.println("Player " + playerNumber + " points: " + p.getPoints());
        }
    }

    public static void addTestClaimRoutes()
    {
        claimRoute("Denver", "Santa Fe", 1);
        claimRoute("Santa Fe", "El Paso",1);

        claimRoute("Los Angeles", "El Paso",2);
        claimRoute("Houston", "El Paso",2);
        claimRoute("Houston", "New Orleans",2);
        claimRoute("New Orleans", "Miami",2);

        claimRoute("Omaha", "Chicago",3);

        claimRoute("Duluth","Helena",4);
        claimRoute("Seattle","Helena",4);
        claimRoute("Seattle","Calgary",4);
        claimRoute("Calgary","Helena",4);


    }

    public static void addTestDestinationCards()
    {
        addDestinationCard(new DestinationCard("Denver", "El Paso", 4), 1);

        addDestinationCard(new DestinationCard("Los Angeles", "Miami", 20), 2);

        addDestinationCard(new DestinationCard("Los Angeles", "Chicago", 16), 3);
    }

    public static void addDestinationCard(DestinationCard dc, int PlayerNumber)
    {
        stupidPlayers.get(PlayerNumber-1).addDestinationCard(dc);
    }

    public static void claimRoute(String city1, String city2, int PlayerNumber)
    {
        routes.stupidClaimRoute(city1,city2,PlayerNumber);
    }
}
