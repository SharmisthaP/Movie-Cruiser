package com.cognizant.moviecruiser.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cognizant.moviecruiser.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mo_id")
	private long id;
	
	@Column(name="mo_title")
	private String title;
	
	@Column(name="mo_genre")
	private String genre;
	
	@Column(name="mo_box_office")
	private float boxOffice;
	
	@Column(name="mo_active")
	private String active;
	
	@Column(name="mo_has_teaser")
	private String hasTeaser;
	
	@Temporal(TemporalType.DATE) 
	@Column(name="mo_date_of_launch")
	@JsonFormat(pattern="dd/MM/yyyy",timezone = JsonFormat.DEFAULT_TIMEZONE)
	private Date dateOfLaunch;
	
	
	/*@ManyToMany(mappedBy="movieList")
	List<User> userList;*/
	
	
	public Movie() {
	}

	public Movie(long id, String title, String genre, float boxOffice, String active, String hasTeaser,
			Date dateOfLaunch) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.boxOffice = boxOffice;
		this.active = active;
		this.hasTeaser = hasTeaser;
		this.dateOfLaunch = dateOfLaunch;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public float getBoxOffice() {
		return boxOffice;
	}
	public void setBoxOffice(float boxOffice) {
		this.boxOffice = boxOffice;
	}
	public String isActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String isHasTeaser() {
		return hasTeaser;
	}
	public void setHasTeaser(String hasTeaser) {
		this.hasTeaser = hasTeaser;
	}
	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}
	public void setDateOfLaunch(String dateOfLaunch) {
		this.dateOfLaunch = DateUtil.convertToDate(dateOfLaunch);
	}
	

	/*public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}*/

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genre=" + genre + ", boxOffice=" + boxOffice + ", active="
				+ active + ", hasTeaser=" + hasTeaser + ", dateOfLaunch=" + dateOfLaunch + "]";
	}
	
	
}
