package com.cognizant.moviecruiser.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertThrows;

import com.cognizant.moviecruiser.exception.UserAlreadyExistsException;
import com.cognizant.moviecruiser.model.Role;
import com.cognizant.moviecruiser.model.User;
import com.cognizant.moviecruiser.repository.RoleRepository;
import com.cognizant.moviecruiser.repository.UserRepository;
import com.cognizant.moviecruiser.service.UserDetailsService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserControllerMock {
	
	@InjectMocks
	UserDetailsService service;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	RoleRepository roleRepository;
	
	@Test
	public void mockTestSignUpSuccess()
	{
		
		when(userRepository.findByUsername("SP")).thenReturn(null);
		String expected = "saved";
		String msg= service.signup(createUser());
		assertEquals(expected,msg);
	}
	@Test
	public void mockTestSignUpFailure()
	{
		when(userRepository.findByUsername("SP")).thenReturn(createUser());
		assertThrows(UserAlreadyExistsException.class,()->service.signup(createUser()));
	}
	private User createUser() {
		
		List<Role> rlist=new ArrayList<>();
		User user = new User(1,"SP",rlist);
		return user;
	}

}
