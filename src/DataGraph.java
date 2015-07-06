/**
 * Created by Drew on 7/4/2015.
 */
public abstract class DataGraph<V,E> extends Graph {

    public abstract int addVertex(V v);

    public abstract int addEdge(int srcID, int dstID, E attribute) throws IllegalArgumentException;

    public abstract V getData(int id) throws IllegalArgumentException;

    public abstract E getAttribute(int id) throws IllegalArgumentException;
}
