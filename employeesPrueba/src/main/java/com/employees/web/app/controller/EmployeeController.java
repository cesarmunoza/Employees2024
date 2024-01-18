package com.employees.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employees.web.app.model.Employee;
import com.employees.web.app.service.EmployeeSalary;
import com.employees.web.app.service.EmployeeService;

@Controller
public class EmployeeController {
		
	private final EmployeeService employeeService;
	private final EmployeeSalary employeeSalary;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService, EmployeeSalary employeeSalary) {		
		this.employeeService = employeeService;
		this.employeeSalary = employeeSalary;
	}

	@GetMapping({"/index","","/", "/home"})
	public String index(Model model) {
		model.addAttribute("titulo", "App for managing employees");
		return "home";
	}
	
	@GetMapping("/employee-list")
	public String getEmployees(Model model) {
		List<Employee> employees = employeeService.getAllEmployees();		
		model.addAttribute("titulo", "Employees list");		
		model.addAttribute("employees", employees);
		return "employee-list";
	}
	
	@GetMapping("/employee-details")
	public String getEmployeeDetails(@RequestParam(name = "id", required = false) Long id, Model model) {
	    if (id != null) {
	        Employee employee = employeeService.getEmployeeById(id);
	        if (employee != null) {
	            model.addAttribute("titulo", "Employee details");
	            model.addAttribute("employee", employee);
	            model.addAttribute("annualSalary", employeeSalary.calculateAnualSalary(employee.getEmployee_salary()));
	            return "employee-details";
	        }else {
	            model.addAttribute("titulo", "Employee not found");
	        }
	    } else {
	        // Si el campo de texto está vacío, devuelve la lista completa de empleados
	        List<Employee> employees = employeeService.getAllEmployees();
	        model.addAttribute("titulo", "Employee List");
	        model.addAttribute("employees", employees);
	    }

	    return "employee-details";
	}

	
	
	
	


}
