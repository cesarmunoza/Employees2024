package com.employees.web.app.model;

import lombok.Data;

@Data
public class Employee {
	
	private Long id;
	private String name;
	
	private Employee() {
		super();
	}
		
	public Employee(Long id, String name) {		
		this.id = id;
		this.name = name;
	}



}
