package com.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String feedback;
	private int rating=0;
	private int userid;
	public Feedback() {
		super();
	}
	public int getId() {
		return id;
	}
	public Feedback setId(int id) {
		this.id = id;
		return this;
	}
	public String getFeedback() {
		return feedback;
	}
	public Feedback setFeedback(String feedback) {
		this.feedback = feedback;
		return this;
	}
	public int getRating() {
		return rating;
	}
	public Feedback setRating(int rating) {
		this.rating = rating;
		return this;
	}
	public int getUserid() {
		return userid;
	}
	public Feedback setUserid(int userid) {
		this.userid = userid;
		return this;
	}
	public Feedback(int id, String feedback, int rating, int userid) {
		super();
		this.id = id;
		this.feedback = feedback;
		this.rating = rating;
		this.userid = userid;
	}
	
	public Feedback build() {
		return new Feedback(id,feedback,rating,userid);
	}
	
	
}
