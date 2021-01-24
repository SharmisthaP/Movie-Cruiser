package com.cognizant.moviecruiser.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.moviecruiser.dto.FavoritesDTO;
import com.cognizant.moviecruiser.exception.FavoritesEmptyException;
import com.cognizant.moviecruiser.exception.MovieNotFoundException;
import com.cognizant.moviecruiser.service.FavoritesService;

@RestController
@RequestMapping("/favorites") 
public class FavoritesController {
	
	@Autowired
	FavoritesService favoritesService;
	
	@PostMapping("/{userId}/{movieId}")
	public String addToFavorites(@PathVariable("userId") int userId,@PathVariable("movieId") long movieId)
	{
		String res=null;
		try{
			
			res= favoritesService.addToFavorites(userId, movieId);
		}
		catch(MovieNotFoundException e){
			throw e;
		}
		return res;
	}
	
	@GetMapping("/{userId}")
	public FavoritesDTO getFavorites(@PathVariable("userId") int userId)
	{
		FavoritesDTO dto =null;
		try{
			dto = new FavoritesDTO(favoritesService.getFavorites(userId),favoritesService.getTotalFavorites(userId));
		}
		catch(FavoritesEmptyException e)
		{
			throw e;
		}
		return dto;
	}
	
	@DeleteMapping("/{userId}/{movieId}")
	public String removeFavorite(@PathVariable("userId") int userId,@PathVariable("movieId") long movieId)
	{
		String res=null;
		try{
			res=favoritesService.removeFavorite(userId, movieId);
		}
		catch(Exception e)
		{
			throw e;
		}
		return res;
	}

}
