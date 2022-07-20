<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>신규사원등록화면</h1>
	<form action="/MYBATIS/insert.do" method="post">
		<div>사원명 <input type="text" name="name"></div>
		<div>부서명 <input type="text" name="dept"></div>
		<div>급여   <input type="text" name="salary"></div>
		<div>
			<button>등록</button>
			<input type="reset" value="다시작성">
			<input type="button" value="사원목록" onclick="location.href='/MYBATIS/list.do'">
		</div>
	</form>

</body>
</html>