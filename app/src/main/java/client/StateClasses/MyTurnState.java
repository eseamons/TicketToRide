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

    public void DrawDestinationCardButtonClicked()
    {
        client.retrieve3DestinationCards();
        client.setClientState(new DrawDestinationCardState());
        //set state to DrawDestinationCardState
    }

    public void ClaimRouteButtonClicked(Route desiredRoute, List<CardColor> hand)
    {
        if(canClaimRoute(desiredRoute, hand))
        {
            client.ClaimRoute(desiredRoute);
        }
        else
        {
            //create a toast saying not enough cards
        }

    }

    public boolean canClaimRoute(Route desiredRoute, List<CardColor> hand)
    {
        int count = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i) == desiredRoute.color)
            {
                count++;
            }
        }
        return count >= desiredRoute.length;
    }

    public void DeckCardClicked()
    {
        client.drawDeckCard();
        client.setClientState(new DrawCardState());
    }

    public boolean isWild(int index)
    {
        return client.getFaceUpCard(index) == CardColor.WILD;
    }

    public void templateFaceUpClicked(int cardIndex)
    {
        client.drawFaceUpCard(cardIndex);
        if(isWild(cardIndex))
        {
            client.setClientState(new NotMyTurnState());
            //set to NotMyTurnState
        }
        else
        {
            client.setClientState(new DrawCardState());
            //set to DrawCardState
        }
    }

    public void FaceUp0Clicked()
    {
        templateFaceUpClicked(0);
    }

    public void FaceUp1Clicked()
    {
        templateFaceUpClicked(1);
    }

    public void FaceUp2Clicked()
    {
        templateFaceUpClicked(2);
    }

    public void FaceUp3Clicked()
    {
        templateFaceUpClicked(3);
    }

    public void FaceUp4Clicked()
    {
        templateFaceUpClicked(4);
    }

}
