package com.springboot.HibernateAdvancedMapping.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.HibernateAdvancedMapping.Entity.Course;
import com.springboot.HibernateAdvancedMapping.Entity.Instructor;
import com.springboot.HibernateAdvancedMapping.Entity.InstructorDetails;
import com.springboot.HibernateAdvancedMapping.Entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AppDAOImpl implements AppDAO {

	private EntityManager entityManager;
	
	@Autowired
	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	
	@Override
	@Transactional //From spring framework
	public void save(Instructor instructor) {
		this.entityManager.persist(instructor);
	}

	@Override
	public Instructor findInstructorById(int id) {
		//This function will also fetch InstructorDetail, because by default the fetch type of @OneToOne mapping is "EAGER".
		return entityManager.find(Instructor.class, id); 
	}

	@Override
	@Transactional
	public void deleteInstructorById(int id)
	{
		Instructor instructor = entityManager.find(Instructor.class, id);
		List<Course> courses = instructor.getCourses();
		for(Course course : courses) {
			course.setInstructor(null);
		}
		//This will also delete associated InstructorDetail object because of CASCADE.ALL
		entityManager.remove(instructor);
	}

	@Override
	public InstructorDetails findInstructorDetail(int id) {
		return entityManager.find(InstructorDetails.class, id);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int id) {
		InstructorDetails instructorDetail=entityManager.find(InstructorDetails.class, id);
		
		//This will also delete the associated Instructor object.
		entityManager.remove(instructorDetail);
		
	}

	@Override
	public List<Course> findCoursesByInstructorId(int id) {
		TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
		query.setParameter("data", id);
		List<Course> courses = query.getResultList();
		return courses;
	}

	@Override
	public Instructor findInstructorByIdJoinFetch(int id) {
		TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id = :data",Instructor.class);
		query.setParameter("data", id);
		Instructor instructor = query.getSingleResult();
		return instructor;
	}
	
	@Override
	@Transactional
	public void update(Instructor instructor)
	{
		entityManager.merge(instructor);
	}
	
	@Override
	@Transactional
	public void update(Course course)
	{
		entityManager.merge(course);
	}
	
	@Override
	public Course findCourseById(int id)
	{
		Course course = entityManager.find(Course.class, id);
		return course;
	}
	
	@Override
	@Transactional
	public void deleteCourseById(int id)
	{
		Course course = entityManager.find(Course.class, id);
		entityManager.remove(course);
	}
	
	@Override
	@Transactional
	public void save(Course course)
	{
		entityManager.persist(course);//This will save all the reviews because of cascade.ALL
	}
	
	@Override
	public Course findCourseAndReviewsByCourseId(int id)
	{
		TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.reviews where c.id = :data", Course.class);
		query.setParameter("data", id);
		Course course = query.getSingleResult();
		
		return course;
	}
	
	@Override
	public Course findCourseAndStudentsByCourseId(int id)
	{
		TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.students where c.id = :data", Course.class);
		query.setParameter("data", id);
		Course course = query.getSingleResult();
		return course;
		
	}
	
	@Override
	public Student findStudentAndCoursesByStudentId(int id)
	{
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s JOIN FETCH s.courses where s.id = :data", Student.class);
		query.setParameter("data", id);
		
		Student student = query.getSingleResult();
		return student;
	}
	
	@Override
	@Transactional
	public void updateStudent(Student student)
	{
		entityManager.merge(student);
	}
	
	@Override
	@Transactional
	public void deleteStudentById(int id)
	{
		Student student = entityManager.find(Student.class, id);
		entityManager.remove(student);
	}
	
	
}
