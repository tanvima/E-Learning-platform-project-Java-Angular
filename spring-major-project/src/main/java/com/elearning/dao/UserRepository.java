package com.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.elearning.model.User;
@Repository
public interface UserRepository <T extends User>extends JpaRepository<T,Integer>{

	/*
	 * @Query(value = "update user set usertype=PrimeUser where id=?",nativeQuery =
	 * true) public void updateUserType(int id);
	 * 
	 * 
	 */
	
	User findByUsername(String username);
	User findByEmail(String email);
	 @Query(value = "select usertype from user where username=?",nativeQuery =true) 
	 public String getUserType(String username);
		
	 @Modifying
	 @Query(value="update user set no_of_attempts = 0 where username=?",nativeQuery = true)
	 public void clearNoOfAttempt(String username);
	 
	 @Modifying
	 @Query(value="update user set no_of_attempts = no_of_attempts+1 where username=?",nativeQuery = true)
	 public void updateNoOfAttempt(String username);
	 
	 @Query(value="select no_of_attempts from user where username=?",nativeQuery = true)
	 public int getNoOfAttempt(String name);
	 
	 
}
