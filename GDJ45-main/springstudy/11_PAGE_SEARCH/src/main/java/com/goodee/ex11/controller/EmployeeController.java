package com.goodee.ex11.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex11.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/employee/searchPage")
	public String searchPage() {
		return "employee/search";
	}
	
	@GetMapping("/employee/list")
	public String list(HttpServletRequest request, Model model) {
		employeeService.getEmployees(request, model);
		return "employee/search";  // search.jsp를 열면 list.jsp가 포함되어 있으므로 search.jsp로 간다.
	}
	
	@GetMapping("/employee/search")
	public String search(HttpServletRequest request, Model model) {
		employeeService.findEmployees(request, model);
		return "employee/search";
	}
	
	@ResponseBody
	@GetMapping(value="/employee/autoComplete", produces="application/json")
	public Map<String, Object> autoComplete(HttpServletRequest request){
		return employeeService.autoComplete(request);
	}
	
}
