package com.student.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Student Portal application started");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Student Portal application stopped");
	}
	
}
