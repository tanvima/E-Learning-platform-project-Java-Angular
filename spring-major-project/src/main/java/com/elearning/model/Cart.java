package com.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	public int getId() {
		return id;
	}
	public Cart setId(int id) {
		this.id = id;
		return this;
	}
	public int getUserId() {
		return userId;
	}
	public Cart setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public Cart() {
		super();
	}
	public Cart(int id, int userId) {
		super();
		this.id = id;
		this.userId = userId;
	}
	
	public Cart build() {
		return new Cart(id,userId);
	}
	
}
