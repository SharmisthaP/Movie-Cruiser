package com.cognizant.moviecruiser.service;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.dao.FavoritesDao;
import com.cognizant.moviecruiser.exception.FavoritesEmptyException;
import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.model.User;
import com.cognizant.moviecruiser.repository.MovieRepository;
import com.cognizant.moviecruiser.repository.UserRepository;

@Service
public class FavoritesService {
	
	@Autowired
	FavoritesDao favoritesDao;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	public String addToFavorites(int userId, long movieId){
		
		/*String res=null;
		try{
			
			res= favoritesDao.addToFavorites(user, movieId);
		}
		catch(MovieNotFoundException e){
			throw e;
		}
		return res;*/
		//add usernotfound exception
		User user = userRepository.findById(userId).orElse(null);
		Movie movie =movieRepository.findById(movieId).orElse(null);
		
		if(movie==null)
			throw new MovieNotFoundException();
		
		user.getMovieList().add(movie);
		userRepository.save(user);
		return "Movie added to favotites";
		
		
	}
	
	public Set<Movie> getFavorites(int userId){
		
		Set<Movie> list =null;
		/*try{
			list= favoritesDao.getFavorites(user);
		}
		catch(FavoritesEmptyException e)
		{
			throw e;
		}
		return list;*/
		User user = userRepository.findById(userId).orElse(null);
		list= user.getMovieList();
		if(list.size()==0 || list==null)
			throw new FavoritesEmptyException();
		return list;
	}
	
	public int getTotalFavorites(int userId)
	{
		User user = userRepository.findById(userId).orElse(null);
		return user.getMovieList().size();
		
	}
	
	public String removeFavorite(int userId, long movieId){
		
		/*String res=null;
		try{
			res=favoritesDao.removeFavorite(user, movieId);
		}
		catch(Exception e)
		{
			throw e;
		}
		return res;*/
		User user = userRepository.findById(userId).orElse(null);
		Iterator<Movie> iterator = user.getMovieList().iterator();
		while (iterator.hasNext()) {
		    Movie movie = iterator.next();
		    if (movie.getId()==movieId) {
		        iterator.remove();
		    }
		}
		userRepository.save(user);
		return "Movie removed from favorites";

		
	}

}
