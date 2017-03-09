package shared.command_classes;

import client.ClientFacade;
import shared.CardColor;
import shared.command_data_classes.SetFaceUpCardCommandData;

/**
 * Created by rebeccaredd on 3/8/17.
 */

public class SetFaceUpCardCommand extends Command {
    @Override
    public void executeOnClient() {
        ClientFacade clientFacade = new ClientFacade();
        int gameID = ((SetFaceUpCardCommandData) info).getGameID();
        CardColor card = ((SetFaceUpCardCommandData) info).getCard();
        int cardIndex = ((SetFaceUpCardCommandData)info).getCardIndex();


        clientFacade.setFaceUpCard(gameID, card, cardIndex);
    }
}
