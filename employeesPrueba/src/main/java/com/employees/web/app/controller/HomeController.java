package com.employees.web.app.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
	@GetMapping("/")
	public String home() {
		return "home";
	}

}
