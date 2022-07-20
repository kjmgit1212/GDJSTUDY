<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="/JUNIT/remove.prod?productNo=${product.productNo}">삭제</a>
	
	<input type="button" value="삭제" onclick="location.href='/JUNIT/remove.prod?productNo=${product.productNo}'">
	
	<form action="/JUNIT/remove.prod">
		<input type="hidden" name="productNo" value="${product.productNo}">
		<button>삭제</button>
	</form>
	
	<hr>

	<div>${product.productNo}</div>
	<div>${product.name}</div>
	<div>${product.price}</div>
	<div><img src="${contextPath}/storage/${product.image}" alt="${product.image}"></div>
	
</body>
</html>