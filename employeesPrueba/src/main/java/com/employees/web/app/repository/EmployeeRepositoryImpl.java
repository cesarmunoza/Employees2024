package com.employees.web.app.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.employees.web.app.model.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	private final RestTemplate restTemplate;
	private final String baseUrl;
		
	public EmployeeRepositoryImpl(RestTemplate restTemplate, @Value("${employee.api.url}") String baseUrl) {		
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
	}

	@Override
	public Employee getEmployeeById(Long id) {		
		return restTemplate.getForObject(baseUrl + "employee/" + id, Employee.class);
	}

	@Override
	public List<Employee> getAllEmployees() {
		Employee[] employees = restTemplate.getForObject(baseUrl + "employees", Employee[].class);
		return Arrays.asList(employees);
	}

}
