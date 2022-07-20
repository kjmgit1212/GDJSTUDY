<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>사원정보수정화면</h1>
	<form action="/MYBATIS/update.do" method="post">
		<div>사원번호 ${param.no}</div>
		<div>사원명 <input type="text" name="name" value="${param.name}"></div>
		<div>부서명 <input type="text" name="dept" value="${param.dept}"></div>
		<div>급여 <input type="text" name="salary" value="${param.salary}"></div>
		<div>입사일자 ${param.hired}</div>
		<input type="hidden" name="no" value="${param.no}">
		<button>수정</button>
		<input type="button" value="사원목록" onclick="location.href='/MYBATIS/list.do'">
	</form>

</body>
</html>