package snaproject;

import java.io.FileWriter;
import java.util.Arrays;

public class CSVUtilExample {

    public static void main(String[] args) throws Exception {

        String csvFile = "test.csv";
        FileWriter writer = new FileWriter(csvFile);

        //CSVUtils.writeLine(writer, Arrays.asList("a", "b", "c", "d"));

        //custom separator + quote
        CSVUtils.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), '\t');

        //custom separator + quote
        //CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc,c"), '|', '\'');

        //double-quotes
        //CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"));


        writer.flush();
        writer.close();

    }

}