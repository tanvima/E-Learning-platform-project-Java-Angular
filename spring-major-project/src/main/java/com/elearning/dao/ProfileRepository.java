package com.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elearning.model.Profile;
import com.elearning.model.User;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	Profile findByUser(User u);
	
	@Modifying
	@Query(value="update profile set address=?,education=?,profession=? where profileid=?",nativeQuery = true)
	public void updateProfile(String address,String education,String profession,int profileid);
}
