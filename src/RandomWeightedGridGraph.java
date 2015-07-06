import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Drew on 7/4/2015.
 */
public class RandomWeightedGridGraph extends Graph implements Weighing {

    private HashSet<Integer> vertices;
    private HashMap<Integer, Set<Integer>> neighbors;
    private HashMap<Integer, Double> weights;
    private Random generator;

    private int width;
    private int height;

    public RandomWeightedGridGraph(int width, int height)
    {
        currentID = 0;
        this.width = width;
        this.height = height;
        vertices = new HashSet<>();
        weights = new HashMap<>();
        neighbors = new HashMap<>();
        generator = new Random();
        generateGraph();
    }

    @Override
    public Set<Integer> getVertices() {
        return vertices;
    }

    @Override
    public Set<Integer> getEdges() {
        return weights.keySet();
    }

    @Override
    public Set<Integer> getNeighborsOf(int id) throws IllegalArgumentException {
        if(!vertices.contains(id)) throw new IllegalArgumentException(id + " is not a valid vertex ID.");
        return neighbors.get(id);
    }

    @Override
    public int addEdge(int srcID, int dstID) throws IllegalArgumentException {
        if(!vertices.contains(srcID)) throw new IllegalArgumentException(srcID + " is not a valid vertex ID.");
        if(!vertices.contains(dstID)) throw new IllegalArgumentException(dstID + " is not a valid vertex ID.");
        int thisID = currentID++;

        double weight = generator.nextDouble();
        weights.put(thisID, weight);

        Set<Integer> newSrcNeighbors = neighbors.get(srcID);
        newSrcNeighbors.add(dstID);
        neighbors.put(srcID, newSrcNeighbors);

        Set<Integer> newDstNeighbors = neighbors.get(dstID);
        newDstNeighbors.add(srcID);
        neighbors.put(dstID, newDstNeighbors);

        return thisID;
    }

    @Override
    public int addVertex() {
        int thisID = currentID++;

        vertices.add(thisID);
        neighbors.put(thisID, new HashSet<>());

        return thisID;
    }

    @Override
    public double weight(int id) throws IllegalArgumentException {
        if(!weights.keySet().contains(id)) throw new IllegalArgumentException(id + " is not a valid edge ID.");
        return weights.get(id);
    }

    private void generateGraph()
    {
        // add vertices
        int numVertices = width * height;
        for(int i = 0; i < numVertices; i++)
        {
            addVertex();
        }

        // add edges
        for(Integer i : vertices)
        {
            //handles the corner vertices
            if(i ==0)
            {
                addEdge(i, i+1);
                addEdge(i, i + width);
                continue;
            } else if (i == width * (height - 1))
            {
                addEdge(i, i + 1);
                addEdge(i, i - width);
                continue;
            } else if (i == width - 1)
            {
                addEdge(i, i - 1);
                addEdge(i, i + width);
                continue;
            } else if (i == (width * height) - 1)
            {
                addEdge(i, i - 1);
                addEdge(i, i - width);
                continue;
            }


            if(i % width == 0) //handles the left boundary
            {
                addEdge(i, i + width);
                addEdge(i, i - width);
                addEdge(i, i + 1);
                continue;
            } else if (i % width == width - 1) //handles the right boundary
            {
                addEdge(i, i + width);
                addEdge(i, i - width);
                addEdge(i, i - 1);
                continue;
            }

            if(i > 0 && i < width - 1) //handles the bottom boundary
            {
                addEdge(i, i - 1);
                addEdge(i, i + 1);
                addEdge(i, i + width);
                continue;
            } else if (i > width * (height - 1) && i < (width * height) - 1) //handles the top boundarey
            {
                addEdge(i, i - 1);
                addEdge(i, i + 1);
                addEdge(i, i - width);
                continue;
            }

            //default behavior for internal vertices
            addEdge(i, i - 1);
            addEdge(i, i + 1);
            addEdge(i, i - width);
            addEdge(i, i + width);



        }

    }
}
