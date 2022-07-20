<%@page import="ex06_session.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- session에 저장된 cart 정보 가져와서 화면에 출력하기 --%>
	<%
		ArrayList<Product> cart = (ArrayList<Product>)session.getAttribute("cart");
		StringBuilder sb = new StringBuilder();
		if(cart == null) {
			sb.append("장바구니가 비었습니다.");
		} else {
			for(Product product : cart) {
				sb.append(product.getName() + "-----" + product.getQuantity() + "<br>");
			}
		}
	%>
	
	<% if(cart != null) { %>
		<h1>장바구니(<%=cart.size()%>건)</h1>
	<% } %>
	<div><%=sb.toString()%></div>
	
	<hr>
	
	<input type="button" value="계속쇼핑하기" onclick="location.href='/JSP/ex06_session/021_shopping.jsp'">
	<input type="button" value="장바구니비우기" onclick="fnEmptyCart()">
	<script>
		function fnEmptyCart(){
			if(confirm('장바구니를 비울까요?')) {
				location.href = '/JSP/ex06_session/024_remove_cart.jsp';
			} else {
				alert('취소되었습니다.');
			}
		}
	</script>

</body>
</html>