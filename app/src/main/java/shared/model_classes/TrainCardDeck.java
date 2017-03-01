package shared.model_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shared.ColorNum;

/**
 * Created by rebeccaredd on 2/28/17.
 * created so we could start working on the commands
 */
public class TrainCardDeck {

    private static TrainCardDeck instance = new TrainCardDeck();

    private List<ColorNum> cardsInDeck = new ArrayList<>();
    private List<ColorNum> discardDeck = new ArrayList<>();

    //TODO: should we add a discard deck in here or in another class?
    //might be easier here so that you can keep the reshuffle in one place.

    private TrainCardDeck() {
        //TODO:create colors in the Deck.
        for(int i =0; i < 12; i++)
        {
            cardsInDeck.add(ColorNum.RED);
            cardsInDeck.add(ColorNum.BLUE);
            cardsInDeck.add(ColorNum.GREEN);
            cardsInDeck.add(ColorNum.YELLOW);
            cardsInDeck.add(ColorNum.BLACK);
            //cardsInDeck.add(ColorNum.WHITE);
            //cardsInDeck.add(ColorNum.ORANGE);
            //cardsInDeck.add(ColorNum.PURPLE);
        }
        for(int j =0; j <14; j++)
        {
            //cardsInDeck.add(ColorNum.WILD);
        }


    }

    public static TrainCardDeck getInstance() {
        return instance;
    }

    public ColorNum drawCard() {
        int min = 0;
        int max = cardsInDeck.size();
        int randomIndex = (int) Math.random()*(max-min)+min;

        ColorNum cardColor =cardsInDeck.get(randomIndex);
        cardsInDeck.remove(randomIndex);

        return cardColor;
    }

    public void addCardsToDiscard(List<ColorNum> cardColors) {

        for(ColorNum color :cardColors )
        {
            discardDeck.add(color);
        }
    }

    public void reShuffleDeck() {
        for(ColorNum card: discardDeck)
        {
            cardsInDeck.add(card);
        }
        discardDeck = new ArrayList<>();
    }

}
