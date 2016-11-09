package snaproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONObject;

public class Movie {
	
	
	public static void main(String[] args) throws Exception{
		ArrayList<JSONObject> list = readFile("testinput.txt");
//		for(JSONObject o : list) {
//			System.out.println("Name: " + o.get("Title") + ", Year: " + o.get("Year"));
//		}
		for(String o : getAttributes(list.get(0))) {
			System.out.println(o);
		}
	}
	
	private static ArrayList<String> getTitles(JSONObject o) {
		Iterator<String> i = o.keys();
		
		ArrayList<String> list = new ArrayList<String>();
		while(i.hasNext()) {
			list.add(i.next());
		}
		return list;
	}
	
	private static ArrayList<String> getAttributes(JSONObject o) {
		Iterator<String> i = o.keys();
		ArrayList<String> list = new ArrayList<String>();
		while(i.hasNext()) {
			list.add(o.getString(i.next()));
		}
		return list;
	}
	
	private static ArrayList<JSONObject> readFile(String path) throws Exception{
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();

		Scanner in = new Scanner(new File(path));
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String json = readUrl("http://www.omdbapi.com/?t=" + s + "&y=&plot=short&r=json");
			list.add(new JSONObject(json));
		}
		in.close();
		return list;
	}
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}
