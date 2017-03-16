package client.StateClasses;

import client.ClientFacade;
import shared.CardColor;

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
        //set state to DrawDestinationCardState
    }

    public void ClaimRouteButtonClicked()
    {

    }

    public void DeckCardClicked()
    {
        client.drawDeckCard();
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
            //set to NotMyTurnState
        }
        else
        {
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
