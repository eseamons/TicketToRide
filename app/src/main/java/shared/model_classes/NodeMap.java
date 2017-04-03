package shared.model_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mepeter on 4/3/17.
 */

public class NodeMap
{
    Map<String, ArrayList<RouteNode>> dataNodes = new HashMap<>();
    public void addDataNode(RouteNode n)
    {
        String key1 = n.getCity1();
        String key2 = n.getCity2();
        if(!dataNodes.containsKey(key1))
        {
            dataNodes.put(key1, new ArrayList<RouteNode>());
        }
        if(!dataNodes.containsKey(key2))
        {
            dataNodes.put(key2, new ArrayList<RouteNode>());
        }
        dataNodes.get(key1).add(n);
        dataNodes.get(key2).add(n);
    }

    public boolean isReachableFrom(String city1, String city2)
    {

        markAllNodesAsNotVisited();
        if(!dataNodes.containsKey(city1) || !dataNodes.containsKey(city2))
        {
            return false;
        }
        else
        {
            return DFS(city1, city2);
        }
    }

    public void markAllNodesAsNotVisited()
    {
        for(String s: dataNodes.keySet())
        {
            ArrayList<RouteNode> nodeList = dataNodes.get(s);
            for(RouteNode rn : nodeList)
            {
                rn.cleanVisited();;
            }
        }
    }

    public boolean DFS(String cur, String target)
    {
        if(cur == target)
            return true;
        ArrayList<RouteNode> start = dataNodes.get(cur);
        for(RouteNode rn : start)
        {
            if(!rn.isVisited())
            {
                rn.markVisited();
                if(cur == rn.getCity1())
                {
                    return DFS(rn.getCity2(), target);
                }
                else
                {
                    return DFS(rn.getCity1(), target);
                }
            }
        }
        return false;
    }
}
