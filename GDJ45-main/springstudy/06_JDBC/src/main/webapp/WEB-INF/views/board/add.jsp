<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="../resources/css/summernote-0.8.18-dist/summernote-lite.css">
<script src="../resources/js/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="../resources/js/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
<script>
	// 페이지 로드 이벤트
	$(document).ready(function(){
		
		// 폼의 서브밋 이벤트
		$('#f').on('submit', (event)=>{
			if( $('#writer').val() == '' || $('#title').val() == '' ){
				alert('작성자와 제목은 필수입니다.');
				event.preventDefault();
			}
		})
		
		// summernote
		$('#content').summernote({
			width: 1000,
			height: 300,
			lang: 'ko-KR',
			placeholer: '내용',
			// 툴바 수정
			// https://summernote.org/deep-dive/#custom-toolbar-popover
			toolbar: [
			    // [groupName, [list of button]]
			    ['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['picture','link','video']],
			    ['view', ['fullscreen', 'help']]
			],
			fontNames: ['Arial', 'Arial Black', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','36','48','72']
		})
		/*
			// 서머노트에 text 쓰기
			$('#content').summernote('insertText', 써머노트에 쓸 텍스트);
			// 서머노트 쓰기 비활성화
			$('#content').summernote('disable');
			// 서머노트 쓰기 활성화
			$('#content').summernote('enable');
			// 서머노트 리셋
			$('#content').summernote('reset');
			// 마지막으로 한 행동 취소 ( 뒤로가기 )
			$('#content').summernote('undo');
			// 앞으로가기
			$('#content').summernote('redo');
		*/
		
		// 목록
		$('#btnList').on('click', ()=>{
			location.href='${contextPath}/board/list';
		})
		
	})
</script>
</head>
<body>

	<form id="f" action="${contextPath}/board/add" method="post">
		<input type="text" name="writer" id="writer" placeholder="작성자" autofocus><br>
		<input type="text" name="title" id="title" placeholder="제목"><br>
		<textarea name="content" id="content"></textarea><br><br>
		<button>작성완료</button>
		<input type="reset" value="다시작성">
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>