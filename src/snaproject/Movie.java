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
	
	
	static ArrayList<String> getTitles(JSONObject o) {
		Iterator<String> i = o.keys();
		
		ArrayList<String> list = new ArrayList<String>();
		while(i.hasNext()) {
			list.add(i.next());
		}
		return list;
	}
	
	static ArrayList<String> getAttributes(JSONObject o) {
		Iterator<String> i = o.keys();
		ArrayList<String> list = new ArrayList<String>();
		while(i.hasNext()) {
			String s = i.next();
			list.add(o.getString(s));
		}
		return list;
	}
	
	static ArrayList<JSONObject> readFile(String path) throws Exception{
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();

		Scanner in = new Scanner(new File(path));
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			s = s.replaceAll(" ", "%20");
			
			String json = readUrl("http://www.omdbapi.com/?t=" + s + "&y=&plot=short&r=json");
			JSONObject obj = new JSONObject(json);
			if(!obj.keys().next().equals("Response")) {
				System.out.println(s);
				list.add(obj);
			}
			else {
				System.out.println("-----------DELETED---------------");
			}
		}
		in.close();
		return list;
	}
	
	static String readUrl(String urlString) throws Exception {
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
