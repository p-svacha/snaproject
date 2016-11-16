package snaproject;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws Exception {

        String csvFile1 = "nodes.csv";
        String csvFile2 = "edges.csv";
        
        FileWriter writer1 = new FileWriter(csvFile1);
        FileWriter writer2 = new FileWriter(csvFile2);
        
        ArrayList<JSONObject> list = Movie.readFile("full_input.txt");

        for(JSONObject o : list) {
        	if(o.length() == 20) Movie.addAttributes(o);
        	else System.out.println("######################INVALID LENGTH###################");
        }

        //CSVUtils.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), '\t');
        CSVUtils.writeLine(writer1, Movie.getNodeTitles(), '\t');
        for(List<String> l : Movie.getSetAsListOfLists(Movie.actorSet, 0)){
        	CSVUtils.writeLine(writer1, l, '\t');
        }
        for(List<String> l : Movie.getSetAsListOfLists(Movie.genreSet, 1)){
        	CSVUtils.writeLine(writer1, l, '\t');
        }
        
        
        CSVUtils.writeLine(writer2, Movie.getEdgeTitles(), '\t');
        for(Edge e : Movie.edgeList){
        	CSVUtils.writeLine(writer2, e.toStringList(), '\t');
        }
        
        
        writer1.flush();
        writer1.close();
        writer2.flush();
        writer2.close();

    }

}