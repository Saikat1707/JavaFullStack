package com.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/student_portal";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "saikat123";
	
	
	public static Connection getConnection() throws SQLException {

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("MYSQL DRIVER LOADED");
	    } catch (ClassNotFoundException e) {
	        System.out.println("MYSQL DRIVER NOT FOUND");
	        e.printStackTrace();
	    }

	    return DriverManager.getConnection(
	            URL,
	            USERNAME,
	            PASSWORD
	    );
	}
}
