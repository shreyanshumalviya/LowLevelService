package com.make73.annotation_processor.lld_drivers.BookMyShow.managers;

import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private List<Movie> movies;

    // constructor
    public MovieManager() {
        this.movies = new ArrayList<>();
    }

    // addMovie
    public void addMovie(String name) {
        Movie movie = new Movie(movies.size(), name);
        movies.add(movie);
    }

    // list movies
    public List<Movie> getMovies() {
        return movies;
    }

    // getMovie
    public Movie getMovie(int id) {
        return movies.get(id);
    }
}
