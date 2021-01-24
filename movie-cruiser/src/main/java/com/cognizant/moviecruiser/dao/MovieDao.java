package com.cognizant.moviecruiser.dao;

import java.util.List;

import com.cognizant.moviecruiser.model.Movie;

public interface MovieDao {

	 List<Movie> getMovieListAdmin();
	 List<Movie> getMovieListCustomer();
	 String modifyMovie(Movie movie);
	 Movie getMovie(long movieId);

}
