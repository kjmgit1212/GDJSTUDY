package com.goodee.gdlibrary.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BookManageService {
	public void rentBookByNo(HttpServletRequest request, HttpServletResponse response);
	public List<Map<String, Object>> rentBookList(HttpServletRequest request);
	public Map<String, Object> returnedRentBook(HttpServletRequest request);
	public List<Map<String, Object>> overdueBookList(HttpServletRequest request);
	public Map<String, Object> returnedOverdueBook(HttpServletRequest request);
}
