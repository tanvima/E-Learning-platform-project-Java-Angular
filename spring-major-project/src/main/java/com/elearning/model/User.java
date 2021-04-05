package com.elearning.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="usertype")
@DiscriminatorValue("regular")

public  class User {
	@Id		
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;
	protected String username;
	protected String password;
	protected int noOfAttempts=0;
	
	
	
	
	public int getNoOfAttempts() {
		return noOfAttempts;
	}
	public User setNoOfAttempts(int noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	protected String name;
	
	public String getName() {
		return name;
	}
	public User setName(String name) {
		this.name = name;
		return this;
	}

	protected String email;
	protected long mobileNo;
	protected String status="block";
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="user_roles",
	    joinColumns=@JoinColumn(name="id"))
	@Column(name = "role")
	protected List<String> roles = new ArrayList<>();
	
	
	@Transient
	protected String token;
	
	
	
	
	public String getToken() {
		return token;
	}


	public User setToken(String token) {
		this.token = token;
		return this;
	}

	@OneToOne(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,mappedBy = "user"
			)
	protected Profile profile;

	
	@OneToMany(targetEntity = Certificate.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="userId", referencedColumnName = "id")
	protected List<Certificate> certificate;
	
	
	public User() {
		super();
	}
	
	
	public Profile getProfile() {
		System.out.println("Getting profile");
		return profile;
	}


	public User setProfile(Profile profile) {
		this.profile = profile;
		return this;
	}


	public int getId() {
		return id;
	}
	public User setId(int id) {
		this.id = id;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public User setMobileNo(long l) {
		this.mobileNo = l;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public User setStatus(String status) {
		this.status = status;
		return this;
	}


	


	


	public List<String> getRoles() {
		return roles;
	}


	public User setRoles(List<String> roles) {
		this.roles = roles;
		return this;
	}


	public List<Certificate> getCertificate() {
		return certificate;
	}


	public User setCertificate(List<Certificate> certificate) {
		this.certificate = certificate;
		return this;
	}
	public User(int id, String username, String password, int noOfAttempts, String name, String email, long mobileNo,
			String status, List<String> roles, String token, Profile profile, List<Certificate> certificate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.noOfAttempts = noOfAttempts;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.status = status;
		this.roles = roles;
		this.token = token;
		this.profile = profile;
		this.certificate = certificate;
	}


	
	public User build() {
		return new User( id,  username,  password,  noOfAttempts,  name,  email,  mobileNo,
			 status,  roles,  token,  profile,  certificate);
	}
	

	
	
	
	
	
}
