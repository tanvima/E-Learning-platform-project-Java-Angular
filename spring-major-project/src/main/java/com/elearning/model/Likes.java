package com.elearning.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//private int likes=0;
	private int useId;
	
	
	private String status;
	
	
	public Likes() {
		super();
	}

	public int getId() {
		return id;
	}

	public Likes setId(int id) {
		this.id = id;
		return this;
	}

	public int getUseId() {
		return useId;
		
	}

	public Likes setUseId(int useId) {
		this.useId = useId;
		return this;
	}



	public String getStatus() {
		return status;
	}

	public Likes setStatus(String status) {
		this.status = status;
		return this;
	}



	public Likes(int id, int useId,  String status) {
		super();
		this.id = id;
		this.useId = useId;	
		this.status = status;
		
	}
	
	public Likes build() {
		return new Likes(id,useId,status);
	}
	
	
}
