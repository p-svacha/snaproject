package snaproject;

import java.util.ArrayList;
import java.util.List;

public class Edge {

	private String actor;
	private String genre;
	private int movies;
	private float imdbRatingTotal;
	
	public Edge(String actor, String genre, int movies, float imdbRating) {
		super();
		this.actor = actor;
		this.genre = genre;
		this.movies = movies;
		this.imdbRatingTotal = imdbRating;
	}
	
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getMovies() {
		return movies;
	}
	public void setMovies(int movies) {
		this.movies = movies;
	}
	
	public void addImbdRating(float rating){
		imdbRatingTotal += rating;
	}
	
	public float getImdbRating() {
		return imdbRatingTotal / movies;
	}
	public void setImdbRating(float imdbRating) {
		this.imdbRatingTotal = imdbRating;
	}
	
	public boolean equals(String actor, String genre) {
		return actor.equals(this.actor) && genre.equals(this.genre);
	}
	
	public List<String> toStringList() {
		List<String> list = new ArrayList<String>();
		list.add(actor);
		list.add(genre);
		list.add(movies + "");
		list.add(imdbRatingTotal / movies + "");
		
		return list;
	}
	
}
