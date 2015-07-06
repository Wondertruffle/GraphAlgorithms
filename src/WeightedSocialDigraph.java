import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
/**
 * Created by Drew on 7/1/2015.
 */

/**
 * A class which represents a social network as a weighted directed graph.  Each vertex represents a person.  Each edge
 * represents acquaintanceship in the following manner: if there is an edge A to B with weight 2, A knows B with affinity
 * 2.  The weights on the edges can be interpreted as inversely proportional to the strength of the bond; for example,
 * if A is married to B we might have edges A to B with weight 0.1, B to A with weight 0.1.  Likewise, if A met B once at a
 * party, but B doesn't remember A, we might have A to B with weight 12.5 and no edge B to A.
 */
public class WeightedSocialDigraph extends DataDigraph<Person,Double> implements Weighing {

    /**
     * The ID of the next vertex or edge to be added.
     */
    private static int currentID = 0;

    /**
     * A mapping from vertex ID to vertex data.
     */
    private Map<Integer, Person> vertices;

    /**
     * A mapping from edge ID to edge object.
     */
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

        //check if edge already exists in the graph
        for(Integer i : edges.keySet())
        {
            WeightedDigraphEdge e = edges.get(i);
            if(e.getSrcID() == srcID && e.getDestID() == dstID)
            {
                System.out.println("Edge from " + srcID + " to " + dstID + " already exists with weight " + e.getWeight());
                return i;
            }
        }

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
    public int getSrc(int id) throws IllegalArgumentException {
        if(!edges.containsKey(id)) throw new IllegalArgumentException(id + " is not a valid edge ID.");
        return edges.get(id).getSrcID();
    }



    @Override
    public int getDest(int id) throws IllegalArgumentException {
        if(!edges.containsKey(id)) throw new IllegalArgumentException(id + " is not a valid edge ID.");
        return edges.get(id).getDestID();
    }

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

    @Override
    public double weight(int id) throws IllegalArgumentException {
        try
        {
            return getAttribute(id);
        } catch (IllegalArgumentException e)
        {
            throw e;
        }
    }

    @Override
    public Set<Integer> getNeighborsOf(int id)
    {
        return new HashSet<Integer>(); //TODO
    }

    @Override
    public int addEdge(int srcID, int dstID) throws IllegalArgumentException {
        return addEdge(srcID, dstID, 0.00);
    }

    @Override
    public int addVertex() {
        return addVertex(new Person("Default Name", 0.0, 0.0, 0));
    }
}
