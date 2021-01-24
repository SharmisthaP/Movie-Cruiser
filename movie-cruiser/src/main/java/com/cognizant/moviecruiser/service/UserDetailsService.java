package com.cognizant.moviecruiser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.exception.UserAlreadyExistsException;
import com.cognizant.moviecruiser.model.User;
import com.cognizant.moviecruiser.repository.UserRepository;

@Service
public class UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	public String signup(User user)
	{
		User temp = userRepository.findByUsername(user.getUsername());
		if(temp!=null)
			throw new UserAlreadyExistsException();
		userRepository.save(user);
		return "saved";
	}
	
}
