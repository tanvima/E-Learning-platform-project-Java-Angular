package com.elearning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("prime")
public class PrimeUser extends User{


	
	public PrimeUser() {
		super();
		// TODO Auto-generated constructor stub
	}



	

	public PrimeUser(int id, String username, String password, int noOfAttempts, String name, String email,
			long mobileNo, String status, List<String> roles, String token, Profile profile,
			List<Certificate> certificate) {
		super(id, username, password, noOfAttempts, name, email, mobileNo, status, roles, token, profile, certificate);
		// TODO Auto-generated constructor stub
	}





	public PrimeUser build() {
		return new PrimeUser( id,  username,  password,  noOfAttempts,  name,  email,
				 mobileNo,  status,  roles,  token,  profile,
				 certificate);
	}

	
}
