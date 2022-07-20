<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jquery library -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<!-- jquery ui library -->
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js" integrity="sha256-6XMVI0zB8cRzfZjqKcD01PBsAy3FlDASrlC8SxCpInY=" crossorigin="anonymous"></script>

<!-- jquery ui theme css -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">

<script>
	
	// 페이지 로드 이벤트
	$(document).ready(function(){
		
		// datepicker
		$('#currentDate').datepicker({
			showOn: 'focus',
			dateFormat: 'yymmdd'
		});
		
		fnTour();
		
	})
	
	// 함수
	function fnTour(){
		$('#btn').on('click', function(){
			$.ajax({
				url: 'tourStnInfoSvc.do',
				data: $('#formTour').serialize(),
				type: 'get',
				dataType: 'json',
				success: function(responseText){
					var items = responseText.response.body.items.item;
					$('#items').empty();
					$.each(items, function(i, item){
						var tr = '<tr>';
						tr += '<td>' + item.courseName + '</td>';
						tr += '<td>' + item.spotName + '</td>';
						tr += '<td>' + item.thema + '</td>';
						tr += '<td>' + item.wd + '</td>';
						tr += '<td>' + item.ws + '</td>';
						var sky;
						switch(item.sky){
						case 1: sky = '맑음'; break;
						case 2: sky = '구름조금'; break;
						case 3: sky = '구름많음'; break;
						case 4: sky = '흐림'; break;
						case 5: sky = '비'; break;
						case 6: sky = '비눈'; break;
						case 7: sky = '눈비'; break;
						case 8: sky = '눈'; break;
						default: sky = '모름';
						}
						tr += '<td>' + sky + '</td>';
						tr += '<td>' + item.th3 + '</td>';
						tr += '<td>' + item.rhm + '%</td>';
						tr += '<td>' + item.pop + '%</td>';
						$('#items').append(tr);
					})
				}
			})
		});
	}
	
</script>
</head>
<body>

	<div>
		<form id="formTour">
			조회시작일시
			<input type="text" id="currentDate" name="currentDate">
			<br>
			코스 ID 
			<select name="courseId">
				<c:forEach var="i" begin="1" end="438">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select>
			<br><br>
			<input type="button" value="조회하기" id="btn">
		</form>
	</div>
	<hr>
	<div>
		<div id="courseAreaName"></div>
		<table border="1">
			<thead>
				<tr>
					<td>코스명</td>
					<td>관광지명</td>
					<td>테마</td>
					<td>풍향</td>
					<td>풍속</td>
					<td>날씨</td>
					<td>기온</td>
					<td>습도</td>
					<td>강수확률</td>
				</tr>
			</thead>
			<tbody id="items"></tbody>
		</table>
	</div>

</body>
</html>