package com.student.controller;

import java.io.IOException;

import com.student.model.Student;
import com.student.service.StudentService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/register.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
	    int age = Integer.parseInt(req.getParameter("age"));
	    String email = req.getParameter("email");
	    String course = req.getParameter("course");

	    System.out.println("Name: " + name);
	    System.out.println("Age: " + age);
	    System.out.println("Email: " + email);
	    System.out.println("Course: " + course);
	    
	    
	    Student student = new Student(name,age,email,course);
	    
	    StudentService.addStudent(student);
	    
	    resp.sendRedirect(req.getContextPath() + "/");
	}
	
}
