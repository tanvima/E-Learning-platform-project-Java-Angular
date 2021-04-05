package com.elearning.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.elearning.controller.AdminController;
import com.elearning.dao.CategoryRepository;
import com.elearning.model.Cart;
import com.elearning.model.Category;
import com.elearning.model.Course;
import com.elearning.model.Enrollment;
import com.elearning.model.Feedback;
import com.elearning.model.Video;
import com.elearning.service.ApplicationService;

class AdminServiceImplMockTest {

	@Mock
	private AdminController a;
	@Mock
	private ApplicationService adminService;
	@Mock
	private CategoryRepository categoryRepo;
	@InjectMocks
	private Category cat;

	@InjectMocks
	private Course course;


	private List<Category> list;
	private List<Course>listCourse;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);
		list=new ArrayList<>();
		listCourse =new ArrayList<>();

		cat.setCategoryDesc("xyz");
		cat.setCategoryId(1);
		cat.setCategoryLogo("abc");
		cat.setCategoryName("pqrs");                   
		list.add(cat);//size of list 1

		//list of course
		course.setCourseDesc("pqr");
		course.setCourseId(1);
		course.setCourseLogo("abc");
		course.setCourseName("abhd");
		course.setCoursePrice(200);

		List<Video> listVideo=new ArrayList<>();
		listVideo.add(new Video(1, "pqrs", "abc", "abc"));

		List<Feedback> listfeedback=new ArrayList<>();
		listfeedback.add(new Feedback(1, "abc", 5, 1));

		List<Enrollment> listEnrollment=new ArrayList<>();
		listEnrollment.add(new Enrollment(1, "adasdas", 5, 1));

		List<Cart> listCart=new ArrayList<>();
		listCart.add(new Cart(1,1));

		course.setCart(listCart);
		course.setVideo(listVideo);
		course.setFeedback(listfeedback);
		course.setEnrollment(listEnrollment);

		listCourse.add(course);

	}

	@Test
	public void  getAllCategory() {
		list=new ArrayList<>();
		cat.setCategoryDesc("xyz");
		cat.setCategoryId(1);
		cat.setCategoryLogo("abc");
		cat.setCategoryName("pqrs");                   
		list.add(cat);//size of list 1
		when(adminService.getAllCategory()).thenReturn(list);
		List<Category> list3 = adminService.getAllCategory();
		assertEquals(1,list3.size());
	} 


	@Test
	public void addCategory() {
		list=new ArrayList<>();
		cat.setCategoryDesc("xyz");
		cat.setCategoryId(1);
		cat.setCategoryLogo("abc");
		cat.setCategoryName("pqrs");                   
		list.add(cat);//size of list 1
		when(adminService.addCategory(cat)).thenReturn(cat);
		Category aa=adminService.addCategory(cat);
		assertEquals(cat, aa);
	}


	@Test
	public void getCategory() {
		Category res=new Category();
		list=new ArrayList<>();
		cat.setCategoryDesc("xyz");
		cat.setCategoryId(1);
		cat.setCategoryLogo("abc");
		cat.setCategoryName("pqrs");                   
		list.add(cat);//size of list 1
		when(adminService.getCategory(Mockito.anyInt())).thenReturn(Optional.of(cat));
		res=adminService.getCategory(1).get();
		assertEquals(cat, res);
	}
	
	@Test
	public void deleteCategory() {
//	                            list=new ArrayList<>();
//	            cat.setCategoryDesc("xyz");                        
//	            cat.setCategoryId(1);
//	            cat.setCategoryLogo("abc");
//	            cat.setCategoryName("pqrs");                   
//	            list.add(cat);//size of list 1
	                when(adminService.deleteCategory(Mockito.anyInt())).thenReturn(1);
	                int i=adminService.deleteCategory(1);
	                assertEquals(i, 1);
	                                }
	
    @Test
    public void  getAllCourses(){
                    
                    when(adminService.getAllCourses()).thenReturn(listCourse);
                    List<Course> list3 = adminService.getAllCourses();
        assertEquals(1,list3.size());
                    }
    @Test
    public void deleteCourse() {
                    
                    
                    course.setCourseDesc("pqr");
                    course.setCourseId(1);
                    course.setCourseLogo("abc");
                    course.setCourseName("abhd");
                    course.setCoursePrice(200);
                    listCourse.add(course);//size of list 1
                    when(adminService.deleteCourse(Mockito.anyInt())).thenReturn(1);
                    int i=adminService.deleteCourse(1);
                    assertEquals(i, 1);
                    
    }
    
    @Test
    public void  getAllVideo()
    {

                    List<Video> listVideo=new ArrayList<>();
                    listVideo.add(new Video(1, "pqrs", "abc", "abc"));
                    when(adminService.getAllVideo()).thenReturn(listVideo);
                    List<Video> list3 = adminService.getAllVideo();
        assertEquals(1,list3.size());
    }
    @Test
    public void updateCourse() {
                    course.setCourseDesc("pqr");
                    course.setCourseId(1);
                    course.setCourseLogo("abc");
                    course.setCourseName("abhd");
                    course.setCoursePrice(200);
                    listCourse.add(course);//size of list 1
                    when(adminService.updateCourse(1,course)).thenReturn(1);

                    int i=adminService.updateCourse(1, course);
                    assertEquals(i, 1);
                    
    }

    






}
