package client.StateClasses;

import client.ClientFacade;

/**
 * Created by Michaels on 3/16/2017.
 */

public class DrawDestinationCardState extends ClientState {

    ClientFacade client = new ClientFacade();

    public DrawDestinationCardState()
    {
        client.intializeDestinationCardsAcceptance();
    }


    public String DestinationConfirmedClicked()
    {
        if(client.canConfirmDestinationCards())
        {
            client.setClientState(new NotMyTurnState());
            client.confirmDestinationCards();
            return "";
        }
        else
        {
            return "Must keep at least 1 destination card and 2 if it is the beginning of game";
        }

    }

    @Override
    public void DestinationCard1Clicked()
    {
        client.toggleDestinationCardsAcceptance(0);
    }


    public void DestinationCard2Clicked()
    {
        client.toggleDestinationCardsAcceptance(1);
    }

    public void DestinationCard3Clicked()
    {
        client.toggleDestinationCardsAcceptance(2);
    }

    public String DrawDestinationCardButtonClicked() {
        return "You have already drawn the destination cards";
    }

    public String DeckCardClicked()
    {
        return defaultDrawCardResponse();
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
        return "You cannot draw train cards while selected your destination cards";
    }

}
