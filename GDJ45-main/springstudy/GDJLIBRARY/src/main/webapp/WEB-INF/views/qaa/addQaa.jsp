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
	.addQaa_wrap {
		width: 600px;
		margin: 200px auto; 
		/* padding-left: 20px; */
	}
	input[name="title"], input[name="memberId"] {
		width: 400px;
		height: 25px;
		padding-left: 15px;
	}
	textarea {
		width: 470px;
		height: 300px;
		resize: none;
	}
	#btn {
		width: 500px;
		margin-top: 20px;
		text-align: center;
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
		padding-left: 15px;
		padding-top: 15px;
		font-size: 16px;
	}

</style>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(function(){
	
		fnQaaAdd();
		fnList();
	});
	
	
	function fnQaaAdd(){
		$('#f').on('submit', function(event){
			if($('#title').val() == '' || $('#content').val() == ''){
				alert('제목과 내용을 전부 작성해주세요.');
				event.preventDefault();
				return;
			}
			
			$(this).submit();
			
		});
	}
	
	function fnList() {
		$('#btnList').on('click', function(){
			location.href='${contextPath}/qaa/qaaPage';
		});
	}

</script>

</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include><br><br><br>

	<div class="addQaa_wrap">
		<form id="f" action="${contextPath}/qaa/addQaa" method="post">
			<table>
				<tbody>
					<tr class="title_wrap">
						<td class="td_title title_first">제목</td>
						<td class="td_title"><input type="text" name="title" id="title"></td>
					</tr>
					<tr class="memberId_wrap">
						<td class="td_memberId memberId_first">회원ID</td>
						<td class="td_memberId"><input type="text" name="memberId" value="${loginMember.memberId}" readonly></td>
					</tr>
					<tr class="question_wrap">
						<td colspan="2">질문내용</td>
					</tr>
					<tr>
						<td colspan="2"><textarea name="content" id="content" placeholder="내용을 입력해 주세요."></textarea></td>
					</tr>
				</tbody>
			</table>
			<div id="btn">
				<button class="pointer">등록</button>&nbsp;&nbsp;
				<input class="pointer" type="reset" value="초기화">&nbsp;&nbsp;
				<input class="pointer" id="btnList" type="button" value="목록">
			</div>
		</form>
	</div>

</body>
</html>