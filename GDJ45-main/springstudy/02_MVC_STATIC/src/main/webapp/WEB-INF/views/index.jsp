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

	<!--
		var="contextPath"                           contextPath 속성(attribute)으로 저장합니다.
		value="${pageContext.request.contextPath}"  contextPath 속성에 저장되는 값입니다.
		scope="application"                         application에 저장된 속성은 애플리케이션(프로젝트)에서 공유합니다.
		                                            즉, 모든 JSP가 contextPath 속성을 사용할 수 있습니다.
	-->
	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application" />

	<h1>${res}</h1>
	
	<a href="/ex02/imageView">이미지보러가기</a>
	
	<br>
	
	<!-- hosting에 올릴 때 "/ex02"가 폴더로 인식되는 경우가 있어요. -->
	<!-- contextPath를 변수로 처리하면 해결됩니다. -->
	
	<a href="${contextPath}/imageView">이미지보러가기</a>
	
	<br>
	
	<a href="${contextPath}/lionView">사자보러가기</a>
	
</body>
</html>