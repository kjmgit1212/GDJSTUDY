<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GDJLibrary</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script>

	$(function(){
	
		fnPagingLink();
		fnList();
		fnPrintFnqList();
		fnToggleHidden();
		fnWrite();
		fnSearch();
		fnEnterSearch();
	});
	
	
	// Enter 누르면 제목 또는 내용 검색
	function fnEnterSearch() {
		$('#query').on('keyup', function(event){
			 if(event.keyCode == 13) {
				$.ajax({
					url: '${contextPath}/fnq/search?page=1',
					type: 'get',
					data: 'column=' + $('#column').val() + '&query=' + $('#query').val(),
					dataType: 'json',
					success: function(obj){
						if(obj.fnqList == ''){
							$('#fnqList').empty();
							$('#fnqList').append($('<div class="nothing_list">').text('데이터가 존재하지 않습니다.'));
							$('#paging').empty();
							$('#paging').html('<div class="nothing_paging">1</div>');
						} else {
							fnPrintFnqList(obj.fnqList);
							fnPrintPaging(obj.paging);							
						}
					},
					error: function(jqXHR){
						alert(jqXHR.responseText);
					}
				});
			 }
		});
	}
	
	
	// 제목 또는 내용 검색
	function fnSearch(){
		$('#btnSearch').on('click', function(){
			if($('#query').val() == ''){
				alert('검색어를 입력하세요.');
				return;
			} else {
				$.ajax({
					url: '${contextPath}/fnq/search?page=1',
					type: 'get',
					data: 'column=' + $('#column').val() + '&query=' + $('#query').val(),
					dataType: 'json',
					success: function(obj){
						if(obj.fnqList == ''){
							$('#fnqList').empty();
							$('#fnqList').append($('<div class="nothing_list">').text('데이터가 존재하지 않습니다.'));
							$('#paging').empty();
							$('#paging').html('<div class="nothing_paging">1</div>');
						} else {
							fnPrintFnqList(obj.fnqList);
							fnPrintPaging(obj.paging);							
						}
					},
					error: function(jqXHR){
						alert(jqXHR.responseText);
					}
				});
			}
		});
	}
	
	
	// 관리자가 글쓰기 버튼을 누르면 글작성 페이지로 이동
	function fnWrite(){
		$('#btnWrite').on('click', function(){
			location.href='${contextPath}/fnq/fnqWrite';
		});
	}
	
	
	// <td>제목</td> 태그를 누를 때마다 blind class 넣다 뺐다 하기(Toggle)
	function fnToggleHidden(){
		$(document).on('click', '.title', function(){
						
			$(this).toggleClass('put');
			
			if($(this).hasClass('put')){
				$('.title').removeClass('put');
				$(this).addClass('put');
				$('.content').addClass('blind');
				$(this).next().removeClass('blind');
			}
			
			if($(this).hasClass('put')==false){
				$(this).next().addClass('blind');
			}
			
			
		});
	}
	
	
	
	
	// 페이징 링크 처리(page 전역변수 값을 링크의 data-page값으로 바꾸고 fnList() 호출)
	function fnPagingLink(){
		$(document).on('click', '.enable_link', function(){
			page = $(this).data('page');
			fnList();
		});
	}
	
	
	// FNQ 테이블 목록 + page 전역변수
	var page = 1;  // 초기화
	function fnList(){
		$.ajax({
			url: '${contextPath}/fnq/page/' + page,
			type: 'GET',
			dataType: 'json',
			success: function(obj){
				fnPrintFnqList(obj.fnqList);
				fnPrintPaging(obj.p);
			}
		});
	}
	
	
	// FNQ 테이블 목록 출력
	function fnPrintFnqList(fnqList){
		$('#fnqList').empty();
		$.each(fnqList, function(i, fnq){
			// $('#fnqList').append($('<div class="title">').text(fnq.fnqTitle));
			// $('#fnqList').append($('<div class="title">').html($('<span class="question">Q</span>')));
			$('#fnqList').append('<div class="title"><span class="question">Q.</span>&nbsp;&nbsp;' + fnq.fnqTitle + '</div>');
			$('#fnqList').append($('<div class="content blind">').text(fnq.fnqContent));
		});
	}
	
	
	// 페이징 정보 출력
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
	

</script>
<style>
 	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	
	* {
		font-family: 'Noto Sans KR', sans-serif;
		color: #4e4c4c;
	} 
	
	h2 {
		display: inline-block;
	}
	#btnWrite {
		margin-left: 550px;
	}
	#fnq_wrap {
		width: 800px;
		margin: 200px auto; 
		/* padding-left: 20px; */
	}
	.top_title_wrap {
		border-bottom: 1px solid #E2E2E2;
		box-sizing: border-box;
		width: 100%;
		padding-left: 15px;
	}
	.content_top_wrap, .content_bottom_wrap {
		border: 1px solid gray;
		box-sizing: border-box;
		width: 100%; 
		margin-top: 20px;
	}
	.content_top_wrap {
		border-bottom: none;
		border-top-color: #aec6ec;
		border-top-width: medium;
		border-left: none;
		border-right: none;
	}
	.content_bottom_wrap {
		margin-top: 30px;
		
		/*
		border-left: none;
		border-right: none;
		*/
		
		border: none;
	}
	.title, .nothing_list {
		padding-top: 3px;
		height: 50px;
		line-height: 250%;
	}
	.question {
		padding-left: 10px;
		color: #122aa3;
		font-weight: bold;
	}
	.content {
		padding-left: 26px;
		box-sizing: border-box;
		padding-top: 20px;
		padding-bottom: 20px;
		background-color: #fdfcf5;
	}
	#paging {
		display: flex;
		justify-content: center;
	}
	#fnqList div {
		border-bottom: 1px solid gray;
		box-sizing: border-box;
	}
	#paging div {
		width: 32px;
		height: 20px;
		text-align: center;
	}
	.disable_link {
		color: lightgray;
	}
	.title, .enable_link {
		cursor: pointer;
	}
	.now_page, .nothing_paging {
		border: 1px solid gray;
		color: #1b56db;
		font-weight: 900;
	}
	.blind {
		display: none;
	}	
	#fnqList {
		font-size: 18px;
	}
	#fnqList {
		color: #385772;
	}
	#fnq_search {
		width: 100%;
		margin-top: 40px;
		text-align: center;
	}
 	.btn_style, .search_column {
		border: 1px solid #7c7c7c;
		border-radius: 10px;
		width: 60px;
		height: 25px;
		color: white;
		background-color: #4390de;
		cursor: pointer;
	} 
	.search_column {
		background-color: white;
		color: black;
		font-size: 14px;
	}
	.search_query {
		height: 20px;
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
		height: 25px;
		background-color: #4390de;
		cursor: pointer;
	}
	.btn_write {
		height: 30px;
	}
</style>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include><br><br><br>

	<div id="fnq_wrap">
		<div class="top_title_wrap">
			<h2>자주하는질문</h2>
			<c:if test="${loginMember.memberId eq 'admin'}">
				<input type="button" value="글쓰기" id="btnWrite" class="btn_style btn_write">			
			</c:if>
		</div>
		
		<div class="content_top_wrap" id="fnqList"></div>
		<div class="content_bottom_wrap" id="paging"></div>
		<div id="fnq_search">

			<select name="column" id="column" class="search_column">
				<option value="FNQ_TITLE">제목</option>
				<option value="FNQ_CONTENT">내용</option>
			</select>			
			<input type="text" name="query" id="query" class="search_query" size="30" placeholder="검색어를 입력해주세요">&nbsp;&nbsp;
			<input type="button" id="btnSearch">
			<label for="btnSearch" class="icon_wrap"><i class="fa-solid fa-magnifying-glass" style="color: white"></i></label>
		</div>
	</div>

</body>
</html>