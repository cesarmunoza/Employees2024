package com.employees.web.app.repository;

import java.util.List;

import com.employees.web.app.model.Employee;

public interface EmployeeRepository {
	Employee getEmployeeById(Long id);
	List<Employee> getAllEmployees();

}
