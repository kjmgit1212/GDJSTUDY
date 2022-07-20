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
		fnSearch();
	})
	
	// 함수
	function fnSearch(){
		$('#btnSearch').on('click', function(){
			$.ajax({
				url: '/OPENAPI/search.do',
				data: $('#formSearch').serialize(),
				type: 'get',
				dataType: 'json',
				success: function(responseText){
					$('#products').empty();
					$.each(responseText.items, function(i, product){
						var tr = '<tr>';
						tr += '<td><a href="' + product.link + '">' + product.title + '</a></td>'; 
						tr += '<td><a href="' + product.link + '"><img src="' + product.image + '" alt="' + product.title + '"></a></td>';
						tr += '<td>' + product.lprice + '</td>';
						tr += '<td>' + product.hprice + '</td>';
						tr += '<td>' + product.mallName + '</td>';
						$('#products').append(tr);  // $(tr).appendTo('#products');
					})
				},
				error: function(jqXHR){
					alert(jqXHR.responseText);
				}
			})
		})
	}
	
</script>
</head>
<body>

	<div>
		<form id="formSearch">
			<label for="query">
				검색
				<input type="text" name="query" id="query">	
			</label>
			<label for="display">
				검색결과건수
				<select name="display" id="display">
					<option value="10" selected>10</option>
					<option value="20">20</option>
					<option value="40">40</option>
					<option value="60">60</option>
					<option value="80">80</option>
					<option value="100">100</option>
				</select>
			</label>
			<label for="sim">유사도순<input type="radio" name="sort" id="sim" value="sim" checked></label>
			<label for="date">날짜순<input type="radio" name="sort" id="date" value="date"></label>
			<label for="asc">낮은가격순<input type="radio" name="sort" id="asc" value="asc"></label>
			<label for="dsc">높은가격순<input type="radio" name="sort" id="dsc" value="dsc"></label>
			<input type="button" value="검색" id="btnSearch">
		</form>
	</div>
	<hr>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>상품명</td>
					<td>썸네일</td>
					<td>최저가</td>
					<td>최고가</td>
					<td>판매처</td>
				</tr>
			</thead>
			<tbody id="products"></tbody>
		</table>
	</div>
	
</body>
</html>