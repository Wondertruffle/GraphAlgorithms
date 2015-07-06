/**
 * Created by Drew on 7/4/2015.
 */
public abstract class DataDigraph<V,E> extends DataGraph<V,E> {

    public abstract int getSrc(int id) throws IllegalArgumentException;

    public abstract int getDest(int id) throws IllegalArgumentException;
}
