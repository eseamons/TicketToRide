package shared.model_classes;

/**
 * Created by Maynson on 3/1/2017.
 */

public class DestinationCard {

    private String city1;
    private String city2;
    private int points;
    private int ownership; //-1 = no one.  0-4 = players

    public DestinationCard(String city1, String city2, int points) {
        this.city1 = city1;
        this.city2 = city2;
        this.points = points;
        ownership = -1;
    }


    public void setOwnership(int playerID) {
        this.ownership = playerID;
    }

    public int getOwnership() {
        return this.ownership;
    }

    public String getDestinationCardName() {
        return city1 + " " + city2;
    }





}
