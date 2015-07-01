import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
/**
 * Created by Drew on 7/1/2015.
 */

/**
 * A class which represents a social network as a weighted directed graph.  Each vertex represents a person.  Each edge
 * represents acquaintanceship in the following manner: if there is an edge A -> B with weight 2, A knows B with affinity
 * 2.  The weights on the edges can be interpreted as inversely proportional to the strength of the bond; for example,
 * if A is married to B we might have edges A->B with weight 0.1, B->A with weight 0.1.  Likewise, if A met B once at a
 * party, but B doesn't remember A, we might have A->B with weight 12.5 and no edge B->A.
 */
public class WeightedSocialDigraph implements Graph<Person,Double> {

    private static int currentID = 0;
    private Map<Integer, Person> vertices;
    private Map<Integer, WeightedDigraphEdge> edges;

    public WeightedSocialDigraph()
    {
        vertices = new HashMap<>();
        edges = new HashMap<>();
    }

    @Override
    public int addVertex(Person person) {
        vertices.put(currentID++, person);
        return currentID - 1;
    }

    @Override
    public int addEdge(int srcID, int dstID, Double aDouble) throws IllegalArgumentException {
        if(!vertices.containsKey(srcID)) throw new IllegalArgumentException(srcID + " is not a valid vertex ID.");
        if(!vertices.containsKey(dstID)) throw new IllegalArgumentException(dstID + " is not a valid vertex ID.");

        WeightedDigraphEdge edge = new WeightedDigraphEdge(srcID, dstID, aDouble);
        edges.put(currentID++, edge);

        return currentID - 1;
    }

    @Override
    public Set<Integer> getVertices() {
        return vertices.keySet();
    }

    @Override
    public Set<Integer> getEdges() {
        return edges.keySet();
    }

    @Override
    public Person getData(int id) throws IllegalArgumentException {
        if(!vertices.containsKey(id)) throw new IllegalArgumentException(id + " is not a valid vertex ID.");
        return vertices.get(id);
    }

    @Override
    public Double getAttribute(int id) throws IllegalArgumentException {
        if(!edges.containsKey(id)) throw new IllegalArgumentException(id + " is not a valid edge ID.");

        return edges.get(id).getWeight();
    }

    @Override
    public int getSource(int id) throws IllegalArgumentException {
        if(!edges.containsKey(id)) throw new IllegalArgumentException(id + " is not a valid edge ID.");
        return edges.get(id).getSrcID();
    }

    @Override
    public int getDest(int id) throws IllegalArgumentException {
        if(!edges.containsKey(id)) throw new IllegalArgumentException(id + " is not a valid edge ID.");
        return edges.get(id).getDestID();
    }

    @Override
    public Set<Integer> getEdgesOf(int id) throws IllegalArgumentException {
        if(!vertices.containsKey(id)) throw new IllegalArgumentException(id + " is not a valid vertex ID.");

        Set<Integer> edgeSet = edges.keySet();
        HashSet<Integer> result = new HashSet<>();
        for(Integer i : edgeSet)
        {
            if(edges.get(i).getSrcID() == id) result.add(i);
        }
        return result;
    }
}
