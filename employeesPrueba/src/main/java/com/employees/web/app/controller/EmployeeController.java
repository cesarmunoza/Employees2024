package com.employees.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employees.web.app.model.Employee;
import com.employees.web.app.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
//	@GetMapping("")
//	List<Employee> getEmployees(){
//		return employeeService.getEmployees();
//	}
//	
//	@GetMapping("/{id}")
//	public Employee getEmployee(@PathVariable("id") Long id) {
//		return employeeService.getEmployeeById(id);
//	}
		
	
	@GetMapping({"/index","","/", "/home"})
	public String index(Model model) {
		model.addAttribute("titulo", "App for managing employees");
		return "home";
	}
	
	@GetMapping("/employee/{id}")
	public String getEmployee(@PathVariable("id") Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("titulo", "Employee details");
		model.addAttribute("employee", employee);
		return "employee-details";
	}
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Employee> employees = employeeService.getEmployees();
		
		model.addAttribute("titulo", "Employees list");		
		model.addAttribute("employees", employees);
		return "listar";
	}
	


}
