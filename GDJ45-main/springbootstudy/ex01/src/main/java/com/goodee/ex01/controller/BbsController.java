package com.goodee.ex01.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex01.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	private BbsService bbsService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/bbs/list")
	public String list(Model model) {
		model.addAttribute("bbses", bbsService.getBbses());
		return "bbs/list";
	}
	
	@GetMapping("/bbs/detail")
	public String detail(@RequestParam(value="bbsNo", required=false, defaultValue="0") Long bbsNo, Model model) {
		model.addAttribute("bbs", bbsService.getBbsByNo(bbsNo));
		return "bbs/detail";
	}
	
	@GetMapping("/bbs/addPage")
	public String addPage() {
		return "bbs/add";
	}
	
	@PostMapping("/bbs/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		bbsService.addBbs(request, response);
	}
	
	@ResponseBody
	@PostMapping(value="/bbs/uploadSummernoteImage", produces="application/json")
	public Map<String, Object> uploadSummernoteImage(MultipartHttpServletRequest multipartRequest){
		return bbsService.uploadSummernoteImage(multipartRequest);
	}
	
	@GetMapping("/bbs/modifyPage")
	public String modifyPage(@RequestParam Long bbsNo, Model model) {
		model.addAttribute("bbs", bbsService.getBbsByNo(bbsNo));
		return "bbs/modify";
	}
	
	@PostMapping("/bbs/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		bbsService.modifyBbs(request, response);
	}
	
	@GetMapping("/bbs/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		bbsService.removeBbs(request, response);
	}
	
}
