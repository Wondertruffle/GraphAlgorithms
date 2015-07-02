/**
 * Created by Drew on 7/1/2015.
 */
public class Person {
    private String name;
    private double height;
    private double weight;
    private int age;

    public Person(String name, double height, double weight, int age) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public String getName()
    {
        return this.name;
    }

    public double getHeight()
    {
        return this.height;
    }

    public double getWeight()
    {
        return this.weight;
    }

    public double getAge()
    {
        return this.age;
    }

    @Override
    public String toString()
    {
        return name;
    }



}
