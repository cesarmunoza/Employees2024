package com.employees.web.app.service;

import java.util.List;

import com.employees.web.app.model.Employee;

public interface EmployeeService {
	
	Employee getEmployeeById(Long id);
	List<Employee> getAllEmployees();

}
