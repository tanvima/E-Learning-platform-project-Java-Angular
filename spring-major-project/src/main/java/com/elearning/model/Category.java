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
import javax.persistence.OneToMany;


@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	private String categoryName;
	@Column(length = 3000)
	private String categoryDesc;
	private String categoryLogo;
	
	@OneToMany(targetEntity = Course.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="categoryId", referencedColumnName = "categoryId")
	List<Course> courses;

	public int getCategoryId() {
		return categoryId;
	}

	public Category setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Category setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public Category setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
		return this;
	}

	public String getCategoryLogo() {
		return categoryLogo;
	}

	public Category setCategoryLogo(String categoryLogo) {
		this.categoryLogo = categoryLogo;
		return this;
	}

	public List<Course> getCourses() {
		return courses;
		
	}

	public Category setCourses(List<Course> courses) {
		this.courses = courses;
		return this;
	}

	public Category() {
		super();
	}

	public Category(int categoryId, String categoryName, String categoryDesc, String categoryLogo,
			List<Course> courses) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.categoryLogo = categoryLogo;
		this.courses = courses;
	}

	public Category(String categoryName, String categoryDesc, String categoryLogo, List<Course> courses) {
		super();
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.categoryLogo = categoryLogo;
		this.courses = courses;
	}

	public Category build() {
		return new Category(categoryId,categoryName,categoryDesc,categoryLogo, courses);
	}
}
