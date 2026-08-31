package com.student.service;

import java.util.List;

import com.student.dao.StudentDao;
import com.student.model.Student;

public class StudentService {
	
	public static void addStudent(Student student) {
		StudentDao.addStudent(student);
	}
	
	public static List<Student> getAllStudent(){
		return StudentDao.getAllStudents();
	}
	
	public static void deleteStudent(String email) {
		StudentDao.deleteStudent(email);
	}
}
