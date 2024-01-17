package com.employees.web.app.model;

import lombok.Data;

@Data
public class Employee {
	
	private Employee() {
		
	}
		
	public Employee(Long productID, String name) {		
		this.productID = productID;
		this.name = name;
	}



	private Long productID;
	private String name;

}
