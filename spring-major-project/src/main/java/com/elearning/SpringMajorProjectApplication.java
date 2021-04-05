package com.elearning;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.elearning.dao.CategoryRepository;
import com.elearning.dao.CourseRepository;
import com.elearning.dao.UserRepository;
import com.elearning.dao.VideoRepository;
import com.elearning.model.Category;
import com.elearning.model.Course;
import com.elearning.model.PrimeUser;
import com.elearning.model.Profile;
import com.elearning.model.User;
import com.elearning.model.Video;

@SpringBootApplication
public class SpringMajorProjectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringMajorProjectApplication.class, args);
	}
//	@Autowired
//	UserRepository<User> userRepo;
	@Override
	public void run(String... args) throws Exception {
		
//		User user = new User().setUsername("admin").setPassword(new BCryptPasswordEncoder().encode("admin")).build();
//		ArrayList<String> roles = new ArrayList<String>();    
//		roles.add("admin");    
//		user.setRoles(roles);   
//		userRepo.save(user);   
//		
	}


}
