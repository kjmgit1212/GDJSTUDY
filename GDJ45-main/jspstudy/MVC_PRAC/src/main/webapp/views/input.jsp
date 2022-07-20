<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>사각형 넓이 구하기</h3>
	<form action="/MVC_PRAC/rectangle.do" method="get">
		<div>
			<label for="width">
				너비
				<input type="text" name="width" id="width">
			</label>
		</div>
		<div>
			<label for="height">
				높이
				<input type="text" name="height" id="height">
			</label>
		</div>
		<div>
			<button>사각형 넓이 확인하기</button>  <%-- views/rectangle.jsp에서 결과 보여주기 --%>
		</div>
	</form>
	
	<hr>
	
	<h3>원 넓이 구하기</h3>
	<form action="/MVC_PRAC/circle.do" method="get">
		<div>
			<label for="radius">
				반지름
				<input type="text" name="radius" id="radius">
			</label>
		</div>
		<div>
			<button>원 넓이 확인하기</button>  <%-- views/circle.jsp에서 결과 보여주기 --%>
		</div>
	</form>
	
</body>
</html>