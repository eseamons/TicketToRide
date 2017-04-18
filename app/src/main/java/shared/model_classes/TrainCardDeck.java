package shared.model_classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import shared.CardColor;
import shared.ColorNum;

/**
 * Created by rebeccaredd on 2/28/17.
 * created so we could start working on the commands
 */
public class TrainCardDeck {

    private static TrainCardDeck instance = new TrainCardDeck();

    private List<CardColor> cardsInDeck = new ArrayList<>();
    private List<CardColor> discardDeck = new ArrayList<>();

    private TrainCardDeck() {
        for(int i =0; i < 12; i++)
        {
            cardsInDeck.add(CardColor.RED);
            cardsInDeck.add(CardColor.BLUE);
            cardsInDeck.add(CardColor.GREEN);
            cardsInDeck.add(CardColor.YELLOW);
            cardsInDeck.add(CardColor.BLACK);
            cardsInDeck.add(CardColor.WHITE);
            cardsInDeck.add(CardColor.ORANGE);
            cardsInDeck.add(CardColor.PURPLE);
        }

        for(int j =0; j <14; j++)
        {cardsInDeck.add(CardColor.WILD);}


    }

    public static TrainCardDeck getInstance() {
        return instance;
    }

    public CardColor drawCard() {

        if(cardsInDeck.size() == 0)
        {
            reShuffleDeck();
            if(cardsInDeck.size() == 0)
            {return CardColor.EMPTY;}
        }

        int min = 0;
        int max = cardsInDeck.size();
        Random r = new Random();
        int randomIndex = (int) (r.nextDouble() * (max - min) + min);

        CardColor cardColor =cardsInDeck.get(randomIndex);
        cardsInDeck.remove(randomIndex);

        if(cardsInDeck.size() == 0)
        {reShuffleDeck();}

        return cardColor;
    }

    public void addCardsToDiscard(List<CardColor> cardColors) {

        for(CardColor color :cardColors )
        {
            discardDeck.add(color);
        }
    }

    public void reShuffleDeck() {
        for(CardColor card: discardDeck)
        {
            cardsInDeck.add(card);
        }
        discardDeck = new ArrayList<>();
    }

    public int getDiscardPileSize()
    {return discardDeck.size();}

    public static void setInstance(TrainCardDeck instance) {
        TrainCardDeck.instance = instance;
    }

    public void setCardsInDeck(List<CardColor> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public void setDiscardDeck(List<CardColor> discardDeck) {
        this.discardDeck = discardDeck;
    }
}
