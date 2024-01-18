package com.employees.web.app.service;

import org.springframework.stereotype.Service;

@Service
public class EmployeeSalaryImpl implements EmployeeSalary {

	@Override
	public double calculateAnualSalary(double monthlySalary) {		
		return monthlySalary * 12;
	}

}
