<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 외부 css 파일 포함하기 -->
<link rel="stylesheet" href="resources/css/detail.css">

<!-- 외부 js 파일 포함하기 -->
<script src="resources/js/jquery-3.6.0.js"></script>

<script>

	$(document).ready(function(){
	
		$('.picture').on('click', function(){
			alert('앗 따거');
		})
		
	})
	
</script>

</head>
<body>

	<h1>gallery에 오신 걸 환영합니다</h1>
	
	<h3>독수리</h3>
	<div class="picture"></div>

</body>
</html>