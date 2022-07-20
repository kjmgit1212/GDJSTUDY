<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/JSP/ex06_session/022_add_cart.jsp">
		<h1>쇼핑 목록</h1>
		<select name="name">
			<option value="TV">TV</option>
			<option value="세탁기">세탁기</option>
			<option value="건조기">건조기</option>
			<option value="냉장고">냉장고</option>
			<option value="인덕션">인덕션</option>
		</select>
		<input type="text" name="quantity" size="4">개
		<button>장바구니에 담기</button>
	</form>
	
</body>
</html>