<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.0.js"></script>
<script>

	// 페이지 로드 이벤트
	$(document).ready(function(){
		$('#btn1').on('click', fnAjax1);
		$('#btn2').on('click', fnAjax2);
		$('#btn3').on('click', fnAjax3);
	})
	
	// 함수
	function fnAjax1(){
		$.ajax({
			url: '${contextPath}/product/list1',
			type: 'get',
			dataType: 'json',
			success: function(products){  // products -> [{no,name,maker,price}, {}, {}]
				$('#products').empty();
				$.each(products, function(i, product){
					var tr = '<tr>';
					tr += '<td>' + product.no + '</td>';
					tr += '<td>' + product.name + '</td>';
					tr += '<td>' + product.maker + '</td>';
					tr += '<td>' + product.price + '</td>';
					tr += '</tr>';
					$('#products').append(tr);
				})
			},
			error: function(jqXHR){
				console.log(jqXHR.status);
				console.log(jqXHR.responseText);
			}
		})
	}  // fnAjax1
	
	function fnAjax2(){
		$.ajax({
			url: '${contextPath}/product/list2',
			type: 'get',
			dataType: 'json',
			success: function(products){  // products -> [{no,name,maker,price}, {}, {}]
				$('#products').empty();
				$.each(products, function(i, product){
					$('<tr>')
					.append($('<td>').text(product.no))
					.append($('<td>').text(product.name))
					.append($('<td>').text(product.maker))
					.append($('<td>').text(product.price))
					.appendTo($('#products'));
				})
			},
			error: function(jqXHR){
				console.log(jqXHR.status);
				console.log(jqXHR.responseText);
			}
		})
	}  // fnAjax2

	function fnAjax3(){
		$.ajax({
			url: '${contextPath}/product/list3',
			type: 'get',
			dataType: 'json',
			success: function(result){  // result -> {"products": [{}, {}, {}]}
				$('#products').empty();
				$.each(result.products, function(i, product){
					$('<tr>')
					.append($('<td>').text(product.no))
					.append($('<td>').text(product.name))
					.append($('<td>').text(product.maker))
					.append($('<td>').text(product.price))
					.appendTo($('#products'));
				})
			},
			error: function(jqXHR){
				console.log(jqXHR.status);
				console.log(jqXHR.responseText);
			}
		})
	}  // fnAjax3
	
</script>
</head>
<body>

	<input type="button" value="목록1" id="btn1">
	<input type="button" value="목록2" id="btn2">
	<input type="button" value="목록3" id="btn3">
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>제품번호</td>
				<td>제품명</td>
				<td>제조사</td>
				<td>가격</td>
			</tr>
		</thead>
		<tbody id="products">
			
		</tbody>
	</table>

</body>
</html>