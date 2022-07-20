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
	
		// 파일이 첨부된 경우에는
		// 첨부된 파일 이름이
		// <input type="file" name="filename"> 요소의 value 속성으로 등록된다.
		
		$('#filename').change(function(){
			
			// 1. 파일 확장자 제한하기
			//    jpg, jpeg, png, gif, tif, tiff
			var filename = $(this).val();
			var extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
			var accepts = ['jpg', 'jpeg', 'png', 'gif', 'tif', 'tiff'];
			if($.inArray(extension, accepts) == -1) {  // extension이 배열 accepts 내부에 없으면 -1을 반환
				alert('확장자가 jpg, jpeg, png, gif, tif, tiff인 파일만 첨부할 수 있습니다.');
				$(this).val('');  // 첨부된 파일명을 없애면 첨부가 없어짐
				return;
			}
			
			// 2. 파일 크기 제한하기
			var size = $(this)[0].files[0].size;  // 바이트 단위
			var maxSize = 1024 * 1024 * 10;       // 바이트 단위(10MB)
			if(size > maxSize) {
				alert('첨부파일의 최대 크기는 10MB입니다.');
				$(this).val('');
				return;
			}
			
		})
		
	})
	
</script>
</head>
<body>

	<h1>파일 첨부하기</h1>
	<form action="012_upload.jsp" method="post" enctype="multipart/form-data">
		<div>
			<input type="text" name="uploader" placeholder="작성자">
		</div>
		<div>
			<input type="file" name="filename" id="filename">
		</div>
		<div>
			<button>첨부</button>
		</div>
	</form>

</body>
</html>