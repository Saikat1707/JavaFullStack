package com.student.dao;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.student.model.Student;
import com.student.util.DBConnection;

public class StudentDao {
	
	public static void addStudent(Student student) {
		
		String sql = "INSERT INTO students(name,age,email,course)"
				+ "values(?,?,?,?)";
		
		try(
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		){
			
			statement.setString(1, student.getName());
			statement.setInt(2, student.getAge());
			statement.setString(3, student.getEmail());
			statement.setString(4, student.getCourse());
			
			statement.executeUpdate();
			
			System.out.println("Student added successfully!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static List<Student> getAllStudents(){
		List<Student> list = new ArrayList<>();
		String sql = "SELECT name, age, email, course FROM students";
		
		try(
				Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()
		){
			while(resultSet.next()) {
				
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String email = resultSet.getString("email");
				String course = resultSet.getString("course");
				
				Student student = new Student(name,age,email,course);
				
				list.add(student);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	public static void deleteStudent(String email) {
		
		String sql = "DELETE FROM students WHERE email = ?";
		
		try(
				Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
		){
				statement.setString(1, email);
				
				statement.executeUpdate();
				
				System.out.println("Student deleted successfully!");
				
		}catch(SQLException e) {
				e.printStackTrace();
		}
	}
}
