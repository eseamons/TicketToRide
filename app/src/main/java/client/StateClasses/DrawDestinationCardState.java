package client.StateClasses;

import client.ClientFacade;

/**
 * Created by Michaels on 3/16/2017.
 */

public class DrawDestinationCardState extends ClientState {

    ClientFacade client = new ClientFacade();
    boolean[] destinationCardsAcceptance;

    public DrawDestinationCardState()
    {
        destinationCardsAcceptance = new boolean[3];
        for(int i = 0; i < destinationCardsAcceptance.length; i++)
        {
            destinationCardsAcceptance[i] = true;
        }
        //view.setDestinationCardsAcceptanceVisibility(true);
    }


    public void DestinationConfirmedClicked()
    {
        //TODO ask the client facade to keep desired ones and discared others
        client.setClientState(new NotMyTurnState());
    }

    @Override
    public void DestinationCard1Clicked()
    {
        toggleDestinationCardAcceptance(0);
    }


    public void DestinationCard2Clicked()
    {
        toggleDestinationCardAcceptance(1);
    }

    public void DestinationCard3Clicked()
    {
        toggleDestinationCardAcceptance(2);
    }

    public void toggleDestinationCardAcceptance(int i)
    {
        destinationCardsAcceptance[i] = !destinationCardsAcceptance[i];
    }


}
