package com.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Certificate {
	
	@Id		
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String path;
	private int courseId;
	
	public Certificate() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public Certificate setId(int id) {
		this.id = id;
		return this;
	}
	public String getPath() {
		return path;
	}
	public Certificate setPath(String path) {
		this.path = path;
		return this;
	}
	public int getCourseId() {
		return courseId;
	}
	public Certificate setCourseId(int courseId) {
		this.courseId = courseId;
		return this;
	}

	public Certificate(int id, String path, int courseId) {
		super();
		this.id = id;
		this.path = path;
		this.courseId = courseId;
	}
	
	public Certificate build() {
		return new Certificate(id,path,courseId);
	}
}
