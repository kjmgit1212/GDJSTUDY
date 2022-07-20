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
	
	$(document).ready(function(){
		fnInit();
		fnList();
		fnFind();
	});

	// 화면 초기화 함수
	function fnInit(){
		$('#btnInit').click(function(){
			$('#column, #query').val('');
		});
	}

	// 전체 검색 함수
	function fnList(){
		$('#btnList').click(function(){
			location.href='${contextPath}/notice/list';
		});
	}
	
	// 검색 함수
	function fnFind(){
		$('#f').submit(function(event){
			if ($('#column').val() == '') {
				alert('검색 카테고리를 선택하세요.');
				$('#column').focus();
				event.preventDefault();
				return false;
			}
			return true;
		});
	}
	
</script>
<style>
	* {
		box-sizing: border-box;
	}
	.unlink, .link {
		display: inline-block;  /* 같은 줄에 둘 수 있고, width, height 등 크기 지정 속성을 지정할 수 있다. */
		padding: 10px;
		margin: 5px;
		border: 1px solid white;
		text-align: center;
		text-decoration: none;  /* 링크 밑줄 없애기 */
		color: gray;
	}
	.link:hover {
		border: 1px solid orange;
		color: limegreen;
	}
</style>
</head>
<body>

	<h1>공지사항 목록 화면</h1>
	
	<a href="${contextPath}/notice/addPage">새 공지사항 작성</a>
	
	<hr>
	
	<form id="f" method="get" action="${contextPath}/notice/find">
		<select name="column" id="column">
			<option value="">:::선택:::</option>
			<option value="TITLE">TITLE</option>
			<option value="CONTENT">CONTENT</option>
		</select>
		<input type="text" name="query" id="query">
		<button>검색하기</button>
		<input type="button" value="검색조건초기화" id="btnInit">
		<input type="button" value="전체공지조회" id="btnList">
	</form>
	
	<br>
	
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty notices}">
				<tr>
					<td colspan="3">공지사항 없음</td>
				</tr>
			</c:if>
			<c:if test="${not empty notices}">
				<c:forEach var="notice" items="${notices}" varStatus="vs">
					<tr>
						<td>${startNo - vs.index}</td>
						<td>${notice.member.memberName}</td>
						<td><a href="${contextPath}/notice/detail?noticeNo=${notice.noticeNo}">${notice.title}</a></td>
						<td><fmt:formatDate value="${notice.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">${paging}</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>