package com.student;

import java.util.List;

public interface StudentDao {
	
	void save(Student student);
	
	Student findById(int id);
	
	List<Student> getAll();
	
	void update(Student student);
	
	void delete(int id);
}
