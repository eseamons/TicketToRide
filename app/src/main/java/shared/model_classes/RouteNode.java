package shared.model_classes;

import java.util.ArrayList;

/**
 * Created by mepeter on 4/3/17.
 */

public class RouteNode
{
    private String city1;
    private String city2;
    boolean visited = false;
    public RouteNode()
    {
        neighbors = new ArrayList();
    }

    public void markVisited()
    {
        visited = true;
    }

    public void cleanVisited()
    {
        visited = false;
    }

    public boolean isVisited()
    {
        return visited;
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

    public String getCity1()
    {
        return city1;
    }

    public String getCity2()
    {
        return city2;
    }

    public boolean isEqual(RouteNode r)
    {
        return city1 == r.city1 && city2 == r.city2;
    }






}
