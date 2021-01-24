package com.cognizant.moviecruiser.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="us_id")
	private int id;
	@Column(name="us_name")
	private String username;
	@Column(name="password")
	private String password;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "user_role", 
	joinColumns = @JoinColumn(name = "ur_us_id"), 
	inverseJoinColumns = @JoinColumn(name = "ur_ro_id"))
	private List<Role> roleList;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "favorite", 
	joinColumns = @JoinColumn(name = "ft_us_id"), 
	inverseJoinColumns = @JoinColumn(name = "ft_pr_id"))
	Set<Movie> movieList= new HashSet<>();
	
	public User()
	{
		
	}

	public User(int id , String name , List<Role> list)
	{
		this.id=id;
		this.username=name;
		this.roleList=list;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Set<Movie> getMovieList() {
		return movieList;
	}
	public void setMovieList(Set<Movie> movieList) {
		this.movieList = movieList;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", roleList=" + roleList + ", movieList=" + movieList
				+ "]";
	}
	
	

}
