package com.cognizant.moviecruiser.dto;

import java.util.Set;

import com.cognizant.moviecruiser.model.Movie;

public class FavoritesDTO {

	private Set<Movie> movieList;
	private int total;
	
	public FavoritesDTO(Set<Movie> movieList, int total) {
		super();
		this.movieList = movieList;
		this.total = total;
	}
	public Set<Movie> getMovieList() {
		return movieList;
	}
	public void setMovieList(Set<Movie> movieList) {
		this.movieList = movieList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Favorites [total=" + total + "]";
	}
	
	
}
