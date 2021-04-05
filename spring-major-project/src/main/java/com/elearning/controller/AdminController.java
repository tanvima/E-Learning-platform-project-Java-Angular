package com.elearning.controller;

import java.util.List;
import java.util.Optional;

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

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.elearning.model.*;
import com.elearning.service.ApplicationService;

@RestController
@RequestMapping("/elearning")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

	@Autowired
	ApplicationService applicationService;

	// add category
	@PreAuthorize("hasRole('ROLE_admin')")
	@PostMapping("/category")
	public  ResponseEntity<String> addCategory(@RequestBody Category category) {
		 Category addedCategory = applicationService.addCategory(category);
		 System.out.println(addedCategory.getCategoryName().isEmpty());
		 if(addedCategory.getCategoryName().isEmpty()) {
			 System.out.println("if");
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Category not added");
		 }else {
			 System.out.println("else");
			 return ResponseEntity.status(HttpStatus.ACCEPTED).body("Category added");
		 }
	}

	// to get all category
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> categoryList = applicationService.getAllCategory();

		if (categoryList.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryList);

		}
	}

	// to get category by id
	@GetMapping("/category/{id}")
	public ResponseEntity<Optional<Category>> getCategory(@PathVariable int id) {
		Optional<Category> category = applicationService.getCategory(id);

		if (category.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(category);

		}

	}

	// to delete category
	@PreAuthorize("hasRole('ROLE_admin')")
	@DeleteMapping("/category/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int id) {
		// applicationService.deleteCategory(id);
		Optional<Category> category = applicationService.getCategory(id);
		if (category.isEmpty()) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Given category not found, so cannot be deleted!!!");
		} else {
			applicationService.deleteCategory(id);
			return ResponseEntity.status(HttpStatus.OK).body("divya deleted");
		}

	}

	// to update category
	@PreAuthorize("hasRole('ROLE_admin')")
	@PutMapping("/category/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody Category newCategory) {

		Optional<Category> category = applicationService.getCategory(id);

		if (category.isEmpty()) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Given category not found, so cannot be updated!!!");
		} else {
			applicationService.updateCategory(id, newCategory);
			return ResponseEntity.status(HttpStatus.OK).body("divya updated");
		}
	}

	// to add course
	@PreAuthorize("hasRole('ROLE_admin')")
	@PostMapping("/course/{id}")
	public ResponseEntity<String> addCourse(@PathVariable int id, @RequestBody Course course) {
		System.out.println("----------------->" + course + "------------------>" + id);

		applicationService.addCourse(id, course);

		return ResponseEntity.status(HttpStatus.OK).body("course added");

	}

	// get all courses by category id
	@GetMapping("/course/{id}")
	public ResponseEntity<List<Course>> getCourseByCatId(@PathVariable int id) {
		List<Course> course = applicationService.getCourseByCat(id);

		if (course.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No courses found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(course);

		}

	}
	
	//get course by category id
	@GetMapping("/display/{id}")
	public ResponseEntity<List<Course>> getCourseByCatIdForDisplay(@PathVariable int id) {
		List<Course> course = applicationService.getCourseByCatForDisplay(id);

		if (course.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(course);

		}
	}

	// to get all course
	@GetMapping("/course")
	public ResponseEntity<List<Course>> getAllCourses() {

		List<Course> courseList = applicationService.getAllCourses();
		if (courseList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No course found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(courseList);

		}

	}

	 //get Course by course id

	@GetMapping("/courseid/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable int id) {
		Course course = applicationService.getCourseByCourseId(id);
		if (course==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No course found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(course);

		}
	}

	// to update course
	@PreAuthorize("hasRole('ROLE_admin')")
	@PutMapping("/course/{id}")
	public ResponseEntity<String> updateCourse(@PathVariable int id, @RequestBody Course newcourse) {

		Optional<Course> course = applicationService.getCourse(id);

		if (course.isEmpty()) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given course not found, so cannot be updated!!!");
		} else {
			applicationService.updateCourse(id, newcourse);
			return ResponseEntity.status(HttpStatus.OK).body("course updated");
		}

	}

	// to delete course
	@PreAuthorize("hasRole('ROLE_admin')")
	@DeleteMapping("/course/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable int id) {
		Optional<Course> course = applicationService.getCourse(id);
		if (course.isEmpty()) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Given category not found, so cannot be deleted!!!");
		} else {
			applicationService.deleteCourse(id);
			return ResponseEntity.status(HttpStatus.OK).body("course deleted");
		}

	}

	// to add video()
	@PreAuthorize("hasRole('ROLE_admin')")
	@PostMapping("/video/{id}")
	public ResponseEntity<String> addVideo(@PathVariable int id, @RequestBody Video video) {
		System.out.println("Amer" + video + id);
		applicationService.addVideo(id, video);
		return ResponseEntity.status(HttpStatus.OK).body("video added");
	}

	// to delete video
	@PreAuthorize("hasRole('ROLE_admin')")
	@DeleteMapping("/video/{id}")
	public ResponseEntity<String> deleteVideo(@PathVariable int id) {
//			applicationService.deleteVideo(id);
		Optional<Video> video = applicationService.getVideo(id);
		if (video.isEmpty()) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Given category not found, so cannot be deleted!!!");
		} else {
			applicationService.deleteVideo(id);
			return ResponseEntity.status(HttpStatus.OK).body("video deleted");
		}
	}

	// to update video()
	@PreAuthorize("hasRole('ROLE_admin')")
	@PutMapping("/video/{id}")
	public ResponseEntity<String> updateVideo(@PathVariable int id, @RequestBody Video newvideo) {
		Optional<Video> video = applicationService.getVideo(id);
		if (video.isEmpty()) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given course not found, so cannot be updated!!!");
		} else {
			applicationService.updateVideo(id, newvideo);
			return ResponseEntity.status(HttpStatus.OK).body("video updated");
		}

	}

	// to get video
	@GetMapping("/video/{id}")
	public ResponseEntity<Optional<Video>> getVideoById(@PathVariable int id) {
		Optional<Video> video = applicationService.getVideo(id);
		if (video.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No video found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(video);

		}
	}

	// to get All video
	@GetMapping("/video")
	public ResponseEntity<List<Video>> getAllVideo() {
		List<Video> video = applicationService.getAllVideo();
		if (video.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No videos found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(video);

		}
	}

	//to get popular courses (top 6)
	@GetMapping("/popularCourse")
	public ResponseEntity<List<Course>> getPopularCourse() {
		List<Course> courseList = applicationService.getPopularCourse();

		if (courseList.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No courses found!!!");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(courseList);

		}
	}

	@GetMapping("/videolist/{id}")
	public ResponseEntity<List<Video>> getVideoByCourseId(@PathVariable int id) {
		List<Video> video = applicationService.getVideoByCourseId(id);

		if (video.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No videos found!!!");
		} else {

			return ResponseEntity.status(HttpStatus.OK).body(video);

		}
	}

	//to block user
	@GetMapping("/blockuser")
	public ResponseEntity<List<User>> getAllBlockUser() {
		List<User> userList = applicationService.getAllBlockUser();
		if(userList.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Blocked User found!!!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	//to unblock a specific user
	@GetMapping("/blockuser/{userid}")
	public ResponseEntity<Message> unblockUser(@PathVariable int userid) {
		User u = applicationService.unblockUser(userid);
		if(u.getUsername().equals(null)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Cannot unblock User");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(new Message("user unblock successfully"));
		}

	}

}
