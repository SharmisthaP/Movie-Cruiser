package com.cognizant.moviecruiser.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.moviecruiser.model.User;

@SuppressWarnings("serial")
public class AppUser implements UserDetails {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppUser.class); 
	private User user; // entity reference 
	private Collection<? extends GrantedAuthority> authorities;
	
	public AppUser(User user)
	{
		this.user=user;
		this.authorities = user.getRoleList().stream().map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList());
		//LOGGER.info(this.authorities+"");
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
