package com.goodee.ex12.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.goodee.ex12.util.SecurityUtils;

@Controller
public class UserController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/user/login")
	public String login(HttpSession session, HttpServletRequest request) {
		// 마치 로그인처럼 아이디, 비번, 이름을 Map으로 만들어서 session에 보관
		Map<String, String> user = new HashMap<>();
		user.put("id", SecurityUtils.XSS(request.getParameter("id")));
		user.put("pw", request.getParameter("pw"));
		user.put("name", "아무개");
		session.setAttribute("user", user);
		return "index";
	}
	
	@PostMapping("/x/y/z")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 마치 로그인처럼 아이디, 비번, 이름을 Map으로 만들어서 session에 보관
		Map<String, String> user = new HashMap<>();
		user.put("id", request.getParameter("id"));
		user.put("pw", request.getParameter("pw"));
		user.put("name", "아무개");
		session.setAttribute("user", user);
		return "board/list";
	}
	
}
