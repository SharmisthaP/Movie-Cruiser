package com.cognizant.moviecruiser.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class AuthenticationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
	
	@GetMapping(value="/authenticate")
	public Map<String,String> authenticate(@RequestHeader("Authorization") String authHeader)
	{
		LOGGER.debug(authHeader);
		LOGGER.debug(getUser(authHeader));
		String role = SecurityContextHolder.getContext().getAuthentication() 
				.getAuthorities().toArray()[0].toString(); 
		Map<String,String> map = new HashMap<>();
		map.put("token",generateJwt(getUser(authHeader)));
		map.put("role", role);
		return map;
	}
	
	private String getUser(String authHeader) {
		
		String encodedCredentials = authHeader.substring(6,authHeader.length()-1);
		String decoded= new String( Base64.getDecoder().decode(encodedCredentials));
		String[] user=decoded.split(":");
		return user[0];
	}
	
	private String generateJwt(String user) {
		
		JwtBuilder builder = Jwts.builder(); 
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date((new Date()).getTime() + 1200000)); 
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact(); 
		return token;
	}
	

}
