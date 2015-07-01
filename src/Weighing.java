/**
 * Created by Drew on 7/1/2015.
 */
public interface Weighing {

    /**
     * Returns the weight of the specified edge.
     * @param id The edge to be weighed.
     * @return The weight of the specified edge.
     * @throws IllegalArgumentException if <code>id</code> does not refer to a valid edge.
     */
    double weight(int id) throws IllegalArgumentException;
}
