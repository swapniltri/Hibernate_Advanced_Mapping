package com.springboot.HibernateAdvancedMapping.Dao;

import java.util.List;

import com.springboot.HibernateAdvancedMapping.Entity.Course;
import com.springboot.HibernateAdvancedMapping.Entity.Instructor;
import com.springboot.HibernateAdvancedMapping.Entity.InstructorDetails;
import com.springboot.HibernateAdvancedMapping.Entity.Student;

public interface AppDAO {

	void save(Instructor instructor);
	
	Instructor findInstructorById(int id);
	
	void deleteInstructorById(int id);
	
	InstructorDetails findInstructorDetail(int id);
	
	void deleteInstructorDetailById(int id);
	
	List<Course> findCoursesByInstructorId(int id);
	
	Instructor findInstructorByIdJoinFetch(int id);
	
	void update(Instructor instructor);
	
	void update(Course course);
	
	Course findCourseById(int id);
	
	void deleteCourseById(int id);
	
	void save(Course course);//Saving courses along with reviews.
	
	Course findCourseAndReviewsByCourseId(int id);
	
	Course findCourseAndStudentsByCourseId(int id);
	
	Student findStudentAndCoursesByStudentId(int id);
	
	void updateStudent(Student student);
	
	void deleteStudentById(int id);
	
}
