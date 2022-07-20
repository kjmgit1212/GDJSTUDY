package com.goodee.ex17.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.goodee.ex17.domain.MemberDTO;
import com.goodee.ex17.service.FreeBoardService;

@Controller
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/member/login")
	public String login(HttpSession session, MemberDTO member) {
		session.setAttribute("member", member);
		return "index";
	}
	
	@GetMapping("/freeBoard/list")
	public String list(HttpServletRequest request, Model model) {
		freeBoardService.findFreeBoards(request, model);
		return "free/list";  // free 폴더 아래 list.jsp로 이동한다.
	}
	
	@PostMapping("/freeBoard/saveFreeBoard")
	public void saveFreeBoard(HttpServletRequest request, HttpServletResponse response) {
		int res = freeBoardService.saveFreeBoard(request);
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('게시글이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/freeBoard/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글이 등록되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/freeBoard/saveReply")
	public void saveReply(HttpServletRequest request, HttpServletResponse response) {
		int res = freeBoardService.saveReply(request);
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('댓글이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/freeBoard/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('댓글이 등록되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/freeBoard/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		Long freeBoardNo = Long.parseLong(request.getParameter("freeBoardNo"));
		int res = freeBoardService.removeFreeBoard(freeBoardNo);
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('게시글이 삭제되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/freeBoard/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글이 삭제되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
