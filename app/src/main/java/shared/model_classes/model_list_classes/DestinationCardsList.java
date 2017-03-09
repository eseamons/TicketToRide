package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shared.model_classes.DestinationCard;

/**
 * Created by erics on 3/6/2017.
 */

public class DestinationCardsList {

    private List<DestinationCard> destinationCardsList;
    private List<DestinationCard> discardPile = new ArrayList<>();

    public DestinationCardsList() {
        destinationCardsList = new ArrayList<>();

        destinationCardsList.add(new DestinationCard("Los Angeles", "New York", 21));
        destinationCardsList.add(new DestinationCard("Duluth", "Houston", 8));
        destinationCardsList.add(new DestinationCard("Sault St Marie", "Nashville", 8));
        destinationCardsList.add(new DestinationCard("New York", "Atlanta", 6));
        destinationCardsList.add(new DestinationCard("Portland", "Nashville", 17));
        destinationCardsList.add(new DestinationCard("Vancouver", "Montreal", 20));
        destinationCardsList.add(new DestinationCard("Duluth", "El Paso", 10));
        destinationCardsList.add(new DestinationCard("Toronto", "Miami", 10));
        destinationCardsList.add(new DestinationCard("Portland", "Phoenix", 11));
        destinationCardsList.add(new DestinationCard("Dallas", "New York", 11));
        destinationCardsList.add(new DestinationCard("Calgary", "Salt Lake City", 7));
        destinationCardsList.add(new DestinationCard("Calgary", "Phoenix", 13));
        destinationCardsList.add(new DestinationCard("Los Angeles", "Miami", 20));
        destinationCardsList.add(new DestinationCard("Winnipeg", "Little Rock", 11));
        destinationCardsList.add(new DestinationCard("San Francisco", "Atlanta", 17));
        destinationCardsList.add(new DestinationCard("Kansas City", "Houston", 5));
        destinationCardsList.add(new DestinationCard("Los Angeles", "Chicago", 16));
        destinationCardsList.add(new DestinationCard("Denver", "Pittsburgh", 11));
        destinationCardsList.add(new DestinationCard("Chicago", "Santa Fe", 9));
        destinationCardsList.add(new DestinationCard("Vancouver", "Santa Fe", 13));
        destinationCardsList.add(new DestinationCard("Boston", "Miami", 12));
        destinationCardsList.add(new DestinationCard("Chicago", "New Orleans", 7));
        destinationCardsList.add(new DestinationCard("Montreal", "Atlanta", 9));
        destinationCardsList.add(new DestinationCard("Seattle", "New York", 22));
        destinationCardsList.add(new DestinationCard("Denver", "El Paso", 4));
        destinationCardsList.add(new DestinationCard("Helena", "Los Angeles", 8));
        destinationCardsList.add(new DestinationCard("Winnipeg", "Houston", 12));
        destinationCardsList.add(new DestinationCard("Montreal", "New Orleans", 13));
        destinationCardsList.add(new DestinationCard("Sault St Marie", "Oklahoma City", 9));
        destinationCardsList.add(new DestinationCard("Seattle", "Los Angeles", 9));


    }

    public boolean destinationCardIsOwned(String destinationCardName) {
        return false;
    }

    public DestinationCard setDestinationCardOwnership(String destinationCardName, int playerID ) {
        String[] destinations = destinationCardName.split(" ");
        String city1 = destinations[0];
        String city2 = destinations[1];
        return null;
    }

    public DestinationCard getDestinationCardByName(String destinationCardName) {
        return null;
    }

    public DestinationCard drawCard(String auth) {
        int min = 0;
        int max = destinationCardsList.size();
        Random r = new Random();
        int randomIndex = (int) (r.nextDouble() * (max - min) + min);

        DestinationCard card =destinationCardsList.get(randomIndex);
        card.setOwnership(auth);
        destinationCardsList.remove(randomIndex);

        return card;
    }

    public void discardCard(DestinationCard destinationCard) {
        discardPile.add(destinationCard);
    }
}
