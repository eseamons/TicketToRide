package client.StateClasses;

import java.util.List;

import client.ClientFacade;
import shared.CardColor;
import shared.model_classes.Route;

/**
 * Created by Michaels on 3/16/2017.
 */

public class MyTurnState extends ClientState
{
    ClientFacade client;

    public MyTurnState()
    {
        client = new ClientFacade();
    }

    public String DrawDestinationCardButtonClicked()
    {
        client.setClientState(new DrawDestinationCardState());
        client.retrieve3DestinationCards();

        return "";
    }

    public String ClaimRouteButtonClicked(Route desiredRoute)
    {
        if(client.canClaimRoute(desiredRoute))
        {

            client.ClaimRoute(desiredRoute);
            client.setClientState(new NotMyTurnState());
            client.endTurn();
            return "";
        }
        else
        {
            return "Not enough cards to claim route";
        }

    }

    public String DeckCardClicked()
    {
        client.setClientState(new DrawCardState());
        client.drawDeckCard();
        return "";
    }

    public boolean isWild(int index)
    {
        return client.getFaceUpCard(index).equals(CardColor.WILD);
    }

    public String templateFaceUpClicked(int cardIndex)
    {

        if(isWild(cardIndex))
        {
            client.endTurn();
        }
        else
        {
            client.setClientState(new DrawCardState());
        }
        client.drawFaceUpCard(cardIndex);

        return "";
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

}
