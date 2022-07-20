package com.goodee.ex16.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex16.domain.MemberDTO;
import com.goodee.ex16.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/member/management")
	public String management() {
		return "member/manage";
	}
	
	@ResponseBody
	@PostMapping(value="/members", produces="application/json")
	public Map<String, Object> addMember(@RequestBody MemberDTO member, HttpServletResponse response) {
		return memberService.addMember(member, response);
	}
	
	@ResponseBody
	@GetMapping(value="/members/page/{page}", produces="application/json")
	public Map<String, Object> getMembers(@PathVariable(value="page", required=false) Optional<String> opt){
		int page = Integer.parseInt(opt.orElse("1"));
		return memberService.getMembers(page);
	}
	
	@ResponseBody
	@GetMapping(value="/members/{memberNo}", produces="application/json")
	public Map<String, Object> getMember(@PathVariable(value="memberNo", required=false) Optional<String> opt){
		Long memberNo = Long.parseLong(opt.orElse("0"));
		return memberService.getMember(memberNo);
	}
	
	@ResponseBody
	@PutMapping(value="/members", produces="application/json")
	public Map<String, Object> changeMember(@RequestBody MemberDTO member, HttpServletResponse response){
		return memberService.changeMember(member, response);
	}
	
	
	@ResponseBody
	@DeleteMapping(value="/members/{memberNo}", produces="application/json")
	public Map<String, Object> removeMember(@PathVariable Long memberNo){
		return memberService.removeMember(memberNo);
	}
	
	
	
	
	
	
}
