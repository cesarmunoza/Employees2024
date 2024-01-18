package com.employees.web.app.model;

import lombok.Data;

@Data
public class Employee {
	
	private Long id;
	private String employee_name;
	private double employee_salary;
	private int employee_age;
	private String profile_image;
	
	public Employee() {		
	}

	public Employee(Long id, String employee_name, double employee_salary, int employee_age, String profile_image) {		
		this.id = id;
		this.employee_name = employee_name;
		this.employee_salary = employee_salary;
		this.employee_age = employee_age;
		this.profile_image = profile_image;
	}			
}
