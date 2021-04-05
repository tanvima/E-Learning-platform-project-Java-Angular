package com.elearning.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String status="incomplete";
	
	private int videoWatched =0;
	
//	@ManyToOne(targetEntity = Course.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="courseId", referencedColumnName = "courseId")
//	private Course course;
	
	
	private int userid;
	
	public int getUser() {
		return userid;
	}

	public Enrollment setUser(int user) {
		this.userid = user;
		return this;
	}

	public Enrollment() {
		super();
	}

	public int getId() {
		return id;
	}

	public Enrollment setId(int id) {
		this.id = id;
		return this;
	}


	public String getStatus() {
		return status;
	}

	public Enrollment setStatus(String status) {
		this.status = status;
		return this;
	}

	public int getVideoWatched() {
		return videoWatched;
	}

	public Enrollment setVideoWatched(int videoWatched) {
		this.videoWatched = videoWatched;
		return this;
	}

	
//	public Course getCourse() {
//		return course;
//	}
//
//	public Enrollment setCourse(Course course) {
//		this.course = course;
//		return this;
//	}

	
	
	public Enrollment(int id, String status, int videoWatched, int userid) {
		super();
		this.id = id;
		this.status = status;
		this.videoWatched = videoWatched;
		
		this.userid = userid;
	}

	public Enrollment build() {
		return new Enrollment(id, status, videoWatched, userid);
	}
	
}
