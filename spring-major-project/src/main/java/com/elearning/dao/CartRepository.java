package com.elearning.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elearning.model.Cart;
import com.elearning.model.Course;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	@Modifying
	@Query(value = "delete from cart  where user_id = ? and course_id=?", nativeQuery = true) 
	  public void removeFromCart(int user_id,int course_id );
	
	@Query(value = "select course_id from cart  where user_id = ? ", nativeQuery = true)
	public List<Integer> getAllCart(int user_id);
	

}
