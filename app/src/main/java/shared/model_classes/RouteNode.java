package shared.model_classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mepeter on 4/3/17.
 */

public class RouteNode
{
    private String city1;
    private String city2;
    private int length;
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


    public void setLength(int l)
    {
        length = l;
    }

    public int getLength()
    {
        return length;
    }

    public void setCities(String c1, String c2)
    {
        city1 = c1;
        city2 = c2;
    }
    ArrayList<RouteNode> neighbors;
    public void addNeighbor(RouteNode neighbor)
    {
        if(!neighbors.contains(neighbor) && !isEqual(neighbor))
        {
            neighbors.add(neighbor);
        }
    }

    public boolean hasNeighbor(String cont)
    {
        return neighbors.contains(cont);
    }

    public ArrayList<RouteNode> getNeighbors()
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
