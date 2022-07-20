<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		1. 가능
			Java 변수 -> JavaScript	
	--%>
	
	<% 
		String name = "민경태";
		int age = 45;
	%>
	<script>	
		var name = '<%=name%>';
		var age = <%=age%>;
		alert(name + ', ' + age);
	</script>
	

	<%--
		2. 불가능
			JavaScript 변수 -> Java
	--%>
	<%--
	<script>
		var money = 1000;
	</script>
	<h3>주머니에 <%=money%>원이 있습니다.</h3>
	--%>

</body>
</html>