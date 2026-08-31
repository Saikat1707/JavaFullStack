package com.employee.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.employee.dao.EmployeeDao;
import com.employee.service.EmployeeService;

@Configuration
public class AppConfig {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource datasource = 
				new DriverManagerDataSource();
		
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/employee_db");
		datasource.setUsername("root");
		datasource.setPassword("saikat123");
		
		return datasource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public EmployeeDao employeeDao(JdbcTemplate jdbcTemplate) {
		return new EmployeeDao(jdbcTemplate);
	}
	
	@Bean
	public EmployeeService employeeService(EmployeeDao employeeDao) {
		return new EmployeeService(employeeDao);
	}
	
}
