package client.StateClasses;

import client.ClientFacade;

/**
 * Created by Michaels on 3/16/2017.
 */

public class DrawDestinationCardState extends ClientState {

    ClientFacade client = new ClientFacade();
    public DrawDestinationCardState()
    {

    }

    public void toggleCard(int cardIndex)
    {
        //TODO switches the card between 'keep' and 'discard'
    }

    public void DestinationCard1Clicked()
    {
        toggleCard(0);
    }

    public void DestinationCard2Clicked()
    {
        toggleCard(1);
    }
    public void DestinationCard3Clicked()
    {
        toggleCard(2);
    }

    public void DestinationConfirmedClicked()
    {
        //TODO ask the client facade to keep desired ones and discared others
        client.setClientState(new NotMyTurnState());
    }

}
