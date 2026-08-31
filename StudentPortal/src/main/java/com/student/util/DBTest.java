package com.student.util;

import java.sql.Connection;

public class DBTest {
	public static void main(String[] args) {
		try {
			
			Connection connection = DBConnection.getConnection();
			
			System.out.println("Database connected Successfully");
			
			connection.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
