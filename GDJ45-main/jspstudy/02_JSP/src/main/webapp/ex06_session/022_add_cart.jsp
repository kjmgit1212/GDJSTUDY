<%@page import="java.util.ArrayList"%>
<%@page import="ex06_session.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	String qtt = request.getParameter("quantity");
	if(qtt.isEmpty()) {
		qtt = "1";
	}
	int quantity = Integer.parseInt(qtt);
	
	// 장바구니에 담을 product
	Product product = new Product(name, quantity);
	
	// 1. session에 저장된 cart를 확인한다.
	// 2. session에 저장된 cart가 없으면 새로 만들어서 session에 넣는다.
	ArrayList<Product> cart = (ArrayList<Product>)session.getAttribute("cart");
	if(cart == null) {
		cart = new ArrayList<>();
		session.setAttribute("cart", cart);
	}
	
	// 장바구니에 product 담기
	cart.add(product);
		
%>

<script>
	alert('<%=product.getName()%> 제품을 장바구니에 추가했습니다.');
	var choice = confirm('장바구니를 확인하려면 "확인", 계속 쇼핑하려면 "취소"를 누르세요.');
	if(choice == true) {
		location.href = '/JSP/ex06_session/023_cart.jsp';
	} else {
		location.href = '/JSP/ex06_session/021_shopping.jsp';
	}
</script>
