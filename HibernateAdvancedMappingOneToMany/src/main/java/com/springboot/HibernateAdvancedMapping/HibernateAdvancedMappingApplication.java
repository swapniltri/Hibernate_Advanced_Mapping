package com.springboot.HibernateAdvancedMapping;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.HibernateAdvancedMapping.Dao.AppDAO;
import com.springboot.HibernateAdvancedMapping.Entity.Course;
import com.springboot.HibernateAdvancedMapping.Entity.Instructor;
import com.springboot.HibernateAdvancedMapping.Entity.InstructorDetails;
import com.springboot.HibernateAdvancedMapping.Entity.Review;
import com.springboot.HibernateAdvancedMapping.Entity.Student;

@SpringBootApplication
public class HibernateAdvancedMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateAdvancedMappingApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO)
	{
		return runner -> {
//			createInstructor(appDAO);
			
//			findInstructor(appDAO);
			
//			deleteInstructor(appDAO);
			
//			findInstructorDetailById(appDAO);
			
//			deleteInstructorDetailById(appDAO);
			
//			createInstructorWithCourses(appDAO);
			
//			findInstructorWithCourses(appDAO);
			
			//This function will fetch the instructor LAZY, but because of JOIN FETCH courses will be fetched eagerly.
//			findInstructorWithCoursesJoinFetch(appDAO); 
			
//			updateInstructor(appDAO);
			
//			updateCourse(appDAO);
			
//			deleteInstructorById(appDAO);
			
//			deleteCourseById(appDAO);
			
//			createCourseWithReviews(appDAO);
			
//			findCourseAndReviewsByCourseId(appDAO);
			
//			deleteCourseAndItsReviews(appDAO);
			
//			createCourseAndStudent(appDAO);
			
//			findCourseAndStudentByCourseId(appDAO);
			
//			findStudentAndCourseByStudentId(appDAO);
			
//			addMoreCoursesToStudent(appDAO);
			
//			deleteCourseById(appDAO); //This delete the course and all its links with the student in course_student join table.
			
			deleteStudentById(appDAO);
			
			
		};
	}
	
	private void deleteStudentById(AppDAO appDAO)
	{
		int id=2;
		appDAO.deleteStudentById(id);
	}
	
	private void addMoreCoursesToStudent(AppDAO appDAO)
	{
		int id=1;
		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		
		Course extraCourse1 = new Course("Near to Supreme");
		Course extraCourse2 = new Course("Significance of lotus feet of lord Krishna");
		
		student.addCourse(extraCourse1);
		student.addCourse(extraCourse2);
		
		appDAO.updateStudent(student);
	}
	
	private void findStudentAndCourseByStudentId(AppDAO appDAO)
	{
		int id = 1;
		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		System.out.println(student);
		System.out.println(student.getCourses());
	}
	
	private void findCourseAndStudentByCourseId(AppDAO appDAO)
	{
		int id = 4;
		Course course = appDAO.findCourseAndStudentsByCourseId(id);
		System.out.println(course);
		for(Student student: course.getStudents()) {
			System.out.println(student);
		}
	}
	
	private void createCourseAndStudent(AppDAO appDAO)
	{
		Course course = new Course("Habibi, come to Dubai by Andrew Tate");
		
		Student student1 = new Student("Swapnil", "Tripathi", "swapnil@gmail.com");
		Student student2 = new Student("Simarprit", "Singh", "simar@gmail.com");
		
		course.addStudent(student1);
		course.addStudent(student2);
		
		appDAO.save(course);
	}
	
	private void deleteCourseAndItsReviews(AppDAO appDAO)
	{
		int id=3;
		appDAO.deleteCourseById(id);
	}
	
	private void findCourseAndReviewsByCourseId(AppDAO appDAO)
	{
		int id=3;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);
		System.out.println(course);
		for(Review review:course.getReviews()) {
			System.out.println(review.getComment());
		}
	}
	
	private void createCourseWithReviews(AppDAO appDAO)
	{
		Course course = new Course("Pacman...The complete guide");
		
		course.addReview(new Review("Nice course..."));
		course.addReview(new Review("Amazing..."));
		course.addReview(new Review("Habibi..."));
		
		appDAO.save(course);
		
	}
	
	private void deleteCourseById(AppDAO appDAO)
	{
		int id=5;
		appDAO.deleteCourseById(id);
	}
	
	private void deleteInstructorById(AppDAO appDAO)
	{
		int id=1;
		appDAO.deleteInstructorById(id);
	}
	
	private void updateCourse(AppDAO appDAO)
	{
		int id=1;
		Course course = appDAO.findCourseById(id);
		course.setTitle("Enjoy the simple thing");
		appDAO.update(course);
	}
	
	private void updateInstructor(AppDAO appDAO)
	{
		int id=1;
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println(instructor);
		
		instructor.setLastName("Tester");
		appDAO.update(instructor);
	}
	
	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO)
	{
		int id=1;
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		
		System.out.println(instructor);
		System.out.println(instructor.getCourses());
	}
	
	private void findInstructorWithCourses(AppDAO appDAO)
	{
		int id=1;
		
		//since the fetch type is lazy, courses will not get fetched instantly
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println(instructor);
		
		
		
		//This will work with fetch type eager.
//		System.out.println(instructor.getCourses());
		
		
		
		//This will work with fetch type lazy.
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println(courses);
	}
	
	private void createInstructorWithCourses(AppDAO appDAO)
	{
		Instructor instructor = new Instructor("Swapnil", "Tripathi", "swapnil@gmail.com");
		InstructorDetails instructorDetails = new InstructorDetails("asdfjkl;", "gym");
		instructor.setInstructorDetailId(instructorDetails);
		
		//creating courses
		Course course1 = new Course("Air guitar, the ultimate guide");
		Course course2 = new Course("Pinball superclass");
		
		//Adding courses to instructor.
		instructor.add(course1);
		instructor.add(course2);
		
		appDAO.save(instructor);
	}
	
	private void deleteInstructorDetailById(AppDAO appDAO)
	{
		appDAO.deleteInstructorDetailById(2);
	}
	
	private void findInstructorDetailById(AppDAO appDAO)
	{
		InstructorDetails instructorDetails=appDAO.findInstructorDetail(2);
		System.out.println(instructorDetails);
		System.out.println(instructorDetails.getInstructor());
	}
	
	private void deleteInstructor(AppDAO appDAO)
	{
		int id=1;
		appDAO.deleteInstructorById(id);
	}
	
	private void findInstructor(AppDAO appDAO)
	{
		int id=1;
		Instructor instructor=appDAO.findInstructorById(id);
		System.out.println(instructor);
	}
	
	private void createInstructor(AppDAO appDAO)
	{
		Instructor tempInstructor=new Instructor("Swapnil", "Tripathi", "swapnil@gmail.com");
		InstructorDetails tempInstructorDetails=new InstructorDetails("http://www.youtube.com/bodying", "Gyming");
		tempInstructor.setInstructorDetailId(tempInstructorDetails);
		
		/*
		 * This function will save both tempInstructor and tempInstructorDetails
		 * into their corresponding sql tables because of (cascade=CascadeType.ALL
		 * 
		 */
		appDAO.save(tempInstructor);
		System.out.println("Instructor saved: "+tempInstructor);
	}

}
