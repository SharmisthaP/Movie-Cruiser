package com.cognizant.moviecruiser.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecruiser.dto.FavoritesDTO;
import com.cognizant.moviecruiser.exception.FavoritesEmptyException;
import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.model.Movie;

@Repository
public class FavoritesDaoCollectionImpl implements FavoritesDao {

	private HashMap<String,FavoritesDTO> userFavourites=null;
	
	@Autowired
	MovieDao movieDao;
	
	public FavoritesDaoCollectionImpl() {
		if(userFavourites==null)
			userFavourites = new HashMap<>();
	}
	
	@Override
	public String addToFavorites(String user, long movieId) {

		Set<Movie> movieList=null;
		Movie movie=null;
		try{
			movie = movieDao.getMovie(movieId);
		}
		catch(MovieNotFoundException e)
		{
			throw e;
		}
		
		if(userFavourites.containsKey(user))
		{
			 movieList=userFavourites.get(user).getMovieList();
			 movieList.add(movie);
		}
		else
		{
			movieList=new HashSet<>();
			movieList.add(movie);
			FavoritesDTO favourites = new FavoritesDTO(movieList,0);
			userFavourites.put(user, favourites);
			
		}
		return "Movie added to favorites!!";	

	}

	@Override
	public Set<Movie> getFavorites(String user) {
		
		//add exception for empty
		if(!userFavourites.containsKey(user))
			throw new FavoritesEmptyException();
		
		FavoritesDTO favourites = userFavourites.get(user);
		
		Set<Movie> movieList = favourites.getMovieList();
		
		if(movieList.isEmpty())
			throw new FavoritesEmptyException();
		
		favourites.setTotal(movieList.size());
		
		return movieList;
	}

	@Override
	public String removeFavorite(String user, long movieId) {
		
		if(!userFavourites.containsKey(user))
			throw new FavoritesEmptyException();
		
		FavoritesDTO favorites = userFavourites.get(user);
		Set<Movie> movieList = favorites.getMovieList();
		
		if(movieList.isEmpty())
			throw new FavoritesEmptyException();
		
		List<Movie>tempList = new ArrayList<>();
		for(Movie movie: movieList)
			if(movie.getId()==movieId)
				tempList.add(movie);
		
		if(tempList.size()==0)
			throw new MovieNotFoundException();
		
		movieList.removeAll(tempList);
		
		return "Movie removed from favorites";

	}

}
