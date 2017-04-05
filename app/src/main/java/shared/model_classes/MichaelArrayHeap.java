package shared.model_classes;

import java.util.ArrayList;

/**
 * Created by Michaels on 4/4/2017.
 */

public class MichaelArrayHeap
{
    RouteNode[] list;

    //Implementation of a Max_Heap using array heap style
    public MichaelArrayHeap()
    {
        list = new RouteNode[99];
    }
    int size = 0;
    public void insert(RouteNode rn)
    {
        list[size] = rn;
        size++;
    }

    public RouteNode remove()
    {
        int index_of_largest = -1;
        int length_of_longest = -1;
        for(int i = 0; i < size; i++)
        {
            RouteNode rn = list[i];
            int cur_length = rn.getLength();
            if(cur_length > length_of_longest)
            {
                length_of_longest = cur_length;
                index_of_largest = i;
            }
        }
        RouteNode best_Node = list[index_of_largest];
        size--;
        list[index_of_largest] = list[size];
        return best_Node;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }
}
