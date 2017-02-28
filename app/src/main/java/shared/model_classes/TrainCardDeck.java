package shared.model_classes;

import java.util.List;

import shared.ColorNum;

/**
 * Created by rebeccaredd on 2/28/17.
 * created so we could start working on the commands
 */
public class TrainCardDeck {

    private static TrainCardDeck instance = new TrainCardDeck();

    private List<ColorNum> cardsInDeck;

    private TrainCardDeck()
    {
        //TODO:create colors in the Deck.
    }

    public static TrainCardDeck getInstance() {
        return instance;
    }

}
