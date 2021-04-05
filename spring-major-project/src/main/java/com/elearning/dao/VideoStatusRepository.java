package com.elearning.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elearning.model.Videostatus;

@Repository
public interface VideoStatusRepository extends JpaRepository<Videostatus, Integer>{

	@Modifying
	@Transactional
	@Query(value="update videostatus  set status='completed' where user_id=?1 and video_id=?2",nativeQuery = true)
	public void updateVideoStatus(int user_id, int video_id);
	
	
	@Query(value="select * from videostatus where user_id=?1 and video_id=?2",nativeQuery = true)
	public Videostatus getVideoStatus(int userid,int videoid);
	

}
