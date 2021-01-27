package com.cognizant.moviecruiser.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.model.User;
import com.cognizant.moviecruiser.repository.UserRepository;




@Service
public class AppUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);
	@Autowired
	UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.findByUsername(username);
		//LOGGER.info("in here "+user);
		if(user==null)
			throw new UsernameNotFoundException(username + " not found");
		AppUser appUser = new AppUser(user);
		return appUser;
	}

}
