package com.student.controller;

import java.io.IOException;
import java.util.List;

import com.student.model.Student;
import com.student.service.StudentService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/students")
public class StudentsServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> list = StudentService.getAllStudent();
		
		req.setAttribute("studentList", list);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/students.jsp");
		
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		StudentService.deleteStudent(email);
		resp.sendRedirect(req.getContextPath() + "/students");
	}
	
}
