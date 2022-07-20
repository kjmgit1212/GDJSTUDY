<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GDJLibrary</title>
<style>
		
		@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
   
   * {
      font-family: 'Noto Sans KR', sans-serif;
      color: #4e4c4c;
   }
		
		.all{
			margin: 100px 0 50px 0px;
		}
		
		.listTable{
			margin-left: auto;
			margin-right: auto;			
			text-align: center;
			width: 1000px;

		}
		
		a {
		  text-decoration: none;
		  color: black;
		}
		
		a:hover{
			color: blue;
			
		}
		
		thead, tfoot{
			height: 50px;
			background: #ffffde;
		}
		
		tbody{
			background: #fffff3;
		}
		
		#f{
			text-align: center;
		}

		.firstSearch{
			margin-bottom: 20px;
		}
		.endSearch{
			margin-top: 20px;
		}
		
		#paging {
			display: flex;
			justify-content: center;
		}
		
		#paging div {
			width: 32px;
			height: 20px;
			text-align: center;
		}
		
		.disable_link {
			color: lightgray;
		}
		
		.enable_link {
			cursor: pointer;
		}
		.now_page {
			border: 1px solid gray;
			color: limegreen;
			font-weight: 900;
		}
		
		.hidden{
			display: none;
		}
		
		.seen{
			display: inline;
		}
		
		#query{
			width: 250px;
			height: 20px;
		}
		
		#column{
			width: 80px;
			height: 25px;
			text-align: justify;
		}
		
		#btnSearch, #btnSearchAll {
		  background-color: #eeede4;
		  color: black;
		  padding: 0.5em 1.5em;
		  border: none;
		  border-radius: 5px;
		  letter-spacing: 2px;
		  outline: none;
		  align-self: center;
		  cursor: pointer;
		  font-weight: bold;
		}

		
		
		

</style>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
			// 페이지로드 이벤트
			$(function(){
				fnList();
				fnPagingLink();
				fnSearch();
			})

			
			// 1. 책목록 + page 전역변수
			var page = 1;  // 초기화
			function fnList(){
				$.ajax({
					url: '${contextPath}/book/list',
					type: 'GET',
					data: 'page=' + page,
					dataType: 'json',
					success: function(obj){
						fnPrintBookList(obj.books, obj.p);
						fnPrintPaging(obj.p);
					}
				})
			}
	
			// 1-1) 책 목록 출력
			function fnPrintBookList(books, p){
			$('#bookInfo').empty();
			$.each((books), function(i, book){
			var tr = '<tr>';
			tr += '<td>' + p.beginRecord++ + '</td>';
			tr += '<td><a href="${contextPath}/book/detail?bookNo=' + book.bookNo + '"><img src="' + book.bookImage + '"width=100px height=130px></td>';
			tr += '<td><a href="${contextPath}/book/detail?bookNo=' + book.bookNo + '">' + book.bookTitle + '</a></td>';
			tr += '<td>' + book.bookAuthor + '</td>';
			tr += '<td>' + book.bookType + '</td>';
			tr += '</tr>';
			$('#bookInfo').append(tr);
				})
			}
		
			// 1-2 페이징 링크 처리
			function fnPagingLink(){
			$(document).on('click', '.enable_link', function(){
				page = $(this).data('page');
				fnList();
				})
			}
	
			// 1-2) 페이징 정보 출력
			function fnPrintPaging(p){
			
			$('#paging').empty();
			
			var paging = '';
			
			// ◀◀ : 이전 블록으로 이동
			if(page <= p.pagePerBlock){
				paging += '<div class="disable_link">◀◀</div>';
			} else {
				paging += '<div class="enable_link" data-page="' + (p.beginPage - 1) + '">◀◀</div>';
			}
			
			// ◀  : 이전 페이지로 이동
			if(page == 1){
				paging += '<div class="disable_link">◀</div>';
			} else {
				paging += '<div class="enable_link" data-page="' + (page - 1) + '">◀</div>';
			}
			
			// 1 2 3 4 5 : 페이지 번호
			for(let i = p.beginPage; i <= p.endPage; i++){
				if(i == page){
					paging += '<div class="disable_link now_page">' + i + '</div>';
				} else {
					paging += '<div class="enable_link" data-page="' + i + '">' + i + '</div>';
				}
			}
			
			// ▶  : 다음 페이지로 이동
			if(page == p.totalPage){
				paging += '<div class="disable_link">▶</div>';
			} else {
				paging += '<div class="enable_link" data-page="' + (page + 1) + '">▶</div>';
			}
			
			// ▶▶ : 다음 블록으로 이동
			if(p.endPage == p.totalPage){
				paging += '<div class="disable_link">▶▶</div>';
			} else {
				paging += '<div class="enable_link" data-page="' + (p.endPage + 1) + '">▶▶</div>';
			}
			
			$('#paging').append(paging);
			
			}
	
			
			var page = 1; 
			function fnSearch(){
			$('#btnSearch').on('click', function(){
				$.ajax({
					url: '${contextPath}/book/search',
					type: 'GET',
					data: 'page=' + page + '&column=' + $('#column').val() + '&query=' + $('#query').val(),
					dataType: 'json',
					success: function(obj){
						fnPrintBookList(obj.books, obj.p);
						fnPrintPaging(obj.p);
						}
					})
				})
			}
		
			function enterKey(){
				$.ajax({
					url: '${contextPath}/book/search',
					type: 'GET',
					data: 'page=' + page + '&column=' + $('#column').val() + '&query=' + $('#query').val(),
					dataType: 'json',
					success: function(obj){
						fnPrintBookList(obj.books, obj.p);
						fnPrintPaging(obj.p);
						}
					})
				}
		
		
</script>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include><br><br><br>
		<div id="insertApi">
		<a href="${contextPath}/book/insertBook">책추가하기</a>
		</div>
		<div class="all">
		
		<form id="f">
			<div class="firstSearch">
				자료검색 &nbsp; 
				<select name="column" id="column">
					<option value="">:::선택:::</option>
					<option value="BOOK_TITLE">:::제목:::</option>
					<option value="BOOK_AUTHOR">:::저자:::</option>
					<option value="BOOK_TYPE">:::분야:::</option>
				</select>
				<input type="text" id="query" name="query" onkeypress="if(event.keyCode == 13){enterKey()}" placeholder="검색어를 입력하세요"/>
				<input type="text" style="display: none;" />
				<input type="button" id="btnSearch" value="검색" />
				<input type="button" id="btnSearchAll" value="전체조회" onclick="location.href='${contextPath}/book/listPage'"></input>
			</div>
		</form>
		
		<table class="listTable" >
		
		<thead class="listTable head">
			<tr>
				<td>순서</td>
				<td>도서이미지</td>
				<td>책제목</td>
				<td>저자</td>
				<td>분야</td>
			</tr>
		</thead>
		<tbody id="bookInfo" class="listTable body">
			
		</tbody>
		<tfoot class="listTable foot">
			<tr>
				<td colspan="5">
					<div id="paging"></div>
				</td>
			</tr>
		</tfoot>
	</table>
	
	<form id="f">
			<div class="endSearch">
				자료검색 &nbsp; 
				<select name="column" id="column">
					<option value="">:::선택:::</option>
					<option value="BOOK_TITLE">:::제목:::</option>
					<option value="BOOK_AUTHOR">:::저자:::</option>
					<option value="BOOK_TYPE">:::분야:::</option>
				</select>
				<input type="text" id="query" name="query" onkeypress="if(event.keyCode == 13){enterKey()}" placeholder="검색어를 입력하세요"/>
				<input type="text" style="display: none;" />
				<input type="button" id="btnSearch" value="검색" />
				<input type="button" id="btnSearchAll" value="전체조회" onclick="location.href='${contextPath}/book/listPage'"></input>
			</div>
		</form>
	
	</div>

	

</body>
</html>