package com.employees.web.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.employees.web.app.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
//	public EmployeeServiceImpl() {
//		employees.add(new Employee(1L, "Cesar"));
//		employees.add(new Employee(2L, "Diana"));
//		employees.add(new Employee(3L, "Wilson"));
//	}

	List<Employee> employees = new ArrayList<Employee>();
	
	private final String BASE_URL ="http://dummy.restapiexample.com/api/v1/";
	
	@Override
	public List<Employee> getEmployees() {				
		return employees;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) {
			Employee employee = iterator.next();
			if (employee.getId().equals(id)) {
				return employee;
			}
		}
		return null;
	}

}
