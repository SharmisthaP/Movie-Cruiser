package com.cognizant.moviecruiser.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AppUserDetailsService appUserDetailsService; 
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder()); 
	} 

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		
		return new BCryptPasswordEncoder(); 
		//return NoOpPasswordEncoder.getInstance();
	} 
	/*@Bean 
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() { 
		 
		List<UserDetails> userDetailsList = new ArrayList<>(); 
	
		userDetailsList.add( 
		User.withUsername("user") 
		.password(passwordEncoder() 
		.encode("pwd")) 
		.roles("USER").build()); 
	
		userDetailsList.add( 
		User.withUsername("admin") 
		.password(passwordEncoder() 
		.encode("pwd")) 
		.roles("ADMIN").build()); 
	
		return new InMemoryUserDetailsManager(userDetailsList); 
	} */

	@Override 
	protected void configure(HttpSecurity httpSecurity) throws Exception { 
		
		httpSecurity.cors(); 
		httpSecurity.csrf().disable().httpBasic().and() 
		.authorizeRequests()
		.antMatchers("/movies").permitAll()
		.antMatchers("/users/signup").permitAll()
		.antMatchers("/authenticate").permitAll()
		.anyRequest().authenticated() 
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JwtAuthorizationFilter(authenticationManager()));; 
	} 
	

}
