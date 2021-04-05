package com.elearning.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.elearning.model.Category;
import com.elearning.model.Comments;
import com.elearning.model.Course;
import com.elearning.model.Feedback;
import com.elearning.model.Message;
import com.elearning.model.Profile;
import com.elearning.model.User;
import com.elearning.model.Video;
import com.elearning.model.Videostatus;

public interface ApplicationService {

	Category addCategory(Category category);

	List<Category> getAllCategory();

	Optional<Category> getCategory(int id);

	int deleteCategory(int id);
	void updateCategory(int id,Category category);

	void addCourse(int id,Course course);
	
	int updateCourse(int id,Course course);

	List<Course> getAllCourses();

	void addVideo(int id, Video video);

	List<Video> getAllVideo();

	int deleteCourse(int id);

	void addUser(User user,String type);
	
	void updateVideo(int id,Video video);

	void deleteVideo(int id);

	void updateUserType(int id);

	void enrollUser(int userid, int courseid);

	List<User> getAllUser();

	Profile addProfile(Profile profile, int id);
	
	
	Optional<User> getUserById(int id);

	void addToCart(int userid, int courseid);

	void removeFromCart(int userId, int courseId);

	Message addCourseLike(int userid,int courseid);

	List<Course> getPopularCourse();

	void addFeedback(Feedback feedback, int userid,int courseid);

	List<Course> getCourseByCat(int id);
	
	void enrollAllUser(int userid);
	 List<Course> setAvgRating(List<Course> courseList);

	Course getCourseByCourseId(int courseId);
	
	Optional<Video> getVideo(int id);
	
	public  List<Course> getCourseByCatForDisplay(int id);
	
	public  List<Video> getVideoByCourseId(int id);

	Message nextVideo(int userid, int videoid,int courseid);

	List<Video> getAllVideoByCourse(int courseid);

	List<Videostatus> getVideoStatus(int userid, int courseid);
	
	public boolean generateCompeletionCerti(int uid, int cid);

	List<Course> getCartCourses(int userid);
	
	List<Course> getEnrollCourse(int userid);

	void addComment(int courseid, Comments comment);

	List <Message> getCourseLike(int userid);

	void deleteComment(int commentid);
	
	Message sendOTP(String email)throws AddressException, MessagingException;

	User activateAccount(String username);

	void resetPassword(String email, String password);

	Message checkUsername(String username);

	Message updateNoOfAttempts(String username);

	void clearNoOfAttempts(String username);

	void blockUser(String username);

	void requestAdmin(String username) throws AddressException, MessagingException;

	Optional<Profile> getProfileById(int id);

	void updateUserType(User user);

	void updateUser(User user);

	void mailpdf(int userid,String coursename);

	List<User> getAllBlockUser();

	User unblockUser(int userid);

	Optional<Course> getCourse(int id);

//	void getCertificateList(int userid);
	
		

	
}
