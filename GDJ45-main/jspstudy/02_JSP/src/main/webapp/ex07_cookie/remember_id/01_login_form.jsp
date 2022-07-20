<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// rememberId 쿠키값을 자바 변수 rememberId에 넣는다.
		// 자바 변수 rememberId에 저장된 값을 제이쿼리로 id="id"인 요소에 넣는다.
		
		String rememberId = "";
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("rememberId")) {
				rememberId = cookies[i].getValue();
				break;
			}
		}
	%>
	
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<script>
	
		$(document).ready(function(){
			
			// 자바 변수 rememberId는 빈 문자열("") 또는 아이디 저장 상태
			var rememberId = '<%=rememberId%>';
			if(rememberId != '') {
				$('#id').val(rememberId);
				$('#checkRememberId').prop('checked', true);  // attr('checked', 'checked')
			}
			
		})
	
	</script>
	

	<form action="02_remember_id.jsp" method="post">
		<div>
			<input type="text" name="id" id="id" placeholder="아이디">
		</div>
		<div>
			<input type="password" name="pw" placeholder="비밀번호">
		</div>
		<div>
			<button>로그인</button>
		</div>
		<div>
			<label>
				<input type="checkbox" name="checkRememberId" id="checkRememberId">
				아이디 기억
			</label>
		</div>
	</form>

</body>
</html>