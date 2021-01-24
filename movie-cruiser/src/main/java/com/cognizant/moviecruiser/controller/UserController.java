package com.cognizant.moviecruiser.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.moviecruiser.exception.UserAlreadyExistsException;
import com.cognizant.moviecruiser.model.MovieUser;
import com.cognizant.moviecruiser.model.Role;
import com.cognizant.moviecruiser.model.User;
import com.cognizant.moviecruiser.service.RoleService;
import com.cognizant.moviecruiser.service.UserDetailsService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserDetailsService userService;
	
	@Autowired
	RoleService roleService;
	
	@PostMapping(value="/signup")
	public String signup(@RequestBody @Valid MovieUser user){
		
		Role role= roleService.getUserRole();
		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
		
		User newUser = new User();
		newUser.setUsername(user.getUserName());
		newUser.setPassword(passwordEncoder().encode(user.getPassword()));
		newUser.setRoleList(roleList);
		
		String res="";
		try{
			res=userService.signup(newUser);
		}
		catch(UserAlreadyExistsException e)
		{
			throw  e;
		}
		return res;
		
		/*boolean flag = inMemoryUserDetailsManager.userExists(user.getUserName());
		if (flag)
			throw new UserAlreadyExistsException();
		else
		{
			ArrayList<GrantedAuthority> grantedAuthoritiesList= new ArrayList<>();
			grantedAuthoritiesList.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			inMemoryUserDetailsManager.createUser(new User(user.getUserName(), passwordEncoder().encode(user.getPassword()), grantedAuthoritiesList));
			return "User created";
		}*/
			
		
	}
	
	
	public PasswordEncoder passwordEncoder() { 
		
		return new BCryptPasswordEncoder(); 
	} 

	
}
