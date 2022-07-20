<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	
	$(document).ready(function(){
		fnFileCheck();
	})
	
	function fnFileCheck(){
		// 파일을 첨부하면 change 이벤트 동작
		$('#filename').change(function(){
			// 확장자 제한
			var origin = $(this).val().toLowerCase();  // 첨부된 파일의 소문자 이름
			var regExp = /(.*?)\.(jpg|gif|png|tif)$/;
			if(regExp.test(origin) == false) {
				alert('확장자가 jpg, gif, png, tif인 이미지만 업로드가 가능합니다.');
				$(this).val('');
				return;
			}
			// 크기 제한
			var maxSize = 1024 * 1024 * 10;  // 크기 제한 10MB
			var fileSize = $(this)[0].files[0].size;  // 첨부된 파일 크기
			if(maxSize < fileSize) {
				alert('10MB 이하의 파일만 업로드가 가능합니다.');
				$(this).val('');
				return;
			}
		})
	}
	
</script>
</head>
<body>
	<form action="/JUNIT/add.prod" method="post" enctype="multipart/form-data">
		<div>
			<input type="text" name="name" placeholder="제품명">
		</div>
		<div>
			<input type="text" name="price" placeholder="제품가격">
		</div>
		<div>
			<input type="file" name="filename" id="filename">
		</div>
		<div>
			<button>등록</button>
			<input type="reset" value="다시작성">
			<input type="button" value="목록" onclick="location.href='/JUNIT/list.prod'">
		</div>
	</form>
</body>
</html>