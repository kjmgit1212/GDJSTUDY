<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
	$(function(){
		fnModify();
		fnRemove();
		fnList();
	});
	function fnModify(){
		$('#f').on('submit', function(event){
			if ('${notice.title}' == $('#title').val() && '${notice.content}' == $('#content').val()) {
				alert('수정할 내용이 없습니다.');
				event.preventDefault();
				return false;
			}
			return true;
		})
	}
	function fnRemove(){
		$('#btnRemove').on('click', function(){
			if (confirm('삭제할까요?')) {
				location.href='${contextPath}/notice/remove?noticeNo=' + $('#noticeNo').val();
			}
		})
	}
	function fnList(){
		$('#btnList').on('click', function(){
			location.href='${contextPath}/notice/list';
		})
	}
</script>
</head>
<body>

	<h1>공지사항 상세 보기 화면</h1>
	
	<form id="f" method="post" action="${contextPath}/notice/modify">
		공지번호<br>
		${notice.noticeNo}<br><br>
		작성자<br>
		${notice.member.memberName}<br><br>
		제목<br>
		<input type="text" name="title" id="title" value="${notice.title}"><br><br>
		내용<br>
		<textarea rows="2" cols="25" name="content" id="content">${notice.content}</textarea><br><br>
		작성일<br>
		<fmt:formatDate value="${notice.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /><br><br>
		최종수정일<br>
		<fmt:formatDate value="${notice.modifiedDate}" pattern="yyyy-MM-dd HH:mm:ss" /><br><br>
		<input type="hidden" name="noticeNo" id="noticeNo" value="${notice.noticeNo}">
		<button>수정완료</button>
		<input type="button" value="삭제" id="btnRemove">
		<input type="button" value="목록" id="btnList">
	</form>

</body>
</html>