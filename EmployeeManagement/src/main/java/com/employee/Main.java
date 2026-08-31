package com.employee;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.employee.config.AppConfig;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		EmployeeService service = context.getBean(EmployeeService.class);
		
//		Employee employee = new Employee();
//
//	    employee.setName("Sweety");
//	    employee.setEmail("sweety@gmail.com");
//	    employee.setSalary(10000);
//	    
//	    service.createEmployee(employee);
//	    
//	    System.out.println("Employee Created : "+employee);
		
//		
//		List<Employee> list = service.getAllEmployees();
//		
//		list.forEach((employee)-> System.out.println(employee));
		
		
//		Employee employee = service.getEmployeeById(3);
//		System.out.println(employee);
//		
//		employee.setEmail("hari@gmail.com");
//		
//		service.updateEmployee(employee);
//		
//		Employee employee2 = service.getEmployeeById(3);
//		System.out.println(employee2);
		
//		service.deleteEmployee(4);
//		
//		List<Employee> list = service.getAllEmployees();
//		
//		list.forEach((employee)-> System.out.println(employee));
		
	}
}
