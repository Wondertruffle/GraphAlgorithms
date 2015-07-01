/**
 * Created by Drew on 7/1/2015.
 */
public class WeightedDigraphEdge {

    private int srcID;
    private int destID;
    private double weight;

    public WeightedDigraphEdge(int srcID, int destID, double weight)
    {
        this.srcID = srcID;
        this.destID = destID;
        this.weight = weight;
    }

    public int getSrcID()
    {
        return this.srcID;
    }

    public int getDestID()
    {
        return this.destID;
    }

    public double getWeight()
    {
        return this.weight;
    }

}
