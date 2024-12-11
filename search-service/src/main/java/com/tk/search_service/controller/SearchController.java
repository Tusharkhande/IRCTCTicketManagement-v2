package com.tk.search_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
	@GetMapping("/")
	public String demo() {
		return "Hello";
	}
}
