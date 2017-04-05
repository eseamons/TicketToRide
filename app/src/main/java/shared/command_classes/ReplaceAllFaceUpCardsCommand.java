package shared.command_classes;

import java.util.List;

import client.ClientFacade;
import server.ServerFacade;
import shared.CardColor;
import shared.Result;
import shared.command_data_classes.ReplaceAllFaceUpCardsCommandData;

/**
 * Created by rredd94 on 4/4/17.
 */

public class ReplaceAllFaceUpCardsCommand extends Command {

    @Override
    public void executeOnClient() {
        int gameID = ((ReplaceAllFaceUpCardsCommandData) info).getGameID();
        List<CardColor> newCards = ((ReplaceAllFaceUpCardsCommandData) info).getNewFaceUpCards();

        ClientFacade clientFacade = new ClientFacade();
        clientFacade.replaceAllFaceUpCards(gameID, newCards);

    }

    @Override
    public Result execute() {
        int gameID = ((ReplaceAllFaceUpCardsCommandData) info).getGameID();
        boolean success = ServerFacade.getInstance().replaceAllFaceUpCards(gameID);
        return new Result(success, "");
    }
}
