package client.StateClasses;

import client.ClientFacade;
import shared.CardColor;
import shared.model_classes.Route;

/**
 * Created by Michaels on 3/16/2017.
 */

public class DrawCardState extends ClientState
{
    ClientFacade client;

    public DrawCardState()
    {
        client = new ClientFacade();
    }

    public String DeckCardClicked()
    {
        client.endTurn();
        client.drawDeckCard();
        return "";
    }

    public boolean isWild(int index)
    {
        return client.getFaceUpCard(index) == CardColor.WILD;
    }

    public String templateFaceUpClicked(int cardIndex)
    {
        if(!isWild(cardIndex))
        {
            client.endTurn();
            client.drawFaceUpCard(cardIndex);

            return "";
        }
        else
        {
            return "Cannot draw a wild after 1 card is already drawn";
        }
    }

    public String FaceUp0Clicked()
    {
        return templateFaceUpClicked(0);
    }

    public String FaceUp1Clicked()
    {
        return templateFaceUpClicked(1);
    }

    public String FaceUp2Clicked()
    {
        return templateFaceUpClicked(2);
    }

    public String FaceUp3Clicked()
    {
        return templateFaceUpClicked(3);
    }

    public String FaceUp4Clicked()
    {
        return templateFaceUpClicked(4);
    }

    public String DrawDestinationCardButtonClicked() {
        return "You cannot draw destination cards after drawing a train card";
    }

    public String ClaimRouteButtonClicked(Route desiredRoute)
    {
        return "You cannot claim a route after drawing a train card";
    }
}
