package com.elearning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int courseId;
	private String courseName;
	@Column(length = 3000)
	private String courseDesc;
	private String courseLogo;
	private int coursePrice;
	private int noOfVideo = 0;
	private String authorName;
	
	@Transient
	@JsonInclude
	private int rating ;
	
	@Transient
	@JsonInclude
	private String status;
	
	@OneToMany(targetEntity = Video.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="courseId", referencedColumnName = "courseId")
	List<Video> video;
	
	
	@OneToMany(targetEntity = Feedback.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="courseId", referencedColumnName = "courseId")
	List<Feedback> feedback;
	
	
	
	@OneToMany(targetEntity = Enrollment.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="courseId", referencedColumnName = "courseId")
	List<Enrollment>enrollment;
	
	public List<Likes> getLike() {
		return like;
	}

	public Course setLike(List<Likes> like) {
		this.like = like;
		return this;
	}

	@OneToMany(targetEntity = Cart.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="courseId", referencedColumnName = "courseId")
	List<Cart> cart;
	
	@OneToMany(targetEntity = Likes.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="courseId", referencedColumnName = "courseId")
	List<Likes> like;
	
	@OneToMany(targetEntity = Comments.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="courseId", referencedColumnName = "courseId")
	List<Comments> comment;

	public List<Comments> getComment() {
		return comment;
	}

	public Course setComment(List<Comments> comment) {
		this.comment = comment;
		return this;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public Course setCart(List<Cart> cart) {
		this.cart = cart;
		return this;
	}

	public Course() {
		super();
	}

	public int getCourseId() {
		return courseId;
	}

	public Course setCourseId(int courseId) {
		this.courseId = courseId;
		return this;
	}

	public String getCourseName() {
		return courseName;
	}

	public Course setCourseName(String courseName) {
		this.courseName = courseName;
		return this;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public Course setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
		return this;
	}

	public String getCourseLogo() {
		return courseLogo;
	}

	public Course setCourseLogo(String courseLogo) {
		this.courseLogo = courseLogo;
		return this;
	}

	public int getCoursePrice() {
		return coursePrice;
	}

	public Course setCoursePrice(int coursePrice) {
		this.coursePrice = coursePrice;
		return this;
	}

	public List<Video> getVideo() {
		return video;
	}

	public Course setVideo(List<Video> video) {
		this.video = video;
		return this;
	}

	public int getNoOfVideo() {
		return noOfVideo;
	}

	public Course setNoOfVideo(int noOfVideo) {
		this.noOfVideo = noOfVideo;
		return this;
	}
	
	

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public Course setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
		return this;
	}



	
	public List<Enrollment> getEnrollment() {
		return enrollment;
		
	}

	public Course setEnrollment(List<Enrollment> enrollment) {
		this.enrollment = enrollment;
		return this;
	}
	
	
	
	public int getRating() {
		return rating;
	}

	public Course setRating(int rating) {
		this.rating = rating;
		return this;
	}

	
	
	public String getAuthorName() {
		return authorName;
	}

	public Course setAuthorName(String authorName) {
		this.authorName = authorName;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Course setStatus(String status) {
		this.status = status;
		return this;
	}

	public Course(int courseId, String courseName, String courseDesc, String courseLogo, int coursePrice, int noOfVideo,
			List<Video> video, List<Feedback> feedback, List<Enrollment> enrollment,List<Likes> like,List<Comments> comment,
			List<Cart> cart, int rating,String status,String authorName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.courseLogo = courseLogo;
		this.coursePrice = coursePrice;
		this.noOfVideo = noOfVideo;
		this.video = video;
		this.feedback = feedback;
		this.like=like;
		this.comment=comment;
		this.enrollment = enrollment;
		this.cart = cart;
		this.rating=rating;
		this.status=status;
		this.authorName=authorName;
	}

	public Course build() {
		return new Course( courseId,  courseName,  courseDesc,  courseLogo,  coursePrice,  noOfVideo,video,  feedback, enrollment,like,comment,cart,rating,status,authorName);
	}

	@Override
	public String toString() {
		return "Course [courseName=" + courseName + "]";
	}
}
