package com.goodee.gdlibrary.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

	
	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		
		
		
		return "index";
	}
}
