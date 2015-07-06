/**
 * Created by Drew on 7/4/2015.
 */
public abstract class Digraph extends Graph {

    public abstract int getSrc(int id) throws IllegalArgumentException;

    public abstract int getDest(int id) throws IllegalArgumentException;
}
