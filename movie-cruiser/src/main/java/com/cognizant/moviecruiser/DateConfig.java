package com.cognizant.moviecruiser;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class DateConfig {
	
	
	    @Autowired
	    public void configureJackson(ObjectMapper objectMapper) {
	        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
	    }
	
}
