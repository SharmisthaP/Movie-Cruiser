package com.cognizant.moviecruiser.dao;

import java.util.Set;

import com.cognizant.moviecruiser.model.Movie;

public interface FavoritesDao {
	
	public String addToFavorites(String user,long movieId);
	public Set<Movie> getFavorites(String user);// throws FavouritesEmptyException;
	public String removeFavorite(String user,long movieId);

}
