package com.cognizant.moviecruiser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.security.AppUserDetailsService;
import com.cognizant.moviecruiser.service.MovieService;

@RestController
public class MovieController {
	
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@GetMapping(value="/movies")
	public List<Movie> getAllMovies() {
		
		
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication(); 
				String user = authentication.getName(); 
				UserDetails userDetails = appUserDetailsService.loadUserByUsername(user); 
				String role = userDetails.getAuthorities().toArray()[0].toString();
				
		if(role.equals("ROLE_ADMIN"))
			return movieService.getMovieListAdmin();
			
		else
			return movieService.getMovieListCustomer();
	}
	
	@GetMapping(value="/movie-list-admin")
	public List<Movie> getMovieListAdmin() {
		
		return movieService.getMovieListAdmin();
	}
	
	@GetMapping(value="/movies/{id}")
	public Movie getMovie(@PathVariable("id") long movieId)
	{
		Movie movie=null;
		try{
			movie = movieService.getMovie(movieId);
		}
		catch(MovieNotFoundException e)
		{
			throw e ;
		}
		return movie;
	}
	
	@PostMapping(value="/modifymovie")
	public String modifyMovie(@RequestBody Movie movie)
	{
		String res=null;
		try{
			res=movieService.modifyMovie(movie);
		}
		catch(MovieNotFoundException e)
		{
			throw e;
		}
		return res;
	}
	

	

}
