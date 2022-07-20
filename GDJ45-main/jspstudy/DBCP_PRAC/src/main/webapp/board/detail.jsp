<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>게시글상세보기</h1>
	<div>글번호 ${board.no}</div>
	<div>작성자 ${board.writer}</div>
	<div>작성IP ${board.ip}</div>
	<div>조회수 ${board.hit}</div>
	<div>작성일 ${board.created}</div>
	<div>수정일 ${board.lastModified}</div>
	<div>제목 ${board.title}</div>
	<div><pre>${board.content}</pre></div>
	
	<div>
		<input type="button" value="수정" onclick="location.href='/DBCP_PRAC/updatePage.do'">
		<input type="button" value="목록" onclick="location.href='/DBCP_PRAC/list.do'">
	</div>
	
</body>
</html>