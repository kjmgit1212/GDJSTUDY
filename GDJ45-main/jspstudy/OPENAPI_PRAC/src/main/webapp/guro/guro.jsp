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
	$(document).ready(function(){
		fnGuroPointFocInfoSvc();
	})
	
	// 함수
	function fnGuroPointFocInfoSvc(){
		$('#btn').on('click', function(){
			$.ajax({
				url: '/OPENAPI/guroPointFocInfoSvc.do',
				type: 'get',
				dataType: 'xml',
				success: function(responseText){
					$('#focInfo').empty();
					$.each($(responseText).find('item'), function(i, item){
						var tr = '<tr>';
						tr += '<td>' + $(item).find('localCode').text() + '</td>';
						tr += '<td>' + $(item).find('baseDate').text() + '</td>';
						tr += '<td>' + $(item).find('iconNo').text() + '</td>';
						tr += '<td>' + $(item).find('temp').text() + '</td>';
						tr += '<td>' + $(item).find('humi').text() + '</td>';
						tr += '<td>' + $(item).find('rainProb').text() + '</td>';
						tr += '<td>' + $(item).find('rain').text() + '</td>';
						tr += '<td>' + $(item).find('snow').text() + '</td>';
						tr += '<td>' + $(item).find('windDir').text() + '</td>';
						tr += '<td>' + $(item).find('windSpeed').text() + '</td>';
						$('#focInfo').append(tr);
					})
				}
			})
		})
	}
	
</script>
</head>
<body>

	<input type="button" value="구로구포인트날씨갱신" id="btn">
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>지역코드</td>
				<td>발표일시</td>
				<td>날씨코드</td>
				<td>기온</td>
				<td>상대습도</td>
				<td>강수확률</td>
				<td>강수량</td>
				<td>적설량</td>
				<td>풍향코드</td>
				<td>풍속</td>
			</tr>
		</thead>
		<tbody id="focInfo"></tbody>
	</table>

</body>
</html>






