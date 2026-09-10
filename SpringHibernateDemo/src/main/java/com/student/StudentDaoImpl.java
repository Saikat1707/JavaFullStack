package com.student;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository
public class StudentDaoImpl implements StudentDao{
	
	private SessionFactory sessionFactory;
	
	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void save(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(student);
		
	}

	@Override
	public Student findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Student.class, id);
	}

	@Override
	public List<Student> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session
				.createQuery("from Student",Student.class)
				.getResultList();
	}

	@Override
	public void update(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(student);
		
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.find(Student.class, id);
		if(student == null) return;
		session.remove(student);
		
	}

}
