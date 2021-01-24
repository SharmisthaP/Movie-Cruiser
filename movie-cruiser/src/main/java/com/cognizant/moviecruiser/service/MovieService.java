package com.cognizant.moviecruiser.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.dao.MovieDao;
import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired 
	MovieRepository movieRepository;
	
	public List<Movie> getMovieListAdmin() {
			
			//return movieDao.getMovieListAdmin();
		return movieRepository.findAll();
		}

	
	public List<Movie> getMovieListCustomer() {
		
		//return movieDao.getMovieListCustomer();
		return movieRepository.getMovieListCustomer("yes",new Date());
	}

	
	public String modifyMovie(Movie movie) {
		
		/*String res=null;
		try{
			res=movieDao.modifyMovie(movie);
		}
		catch(MovieNotFoundException e)
		{
			throw e;
		}
		return res;*/
		movieRepository.save(movie);
		return "Movie Updated";

	}

	
	public Movie getMovie(long movieId) {
		
		
		
		/*Movie movie=null;
		try{
			movie = movieDao.getMovie(movieId);
		}
		catch(MovieNotFoundException e)
		{
			throw e ;
		}
		return movie;*/
		
		Movie movie=movieRepository.findById(movieId).orElse(null);
		if(movie==null)
			throw new MovieNotFoundException();
		return movie;
		
	}
	

}
