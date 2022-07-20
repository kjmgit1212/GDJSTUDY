<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	// 페이지 로드 이벤트
	$(function(){
		fnList();
		fnDetail();
		fnModify();
	})
	// 함수
	function fnList(){
		$.ajax({
			url: 'list.json',
			type: 'get',
			dataType: 'json',
			success: function(responseText){  // responseText : {"count": 7, "members": [{}, {}, {}, {}, {}, {}, {}]}
				$('#count').text(responseText.count);
				$('#members').empty();
				$.each(responseText.members, function(i, member){
					var tr = '<tr>';
					tr += '<td>' + member.no + '</td>';
					tr += '<td><span class="id">' + member.id + '</span></td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + member.gender + '</td>';
					tr += '<td>' + member.address + '</td></tr>';
					$('#members').append(tr);
				})
			}
		})
	}
	function fnDetail(){
		$('body').on('click', '.id', function(){
			var no = $(this).parent().prev().text();
			$.ajax({
				url: 'detail.json',
				data: 'no=' + no,  // 요청 parameter
				type: 'get',
				dataType: 'json',
				success: function(responseText){  // responseText : {}
					$('#no').val(responseText.no).prop('readonly', true);
					$('#id').val(responseText.id).prop('readonly', true);
					$('#name').val(responseText.name);
					$('#address').val(responseText.address);
				}
			})
		})
	}
	function fnModify(){
		$('#btn').on('click', function(){
			$.ajax({
				url: 'modify.json',
				data: $('#f').serialize(),
				type: 'post',
				dataType: 'json',
				success: function(responseText){  // {"res": 1}, {"res": 0}
					if(responseText.res == 1){
						alert('수정 성공');
						fnList();  // 목록 갱신
					} else {
						alert('수정 실패');
					}
				}
			})
		})
	}
</script>
</head>
<body>
	<form id="f">
		<input type="text" id="no" name="no" placeholder="회원번호"><br>
		<input type="text" id="id" name="id" placeholder="아이디"><br>
		<input type="text" id="name" name="name" placeholder="이름"><br>
		<input type="text" id="address" name="address" placeholder="주소"><br>
		<input type="button" value="수정" id="btn">
	</form>
	<hr>
	<table border="1">
		<caption>회원수 <span id="count"></span>명</caption>
		<thead>
			<tr>
				<td>회원번호</td>
				<td>아이디</td>
				<td>이름</td>
				<td>성별</td>
				<td>주소</td>
			</tr>
		</thead>
		<tbody id="members"></tbody>
	</table>
</body>
</html>