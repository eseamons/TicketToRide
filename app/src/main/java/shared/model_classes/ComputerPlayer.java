package shared.model_classes;

import org.junit.runner.Computer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import server.ServerFacade;
import shared.CardColor;
import shared.Result;
import shared.command_classes.DrawDestinationCardCommand;
import shared.command_data_classes.DrawDestinationCardCommandData;
import shared.model_classes.model_list_classes.RoutesList;

/**
 * Created by mepeter on 4/18/17.
 */

public class ComputerPlayer extends Player
{
    int gameID;
    RoutesList games_routes;
    public ComputerPlayer(int le_gameID,RoutesList routes)
    {
        super();
        gameID = le_gameID;
        games_routes = routes;
    }

    boolean first_turn = true;
    public void takeTurn()
    {
        Random rand = new Random();
        double randomness = rand.nextDouble();

        if(first_turn)
        {
            first_turn = false;
            doDestinationCards();
        }
        else if(randomness > .33)
        {
            drawCards();
        }
        else
        {
            claimRoute();
        }
        ServerFacade.getInstance().endTurn(gameID,account.getAuthentication());
    }

    public void drawCards()
    {
        ServerFacade.getInstance().drawDeckCard(account.getAuthentication(), playerID);
        ServerFacade.getInstance().drawDeckCard(account.getAuthentication(), playerID);

    }

    public void doDestinationCards()
    {
        String auth = account.getAuthentication();
        Result r = ServerFacade.getInstance().drawDestinationCards(gameID, auth);
        DrawDestinationCardCommand cmd = (DrawDestinationCardCommand) r.getInfo();
        DrawDestinationCardCommandData data = (DrawDestinationCardCommandData) cmd.getInfo();
        DestinationCard[] acceptedCards = data.getDestinationCards();
        boolean[] confirmedCardsBools = {true,true,true};
        ServerFacade.getInstance().removeDestinationCard(gameID, playerID, acceptedCards, confirmedCardsBools, auth);
    }

    public boolean claimRoute()
    {
        String auth = account.getAuthentication();
        List<Route> claimableRoutes =  games_routes.getClaimableRoutes(this);
        Route bestRoute = null;
        int longest = -1;
        for(int i = 0; i < claimableRoutes.size(); i++)
        {
            Route cur_Route = claimableRoutes.get(i);
            if(cur_Route.length > longest && cur_Route.color != CardColor.WILD)
            {
                bestRoute = cur_Route;
                longest = bestRoute.length;
            }

        }
        if(bestRoute == null)
        {
            return false;
        }
        ServerFacade.getInstance().claimRoute(gameID,bestRoute,auth,bestRoute.color);
        return true;
    }


}
