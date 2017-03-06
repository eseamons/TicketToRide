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
    public int ownership; //0 = no one.  1-5 = players

    public Route(String city1, String city2, CardColor color, int length)
    {
        this.city1 = city1;
        this.city2 = city2;
        this.color = color;
        this.length = length;
        this.ownership = 0;
    }

    public int getPointValue()
    {
        int pointValue = 0;
        switch (length)
        {
            case 1: pointValue = 1;
                break;
            case 2: pointValue = 2;
                break;
            case 3: pointValue =4;
                break;
            case 4: pointValue = 7;
                break;
            case 5: pointValue = 10;
                break;
            case 6: pointValue = 15;
                break;
        }
        return pointValue;
    }
}
