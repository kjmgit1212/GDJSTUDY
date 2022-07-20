package com.goodee.ex03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.ex03.domain.Member;

@Controller
public class MemberController {

	
	@Autowired
	@Qualifier(value="member1")
	private Member member1;
	
	
	@Autowired
	@Qualifier(value="member2")
	private Member member2;
	
	
	@GetMapping("member/detail")
	public String detail(HttpServletRequest request) {
		
		request.setAttribute("member1", member1);
		request.setAttribute("member2", member2);
		return "member/detail";
		
	}
	
	
}
