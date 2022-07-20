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
<script>

	/* 페이지 로드 */
	$(function(){
		$('#btnInit').on('click', function(){	
			fnInit();
		})
		fnCheckAll();
		fnCheckOne();
		fnAdd();
		fnList();
		fnPagingLink();
		fnDetail();
		fnUpdate();
		fnRemove();
	})

	/* 함수 */
	
	// 8. 회원삭제
	function fnRemove(){
		$('#btnRemove').on('click', function(){
			let deleteCount = 0;
			let checkedCount = 0;
			for(let i = 0; i < $('.checkOne').length; i++){
				/* $('.checkOne')[i].value == $($('.checkOne')[i]).val() */
				if($($('.checkOne')[i]).is(':checked')){
					checkedCount += 1;
					$.ajax({
						url: '${contextPath}/members/' + $('.checkOne')[i].value,
						type: 'DELETE',
						dataType: 'json',
						success: function(obj){
							deleteCount += obj.res;
							console.log(i, deleteCount);
						}
					})
				}
			}
			console.log('삭제: ' + deleteCount);
			console.log('체크: ' + checkedCount);
			if(deleteCount == checkedCount){
				alert('모든 회원이 삭제되었습니다.');
			}
			fnList();
		})
	}
	
	// 7. 회원수정
	function fnUpdate(){
		$('#btnChange').on('click', function(){
			// 수정할 회원 정보 JSON
			let member = JSON.stringify(
				{
					memberNo: $('#memberNo').val(),
					name: $('#name').val(),
					gender: $(':radio[name="gender"]:checked').val(),
					address: $('#address').val()
				}
			);
			$.ajax({
				url: '${contextPath}/members',
				type: 'PUT',
				data: member,
				contentType: 'application/json',
				dataType: 'json',
				success: function(obj){
					if(obj.res > 0){
						alert('회원 정보가 수정되었습니다.');
						fnList();
					} else {
						alert('회원 정보가 수정되지 않았습니다.');
					}
				},
				error: function(jqXHR){
					alert('예외코드[' + jqXHR.status + '] ' + jqXHR.responseText);
				}
			})
		})
	}
	
	// 6. 회원조회
	function fnDetail(){
		$(document).on('click', '.btnDetail', function(){
			$.ajax({
				url: '${contextPath}/members/' + $(this).data('member_no'),
				type: 'GET',
				dataType: 'json',
				success: function(obj){
					if(obj.member == null){
						alert('해당 회원의 정보가 없습니다.');
					} else {
						$('#memberNo').val(obj.member.memberNo);
						$('#id').val(obj.member.id).prop('readonly', true);
						$('#name').val(obj.member.name);
						$(':radio[name="gender"][value="' + obj.member.gender + '"]').prop('checked', true);
						$('#address').val(obj.member.address);
					}
				}
			})
		})
	}
	
	// 5. 페이징 링크 처리(page 전역변수 값을 링크의 data-page값으로 바꾸고 fnList() 호출)
	function fnPagingLink(){
		$(document).on('click', '.enable_link', function(){
			page = $(this).data('page');
			fnList();
		})
	}
	
	// 4. 회원목록 + page 전역변수
	var page = 1;  // 초기화
	function fnList(){
		$.ajax({
			url: '${contextPath}/members/page/' + page,
			type: 'GET',
			dataType: 'json',
			success: function(obj){
				fnPrintMemberList(obj.members);
				fnPrintPaging(obj.p);
			}
		})
	}
	
	// 4-1) 회원 목록 출력
	function fnPrintMemberList(members){
		$('#members').empty();
		$.each(members, function(i, member){
			var tr = '<tr>';
			tr += '<td><input type="checkbox" class="checkOne" value="' + member.memberNo + '"></td>';
			tr += '<td>' + member.id + '</td>';
			tr += '<td>' + member.name + '</td>';
			tr += '<td>' + member.gender + '</td>';
			tr += '<td>' + member.address + '</td>';
			tr += '<td><input type="button" value="조회" class="btnDetail" data-member_no="' + member.memberNo + '"></td>';
			tr += '</tr>';
			$('#members').append(tr);
		})
	}
	
	// 4-2) 페이징 정보 출력
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
	
	// 3. 회원추가
	function fnAdd(){
		$('#btnAdd').on('click', function(){
			// 추가할 회원 정보를 JSON으로 만든다.
			let member = JSON.stringify(
				{
					id: $('#id').val(),
					name: $('#name').val(),
					gender: $(':radio[name="gender"]:checked').val(),
					address: $('#address').val()
				}		
			);
			// ajax 처리
			$.ajax({
				// 요청
				url: '${contextPath}/members',
				type: 'POST',
				data: member,
				contentType: 'application/json',
				// 응답
				dataType: 'json',
				success: function(obj){
					if(obj.res > 0){
						alert('회원이 등록되었습니다.');
						fnInit();
						fnList();
					} else {
						alert('회원이 등록되지 않았습니다.');
					}
				},
				error: function(jqXHR){
					alert('예외코드[' + jqXHR.status + '] ' + jqXHR.responseText);
				}
			})
		})
	}
	
	// 2. 전체 선택 / 개별 선택
	function fnCheckAll(){
		$('#checkAll').on('click', function(){
			$('.checkOne').prop('checked', $('#checkAll').prop('checked'));
		})
	}
	function fnCheckOne(){
		$(document).on('click', '.checkOne', function(){
			let checkCount = 0;
			for(let i = 0; i < $('.checkOne').length; i++){
				checkCount += $($('.checkOne')[i]).prop('checked');				
			}
			$('#checkAll').prop('checked', checkCount == $('.checkOne').length);
		})
	}
	
	// 1. 초기화
	function fnInit(){
		$('#id').val('').prop('readonly', false);
		$('#name').val('');
		$(':radio[name="gender"]').prop('checked', false);
		$('#address').val('');
	}
	
</script>
<style>
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
</style>
</head>
<body>
	
	<h1>회원관리</h1>
	
	<div>
		<input type="hidden" name="memberNo" id="memberNo">
		아이디 <input type="text" name="id" id="id"><br>
		이름   <input type="text" name="name" id="name"><br>
		성별
		<label for="male"><input type="radio" name="gender" value="M" id="male">남</label>
		<label for="female"><input type="radio" name="gender" value="F" id="female">여</label>
		<label for="none"><input type="radio" name="gender" value="NONE" id="none" checked>선택</label><br>
		주소   <input type="text" name="address" id="address"><br><br>
		<input type="button" value="초기화" id="btnInit">
		<input type="button" value="등록" id="btnAdd">
		<input type="button" value="수정" id="btnChange">
	</div>
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td><input type="checkbox" id="checkAll"></td>
				<td>아이디</td>
				<td>이름</td>
				<td>성별</td>
				<td>주소</td>
				<td>단추</td>
			</tr>
		</thead>
		<tbody id="members"></tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					<div id="paging"></div>
				</td>
			</tr>
		</tfoot>
	</table>
	
	<br>
	
	<input type="button" value="선택삭제" id="btnRemove">
	
</body>
</html>