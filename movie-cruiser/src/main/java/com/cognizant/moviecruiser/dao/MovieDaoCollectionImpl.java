package com.cognizant.moviecruiser.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.model.Movie;

@Repository
public class MovieDaoCollectionImpl implements MovieDao {
	
	ArrayList<Movie> movieList;
	
	@SuppressWarnings("unchecked")
	public MovieDaoCollectionImpl()
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("moviecruiser.xml");
		movieList = context.getBean("movieList",java.util.ArrayList.class);
		context.close();
	}

	@Override
	public List<Movie> getMovieListAdmin() {
		
		return movieList;
	}

	@Override
	public List<Movie> getMovieListCustomer() {
		
		List<Movie> custMovieList=null;
		custMovieList=movieList.stream().
					filter(movie->{
						return (movie.getDateOfLaunch().compareTo(new Date())<=0 && movie.isActive().equals("yes"));
						})
					.collect(Collectors.toList());
		return custMovieList;
	}

	@Override
	public String modifyMovie(Movie movie) {
		int index=-1;
		for(int i=0;i<movieList.size();++i)
		{
			if(movie.getId()==movieList.get(i).getId())
			{
				index=i;
				break;
			}
				
		}
		if(index!= -1)
		{
			movieList.set(index,movie);
			return "Movie Updated success!!";
		}
		throw new MovieNotFoundException();
	}

	@Override
	public Movie getMovie(long movieId) {
		
		for(Movie m : movieList)
		{
			if(m.getId()==movieId)
				return m;
		}
		throw new MovieNotFoundException();
	}

}
