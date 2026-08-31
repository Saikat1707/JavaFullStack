package com.employee.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.employee.model.Employee;

public class EmployeeDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public EmployeeDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void createEmployee(Employee employee) {
		String sql = "INSERT INTO employees "
				+ "(name,email,salary) VALUES (?,?,?)";
		
		
		jdbcTemplate.update(
				sql,
				employee.getName(),
				employee.getEmail(),
				employee.getSalary());
		
	}
	
	public List<Employee> getAllEmployees(){
		String sql = "SELECT * FROM employees";
		
		return jdbcTemplate.query(sql, (rs,rowNum)->{
			Employee employee = new Employee();
			
			employee.setId(rs.getInt("id"));
			employee.setEmail(rs.getString("email"));
			employee.setName(rs.getString("name"));
			employee.setSalary(rs.getDouble("salary"));
			
			return employee;
		});
	}
	 
	public Employee getEmployeeById(int id) {
		String sql = "SELECT * FROM employees WHERE id = ?";
		
		return jdbcTemplate.queryForObject(sql,
				(rs,rowNum)->{
					Employee employee = new Employee();
					
					employee.setId(rs.getInt("id"));
					employee.setEmail(rs.getString("email"));
					employee.setName(rs.getString("name"));
					employee.setSalary(rs.getDouble("salary"));
					
					return employee;
				},id);
	}
	
	public void updateEmployee(Employee employee) {
		String sql = "UPDATE employees "
				+ "SET name = ?,email = ?,salary = ? "
				+ "WHERE id = ?";
		jdbcTemplate.update(sql,
				employee.getName(),employee.getEmail(),employee.getSalary(),employee.getId());
	}
	
	public void deleteEmployee(int id) {
		String sql = "DELETE FROM employees where id = ?";
		jdbcTemplate.update(sql,id);
	}
}
