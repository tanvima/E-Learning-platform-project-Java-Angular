package com.elearning.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int profileid;

	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="userid")
	private User user;
	
	private String address;
	private String education;
	private String profession;
	
	public Profile() {
		super();
	}
	public int getProfileid() {
		return profileid;
	}
	public Profile setProfileid(int profileid) {
		this.profileid = profileid;
		return this;
	}
	public String getUser() {
		return this.user.getUsername();
	}
	public Profile setUser(User user) {
		this.user = user;
		return this;
	}
	public String getAddress() {
		return address;
	}
	public Profile setAddress(String address) {
		this.address = address;
		return this;
	}
	public String getEducation() {
		return education;
	}
	public Profile setEducation(String education) {
		this.education = education;
		return this;
	}
	public String getProfession() {
		return profession;
	}
	public Profile setProfession(String profession) {
		this.profession = profession;
		return this;
	}
	public Profile(int profileid, User user, String address, String education, String profession) {
		super();
		this.profileid = profileid;
		this.user = user;
		this.address = address;
		this.education = education;
		this.profession = profession;
	}
	
	
	public Profile build() {
		return new Profile(profileid,user, address, education,profession);
	}
	@Override
	public String toString() {
		return "Profile [profileid=" + profileid + ", user=" + user + ", address=" + address + ", education="
				+ education + ", profession=" + profession + "]";
	}
	
	
}
