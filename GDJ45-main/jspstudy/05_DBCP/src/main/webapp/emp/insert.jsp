<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사원 등록화면</h1>
	<form action="/DBCP/insert.do" method="post">
		<input type="text" name="name" placeholder="사원명">
		<select name="dept">
			<option value="총무">총무</option>
			<option value="개발">개발</option>
			<option value="영업">영업</option>
			<option value="기획">기획</option>
			<option value="QA">QA</option>
		</select><br><br>
		<button>신규등록</button>
		<input type="reset" value="다시작성">
		<input type="button" value="사원목록보기" onclick="location.href='/DBCP/list.do'">
	</form>
</body>
</html>