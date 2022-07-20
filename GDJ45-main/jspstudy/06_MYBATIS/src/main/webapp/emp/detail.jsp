<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>사원상세정보</h1>
	<div>사원번호 ${emp.no}</div>
	<div>사원명 ${emp.name}</div>
	<div>부서명 ${emp.dept}</div>
	<div>급여 ${emp.salary}</div>
	<div>입사일자 ${emp.hired}</div>
	
	<div>
		<form action="/MYBATIS/updatePage.do" method="post">
			<input type="hidden" name="no" value="${emp.no}">
			<input type="hidden" name="name" value="${emp.name}">
			<input type="hidden" name="dept" value="${emp.dept}">
			<input type="hidden" name="salary" value="${emp.salary}">
			<input type="hidden" name="hired" value="${emp.hired}">
			<button>수정</button>
		</form>
	</div>
	
	<div>
		<input type="button" value="삭제" onclick="fnDelete()">
		<script>
			function fnDelete() {
				if(confirm('삭제할까요?')) {
					location.href = '/MYBATIS/delete.do?no=${emp.no}';
				}
			}
		</script>
	</div>
	
</body>
</html>