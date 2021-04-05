package com.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private String comment;
	public int getId() {
		return id;
	}
	public Comments setId(int id) {
		this.id = id;
		return this;
	}
	public int getUserId() {
		return userId;
	}
	public Comments setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public String getComment() {
		return comment;
	}
	public Comments setComment(String comment) {
		this.comment = comment;
		return this;
	}
	public Comments() {
		super();
	}
	public Comments(int id, int userId, String comment) {
		super();
		this.id = id;
		this.userId = userId;
		this.comment = comment;
	}
	
	public Comments build() {
		return new Comments(id, userId, comment);
	}
	
}
