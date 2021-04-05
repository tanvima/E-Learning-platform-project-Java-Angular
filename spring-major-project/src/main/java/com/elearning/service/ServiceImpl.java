package com.elearning.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.elearning.dao.CartRepository;
import com.elearning.dao.CategoryRepository;
import com.elearning.dao.CertificateRepository;
import com.elearning.dao.CommentRepository;
import com.elearning.dao.CourseRepository;
import com.elearning.dao.EnrollmentRepository;
import com.elearning.dao.LikesRepository;
import com.elearning.dao.ProfileRepository;
import com.elearning.dao.UserRepository;
import com.elearning.dao.VideoRepository;
import com.elearning.dao.VideoStatusRepository;
import com.elearning.mail.EmailUtil;
import com.elearning.mail.PdfMail;
import com.elearning.model.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class ServiceImpl implements ApplicationService {
	
	public static final Logger logger = LogManager.getLogger(ServiceImpl.class.getName());

	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	VideoRepository videoRepo;
	@Autowired
	UserRepository<User> userRepo;
	@Autowired
	EnrollmentRepository enrollRepo;
	@Autowired
	ProfileRepository profileRepo;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	VideoStatusRepository videoStatusRepo;
	@Autowired
	LikesRepository likesRepo;
	@Autowired
	CommentRepository commentRepo;
	@Autowired
	CertificateRepository certificateRepo;

	
	
	public Category addCategory(Category category) {
		logger.debug("inside add category");
		return categoryRepo.save(category);

	}

	public List<Category> getAllCategory() {
		List<Category> category = categoryRepo.findAll();
		logger.debug("inside view category");
		return category;

	}

	public Optional<Category> getCategory(int id) {
		Optional<Category> category = categoryRepo.findById(id);
		return category;
	}

	public int deleteCategory(int id) {
	categoryRepo.deleteById(id);
	logger.debug("inside delete category");
	Optional<Category> cat = categoryRepo.findById(id);
		if(cat.isEmpty()) {
			return 0;
		}else {
			return 1;
		}

	}
	
	public void updateCategory(int id, Category category) {
		Category c=new Category();
		logger.debug("inside update category");
		List<Course> list=courseRepo.getCourseByCategory(id);
		c=categoryRepo.getOne(id);
		
		c.setCourses(list);
		c.setCategoryId(id);
		c.setCategoryLogo(category.getCategoryLogo());
		c.setCategoryDesc(category.getCategoryDesc());
		c.setCategoryName(category.getCategoryName());
		//fetch list of courses here and add it in category object , After that u can save 
		categoryRepo.save(c); 		
	}  

	public void addCourse(int id, Course course) {
		Optional<Category> category = categoryRepo.findById(id);
		List<Course> courses = category.get().getCourses();
		courses.add(course);
		category.get().setCourses(courses);
		categoryRepo.save(category.get());

	}

	public List<Course> getAllCourses() {
		List<Course> courses = courseRepo.findAll();
			
		return setAvgRating(courses);
	}

	public void addVideo(int id, Video video) {
		Optional<Course> course = courseRepo.findById(id);
		List<Video> videoList = course.get().getVideo();
		videoList.add(video);
		course.get().setVideo(videoList);
		course.get().setNoOfVideo(videoList.size());
		Course savedcourse = courseRepo.save(course.get());
		List<Video> videoListCurr = savedcourse.getVideo();
		int videoid = videoListCurr.get(videoListCurr.size() - 1).getVideoId();
		System.out.println(videoListCurr.get(videoListCurr.size() - 1).getVideoId());

		List<Enrollment> enrollUserList = course.get().getEnrollment();
		Video video1 = videoRepo.findById(videoid).get();

		List<Videostatus> vs = new ArrayList<Videostatus>();

		for (Enrollment e : enrollUserList) {
			vs.add(new Videostatus().setUserId(e.getUser()));
		}

		video1.setVideostatus(vs);
		videoRepo.save(video1);
	}

	public List<Video> getAllVideo() {
		List<Video> videoList = videoRepo.findAll();
		return videoList;
	}

	public int deleteCourse(int id) {
		courseRepo.deleteById(id);
		Optional<Course> course = courseRepo.findById(id);
		if(course.isEmpty()) {
			return 0;
		}else
			return 1;
		

	}
	
	public int updateCourse(int id,Course course) {
		Course c=new Course();
		List<Video> list=videoRepo. getVideoByCourse(id);
		c=courseRepo.getOne(id);
		
		c.setVideo(list);
		c.setCourseId(id);
		c.setCourseName(course.getCourseName());
		c.setCourseDesc(course.getCourseDesc());
		c.setCourseLogo(course.getCourseLogo());
		c.setCoursePrice(course.getCoursePrice());
		c.setAuthorName(course.getAuthorName());
		c.setNoOfVideo(course.getNoOfVideo());
		//fetch list of courses here and add it in category object , After that u can save 
		Course updatecourse = courseRepo.save(c); 
		if(updatecourse.getCourseName().equals(course.getCourseName())) {
			return 1;
		}else 
			return 0;
		
	}
	
	public void updateVideo(int id, Video video) {
		Video v=new Video();
		v=videoRepo.getOne(id);
		
		
		v.setVideoId(id);
		v.setVideoName(video.getVideoName());
		v.setVideoDesc(video.getVideoDesc());
		v.setVideoPath(video.getVideoPath());
		
		videoRepo.save(v); 
	}
	
	public Optional<Video> getVideo(int id){
		Optional<Video> video = videoRepo.findById(id);
		return video;
	}

	public void deleteVideo(int id) {
		int courseid = videoRepo.getCourseId(id);
		Course course = courseRepo.findById(courseid).get();
		course.setNoOfVideo(course.getNoOfVideo()-1);
		courseRepo.save(course);
		videoRepo.deleteById(id);

	}

	public void addUser(User user, String type) {
		List<String> role=new ArrayList<String>();
		role.add("user");
		if (type.equalsIgnoreCase("prime")) {
			PrimeUser u = (PrimeUser) new PrimeUser().setEmail(user.getEmail()).setMobileNo(user.getMobileNo()).setName(user.getName())
					.setStatus(user.getStatus()).setUsername(user.getUsername()).setPassword(new BCryptPasswordEncoder().encode(user.getPassword())).setRoles(role).build();

			userRepo.save(u);
		} else {
			user.setRoles(role);
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			userRepo.save(user);
		}
	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = userRepo.findAll();
		List<User> userActualList = new ArrayList<User>();
		for(User user:userList) {
			userActualList.add(new User().setEmail(user.getEmail()).setUsername(user.getUsername()).setStatus(user.getStatus()).setNoOfAttempts(user.getNoOfAttempts()).build());
		}
		return userActualList;
	}

	@Override
	public Optional<User> getUserById(int id) {
		Optional<User> user = userRepo.findById(id);
		return user;
	}

	@Override
	public Profile addProfile(Profile profile, int id) {
		User user = userRepo.findById(id).get();
		profile.setUser(user).build();
		user.setProfile(profile);
		return profileRepo.save(profile);
	}

	@Override
	public void updateUserType(int id) {
		Optional<User> u = userRepo.findById(id);
		User user = u.get();
		PrimeUser primeuser = (PrimeUser) new PrimeUser().setEmail(user.getEmail()).setMobileNo(user.getMobileNo())
				.setStatus(user.getStatus()).setUsername(user.getUsername()).setId(id).build();
		userRepo.save(primeuser);
	}

	@Override
	public void enrollUser(int userid, int courseid) {
		

		Course course = courseRepo.findById(courseid).get();
		List<Enrollment> enrollmentList = course.getEnrollment();
		enrollmentList.add(new Enrollment().setUser(userid).build());
		course.setEnrollment(enrollmentList);
		courseRepo.save(course);

		List<Video> videoList = course.getVideo();
		for (Video video : videoList) {
			List<Videostatus> videoStatus = video.getVideostatus();
			videoStatus.add(new Videostatus().setUserId(userid).build());
			video.setVideostatus(videoStatus);
			videoRepo.save(video);
		}		

	}
	
	@Transactional
	public void enrollAllUser(int userid) {
		List<Integer> courseIdList = cartRepo.getAllCart(userid);
		for(int courseid : courseIdList) {
			enrollUser(userid, courseid);
			removeFromCart(userid, courseid);
		}
	}
	
	

	
	public void addToCart(int userid, int courseid) {
		Course course = courseRepo.findById(courseid).get();
		List<Cart> cartList = course.getCart();
		cartList.add(new Cart().setUserId(userid).build());
		course.setCart(cartList);
		courseRepo.save(course);

	}

	@Override
	@Transactional
	public void removeFromCart(int userId, int courseId) {
		cartRepo.removeFromCart(userId, courseId);

	}

	
	public List<Course> setAvgRating(List<Course> courseList) {

		for (Course c : courseList) {
			float avgRating = 0;
			for (Feedback f : c.getFeedback()) {
				avgRating = avgRating + f.getRating();
			}
			int rating = (int) (avgRating / (c.getFeedback().size()));
			System.out.println("\n"+rating);
			c.setRating(rating);
		}
		return courseList;
	}
	
	@Override
	public List<Course> getPopularCourse() {
		List<Course> courseList = courseRepo.findAll();
		Map<Course, Integer> map = new HashMap<Course, Integer>();

		for (Course c : courseList) {
			float avgRating = 0;
			for (Feedback f : c.getFeedback()) {
				avgRating = avgRating + f.getRating();
			}
			int rating = (int) (avgRating / (c.getFeedback().size()));
			
			map.put(c, rating);
		}

		
		
		Map<Course, Integer> sortedByCount = map.entrySet()
                .stream()
                .sorted((Map.Entry.<Course, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		List<Course> courses = new ArrayList<Course>(sortedByCount.keySet());
		
		return setAvgRating(courses).subList(0, 6);
	}

	public void addFeedback(Feedback feedback, int userid, int courseid) {
		Course course = courseRepo.findById(courseid).get();
		List<Feedback> feedBackList = course.getFeedback();
		feedBackList.add(feedback.setUserid(userid));
		course.setFeedback(feedBackList);
		courseRepo.save(course);

	}


	public  List<Course> getCourseByCat(int id) {
		List<Course> courses = courseRepo.getCourseByCategory(id);

		return setAvgRating(courses);
	}

	@Override
	public Message addCourseLike(int userid, int courseid) {
		 Optional<Likes> like = likesRepo.getLike(userid, courseid);
		 if(like.isEmpty()) {
			 Course course = courseRepo.findById(courseid).get();
			 List<Likes> courseLike = course.getLike();
			 courseLike.add(new Likes().setStatus("like").setUseId(userid).build());
			 course.setLike(courseLike);
			courseRepo.save(course) ;
			return new Message("Like");
		 }
		 else if(like.get().getStatus().equalsIgnoreCase("like")) {
			 like.get().setStatus("unlike");
			 likesRepo.save(like.get());	
			 return new Message("Unlike");
		 }
		 else if(like.get().getStatus().equalsIgnoreCase("unlike")) {
			 like.get().setStatus("like");
			 likesRepo.save(like.get());	
			 return new Message("Like");
		 }
		return null;

		
	}

	@Override
	public Course getCourseByCourseId(int courseId) {
		Course course = courseRepo.findById(courseId).get();
		List<Course> c = new ArrayList<Course>();
		c.add(course);
		return setAvgRating(c).get(0);
	}
	
	public Optional<Course> getCourse(int id) {
		Optional<Course> course = courseRepo.findById(id);
		
		return course;
	}
	
	public  List<Course> getCourseByCatForDisplay(int id) {
		List<Course> courses = courseRepo.getCourseByCategory(id);
		return courses;
}
	public  List<Video> getVideoByCourseId(int id){
		List<Video> videos = videoRepo.getVideoByCourse(id);
		return videos;
	}

	@Transactional
	public Message nextVideo(int userid, int videoid, int courseid) {
		System.out.println("\n\n\n\n\n\n");
		Optional<Enrollment> enroll = enrollRepo.getEnrollment(userid, courseid);
		
		if(enroll.get().getStatus().equals("completed")) {
			return new Message("complete");
			
		}
		
		
		int watched_video = enroll.get().getVideoWatched();
		Course course = courseRepo.findById(courseid).get();
		
		Videostatus videostatus = videoStatusRepo.getVideoStatus(userid, videoid);
		if(videostatus.getStatus().equalsIgnoreCase("incomplete")) { 
			
			System.out.println("Watched videos "+watched_video);
			System.out.println("Total videos "+course.getNoOfVideo());
			
			enroll.get().setVideoWatched(watched_video+1);
			enrollRepo.save(enroll.get());
			videoStatusRepo.updateVideoStatus(userid, videoid);
			if(watched_video >= course.getNoOfVideo()-1) {
				enroll.get().setStatus("completed");
				enrollRepo.save(enroll.get());
				
				generateCompeletionCerti(userid, courseid);
				return new Message("certificate");
			}
			else {
				
				return new Message("incomplete");
			}	
		}
		else {
			if(watched_video==course.getNoOfVideo()) {
				enroll.get().setStatus("completed");
				enrollRepo.save(enroll.get());
				generateCompeletionCerti(userid, courseid);
				return new Message("certificate");
			}
		}
		return new Message("incomplete");
		
	}

	@Override
	public List<Video> getAllVideoByCourse(int courseid) {
		
		return courseRepo.findById(courseid).get().getVideo();
	}

	@Override
	public List<Videostatus> getVideoStatus(int userid, int courseid) {
		Course course= courseRepo.findById(courseid).get();
		List<Videostatus> videoStatus = new ArrayList<Videostatus>();
		for(Video v : course.getVideo()) {
			videoStatus.add(videoStatusRepo.getVideoStatus(userid, v.getVideoId()));
		}
		return videoStatus;
	}
	
	public boolean generateCompeletionCerti(int uid, int cid) {
        // TODO Auto-generated method stub
        
        Optional<User> user=userRepo.findById(uid);
       
        Optional<Course> course = courseRepo.findById(cid);
    
        String pdfname=user.get().getUsername()+course.get().getCourseName();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\tanvima\\Desktop\\Java Major\\Certificate\\"+pdfname+".pdf"));
            document.setPageSize(PageSize.A4);
           
            document.open();
            
       
            Rectangle rect= new Rectangle(560,800);
            rect.enableBorderSide(1);
            rect.enableBorderSide(2);
            rect.enableBorderSide(4);
            rect.enableBorderSide(8);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            rect.setBorderColor(BaseColor.BLACK);
            rect.setLeft(22);
            rect.setBottom(17);
            document.add(rect);
            
            
     
            Image img = Image.getInstance("logo1.png");
           document.add(img);
            
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 30, BaseColor.BLACK);
            Chunk chunk = new Chunk("\n\n\nCertificate Of Completion", font);
            Paragraph preface = new Paragraph(chunk); 
            preface.setAlignment(Element.ALIGN_CENTER);
          document.add(preface);
            
         
            
            Font font1 = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 15, BaseColor.GRAY);
            Font font2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 25, BaseColor.DARK_GRAY);
            Font font3 = FontFactory.getFont(FontFactory.TIMES_BOLD, 17, BaseColor.DARK_GRAY);
            Chunk chunk1=new Chunk("\n\n\n\nThis is to Certify that"+"\n\n",font1);
            Chunk chunk2=new Chunk(user.get().getName()+"\n\n",font2);
            Chunk chunk3=new Chunk("has successfully completed \n\n",font1);
            Chunk chunk4 = new Chunk(course.get().getCourseName(),font3);
            Chunk bottomline = new Chunk("\n\n\n\n___________________________________________");
            
            Paragraph content = new Paragraph(); 
            content.add(chunk1);
            content.add(chunk2);
            content.add(chunk3);
            content.add(chunk4);
            content.add(bottomline);
            content.setAlignment(Element.ALIGN_CENTER);
            document.add(content);
            
            
          
            document.close();
            
            Certificate certi=new Certificate().setCourseId(cid).setPath(pdfname+".pdf").build();
            List<Certificate> certificateList = user.get().getCertificate();
            certificateList.add(certi);
            user.get().setCertificate(certificateList);
            userRepo.save(user.get());
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return false;
   }

	@Override
	public List<Course> getCartCourses(int userid) {
		List<Integer> courseid = cartRepo.getAllCart(userid);
		List<Course> courseList = new ArrayList<Course>();
		for(int c : courseid) {
			courseList.add(courseRepo.findById(c).get());
		}
		return courseList;
	}

	@Override
	public List<Course> getEnrollCourse(int userid) {
		List<Course> coursList = new ArrayList<Course>();
		List<Enrollment> enrollment = enrollRepo.getEnrollmentList(userid);
		List<Integer> courseid = enrollRepo.getEnrollmentCourse(userid);
		for(int i=0; i<enrollment.size();i++) {
			Course c = courseRepo.findById(courseid.get(i)).get();
			c.setStatus(enrollment.get(i).getStatus());
			coursList.add(c);
			
		}
		return coursList;
	}

	@Override
	public void addComment(int courseid, Comments comment) {
		Course course = courseRepo.findById(courseid).get();
		List<Comments> comments = course.getComment();
		comments.add(comment);
		course.setComment(comments);
		courseRepo.save(course);

		
	}

	@Override
	public List<Message> getCourseLike(int userid) {
		List<Integer> courseId = enrollRepo.getEnrollmentCourse(userid);
		List<Message> message = new ArrayList<Message>();
		System.out.println(courseId.size());
		for(Integer c : courseId) {
			Optional<Likes> like = likesRepo.getLike(userid, c);
			if(like.isPresent()) {
				if(like.get().getStatus().equalsIgnoreCase("Like")) {
					message.add(new Message("Like"));
				}else message .add(new Message("Unlike"));
			}else {
				System.out.println(("NO Entry"));
				message.add(new Message("Unlike"));
			}
			
		
		}
		
		return message;
	}

	@Override
	public void deleteComment(int commentid) {
		commentRepo.deleteById(commentid);
		
	}

	@Override
	public Message sendOTP(String email) throws AddressException, MessagingException {
		int otp = (int) Math.floor(Math.random()*1000000);
		EmailUtil eu = new EmailUtil();
		String msg = "Welcome to Cybage E-Learning. Please enter the given OTP to activate your account : "+otp;
        eu.sendEmail(email,msg);
        return new Message(Integer.toString(otp));
		
	}

	@Override
	public User activateAccount(String username) {
		System.out.println("\n\n\n"+username);
		User user = userRepo.findByUsername(username);
		user.setStatus("unblock");
		user.setNoOfAttempts(0);
		User u = userRepo.save(user);
		return u;
		
	}

	@Override
	public void resetPassword(String email, String password) {
		User user = userRepo.findByEmail(email);
		System.out.println("\n\n"+password);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		userRepo.save(user);
		
	}

	@Override
	public Message checkUsername(String username) {
		List<User> userList=userRepo.findAll();
		for(User user: userList) {
			if(user.getUsername().equals(username)) {
				return new Message("match");
			}
			else {
				return new Message("not match");
			}
		}
		return null;
	}

	@Override
	@Transactional
	public Message updateNoOfAttempts(String username) {
		userRepo.updateNoOfAttempt(username);
		return new Message(Integer.toString(userRepo.getNoOfAttempt(username)));
		
	}

	@Override
	@Transactional
	public void clearNoOfAttempts(String username) {
		userRepo.clearNoOfAttempt(username);
	}

	@Override
	public void blockUser(String username) {
		User user = userRepo.findByUsername(username).setStatus("block");
		userRepo.save(user);
		
	}

	@Override
	public void requestAdmin(String username) throws AddressException, MessagingException {
		EmailUtil eu = new EmailUtil();
		User user = userRepo.findByUsername(username);
		String msg = "Greeting Admin! Please unblock the user with username : "+username+" and email : "+user.getEmail();
        eu.sendEmail("tanvima@cybage.com",msg);
        userRepo.save(user.setNoOfAttempts(-1));	
	}

	@Override
	public Optional<Profile> getProfileById(int id) {
		User user = userRepo.findById(id).get();
		Optional<Profile> profile = Optional.of(user.getProfile());
		if(profile.isPresent()) {
			profile.get().setUser(null);	
		}
		return profile;
		
	}

	@Override
	public void updateUserType(User user) {
		userRepo.save(user);
		
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		User oldUser = userRepo.findById(user.getId()).get();
		oldUser.setMobileNo(user.getMobileNo());
		oldUser.setName(user.getName());
		
		if(oldUser.getProfile()==null) {
			addProfile(user.getProfile(), user.getId());
		}
		else {
			System.out.println("\n\n\n\n\n\n\n\n In here");
			profileRepo.updateProfile(user.getProfile().getAddress(), user.getProfile().getEducation(), user.getProfile().getProfession(),user.getProfile().getProfileid());
		}
		
		userRepo.save(oldUser);
		
		
	}

	@Override
	public void mailpdf(int userid,String coursename) {
		PdfMail mailpdf = new PdfMail();
		try {
			User user = userRepo.findById(userid).get();
			mailpdf.sendEmail(user.getEmail(), user.getUsername()+coursename, coursename);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllBlockUser() {
		List<User> userList= userRepo.findAll();
		List<User> userListFinal=new ArrayList<User>();
		for(User u : userList) {
			User user = new User().setName(u.getName()).setEmail(u.getEmail()).setId(u.getId()).setNoOfAttempts(u.getNoOfAttempts()).setUsername(u.getUsername()).build();
			System.out.println(user.toString());
			userListFinal.add(user);
		}
		return userListFinal.stream().filter(u->u.getNoOfAttempts() == -1).collect(Collectors.toList());
		
		
	}

	@Override
	public User unblockUser(int userid) {
		User u = activateAccount(userRepo.findById(userid).get().getUsername());
		return u;
		
	}


	}


