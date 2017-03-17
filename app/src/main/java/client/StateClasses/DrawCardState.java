package client.StateClasses;

import client.ClientFacade;
import shared.CardColor;

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
        if(!isWild(cardIndex))
        {
            client.drawFaceUpCard(cardIndex);
            //make client NotMyTurnState
            client.setClientState(new NotMyTurnState());
        }
        else
        {
            //make toast saying cannot draw wild card after drawn 1 card
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
