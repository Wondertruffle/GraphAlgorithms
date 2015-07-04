/**
 * Created by Drew on 7/2/2015.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
public class SocialDigraphTester {

    public static void main(String[] args)
    {
        File graphFile = new File("src/people.csv");
        WeightedSocialDigraph graph = parseGraphFile(graphFile);
        if(graph == null) return;
        System.out.println("Number of vertices: " + graph.getVertices().size());
        System.out.println("Number of edges: " + graph.getEdges().size());

        List<Integer> path = GraphLibrary.shortestPath(graph, 0, 11);
        System.out.println("Shortest path from 0 to 11: ");
        System.out.println(path.toString());
    }

    public static WeightedSocialDigraph parseGraphFile(File file)
    {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e)
        {
            System.out.println("There was a problem opening the specified file.  More details:");
            System.out.println(e.getMessage());
            return null;
        }


        WeightedSocialDigraph graph = new WeightedSocialDigraph();
        String currentLine;
        try {
            while(!(currentLine = reader.readLine()).equals("EDGES:"))
            {
                if(currentLine.toCharArray()[0] == '#') continue; //line is a comment and should be skipped

                String[] personData = currentLine.split(",");
                if(personData.length != 4)
                {
                    System.out.println("Malformed vertex data line:");
                    System.out.println(currentLine);
                    return null;
                }

                String personName;
                int personAge;
                double personHeight;
                double personWeight;

                try {
                    personName = personData[0];
                    personAge = Integer.parseInt(personData[1]);
                    personHeight = Double.parseDouble(personData[2]);
                    personWeight = Double.parseDouble(personData[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing numerical data in line:");
                    System.out.println(currentLine);
                    return null;
                }

                Person personToAdd = new Person(personName, personHeight, personWeight, personAge);
                graph.addVertex(personToAdd);
            }

            while((currentLine = reader.readLine()) != null)
            {
                if(currentLine.toCharArray()[0] == '#') continue;

                String[] edgeData = currentLine.split(",");

                if(edgeData.length != 3)
                {
                    System.out.println("Malformed line:");
                    System.out.println(currentLine);
                    return null;
                }
                int srcID;
                int destID;
                double weight;
                try {
                    srcID = Integer.parseInt(edgeData[0]);
                    destID = Integer.parseInt(edgeData[1]);
                    weight = Double.parseDouble(edgeData[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing numerical data in line:");
                    System.out.println(currentLine);
                    return null;
                }

                graph.addEdge(srcID,destID,weight);
            }
        } catch (IOException e) {
            System.out.println("There was a problem reading a line from the file.  More details:");
            System.out.println(e.getMessage());
            return null;
        }

        return graph;
    }




}
