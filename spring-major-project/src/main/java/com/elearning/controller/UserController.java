package com.elearning.controller;


import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.elearning.model.Comments;
import com.elearning.model.Course;
import com.elearning.model.Feedback;
import com.elearning.model.Message;
import com.elearning.model.Profile;
import com.elearning.model.User;
import com.elearning.model.Video;
import com.elearning.model.Videostatus;
import com.elearning.security.LoginUser;
import com.elearning.service.ApplicationService;


@RestController
@RequestMapping("/elearning/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@Autowired ApplicationService applicationService;
	public static final Logger logger = LogManager.getLogger(UserController.class.getName());
	
		//user registration
		@PostMapping("/{type}")
		public ResponseEntity<String> addUser(@RequestBody User user,@PathVariable String type) {
			applicationService.addUser(user,type);
			logger.debug("New user registered");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		//to get the all user
		@GetMapping("")
		public ResponseEntity<List<User>> getAllUser(){
			List<User> userList = applicationService.getAllUser();
			if(userList.size() == 0) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found!!!");
			}
			return  ResponseEntity.status(HttpStatus.OK).body(userList);
		}
		
		//get user by id
		@GetMapping("/{id}")
		public ResponseEntity<User> getUserById(@PathVariable int id){
			Optional<User> user = applicationService.getUserById(id);
			if(user.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found!!!");
			}
			return  ResponseEntity.status(HttpStatus.OK).body(user.get());
		}
		
		//updateuser profile
		@PutMapping("/updateUser")
		public ResponseEntity<Object> updateUser(@RequestBody User user) {
			System.out.println(user.getProfile());
			applicationService.updateUser(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		

		
		//for the enrollment
		@PreAuthorize("hasRole('ROLE_user')")
		@PostMapping("/enroll")
		public ResponseEntity<String> enrollAllUser(@RequestParam int userid) {
			applicationService.enrollAllUser(userid);
			logger.debug("User enrolled in the new courses");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		//to add the user profile
		@PostMapping("/profile/{id}")
		public ResponseEntity<String> addProfile(@RequestBody Profile profile,@PathVariable int id) {
			Profile updatedProfile = applicationService.addProfile(profile,id);
			if(updatedProfile ==null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not updated");
			}
			logger.debug("User updated profile");
			return  new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		
		
		//to add the course into cart
		@PreAuthorize("hasRole('ROLE_user')")
		@PostMapping("/addtocart")
		public ResponseEntity<String> addToCart(@RequestParam int userid,@RequestParam int courseid) {
			applicationService.addToCart(userid,courseid);
			logger.debug("User added course in the cart");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		//to remove course from cart
		@PreAuthorize("hasRole('ROLE_user')")
		@DeleteMapping("/removecart")
		public ResponseEntity<String> deleteFromCart(@RequestParam int userid,@RequestParam int courseid) {
			applicationService.removeFromCart(userid,courseid);
			logger.debug("User removed course in the cart");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		//get cart of user
		@PreAuthorize("hasRole('ROLE_user')")
		@GetMapping("/getCartCourses/{userid}")
		public List<Course> getCartCourses(@PathVariable int userid){
			return applicationService.getCartCourses(userid);
		}
		

		//get the list of videos of a course
		@PreAuthorize("hasRole('ROLE_user')")
		@GetMapping("/videolist/{id}")
		public ResponseEntity<List<Video>> getVideoByCourseId(@PathVariable int id) {
			List<Video> video = applicationService.getVideoByCourseId(id);

			if (video.size() == 0) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found!!!");
			} else {
				logger.debug("Fetching user the videolist of the category");
				return ResponseEntity.status(HttpStatus.OK).body(video);

			}
		}
		
		
		//to add comment
		@PreAuthorize("hasRole('ROLE_user')")
		@PostMapping("/addcomment/{courseid}/{userid}")
		public ResponseEntity<String> addComment(@PathVariable int courseid, @PathVariable int userid,@RequestBody Comments comment) {
			System.out.println("HELLLLO");
			comment.setUserId(userid);
			System.out.println("\n\nbmvb\n "+comment);
			applicationService.addComment(courseid,comment);
			System.out.println("\n\n\n "+comment);
			logger.debug("User added comment in the course");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		//add feedback to a specific course
		@PreAuthorize("hasRole('ROLE_user')")
		@PostMapping("/feedback/{userid}/{courseid}")
		public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback,@PathVariable int userid,@PathVariable int courseid) {
			applicationService.addFeedback(feedback,userid,courseid);
			logger.debug("User added feedback in the course ");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		//add like to a user
		@PreAuthorize("hasRole('ROLE_user')")
		@GetMapping("/addlike/{userid}/{courseid}")
			public Message addLike(@PathVariable int userid, @PathVariable int courseid) {
			logger.debug("User liked the course");
			return applicationService.addCourseLike(userid, courseid);
			
			}
		
		//get list of liked course by the user
		@PreAuthorize("hasRole('ROLE_user')")
		@GetMapping("/getlike/{userid}")
		public List <Message> getLike(@PathVariable int userid) {
		return applicationService.getCourseLike(userid);
		
		}
		
		//to update the video status when the video is watched
		@PreAuthorize("hasRole('ROLE_user')")
		@PostMapping("/nextvideo")
		public Message nextVideo(@RequestParam int userid, @RequestParam int videoid,@RequestParam int courseid) {
			Message message = applicationService.nextVideo(userid,videoid,courseid);
			logger.debug("User finished watching video");
			  return message;
		}
		
		//get the status of the video of the specific user and course
		@PreAuthorize("hasRole('ROLE_user')")
		@GetMapping("/getVideoStatus/{userid}/{courseid}")
		public List<Videostatus> getVideoStatus(@PathVariable int userid,@PathVariable int courseid){
			return applicationService.getVideoStatus(userid,courseid);
		}
		
		//to get the certificate of the user of a specific course
		@PreAuthorize("hasRole('ROLE_user')")
		@GetMapping("/certificate/{userid}/{courseid}")
		public ResponseEntity<String> generateCertificate(@PathVariable int userid, @PathVariable int courseid) {
			applicationService.generateCompeletionCerti(userid, courseid);
			logger.debug("User generated ceritificate");
			return  new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		//to get list of enrolled courses
		@PreAuthorize("hasRole('ROLE_user')")
		@GetMapping("/mycourses/{userid}")
		public List<Course> getEnrollCourse(@PathVariable int userid){
			logger.debug("User fetched it's enrolled courses");
			return applicationService.getEnrollCourse(userid);
		}
		
		//to delete the comment made by the user
		@PreAuthorize("hasRole('ROLE_user')")
		@DeleteMapping("/deleteComment/{commentid}")
		public ResponseEntity<String> deleteComment(@PathVariable int commentid) {
			applicationService.deleteComment(commentid);
			logger.debug("User deleted comment");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		
		//to send the otp 
		@GetMapping("/sendOTP/{email}")
			public Message senOTP(@PathVariable String email) throws AddressException, MessagingException {
			logger.debug("OTP sent");
				return applicationService.sendOTP(email);
			}
		//unblock the account after verification
		@GetMapping("/activateaccount/{username}")
		public void activateAccount(@PathVariable String username) {
			logger.debug("User account activated :"+username);
			applicationService.activateAccount(username);
		}
		
		//reset password after forgot password
		@PutMapping("/resetPassword/{email}")
		public ResponseEntity<String> resetPassword(@PathVariable String email, @RequestBody LoginUser password) {
			applicationService.resetPassword(email,password.getPassword());
			logger.debug("User reset it's password");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
	
		//update number of attempts made by the user for invalid login
		@GetMapping("/updateNoOfAttempts/{username}")
		public Message updateNoOfAttempt(@PathVariable String username) {
			logger.debug("User attempted in valid login");
			return applicationService.updateNoOfAttempts(username);
		}
		
		//to clear the number of invalid logins
		@GetMapping("/clearNoOfAttempts/{username}")
		public void clearNoOfAttempt(@PathVariable String username) {
			applicationService.clearNoOfAttempts(username);
		}
		
		//to block user after 3 invalid attempts
		@GetMapping("/blockUser/{username}")
		public void blockUser(@PathVariable String username) {
			logger.debug("User account blocked: "+username);
			applicationService.blockUser(username);
		}
		
		//to mail admin to unblock the user
		@GetMapping("/requestadmin/{username}")
		public void requestAdmin(@PathVariable String username) {
			try {
				applicationService.requestAdmin(username);
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
			logger.debug("User requested admin");
			
		}
		//mail the certificate to the user at registered email id
		@PreAuthorize("hasRole('ROLE_user')")
		@GetMapping("/mailcerti/{userid}/{coursename:[a-zA-Z &+-]*}")
		public void sendCertificate(@PathVariable int userid, @PathVariable("coursename") String coursename) {
			
			applicationService.mailpdf(userid,coursename);
			logger.debug("User mailed certificate to the registered email");
			
		}
		
}
