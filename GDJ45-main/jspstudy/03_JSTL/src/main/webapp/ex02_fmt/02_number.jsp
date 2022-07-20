<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 자릿수 조정은 기본적으로 반올림 처리 --%>


	<c:set var="n" value="12345.6789" />
	

	<%-- 천 단위 구분 기호 사용 --%>
	<h3><fmt:formatNumber value="${n}" pattern="#,##0" /></h3>
	<h3><fmt:formatNumber value="${n}" pattern="#,##0.0" /></h3>
	<h3><fmt:formatNumber value="${n}" pattern="#,##0.00" /></h3>


	<%-- 천 단위 구분 기호 없이 사용 --%>
	<h3><fmt:formatNumber value="${n}" pattern="0" /></h3>
	<h3><fmt:formatNumber value="${n}" pattern="0.0" /></h3>
	<h3><fmt:formatNumber value="${n}" pattern="0.00" /></h3>


	<%-- 백분율(%) 사용 : 값 * 100 (1 == 100%) --%>
	<h3><fmt:formatNumber value="${n}" type="percent" /></h3>
	

	<%-- 통화(￦, $) 사용 --%>
	<h3><fmt:formatNumber value="${n}" type="currency" currencySymbol="￦" /></h3>
	<h3><fmt:formatNumber value="${n}" type="currency" currencySymbol="$" /></h3>


</body>
</html>