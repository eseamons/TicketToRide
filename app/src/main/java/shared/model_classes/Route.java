package shared.model_classes;

import shared.CardColor;

/**
 * Created by Maynson on 3/1/2017.
 */

public class Route {
    public String city1;
    public String city2;
    public CardColor color;
    public int length;
    public int ownership; //-1 = no one.  0-4 = players

    public Route(String city1, String city2, CardColor color, int length)
    {
        this.city1 = city1;
        this.city2 = city2;
        this.color = color;
        this.length = length;
        this.ownership = -1;
    }
}
