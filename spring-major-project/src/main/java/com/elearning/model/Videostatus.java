package com.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Videostatus {
	
	@Id		
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int userId;
	private String status="incomplete";
	public Videostatus() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public Videostatus setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public Videostatus setStatus(String status) {
		this.status = status;
		return this;
	}
	
	
	public Videostatus(int id, int userId, String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
	}
	public Videostatus build() {
		return new Videostatus(id,userId,status);
	}
}
