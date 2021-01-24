package com.cognizant.moviecruiser.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.moviecruiser.model.MovieUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UserController userController ;
	
	@Test
	public void contextLoads() {
		assertNotNull(userController);
	}
	
	@Test
	public void testSignUp() throws Exception {
		
		MovieUser user = new MovieUser();
		user.setFirstName("Shar");
		user.setLastName("Par");
		user.setUserName("BP");
		user.setPassword("123456");
		
		ObjectMapper mapper = new ObjectMapper();
		   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		   ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		   String requestJson=ow.writeValueAsString(user);
		   mvc.perform(post("/users/signup").contentType(APPLICATION_JSON_UTF8)
		           .content(requestJson))
		           .andExpect(status().isOk());
	}
	@Test
	public void testSignUp2() throws Exception {
	
		MovieUser user = new MovieUser();
		user.setFirstName("Shar");
		user.setLastName("Par");
		user.setUserName("S");
		user.setPassword("1456");
		
		ObjectMapper mapper = new ObjectMapper();
		   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		   ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		   String requestJson=ow.writeValueAsString(user);
		   mvc.perform(post("/users/signup").contentType(APPLICATION_JSON_UTF8)
		           .content(requestJson))
		           .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testSignUp3() throws Exception {
		
		MovieUser user = new MovieUser();
		user.setFirstName("Shar");
		user.setLastName("Par");
		user.setUserName("XP");
		user.setPassword("123456");
		
		ObjectMapper mapper = new ObjectMapper();
		   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		   ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		   String requestJson=ow.writeValueAsString(user);
		   mvc.perform(post("/users/signup").contentType(APPLICATION_JSON_UTF8)
		           .content(requestJson))
		           .andExpect(status().isOk());
	}
	@Test
	public void testSignUp4() throws Exception {
	
		MovieUser user = new MovieUser();
		user.setFirstName("Shar");
		user.setLastName("Par");
		user.setUserName("XP");
		user.setPassword("123456");
		
		ObjectMapper mapper = new ObjectMapper();
		   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		   ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		   String requestJson=ow.writeValueAsString(user);
		   mvc.perform(post("/users/signup").contentType(APPLICATION_JSON_UTF8)
		           .content(requestJson))
		           .andExpect(status().isBadRequest());
		}
}
