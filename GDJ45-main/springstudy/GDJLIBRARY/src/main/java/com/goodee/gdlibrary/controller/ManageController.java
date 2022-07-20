package com.goodee.gdlibrary.controller;

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

import com.goodee.gdlibrary.service.ManageService;

@Controller
public class ManageController {

	@Autowired
	private ManageService manageService;
	
	@GetMapping("/admin/moveManageMain")
	public String moveManageMain() {
		return "manage/manageMain";
	}
	
	@GetMapping("/admin/manageMain")
	public String manageMain() {
		return "manage/manageMain";
	}
	
	
	
	
	// member매핑
	@GetMapping("/admin/listMember")
	public String listMember(HttpServletRequest request, Model model) {
		manageService.findMembers(request, model);
		return "manage/manageMember";
	}
	
	@GetMapping("/admin/saveMemberPage")
	public String savePage(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "manage/saveMember";
	}
	
	@PostMapping("/admin/saveMember")
	public void saveMember(HttpServletRequest request, HttpServletResponse response) {
		manageService.saveMember(request, response);
	}
	
	@ResponseBody
	@GetMapping(value="/admin/checkMemberId", produces="application/json")
	public Map<String, Object> checkMemberId(@RequestParam String id) {
		return manageService.checkMemberId(id);
		// {"res": null}
		// {"res": {"memberNo":1, ...}}
	}
	
	@ResponseBody
	@GetMapping(value="/admin/checkMemberEmail", produces="application/json")
	public Map<String, Object> checkMemberEmail(@RequestParam String email) {
		return manageService.checkMemberEmail(email);
		// {"res": null}
		// {"res": {"memberNo":1, ...}}
	}
	
	
	@GetMapping("/admin/detailMember")
	public String detailMember(HttpServletRequest request, Model model) {
		manageService.findMemberByNo(request, model);
		return "manage/detailMember";
	}
	@GetMapping("/admin/changeMemberPage")
	public String changeMemberPage(HttpServletRequest request, Model model) {
		manageService.findMemberByNo(request, model);
		return "manage/changeMember";
	}
	@PostMapping("/admin/changeMember")
	public void changeMember(HttpServletRequest request, HttpServletResponse response, Model model) {
		manageService.changeMember(request, response, model);
	}
	@PostMapping("/admin/removeCheckMember")
	public void removeCheckMember(HttpServletRequest request, HttpServletResponse response) {
		manageService.removeCheckMember(request, response);
	}
	
	@GetMapping("/admin/removeMember")
	public void removeMember(HttpServletRequest request, HttpServletResponse response) {
		manageService.removeMember(request, response);
	}
	
	
	@GetMapping("/admin/searchMember")
	public String searchMember(HttpServletRequest request, Model model) {
		manageService.findSearchMembers(request, model);
		return "manage/manageMember";
	}
	@ResponseBody
	@GetMapping(value="/admin/autoCompleteMember", produces="application/json")
	public Map<String, Object> autoCompleteMember(HttpServletRequest request){
		return manageService.autoCompleteMember(request);
	}
	
	
	
	// dormantMember 매핑
	@GetMapping("/admin/listDormantMember")
	public String listDormantMember(HttpServletRequest request, Model model) {
		manageService.findDormantMembers(request, model);
		return "manage/manageDormantMember";
	}
	
	@GetMapping("/admin/saveDormantMemberPage")
	public String saveDormantMemberPage(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		model.addAttribute("memberNo", request.getParameter("memberNo"));
		return "manage/saveDormantMember";
	}
	
	@PostMapping("/admin/saveDormantMember")
	public void saveDormantMember(HttpServletRequest request, HttpServletResponse response, Model model) {
		manageService.findMemberByNo(request, model);
		manageService.saveDormantMember(request, response, model);
		manageService.removeTransMember(request, response);
		
	}
	
	@PostMapping("/admin/saveDormantToMember")
	public void saveDormantToMember(HttpServletRequest request, HttpServletResponse response, Model model) {
		manageService.findDormantMemberByNo(request, model);
		manageService.saveDormantToMember(request, response, model);
		manageService.removeTransDormantMember(request, response);
	}
	
	@ResponseBody
	@GetMapping(value="/admin/checkDormantMemberId", produces="application/json")
	public Map<String, Object> checkDormantMemberId(@RequestParam String id) {
		return manageService.checkDormantMemberId(id);
		// {"res": null}
		// {"res": {"memberNo":1, ...}}
	}
	
	@ResponseBody
	@GetMapping(value="/admin/checkDormantMemberEmail", produces="application/json")
	public Map<String, Object> checkDormantMemberEmail(@RequestParam String email) {
		return manageService.checkDormantMemberEmail(email);
		// {"res": null}
		// {"res": {"memberNo":1, ...}}
	}
	
	
	@GetMapping("/admin/detailDormantMember")
	public String detailDormantMember(HttpServletRequest request, Model model) {
		manageService.findDormantMemberByNo(request, model);
		return "manage/detailDormantMember";
	}
	
	@GetMapping("/admin/removeDormantMember")
	public void removeDormantMember(HttpServletRequest request, HttpServletResponse response) {
		manageService.removeDormantMember(request, response);
	}
	@PostMapping("/admin/removeCheckDormantMember")
	public void removeCheckDormantMember(HttpServletRequest request, HttpServletResponse response) {
		manageService.removeCheckDormantMember(request, response);
	}
	@GetMapping("/admin/searchDormantMember")
	public String searchDormantMember(HttpServletRequest request, Model model) {
		manageService.findSearchDormantMembers(request, model);
		return "manage/manageDormantMember";
	}
	@ResponseBody
	@GetMapping(value="/admin/autoCompleteDormantMember", produces="application/json")
	public Map<String, Object> autoCompleteDormantMember(HttpServletRequest request){
		return manageService.autoCompleteDormantMember(request);
	}
	
	
	
	
	// SignOutMember매핑
	@GetMapping("/admin/listSignOutMember")
	public String listSignOutMember(HttpServletRequest request, Model model) {
		manageService.findSignOutMembers(request, model);
		return "manage/manageSignOutMember";
	}
	
	
	@GetMapping("/admin/detailSignOutMember")
	public String detailSignOutMember(HttpServletRequest request, Model model) {
		manageService.findSignOutMemberByNo(request, model);
		return "manage/detailSignOutMember";
	}
	
	
	@GetMapping("/admin/removeSignOutMember")
	public void removeSignOutMember(HttpServletRequest request, HttpServletResponse response) {
		manageService.removeSignOutMember(request, response);
	}
	@PostMapping("/admin/removeCheckSignOutMember")
	public void removeCheckSignOutMember(HttpServletRequest request, HttpServletResponse response) {
		manageService.removeCheckSignOutMember(request, response);
	}
	@GetMapping("/admin/searchSignOutMember")
	public String searchSignOutMember(HttpServletRequest request, Model model) {
		manageService.findSearchSignOutMembers(request, model);
		return "manage/manageSignOutMember";
	}
	@ResponseBody
	@GetMapping(value="/admin/autoCompleteSignOutMember", produces="application/json")
	public Map<String, Object> autoCompleteSignOutMember(HttpServletRequest request){
		return manageService.autoCompleteSignOutMember(request);
	}
	
	
	
	// book 매핑
	@GetMapping("/admin/listBook")
	public String listBook(HttpServletRequest request, Model model) {
		manageService.findBooks(request, model);
		return "manage/manageBook";
	}
	
	@GetMapping("/admin/saveBookPage")
	public String saveBookPage(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "manage/saveBook";
	}
	
	@GetMapping("/admin/saveNaverBookPage")
	public String saveNaverBookPage(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "book/insert";
	}
	
	@PostMapping("/admin/saveBook")
	public void saveBook(HttpServletRequest request, HttpServletResponse response) {
		manageService.saveBook(request, response);
	}
	

	
	@GetMapping("/admin/detailBook")
	public String detailBook(HttpServletRequest request, Model model) {
		manageService.findBookByNo(request, model);
		return "manage/detailBook";
	}
	@GetMapping("/admin/changeBookPage")
	public String changeBookPage(HttpServletRequest request, Model model) {
		manageService.findBookByNo(request, model);
		return "manage/changeBook";
	}
	@PostMapping("/admin/changeBook")
	public void changeBook(HttpServletRequest request, HttpServletResponse response, Model model) {
		manageService.changeBook(request, response, model);
	}
	@GetMapping("/admin/removeBook")
	public void removeBook(HttpServletRequest request, HttpServletResponse response) {
		manageService.removeBook(request, response);
	}
	@PostMapping("/admin/removeCheckBook")
	public void removeCheckBook(HttpServletRequest request, HttpServletResponse response) {
		manageService.removeCheckBook(request, response);
	}
	@GetMapping("/admin/searchBook")
	public String searchBook(HttpServletRequest request, Model model) {
		manageService.findSearchBooks(request, model);
		return "manage/manageBook";
	}
	@ResponseBody
	@GetMapping(value="/admin/autoCompleteBook", produces="application/json")
	public Map<String, Object> autoCompleteBook(HttpServletRequest request){
		return manageService.autoCompleteBook(request);
	}
	
	@GetMapping("/fnq/fnqWrite")
	public String fnqWrite(HttpServletRequest request, Model model) {
		return "manage/saveFaq";
	}
	
	@PostMapping("/admin/saveFaq")
	public void saveFaq(HttpServletRequest request, HttpServletResponse response) {
		manageService.insertFnq(request, response);
	}

	
}
