package com.goodee.gdlibrary.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.gdlibrary.service.FnqService;

@Controller
public class FnqController {
	
	@Autowired
	private FnqService fnqService;

	@GetMapping("/fnq/fnqPage")
	public String fnqPage() {
		return "fnq/fnq";
	}
	
	
	// 회원목록 + page 전역변수
	@ResponseBody
	@GetMapping(value="/fnq/page/{page}", produces="application/json")
	public Map<String, Object> getMembers(@PathVariable(value="page", required=false) Optional<String> opt){
		int page = Integer.parseInt(opt.orElse("1"));
		return fnqService.getFnqList(page);
	}
	

	// 제목 또는 내용 검색
	@ResponseBody
	@GetMapping(value="/fnq/search", produces="application/json")
	public Map<String, Object> search(HttpServletRequest request) {
		return fnqService.search(request);
	}
	
	
}
