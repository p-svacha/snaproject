package snaproject;

import java.io.FileWriter;
import java.util.ArrayList;

import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws Exception {

        String csvFile = "test.csv";
        FileWriter writer = new FileWriter(csvFile);
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list = Movie.readFile("full_input.txt");

        
        //CSVUtils.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), '\t');
        CSVUtils.writeLine(writer, Movie.getTitles(list.get(0)), '\t');
        for(JSONObject o : list) {
        	if(o.length() == 20) CSVUtils.writeLine(writer, Movie.getAttributes(o), '\t');
        	else System.out.println("######################INVALID LENGTH###################");
        }



        writer.flush();
        writer.close();

    }

}