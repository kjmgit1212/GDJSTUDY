<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GDJLibrary</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(function(){
		fnWrite();
		fnSearch();
		fnEnterSearch();
	});
	
	function fnWrite() {
		$('#btnWrite').on('click', function(){
			location.href='${contextPath}/notice/addNoticePage';			
		});
	}
	
	// Enter 누르면 제목 또는 내용 검색
	function fnEnterSearch(){
		$('#query').on('keyup', function(event){
			if(event.keyCode == 13) {
				location.href='${contextPath}/notice/search?column=' + $('#column').val() + '&query=' + $('#query').val();
			}
		});
		
	}
	
	
	// 제목 또는 내용 검색
	function fnSearch() {
		$('#btnSearch').on('click', function(){
			if($('#query').val() == ''){
				alert('검색어를 입력하세요.');
				return;
			}
			location.href='${contextPath}/notice/search?column=' + $('#column').val() + '&query=' + $('#query').val();
		});
	}
	
	

</script>
<style>

 	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	
	* {
		box-sizing: border-box;
		font-family: 'Noto Sans KR', sans-serif;
		color: #4e4c4c;
	} 
/* 	.link:hover {
		border: 1px solid orange;
		color: limegreen;
	} */
	.notice_title_top {
		font-size: 30px;
		font-weight: 900;
	}
	.unlink, .link {
		display: inline-block;
		margin-left: 10px;
		margin-right: 10px;
	}
	table {
		border-collapse: collapse;
	}
	.notice_all_wrap {
		width: 1020px;
		margin: 200px auto; 
		/* padding-left: 20px; */
	}
	.notice_top {
		width: 100%;
		padding-left: 430px;
		margin-top: 40px;
		margin-bottom: 40px;
		font-size: 24px;
	}
	.notice_write_wrap {
		display: inline-block;
		padding-left: 350px;
	}
	td {
		height: 50px;
		text-align: center;
		border-bottom: 1px solid silver;
	}
	tfoot td {
		border-bottom: none;
	}
	.notice_content {
		width: 100%;
	}
	
	td:nth-child(1) { width: 50px; }
	td:nth-child(2) { 
		width: 750px; 
		text-align: left;
		padding-left: 20px;
	}
	td:nth-child(3) { width: 140px; }
	td:nth-child(4) { width: 80px; }
	
 	#notice_title {
		text-align: center;
	} 
	table thead {
		background: #e0e2ef;
	}
	table tbody {
		background: #f2f2f2;;
	}
	tfoot td div {
		margin-top: 30px;
	}
	#notice_search {
		width: 100%;
		margin-bottom: 20px;
		text-align: right;
	}
	.a_style {
		display:inline-block;
 		width: 100%; 
		text-decoration: none;
	}
	.a_style:link, .a_style:visited {
		color: black;
	}
	.content_wrap:hover {
		background-color: #819dc7;
	}
	
	
	.btn_style, .search_column {
		border: 1px solid #7c7c7c;
		border-radius: 10px;
		width: 60px;
		height: 30px;
		color: white;
		background-color: #4390de;
		cursor: pointer;
	} 
	.search_column {
		background-color: white;
		color: black;
		font-size: 14px;
		width: 60px;
		height: 25px;
	}
	.search_query {
		height: 25px;
		border: 1px solid #7c7c7c;
		border-radius: 10px;
		vertical-align: middle;
		padding-left: 10px;
		/* padding-top: 3px; */
	}
	#btnSearch {
		display: none;
	}
	.icon_wrap {
		display: inline-block;
		border: 1px solid #7c7c7c;
		border-radius: 170px;
		width: 50px;
		height: 27px;
		background-color: #4390de;
		cursor: pointer;
		/* padding-top: 1px; */
		text-align: center;
	}

	
	
	
	
	
	
	
	
	a {
		text-decoration: none;
	}
	.unlink {
		color: lightgray;
	}
	.now_page {
		border: 1px solid gray;
		color: #1b56db;
		font-weight: 900;
		display: inline-block;
		width: 32px;
	}

	
</style>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include><br><br><br>

	<div class="notice_all_wrap">
		<div class="notice_top">
			<span class="notice_title_top">- 공지사항 -</span>
			<c:if test="${loginMember.memberId eq 'admin'}">
				<span class="notice_write_wrap"><input type="button" value="글쓰기" id="btnWrite" class="btn_style"></span>
			</c:if>
		</div>
		<div id="notice_search">
			<select id="column" class="search_column">
				<option value="NOTICE_TITLE">제목</option>
				<option value="NOTICE_CONTENT">내용</option>
			</select>			
			<input type="text" id="query" value="${query}" class="search_query" size="30" placeholder="검색어를 입력해주세요">&nbsp;&nbsp;
			<input type="button" id="btnSearch">
			<label for="btnSearch" class="icon_wrap"><i class="fa-solid fa-magnifying-glass" style="color: white"></i></label>
		</div>
		<div class="notice_content">
			<table>
				<thead>
					<tr>
						<td>번호</td>
						<td id="notice_title">제목</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty noticeList}">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${not empty noticeList}">
						<c:forEach items="${noticeList}" var="notice" varStatus="vs">
							<tr class="content_wrap">
								<td>${startNo - vs.index}</td>
								<td><a class="a_style" href="${contextPath}/notice/detailNotice?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a></td>
								<td>
									<fmt:formatDate value="${notice.noticeCreated}" pattern="yyyy-MM-dd"/>
								</td>
								<td>${notice.noticeHit}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div>
								${paging}
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>