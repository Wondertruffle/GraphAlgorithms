/**
 * Created by Drew on 7/1/2015.
 */
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * A library of graph algorithms.
 * @author Andrew Smith
 */
public class GraphLibrary {

    /**
     * Finds the shortest path between <code>src</code> and <code>dest</code> using Dijkstra's shortest-path algorithm.
     * Note that the current implementation assumes that there is indeed <i>some</i> path between <code>src</code>
     * and <code>dest</code>; as it stands, if they are located in different connected components of a disconnected
     * graph, this implementation will run forever.
     * @param input The graph on which the search is being performed.
     * @param src The source vertex of the path.
     * @param dest The destination vertex.
     * @param <V> Type of the data stored at each graph vertex; unused in the implementation.
     * @param <E> Type of the attribute of each graph edge; unused in the current implementation.
     * @param <G> Type of the input graph.  It is required that this not only implement the <code>Graph</code> interface
     *           but also the <code>Weighing</code> interface.
     * @return A list containing the IDs of the vertices on the shortest path from <code>src</code> to <code>dest</code>, in order.
     * @throws IllegalArgumentException if either <code>src</code> or <code>dest</code> are not vertices in <code>input</code>.
     */
    public static < V, E, G extends Graph<V,E> & Weighing > List<Integer> shortestPath(G input, int src, int dest) throws IllegalArgumentException
    {
        Set<Integer> vertices = input.getVertices();
        if(!vertices.contains(src)) throw new IllegalArgumentException(src + " is not a valid vertex ID in input graph.");
        if(!vertices.contains(dest)) throw new IllegalArgumentException(dest + " is not a valid vertex ID in input graph.");

        HashMap<Integer, Double> distances = new HashMap<>();
        HashMap<Integer, Integer> previous = new HashMap<>();
        previous.put(src,null);

        for(Integer i : vertices)
        {
            distances.put(i, Double.POSITIVE_INFINITY); //tentatively mark all vertices as infinitely far
        }
        distances.put(src,0.00); //however, the distance to source is clearly 0

        int currentNode = src;

        Set<Integer> unvisited = new HashSet<>();
        for(Integer i : vertices)
        {
            if(i == src) continue;
            unvisited.add(i);
        }

        while(unvisited.contains(dest))
        {
            Set<Integer> outEdges = input.getEdgesOf(currentNode);
            Set<Integer> unvisitedOutEdges = new HashSet<>();
            for(Integer i : outEdges)
            {
                if(unvisited.contains(input.getDest(i))) unvisitedOutEdges.add(i);
            }


            for(Integer i : unvisitedOutEdges)
            {
                double tentativeDistance = input.weight(i) + distances.get(currentNode);
                int target = input.getDest(i);
                if(tentativeDistance < distances.get(target))
                {
                    distances.put(target, tentativeDistance);
                    previous.put(target, currentNode);
                }
            }

            unvisited.remove(currentNode);
            int minimumDistanceNode = unvisited.iterator().next();
            for(Integer i : unvisited)
            {
                if(distances.get(minimumDistanceNode) > distances.get(i)) minimumDistanceNode = i;
            }
            currentNode = minimumDistanceNode;

        }

        /**
         * Build the list by reverse-iterating through previous.
         */
        currentNode = dest;
        LinkedList<Integer> path = new LinkedList<>();
        while(previous.get(currentNode) != null)
        {
            path.addFirst(previous.get(currentNode));
            currentNode = previous.get(currentNode);
        }


        return path;
    }

    public static <V,E> Graph<V,E> MSTPrim(Graph<V,E> input)
    {
        return null; //TODO
    }

    public static <V,E> Graph<V,E> MSTKruskal(Graph<V,E> input)
    {
        return null; //TODO
    }
}
