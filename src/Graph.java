/**
 * Created by Drew on 7/1/2015.
 */


import java.util.Set;

/**
 * Interface for a graph in which each vertex has an associated data object of type V and each edge has an associated
 * attribute of type E.  Each vertex and edge is assigned a unique integer ID.  This interface specifies a directed
 * graph; an undirected graph can be modeled by implementing classes if they ensure that all edges are bidirectional.
 */
public interface Graph<V,E> {

    /**
     * Adds a vertex to the graph.
     * @param v The data associated with the edge to be added.
     * @return The ID of the newly-created vertex.
     */
    int addVertex(V v);

    /**
     * Adds an edge to the graph.
     * @param srcID The source of the edge.
     * @param dstID The destination of the edge.
     * @param e The attribute of the edge to be created.
     * @return The ID of the newly-created edge.
     * @throws IllegalArgumentException if either <code>srcID</code> or <code>dstID</code> do not refer to existing vertices.
     */
    int addEdge(int srcID, int dstID, E e) throws IllegalArgumentException;

    /**
     * Gets all the vertices in the graph.
     * @return A set containing the IDs of all vertices in the graph.
     */
    Set<Integer> getVertices();

    /**
     * Gets all the edges in the graph.
     * @return A set containing the IDs of all edges in the graph.
     */
    Set<Integer> getEdges();

    /**
     * Gets the data stored in the specified vertex.
     * @param id The ID of the vertex in question.
     * @return The data stored at the specified vertex.
     * @throws IllegalArgumentException if <code>id</code> does not refer to an existing vertex.
     */
    V getData(int id) throws IllegalArgumentException;

    /**
     * Gets the attribute of the specified edge.
     * @param id The ID of the edge in question.
     * @return The attribute stored in the specified edge.
     * @throws IllegalArgumentException if <code>id</code> does not refer to an existing edge.
     */
    E getAttribute(int id) throws IllegalArgumentException;

    /**
     * Gets the source vertex of the specified edge.
     * @param id The edge to return the source of.
     * @return The ID of the source vertex of the specified edge.
     * @throws IllegalArgumentException if <code>id</code> does not refer to an existing edge.
     */
    int getSource(int id) throws IllegalArgumentException;

    /**
     * Gets the destination vertex of the specified edge.
     * @param id The edge to return the destination of.
     * @return The ID of the destination vertex of the specified edge.
     * @throws IllegalArgumentException if <code>id</code> does not refer to an existing edge.
     */
    int getDest(int id) throws IllegalArgumentException;

    /**
     * Gets a set of all edges originating from the specified vertex.
     * @param id The ID of the vertex to return edges of.
     * @return A set of IDs of all edges originating from the specified vertex.
     * @throws IllegalArgumentException if <code>id</code> does not refer to an existing vertex.
     */
    Set<Integer> getEdgesOf(int id) throws IllegalArgumentException;
}
