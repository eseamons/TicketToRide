package shared.model_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by mepeter on 4/3/17.
 */

public class NodeMap
{
    Map<String, ArrayList<RouteNode>> dataNodes = new HashMap<>();
    ArrayList<RouteNode> list = new ArrayList<>();
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

        list.add(n);
    }

    public boolean isReachableFrom(String city1, String city2)
    {

        markAllNodesAsNotVisited();
        if(!dataNodes.containsKey(city1) || !dataNodes.containsKey(city2))
        {
            return false;
        }
        for(RouteNode rn: list)
        {
            if(!rn.isVisited())
            {
                if(DFS(rn, city2))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private void markAllNodesAsNotVisited()
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

    public boolean DFS(RouteNode cur, String target)
    {
        if(cur.getCity1().equals(target) || cur.getCity2().equals(target))
        {
            return true;
        }
        else
        {
            cur.markVisited();
            for(RouteNode rn: cur.getNeighbors())
            {
                if(DFS(rn,target))
                {
                    return true;
                }
            }
            return false;
        }
    }
    /*
    private boolean DFS(String cur, String target)
    {
        if(cur.equals(target))
            return true;
        ArrayList<RouteNode> start = dataNodes.get(cur);
        for(RouteNode rn : start)
        {
            if(!rn.isVisited())
            {
                rn.markVisited();
                if(cur.equals(rn.getCity1()))
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
    */

    public int findLargestRoute()
    {
        fillInNeighbors();
        int longest = 0;
        for(RouteNode rn : list)
        {
                markAllNodesAsNotVisited();
                int cur = Dijkstras(rn);
                if(cur > longest)
                {
                    longest = cur;
                }
        }
        return longest;
    }

    Map<RouteNode, Integer> node_to_length;
    MichaelArrayHeap queue;
    RouteNode start;
    private int Dijkstras(RouteNode rn)
    {
        node_to_length = new HashMap<>();
        queue = new MichaelArrayHeap();
        queue.insert(rn);
        start = rn;
        node_to_length.put(rn,rn.getLength());
        rn.markVisited();
        while(!queue.isEmpty())
        {
            RouteNode current_node = queue.remove();
            current_node.markVisited();
            visitNeighbors(current_node);
        }

        //find the biggest total length after done calculating and return it
        int longest = -1;
        for(RouteNode rnm: node_to_length.keySet())
        {
            int cur_length = node_to_length.get(rnm);
            if(cur_length > longest)
            {
                longest = cur_length;
            }
        }
        return longest;
    }

    private void visitNeighbors(RouteNode cur)
    {
        for(RouteNode rn : cur.getNeighbors())
        {
            int potential_lenth = node_to_length.get(cur) + rn.getLength();
            if(!rn.isVisited())
            {
                if(!node_to_length.containsKey(rn))
                {
                    queue.insert(rn);
                    node_to_length.put(rn,potential_lenth);
                }
                else
                {
                    int cur_length = node_to_length.get(rn);
                    if(potential_lenth > cur_length)
                    {
                        node_to_length.put(rn, potential_lenth);
                    }
                }

                //System.out.println(rn.getCity1() + " " + rn.getCity2() + " Now:" + potential_lenth);
            }

        }
    }

    private boolean isNeighbor(RouteNode r1, RouteNode r2)
    {
        return r1.getCity1().equals(r2.getCity1()) || r1.getCity1().equals(r2.getCity2()) || r1.getCity2().equals(r2.getCity1()) || r1.getCity2().equals(r1.getCity2());
    }

    private void fillInNeighbors()
    {
        for(int i = 0; i < list.size(); i++)
        {
            for(int j = 0; j < list.size(); j++)
            {
                if(j != i)
                {
                    RouteNode node1 = list.get(i);
                    RouteNode node2 = list.get(j);
                    if(isNeighbor(node1,node2))
                    {
                        node1.addNeighbor(node2);
                        node2.addNeighbor(node1);
                    }
                }
            }
        }
    }
}
