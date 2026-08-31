package com.todo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.todo.model.Todo;
import com.todo.util.HibernateUtil;

public class TodoDao {
	
	public void createToDo(Todo todo) {
		Session session = HibernateUtil
				.getSessionFactory()
				.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.persist(todo);
		
		transaction.commit();
		session.close();
	}
	
	public Todo getTodoById(int id) {
		Session session = HibernateUtil
				.getSessionFactory()
				.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Todo todo = session.find(Todo.class, id);
		
		transaction.commit();
		session.close();
		return todo;
	}
	public List<Todo> getAllTodos(){
		Session session = HibernateUtil
				.getSessionFactory()
				.openSession();
		
		
		Query<Todo> query = session.createQuery(
				"FROM Todo",Todo.class
		);
		
		List<Todo> todos = query.getResultList();
		
		session.close();
		
		return todos;
	}
	
	public void deleteToDo(int id) {
		Session session = HibernateUtil
				.getSessionFactory()
				.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Todo todo = session.find(Todo.class	, id);
		
		if(todo != null) {
			session.remove(todo);
		}
		
		transaction.commit();
		session.close();
	}
	
	public void updateTodo(Todo todo) {
		Session session = HibernateUtil
				.getSessionFactory()
				.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.merge(todo);
		
		transaction.commit();
		session.close();
	}
	
	public void markCompleteToDo(int id) {
		Session session = HibernateUtil
				.getSessionFactory()
				.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Todo todo = session.find(Todo.class, id);
		
		if(todo != null) {
			todo.setCompleted(true);
		}
		
		transaction.commit();
		session.close();
	}
	
}
