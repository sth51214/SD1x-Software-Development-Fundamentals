package moviedatabase;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MovieDatabase {
	
	// Class instances
	private ArrayList<Movie> movieList; // an ArrayList of movies
	private ArrayList<Actor> actorList; // an ArrayList of actors
	
	
	/**The movie database constructor. 
	 * It creates a new movieList and a new actorList.
	 * At the time of construction, both of 
	 * these lists will be empty.
	 */
	public MovieDatabase() {
		this.movieList = new ArrayList<>();
		this.actorList = new ArrayList<>();
	}
	
	
	/**
     * Getter for the movies in the database
     * @return an ArrayList of all the movies in the
     * database
     */
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	
	/**
     * Getter for the actors in the database
     * @return an ArrayList of all the actors in the
     * 			database
     */
	public ArrayList<Actor> getActorList() {
		return actorList;
	}
	
	
	/**
     * Add a movie to the database
     * @param name String for the name of the movie
     * @param actors Array of Strings for the list of
     *               actors in that movie.
     */
	public void addMovie(String name, String[] actors) {
		
		ArrayList<Actor> movieActors = new ArrayList<>(); // list of actors in the movie
		Movie newMovie = new Movie(name, movieActors);
		boolean addedMovie = false;
		for(Movie movie : this.movieList) {
			if(movie.getName().equals(newMovie.getName())) {
				addedMovie = true;
			}
		}
		if(addedMovie == false) {
			ArrayList<Movie> actorMovies = new ArrayList<>(); //list of movies with actor in
			this.movieList.add(newMovie);
			actorMovies.add(newMovie);
			for(String actorName: actors) {
				Actor newActor = new Actor(actorName, actorMovies);
				movieActors.add(newActor);
				boolean added = false;
				for(Actor actor : this.actorList) {
					if (actor.getName().equals(newActor.getName())) {
						added = true;
					}
				}
				if(added == false) this.actorList.add(newActor);
			}
		}
	}
	
	
	/**
     * Add a rating to a particular movie
     * @param name The String name of the movie to be
     *             rated
     * @param rating The double representing the rating
     *               value
     */
	public void addRating(String name, double rating) {
		for(Movie movie: this.movieList) {
			if(movie.getName().equals(name)) {
				movie.setRating(rating);
			}
		}
	}
	
	
	/**
     * Update a particular movie's rating
     * @param name The String name of the movie
     * @param newRating The double representing the new
     *                  value for the movie's rating
     */
	public void updateRating(String name, double newRating) {
		for(Movie movie: this.movieList) {
			if(movie.getName().equals(name)) movie.setRating(newRating);
		}
	}
	
	
	/**
     * Get the actor with the best average rating
     * for their movies
     * @return String of the name of the actor
     */
	public String getBestActor() {
		double max = 0;
		String bestActor = "";
		for(Actor actor : this.actorList) {
			double averageRating = 0;
			for(Movie movie : actor.getMovies()) {
				averageRating += movie.getRating() / actor.getMovies().size();
			}
			if(averageRating > max) {
				max = averageRating;
				bestActor = actor.getName();
			}
		}
		return bestActor;
	}
	
	
	/**
     * Get the movie with the best rating
     * @return String of the name of the actor
     */
	public String getBestMovie() {
		double highestRating = 0;
		String bestMovie = "";
		for(Movie movie : this.movieList) {
			if(movie.getRating() > highestRating) {
				highestRating = movie.getRating();
				bestMovie = movie.getName();
			}
		}
		return bestMovie;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MovieDatabase movieDatabase = new MovieDatabase();
		String line;
		
        // Add movies and actors to the database
		try{
			File textFile = new File("movies.txt");
			Scanner scnr = new Scanner(textFile);
			while(scnr.hasNextLine()) {
				line = scnr.nextLine();
				String[] movie = line.split(",");
				String[] actor = new String[1];
				for(int i = 0; i < movie.length; i++) {
					if(i == 0) {
						actor[i] = movie[i];
					}
					else {
						movieDatabase.addMovie(movie[i].trim(), actor);
					}
				}
			}
			scnr.close();			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
        // Add ratings to the database
		try {
			File textFile = new File("ratings.txt");
			Scanner scnr = new Scanner(textFile);
			while(scnr.hasNextLine()) {
				line = scnr.nextLine();
				String[] movie = line.split("\t");
				try {
					double rating = Double.parseDouble(movie[1]);
					movieDatabase.addRating(movie[0].trim(), rating);
				}catch (NumberFormatException e) {
                    continue;
				}
			}
			scnr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// Print best movie and best actor
		System.out.println("The best actor is" + movieDatabase.getBestActor() );
		System.out.println("The best movie is" + movieDatabase.getBestMovie() );

	}

}
