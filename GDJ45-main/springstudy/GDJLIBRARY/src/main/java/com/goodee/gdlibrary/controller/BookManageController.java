package com.goodee.gdlibrary.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.gdlibrary.service.BookManageService;

@Controller
public class BookManageController {
	
	@Autowired
	private BookManageService bookManageService;

	

	// 책 대여하기
	// 회원은 도서 상세보기 페이지에서 도서를 대여할 수 있음
	// 한번에 한가지 책만 대여 가능
	// rentBook() 메소드 수행 전에 RequiredInterceptor의 preHandle() 메소드가 호출
	// 파라미터로 bookNo를 전달받음.
	@GetMapping("/rent/rentBook")
	public void rentBook(HttpServletRequest request, HttpServletResponse response) {
		bookManageService.rentBookByNo(request, response);
	}
	
	
	// 책 반납 페이지로 이동하기
	// 로그인한 회원만 반납하기 페이지로 이동할 수 있음
	// returnedBook() 메소드 수행 전에 RequiredInterceptor의 preHandle() 메소드가 호출
	@GetMapping("/returned/returnedBookPage")
	public String returnedBook() {
		return "returned/returnedBook";
	}
	
	// 사용자가 대여한 책이 있는지 확인
	@ResponseBody
	@GetMapping(value="/returned/rentBookList", produces="application/json")
	public Map<String, Object> rentBookList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rentBookList", bookManageService.rentBookList(request));
		return result;
	}
	
	// 대여중인 책 반납하기
	@ResponseBody
	@GetMapping("/returned/ReturnedRentBook")
	public Map<String, Object> returnedRentBook(HttpServletRequest request) {
		return bookManageService.returnedRentBook(request);
	}
	
	// 사용자가 연체중인 책이 있는지 확인
	@ResponseBody
	@GetMapping(value="/returned/overdueBookList", produces="application/json")
	public Map<String, Object> overdueBookList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("overdueBookList", bookManageService.overdueBookList(request));
		return result;
	}
	
	// 연체중인 책 반납하기
	@ResponseBody
	@GetMapping("/returned/ReturnedOverdueBook")
	public Map<String, Object> returnedOverdueBook(HttpServletRequest request) {
		return bookManageService.returnedOverdueBook(request);
	}


	
	
}
