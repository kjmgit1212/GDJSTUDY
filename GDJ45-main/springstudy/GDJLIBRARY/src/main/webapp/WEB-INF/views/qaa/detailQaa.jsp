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
	.qaa_all_wrap {
		width: 600px;
		margin: 200px auto; 
		/* padding-left: 20px; */
	}
	input[name="qaaTitle"], #memberId {
		width: 400px;
		height: 25px;
		/* border: none; */
		outline: none;
		
	}
	#title, #memberId {
		padding-left: 10px;
	}
	textarea {
		width: 470px;
		height: 300px;
		resize: none;
		/* border: none; */
		outline: none;
	}
	#btn {
		width: 500px;
		text-align: center;
		margin-top: 30px;
	}
	.pointer {
		display: inline-block;
		border: 1px solid #7c7c7c;
		border-radius: 170px;
		width: 60px;
		height: 27px;
		background-color: #4390de;
		cursor: pointer;
		padding-top: 1px;
		text-align: center;
		color: white;
	}
	.blind {
		display: none;
	}
	.title_wrap, .memberId_wrap, .question_wrap {
		display: block;
		margin-top: 30px;
	}
	.td_title, .td_memberId {
		display: inline-block;
		
	}
	.title_first {
		margin-right: 25px;
	}
	.memberId_first {
		margin-right: 10px;
	}
	#content {
		padding-left: 10px;
		padding-top: 10px;
		font-size: 16px;
	}
</style>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(function(){
	
		fnModifyPage();
		fnReset();
		fnQaaPage();
		fnRemove();
	
	});
		
		
		function fnModifyPage() {
			$('#f').on('submit', function(event){
				if($('.btnText').hasClass('modify') == false){
					if(confirm('수정하시겠습니까?')){
						event.preventDefault();
						/*
						$('#title').css({
							'box-sizing': 'border-box',
							'border': '1px solid #e2e2e2', 
							'padding': '1px'
						});
						*/
						$('#title').attr('readonly', false);
						
						/*
						$('#content').css({
							'box-sizing': 'border-box',
							'border': '1px solid #e2e2e2',
							'padding': '1px'
						});
						*/
						$('#content').attr('readonly', false);
						
						$('.btnText').text('수정').addClass('modify');
						$('#btnReset').removeClass('blind');
						$('#btnRemove').addClass('blind');
					} else {
						event.preventDefault();
					}
				
				} else {
					if($('#title').val() == '${qaa.qaaTitle}' && $('#content').val() == '${qaa.qaaContent}'){
						alert('수정된 내용이 없습니다.');
						event.preventDefault();
						return;
					} else if($('#title').val() == '' || $('#content').val() == ''){
						alert('제목과 내용을 전부 입력해주세요.');
						event.preventDefault();
						return;
					} else {
						$(this).submit();
					}
				}
			});
		}
		
		
		function fnReset(){
			$('#btnReset').on('click', function(){
				if($('#title').prop('readOnly') == false){
					$('#title').val('');
					$('#content').val('');										
				}
			});
		}
	
		
		function fnQaaPage() {
			$('#btnList').on('click', function(){
				location.href='${contextPath}/qaa/qaaPage';
			});
		}
		
		
		function fnRemove() {
			$('#btnRemove').on('click', function(){
				if(confirm('게시글을 삭제하면 댓글도 같이 삭제됩니다. 정말 삭제하시겠습니까?')){
					location.href='${contextPath}/qaa/removeQaa?qaaNo=' + $('#qaaNo').val();
				}
			});
		}

</script>

</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include><br><br><br>
	
	<div class="qaa_all_wrap">
		<form id="f" action="${contextPath}/qaa/modifyQaa" method="post">
			<table>
				<tbody>
					<tr class="title_wrap">
						<td class="td_title title_first">제목</td>
						<td class="td_title"><input type="text" name="qaaTitle" id="title" value="${qaa.qaaTitle}" readonly></td>
					</tr>
					<tr class="memberId_wrap">
						<td class="td_memberId memberId_first">회원ID</td>
						<td class="td_memberId"><input type="text" id="memberId" value="${loginMember.memberId}" readonly></td>
					</tr>
					<tr class="question_wrap" >
						<td colspan="2">질문내용</td>
					</tr>
					<tr>				
						<td colspan="2"><textarea name="qaaContent" id="content" readonly>${qaa.qaaContent}</textarea></td>
					</tr>
				</tbody>
			</table>
			<div id="btn">
				<input type="hidden" name="qaaNo" id="qaaNo" value="${qaa.qaaNo}">
				<button class="pointer btnText">수정</button>&nbsp;
				<input class="pointer" id="btnRemove" type="button" value="삭제">
				<input class="pointer blind" type="button" id="btnReset" value="초기화">
				&nbsp;<input class="pointer" id="btnList" type="button" value="목록">
			</div>
		</form>
	</div>

</body>
</html>