package com.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elearning.model.Enrollment;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
	
//	@Modifying
//	@Query(value="update enrollment set video_watched= video_watched+1  where user_id=?1 and course_id=?2",nativeQuery = true)
//	public void updateVideoWatched(int user_id, int course_id);
	
	@Query(value="select * from enrollment where userid=?1 and course_id=?2",nativeQuery = true)
	public Optional<Enrollment> getEnrollment(int userid, int courseid);
	
	@Query(value="select * from enrollment where userid=?",nativeQuery = true)
	public List<Enrollment> getEnrollmentList(int userid);
	
	@Query(value="select course_id from enrollment where userid=?",nativeQuery = true)
	public List<Integer> getEnrollmentCourse(int userid);

}
