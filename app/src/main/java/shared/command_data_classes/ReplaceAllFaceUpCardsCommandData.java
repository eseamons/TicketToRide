package shared.command_data_classes;

import java.util.ArrayList;
import java.util.List;

import shared.CardColor;

/**
 * Created by rredd94 on 4/4/17.
 */

public class ReplaceAllFaceUpCardsCommandData {
    int gameID;
    List<CardColor> newFaceUpCards = new ArrayList<>();

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public List<CardColor> getNewFaceUpCards() {
        return newFaceUpCards;
    }

    public void setNewFaceUpCards(List<CardColor> newFaceUpCards) {
        this.newFaceUpCards = newFaceUpCards;
    }
}
