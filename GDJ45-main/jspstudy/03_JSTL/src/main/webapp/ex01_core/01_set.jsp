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

	<%--
		변수(속성, attribute) 선언 태그
		
		1. <c:set var="변수명" value="값" scope="영역"></c:set>
		2. 영역은 page, request, session, application 중 선택한다.(디폴트는 page)
		3. 저장된 값은 EL로 사용할 수 있다.
	--%>

	<c:set var="name" value="민경태" scope="page" />
	<c:set var="age" value="45" scope="page" />
	
	<h3>이름 : ${name}</h3>
	<h3>나이 : ${age}</h3>



	<c:set var="tall" value="185.5" scope="page" />
	<c:set var="weight" value="80.5" scope="page" />
	
	<c:set var="bmi" value="${weight div (tall * tall div 10000)}" scope="page" />
	<c:set var="health" value="${bmi ge 25 ? '관리요망' : '정상'}" scope="page" />

	<h3>체질량지수 : ${bmi}</h3>
	<h3>건강상태 : ${health}</h3>

</body>
</html>