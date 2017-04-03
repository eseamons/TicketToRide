package shared.model_classes;

import java.util.ArrayList;

/**
 * Created by mepeter on 4/3/17.
 */

public class RouteNode
{
    private String city1;
    private String city2;
    public RouteNode()
    {
        neighbors = new ArrayList();
    }

    public void setCities(String c1, String c2)
    {
        city1 = c1;
        city2 = c2;
    }
    ArrayList<String> neighbors;
    public void addNeighbor(String neighbor)
    {
        neighbors.add(neighbor);
    }

    public boolean hasNeighbor(String cont)
    {
        return neighbors.contains(cont);
    }

    public ArrayList<String> getNeighbors()
    {
        return neighbors;
    }






}
