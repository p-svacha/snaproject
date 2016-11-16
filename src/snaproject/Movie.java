package snaproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONObject;

public class Movie {
	
	static Set<String> actorSet = new HashSet<>();
	static Set<String> genreSet = new HashSet<>();
	static List<Edge> edgeList = new LinkedList<>();
	
	
	static List<List<String>> getSetAsListOfLists(Set<String> set, int pos){
		List<List<String>> listList = new ArrayList<List<String>>();
		
		for(String s : set){
			List<String> list = new ArrayList<String>();
			if(pos == 0){
				list.add(s);
				list.add("Actor");
			}
			else{
				list.add(s);
				list.add("Genre");
			}
			listList.add(list);
		}
		
		return listList;
	}
	
	
	static ArrayList<String> getNodeTitles() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("id");
		list.add("type");
		return list;
	}
	
	static ArrayList<String> getEdgeTitles() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("source");
		list.add("target");
		list.add("movies");
		list.add("imdbRating");
		return list;
	}
	
	static void addAttributes(JSONObject o) {
		if(!"N/A".equals(o.getString("imdbRating"))){
			String[] actors = o.getString("Actors").split(", ");
			String[] genres = o.getString("Genre").split(", ");
			float imdbRating = Float.valueOf(o.getString("imdbRating"));
			
			actorSet.addAll(Arrays.asList(actors));
			genreSet.addAll(Arrays.asList(genres));
			
			boolean exists = false;
			for(String actor : actors){
				for(String genre : genres){
					exists = false;
					for(Edge e : edgeList){
						if(e.equals(actor, genre)){
							e.setMovies(e.getMovies()+1);
							e.addImbdRating(imdbRating);
							exists = true;
							break;
						}
					}
					if(!exists){
						edgeList.add(new Edge(actor, genre, 1, imdbRating));
					}
				}
			}
		}
	}
	
	static ArrayList<JSONObject> readFile(String path) throws Exception{
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();

		Scanner in = new Scanner(new File(path));
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(s.contains(", The")) {
				s = s.replace(", The", "");
				s = "The ".concat(s);
			}
			else if(s.contains(", An")) {
				s = s.replace(", An", "");
				s = "An ".concat(s);
			}
			else if(s.contains(", A")) {
				s = s.replace(", A", "");
				s = "A ".concat(s);
			}
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
