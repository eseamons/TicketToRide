package shared.model_classes.model_list_classes;

import java.util.ArrayList;
import java.util.List;

import shared.model_classes.DestinationCard;

/**
 * Created by erics on 3/6/2017.
 */

public class DestinationCardsList {

    private List<DestinationCard> destinationCardsList;

    public DestinationCardsList() {
        destinationCardsList = new ArrayList<>();
        //TODO: add all possible destination cards.
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
}
