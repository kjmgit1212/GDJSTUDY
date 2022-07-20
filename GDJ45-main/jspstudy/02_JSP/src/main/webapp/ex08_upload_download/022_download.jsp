<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	// 다운로드 대상(서버에 저장되어 있음)
	String filename = request.getParameter("filename");    // 파일명
	String realPath = application.getRealPath("storage");  // 경로
	File file = new File(realPath, filename);
	
	// 다운로드 대상에 연결하는 입력 스트림
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	
	// 응답 헤더
	response.setHeader("Content-Disposition", "attachment; filename=" + filename);
	response.setHeader("Content-Length", file.length() + "");
	response.setHeader("Content-Type", "application/x-msdownload");
	
	// 응답으로 내 보낼 출력 스트림
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	// JSP에 내장된 출력 스트림 처리하기
	out.clear();
	out = pageContext.pushBody();
	
	// 다운로드 진행(입력 스트림으로 읽은 내용을 출력 스트림으로 보냄 : 파일 복사)
	int size = (int)file.length();
	byte[] b = new byte[size];
	bis.read(b, 0, size);
	bos.write(b, 0, size);
	
	if(bos != null) bos.close();
	if(bis != null) bis.close();
	
%>