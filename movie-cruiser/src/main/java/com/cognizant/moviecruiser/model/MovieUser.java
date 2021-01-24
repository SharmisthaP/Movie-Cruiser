package com.cognizant.moviecruiser.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieUser {
	
	@NotNull
	@Size(min=2, max=10, message="Username must have atleast 2 and atmost 10 characters")
	private String userName;
	@NotNull
	@Size(min=1, max=30, message="Username must have atleast 1 and atmost 30 characters")
	private String firstName;
	@NotNull
	@Size(min=1, max=30, message="Username must have atleast 1 and atmost 30 characters")
	private String lastName;
	@NotNull
	
	private String password;
	
	
	public MovieUser() {
	}


	public MovieUser(String userName, String firstName, String lastName, String password) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + "]";
	}
	
	
	
	

}
