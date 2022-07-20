<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	label {
		display: block;
	}
	label span {
		display: inline-block;
		width: 60px;
	}
</style>
</head>
<body>

	<%
		Date today = new Date();
		String strToday = new SimpleDateFormat("yyyy-MM-dd").format(today);
		
		// EL 사용을 위해서
		pageContext.setAttribute("today", strToday);
	%>

	<h1>문의 남기기</h1>
	<form action="/JSP/ex05_application/qna_save.jsp" method="post">
		<label>
			<span>작성일</span>
			<input type="text" name="created" value="${today}">
		</label>
		<label>
			<span>작성자</span>
			<input type="text" name="writer">
		</label>
		<label>
			<span>제목</span>
			<input type="text" name="title">
		</label>
		<textarea rows="5" cols="32" name="content" placeholder="내용"></textarea>
		<br><br>
		<button>문의 남기기</button>
		<input type="reset" value="다시 작성하기">
	</form>

</body>
</html>