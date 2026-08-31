package com.todo.util;

public class HibernateTest {
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		System.out.println("Hibernate Connected Successfully");
	}
}
