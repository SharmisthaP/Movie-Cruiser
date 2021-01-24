package com.cognizant.moviecruiser.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private MovieController movieController ;
	
	@Test
	public void contextLoads() {
		assertNotNull(movieController);
	}
	
	@Test
	@WithMockUser(username="user",roles= {"USER"})
    public void testGetMoviesCustomer() throws Exception
	{
		mvc.perform(get("/movies"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[2].id").exists())
				.andExpect(jsonPath("$[3].id").doesNotExist());      
				
	}
	
	
	  @Test
	  @WithMockUser(username="admin",roles= {"ADMIN"}) public void
	  testGetMoviesAdmin() throws Exception { mvc.perform(
	  get("/movies")) .andExpect(status().isOk())
	  .andExpect(jsonPath("$[2].id").exists())
	  .andExpect(jsonPath("$[3].id").exists());
	  
	  }

}
