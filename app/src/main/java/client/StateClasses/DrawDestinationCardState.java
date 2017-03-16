package client.StateClasses;

/**
 * Created by Michaels on 3/16/2017.
 */

public class DrawDestinationCardState extends ClientState {

    public DrawDestinationCardState()
    {

    }

    public void toggleCard(int cardIndex)
    {
        //switches the card between 'keep' and 'discard'
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

    }

}
