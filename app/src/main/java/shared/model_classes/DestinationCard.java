package shared.model_classes;

/**
 * Created by Maynson on 3/1/2017.
 */

public class DestinationCard {

    private String city1;
    private String city2;
    private int points;
    private String ownershipAuth;

    public DestinationCard(String city1, String city2, int points) {
        this.city1 = city1;
        this.city2 = city2;
        this.points = points;
        ownershipAuth = null;
    }


    //setters
    public void setOwnership(String auth) {
        this.ownershipAuth = auth;
    }


    //getters
    public String getOwnership() {
        return this.ownershipAuth;
    }

    public int getPoints() {
        return points;
    }

    public String getDestinationCardName() {
        return city1 + " " + city2;
    }

    public String toString()
    {
        return city1 + " to " + city2 + ": " + points + " points";
    }




}
