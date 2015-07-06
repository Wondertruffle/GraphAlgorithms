/**
 * Created by Drew on 7/1/2015.
 */


import java.util.Set;

/**
 * Models an undirected graph consisting of a set of vertices and a set of edges between vertices.
 */
public abstract class Graph {

    protected static int currentID;

    public abstract Set<Integer> getVertices();

    public abstract Set<Integer> getEdges();

    public abstract Set<Integer> getNeighborsOf(int id) throws IllegalArgumentException;

    public abstract int addEdge(int srcID, int dstID) throws IllegalArgumentException;

    public abstract int addVertex();

}
