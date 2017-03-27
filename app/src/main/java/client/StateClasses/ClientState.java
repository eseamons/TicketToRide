package client.StateClasses;

import java.util.List;

import shared.CardColor;
import shared.model_classes.Route;

/**
 * Created by Michaels on 3/16/2017.
 */

public abstract class ClientState {
    public void DestinationCard1Clicked() {

    }

    public void DestinationCard2Clicked() {

    }

    public void DestinationCard3Clicked() {

    }

    public String DestinationConfirmedClicked() {
        return "";
    }

    public String DrawDestinationCardButtonClicked() {
        return defaultResponse();
    }

    public String ClaimRouteButtonClicked(Route desiredRoute) {
        return "";
    }

    public String DeckCardClicked() {
        return defaultResponse();

    }

    public String FaceUp0Clicked() {
        return defaultResponse();
    }

    public String FaceUp1Clicked() {
        return defaultResponse();
    }

    public String FaceUp2Clicked() {
        return defaultResponse();
    }

    public String FaceUp3Clicked() {
        return defaultResponse();
    }

    public String FaceUp4Clicked()
    {
        return defaultResponse();
    }

    public String defaultResponse()
    {
        return "";
    }

}
