package com.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elearning.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>{

	@Query(value = "select * from video where course_id = ?", nativeQuery = true) 
	  public List<Video> getVideoByCourse(int id);
	
	@Query(value = "select course_id from video where video_id=?", nativeQuery = true)
	public int getCourseId(int id); 
	
	
}
