package client.StateClasses;

import shared.model_classes.Route;

/**
 * Created by Michaels on 3/16/2017.
 */

public class NotMyTurnState extends ClientState
{

    public String ClaimRouteButtonClicked(Route desiredRoute)
    {
        return "You can only claim a route when it is your turn";
    }

    public String DeckCardClicked()
    {
        return defaultDrawCardResponse();
    }

    public String DrawDestinationCardButtonClicked() {
        return "You can only draw destination cards when it is your turn";
    }

    public String FaceUp0Clicked() {
        return defaultDrawCardResponse();
    }

    public String FaceUp1Clicked() {
        return defaultDrawCardResponse();
    }

    public String FaceUp2Clicked() {
        return defaultDrawCardResponse();
    }

    public String FaceUp3Clicked() {
        return defaultDrawCardResponse();
    }

    public String FaceUp4Clicked()
    {
        return defaultDrawCardResponse();
    }

    public String defaultDrawCardResponse()
    {
        return "You can only draw cards when it is your turn";
    }
}
