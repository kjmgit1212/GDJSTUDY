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
	
		fnDetail();
		fnBlind();
		fnSubmit();
		fnRemove();
		fnWrite();
		fnSearch();
		fnEnterSearch();
		fnQaaRemove();
	});
	
	// 게시글(원글) 삭제하기
	function fnQaaRemove(){
		$('.btnQaaRemove').on('click', function(){
			if(confirm('게시글을 삭제하면 댓글도 같이 삭제됩니다. 정말 삭제하시겠습니까?')){
				location.href='${contextPath}/qaa/removeQaa?qaaNo=' + $(this).data('qaa_no');
			}
		});
	}
	
	// Enter 누르면 작성자(회원ID) 검색
	function fnEnterSearch() {
		let page = 1;
		$('#query').on('keyup', function(event){
			 if(event.keyCode == 13) {
				 location.href='${contextPath}/qaa/search?column=' + $('#column').val() + '&query=' + $('#query').val() + '&page=' + page;
			 }
		});
	}
	
	// 작성자(회원ID) 검색
	function fnSearch(){
		let page = 1;
		$('#btnSearch').on('click', function(){
			if($('#query').val() == ''){
				alert('검색어를 입력하세요.');
				return;
			} else {
				location.href='${contextPath}/qaa/search?column=' + $('#column').val() + '&query=' + $('#query').val() + '&page=' + page;
			}
		});
	}
	
	
	
	function fnBlind(){
		$('.reply_link').on('click', function(){
	    	
	    	$(this).toggleClass('put');
	    	
			if($(this).hasClass('put')){
				$('.reply_link').removeClass('put');
				$(this).addClass('put');
				$('.reply_form').addClass('blind');
				$(this).parent().parent().next().removeClass('blind');
			}
			
			if($(this).hasClass('put')==false){
				$(this).parent().parent().next().addClass('blind');
			}
	    	
		});
	}
	
	
	// 버튼을 누르면 글 작성한 회원의 글 이력이 나오는 페이지로 이동시키기
	// 질문과 답변 작성 게시판의 jsp 참고해서 수정할 부분만 수정하기
	function fnDetail() {
		$('.btnDetail').on('click', function(){
			location.href='${contextPath}/qaa/detailQaa?qaaNo=' + $(this).data('qaa_no');
		});
	}
	
	
	// 버튼을 누르면 댓글을 삭제할까요? 라는 확인 알람창 나오게 한 뒤
    // 확인 버튼을 누르면 삭제 진행하기
	function fnRemove() {
		$('.btnRemove').on('click', function(){
			if(confirm('댓글을 삭제할까요?')){
				$.ajax({
					url: '${contextPath}/qaa/removeReply',
					type: 'post',
					data: JSON.stringify({
						'qaaNo': $(this).data('qaa_no'),
						'qaaDepth': $(this).next().val(),
						'qaaGroupOrd': $(this).next().next().val(),
						'qaaGroupNo': $(this).next().next().next().val()
					}),
					contentType: 'application/json',
					dataType: 'json',
					success: function(obj){
						if(obj.res == 1){
							alert('댓글이 삭제되었습니다.');
							location.href='${contextPath}/qaa/qaaPage';
						} else {
							alert('댓글이 삭제되지 않았습니다.');
							location.href='${contextPath}/qaa/qaaPage';
						}
					},
					error: function(jqXHR){
						alert(jqXHR.responseText);
					}
				});				
			}
		});
	}
	
	
	function fnSubmit() {
		$('.f').on('submit', function(event){
			if($(this).find('.writeReply').val() == ''){
				alert('댓글을 작성해주세요');
				event.preventDefault();
			}
		});
	}
	
	
	function fnWrite() {
		$('#btnWrite').on('click', function(){
			location.href='${contextPath}/qaa/addQaaPage';
			
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
	.qaa_all_wrap {
		width: 1070px;
		margin: 200px auto; 
		/* padding-left: 20px; */
	}
	.qaa_top_wrap {
		width: 1065px;
		padding-left: 20px;
		margin-bottom: 20px;
	}
	h2 {
		display: inline-block;
	}
	.thead_title {
		background-color: #e0e2ef;
	}
	#btnWrite {
		margin-left: 820px;
	}
	.search_column {
		background-color: white;
		color: black;
		font-size: 16px;
	}
	.blind {
		display: none;
	}	
	
/*	.link:hover {
		border: 1px solid gray;
		color: #1b56db;
	} */
	table {
		border-collapse: collapse;
	}
	td {
		height: 55px;
		padding: 5px;
		border-top: 1px solid silver;
		/* border-bottom: 1px solid silver; */
		text-align: center;
	}
	td:nth-of-type(1) { width: 80px; }
	td:nth-of-type(2) { width: 125px; }
	td:nth-of-type(3) { 
		width: 500px; 
		text-align: left;
	}
	td:nth-of-type(4) { width: 240px; }
	td:nth-of-type(5) { width: 120px; }
	tfoot td {
		border-left: 0;
		border-right: 0;
		border-bottom: 0;
	}
	.deg {
		transform: rotate(175deg);
	}
	#board_reply {
		text-align: center;
	}
	.unlink, .link {
		display: inline-block;
		margin-left: 10px;
		margin-right: 10px;
	}
	.reply_link {
		display: inline-block;
		color: blue;
		cursor: pointer;
	}
	.memberId_box, input[name="content"] {
		height: 35px;
		padding-left: 5px;
		border-radius: 10px;
	}

	tfoot td div {
		margin-top: 30px;
	}
	#qaa_search {
		width: 1065px; 
		margin-top: 40px;
		text-align: center;
	}
 	.btn_style, .search_column {
		border: 1px solid #7c7c7c;
		border-radius: 10px;
		width: 60px;
		height: 20px;
		color: white;
		background-color: #4390de;
		cursor: pointer;
	} 
	.btnReply {
		display: inline-block;
		margin-left: 25px;
		width: 72px;
		height: 25px;
		background-color: #eaeff3;
		color: #201e1e;
	}
	.btnRemove {
		width: 72px;
		height: 25px;
		background-color: #eaeff3;
		color: #201e1e;
	}
	.btnDetail, .btnQaaRemove {
		width: 50px;
		height: 25px;
		background-color: #eaeff3;
		color: #201e1e;
	}
	.search_column {
		background-color: white;
		color: black;
		font-size: 14px;
		width: 100px;
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
	}
	.btn_write {
		height: 30px;
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
	
	<div class="qaa_all_wrap">
		<div class="qaa_top_wrap">
			<h2>질문과 답변</h2>
			<input type="button" value="글쓰기" id="btnWrite" class="btn_style btn_write">
		</div>
		<table>
			<thead>
				<tr class="thead_title">
					<td>번호</td>
					<td>회원ID</td>
					<td id="board_reply">게시글/답변</td>
					<td>작성일</td>
					<td>조회/삭제</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty qaaList}">
					<tr>
						<td colspan="5">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty qaaList}">
					<c:forEach var="qaa" items="${qaaList}" varStatus="vs">
						<tr>
							<td>${startNo - vs.index}</td>
							<td>${qaa.memberId.substring(0, 3)}***</td>
							<td>
								<!-- Depth만큼 들여쓰기(Depth 1 == Space 2) -->
								<c:forEach begin="1" end="${qaa.qaaDepth}" step="1">&nbsp;&nbsp;</c:forEach>
								<!-- 댓글은 아이콘 표시 -->
								<c:if test="${qaa.qaaDepth gt 0}"><i class="fa-solid fa-reply deg"></i></c:if>
								
								<!-- 
									게시글인 경우 : 회원(제목)
									댓글인 경우   : 회원(댓글), 관리자(댓글)
								-->
								<c:if test="${qaa.memberId eq 'admin'}">
									<c:if test="${qaa.qaaContent.length() gt 20}">								
										${qaa.qaaContent.substring(0, 20)}
									</c:if>
									<c:if test="${qaa.qaaContent.length() le 20}">								
										${qaa.qaaContent}
									</c:if>
								</c:if>
								
								<c:if test="${qaa.memberId ne 'admin' && qaa.qaaTitle eq '댓글 작성'}">
									<c:if test="${qaa.qaaContent.length() gt 20}">								
										${qaa.qaaContent.substring(0, 20)}
									</c:if>
									<c:if test="${qaa.qaaContent.length() le 20}">								
										${qaa.qaaContent}
									</c:if>
								</c:if>
								
								<c:if test="${qaa.memberId ne 'admin' && qaa.qaaTitle ne '댓글 작성'}">
									<c:if test="${qaa.qaaTitle.length() gt 20}">								
										${qaa.qaaTitle.substring(0, 20)}
									</c:if>
									<c:if test="${qaa.qaaTitle.length() le 20}">								
											${qaa.qaaTitle}
									</c:if>
								</c:if>
								
								<c:if test="${loginMember ne null}">
									<a class="reply_link">답글</a>
								</c:if>
								
								
							</td>
							<td>
								<fmt:formatDate value="${qaa.qaaCreated}" pattern="yyyy-MM-dd"/>
							</td>
							<td>
								<c:if test="${(loginMember.memberId eq 'admin' && qaa.qaaTitle ne '댓글 작성') || (loginMember.memberId eq qaa.memberId && qaa.qaaTitle ne '댓글 작성')}">
									<!-- 
										<a data-qaa_no="${qaa.qaaNo}" onclick="fnRemove(this)">
									 -->
									 
									<input type="button" value="조회" data-qaa_no="${qaa.qaaNo}" class="btnDetail btn_style">
									<input type="button" value="삭제" data-qaa_no="${qaa.qaaNo}" class="btnQaaRemove btn_style">
									
								</c:if>
								<c:if test="${(loginMember.memberId eq 'admin' && qaa.qaaTitle eq '댓글 작성') || (loginMember.memberId eq qaa.memberId && qaa.qaaTitle eq '댓글 작성')}">
									<input type="button" value="댓글삭제" data-qaa_no="${qaa.qaaNo}" class="btnRemove btn_style">
									<input type="hidden" value="${qaa.qaaDepth}">
									<input type="hidden" value="${qaa.qaaGroupOrd}">
									<input type="hidden" value="${qaa.qaaGroupNo}">
								</c:if>
							</td>
						</tr>
						<tr class="reply_form blind">
							<td colspan="5">
								<form class="f" action="${contextPath}/qaa/saveReply" method="post">
									<input type="hidden" name="memberId" value="${loginMember.memberId}">
									<input type="text" value="${loginMember.memberId.substring(0, 3)}***" readonly size="10" class="memberId_box">
									<input type="text" name="content" class="writeReply" placeholder="내용" size="80">
									<!-- 원글의 Depth, GroupNo, GroupOrd -->
									<input type="hidden" name="depth" value="${qaa.qaaDepth}">
									<input type="hidden" name="groupNo" value="${qaa.qaaGroupNo}">
									<input type="hidden" name="groupOrd" value="${qaa.qaaGroupOrd}">
									<button class="btnReply btn_style">답글달기</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<div>
							${paging}
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
		
		<div id="qaa_search">
			<select name="column" id="column" class="search_column">
				<option value="MEMBER_ID">작성자(ID)</option>
			</select>			
			<input type="text" name="query" id="query" value="${query}" class="search_query" size="30" placeholder="검색어를 입력해주세요">&nbsp;&nbsp;
			<input type="button" id="btnSearch">
			<label for="btnSearch" class="icon_wrap"><i class="fa-solid fa-magnifying-glass" style="color: white"></i></label>
		</div>
	</div>

</body>
</html>