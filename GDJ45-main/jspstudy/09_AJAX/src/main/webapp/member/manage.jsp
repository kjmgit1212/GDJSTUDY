<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	label {
		display: block;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>

	// 페이지 로드 이벤트
	$(document).ready(function(){
		fnList();
		fnDetail();
		fnAdd();
		fnModify();
		fnRemove();
		fnInit();
	})
	
	// 함수
	function fnList(){
		// 화면이 열리면 곧바로 실행
		$.ajax({
			url: '/AJAX/list.do',
			dataType: 'json',
			success: function(responseText){  // responseText : {"count":7, "members":[]}
				$('#memberCount').text(responseText.count);
				var memberList = $('#memberList');
				memberList.empty();  // 회원 목록의 초기화
				$.each(responseText.members, function(i, member){
					var tr = '<tr>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + member.gender + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '<td><input type="hidden" value="' + member.no + '"><input type="button" data-no="' + member.no + '" value="조회" class="btnDetail"></td>';
					tr += '</tr>';
					memberList.append(tr);
				})
			}
		})
	}
	function fnDetail(){
		// 조회 버튼을 클릭하면 실행
		// 조회 버튼은 동적 요소(dynamic element)이므로,
		// 직접 이벤트 대상으로 사용하지 않고, 부모(정적 요소)를 기반으로 이벤트를 등록시킨다.
		$('body').on('click', '.btnDetail' ,function(){
			var no = $(this).prev().val();  // var no = $(this).data('no');
			$.ajax({
				url: '/AJAX/detail.do',
				data: 'no=' + no,
				type: 'get',
				dataType: 'json',
				success: function(responseText){  // responseText : {"result": true, "member": {}}, {"result": false}
					if(responseText.result == true){
						$('#no').val(responseText.member.no);
						$('#id').val(responseText.member.id).prop('readonly', true);
						$('#name').val(responseText.member.name);
						$(':radio[name="gender"][value="' + responseText.member.gender + '"]').prop('checked', true);
						$('#address').val(responseText.member.address);
					} else {
						alert('조회된 회원 정보가 없습니다.');
					}
				}
			})
		})
	}
	function fnAdd(){
		
		// 등록 버튼을 클릭하면 실행
		$('#btnAdd').on('click', function(){
			
			// 요청 URL
			// http://localhost:9090/AJAX/add.do
				
			// 요청 Method
			// POST
			
			// 요청 Parameter
			// id=아이디&name=이름&gender=성별&address=주소
			
			// 요청 Parameter 상세
			// id      : 신규 회원의 아이디 (필수, 중복 불가)
			// name    : 신규 회원의 이름
			// gender  : 신규 회원의 성별
			// address : 신규 회원의 주소
			
			$.ajax({
				url: '/AJAX/add.do',
				type: 'POST',
				// data: 'id=' + $('#id').val() + '&name=' + $('#name').val() + '&gender=' + $(':radio[name="gender"]:checked').val() + '&address=' + $('#address').val(),
				data: $('#formMember').serialize(),  // 폼의 모든 요소를 줄줄이 &로 연결해서 보낸다.
				dataType: 'json',
				success: function(responseText){
					if(responseText.res == 1){
						alert('신규 회원이 등록되었습니다.');
						fnList();
						$('#id').val('');
						$('#name').val('');
						$(':radio[name="gender"]').prop('checked', false);
						$('#address').val('');
					}
				},
				error: function(jqXHR){
					alert(jqXHR.status);
					alert(jqXHR.responseText);
				}
			})
			
		})
	}
	function fnModify(){
		// 수정 버튼을 클릭하면 실행
		$('#btnModify').on('click', function(){
			$.ajax({
				url: '/AJAX/modify.do',
				data: $('#formMember').serialize(),
				type: 'post',
				dataType: 'json',
				success: function(responseText){
					if(responseText.res > 0){
						alert('회원 정보가 수정되었습니다.');
						fnList();
					} else {
						alert('회원 정보 수정이 실패했습니다.');
					}
				}
			})
		})
	}
	function fnRemove(){
		// 삭제 버튼을 클릭하면 실행
		$('#btnRemove').on('click', function(){
			$.ajax({
				url: '/AJAX/remove.do',
				data: 'no=' + $('#no').val(),
				type: 'get',
				dataType: 'json',
				success: function(responseText){
					if(responseText.res > 0){
						alert('회원 정보가 삭제되었습니다.');
						fnList();
						$('#id').val('').prop('readonly', false);
						$('#name').val('');
						$(':radio[name="gender"]').prop('checked', false);
						$('#address').val('');
					} else {
						alert('회원 정보 삭제를 실패했습니다.');
					}
				}
			})
		})
	}
	function fnInit(){
		// 초기화 버튼을 클릭하면 실행
		$('#btnInit').on('click', function(){
			$('#id').val('').prop('readonly', false);
			$('#name').val('');
			$(':radio[name="gender"]').prop('checked', false);
			$('#address').val('');
		})
	}

	</script>
</head>
<body>
	<h1>회원관리</h1>
	<div>
		<form id="formMember">
			<input type="hidden" name="no" id="no">
			<label for="id">
				아이디
				<input type="text" name="id" id="id">
			</label>
			<label for="name">
				이름
				<input type="text" name="name" id="name">
			</label>
			<label for="male">
				남자
				<input type="radio" name="gender" id="male" value="male">
			</label>
			<label for="female">
				여자
				<input type="radio" name="gender" id="female" value="female">
			</label>
			<label for="address">
				주소
				<input type="text" name="address" id="address">
			</label>
			<div>
				<input type="button" value="초기화" id="btnInit">
				<input type="button" value="등록" id="btnAdd">
				<input type="button" value="수정" id="btnModify">
				<input type="button" value="삭제" id="btnRemove">
			</div>
		</form>
	</div>
	<hr>
	<div>
		<table border="1">
			<caption>회원수 <span id="memberCount"></span>명</caption>
			<thead>
				<tr>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
			<tbody id="memberList">
			</tbody>
		</table>
	</div>
</body>
</html>