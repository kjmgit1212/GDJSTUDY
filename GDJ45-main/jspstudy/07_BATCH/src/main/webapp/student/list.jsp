<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/css/student.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>

	$(document).ready(function(){
	
		// 상세 버튼의 이전 형제로 학번을 저장해 두고 사용한다.
		$('.btn_detail').click(function(){
		    location.href='/BATCH/detail.do?stuNo=' + $(this).prev().val();
		});
		
		// 삭제 버튼의 data-stuno 속성에 학번을 저장해 두고 사용한다.
		$('.btn_delete').click(function(){
			if(confirm('삭제할까요?')) {
				location.href='/BATCH/remove.do?stuNo=' + $(this).data('stuno');
			}
		});
		
		// 점수 조회
		$('.btn_search').click(function(){
			location.href='/BATCH/search.do?begin=' + $('#begin').val() + '&end=' + $('#end').val();
		})
		
	});
	
</script>
</head>
<body>

	<div class="wrap">
		<h1 class="title">학생전체목록</h1>
		<div class="btn_area">
			<input type="button" value="학생등록하기" class="btn_insert" onclick="location.href='/BATCH/insertPage.do'">
		</div>
		<div class="search_area">
			<input type="text" name="begin" id="begin" size="4" placeholder="최저점수">
			~
			<input type="text" name="end" id="end" size="4" placeholder="최고점수">
			<input type="button" value="조회" class="btn_search">
		</div>
		<div class="list_area">
			<table>
				<caption>학생 수 : ${totalCount}명</caption>
				<thead>
					<tr>
						<td>학번</td>
						<td>성명</td>
						<td>국어</td>
						<td>영어</td>
						<td>수학</td>
						<td>평균</td>
						<td>학점</td>
						<td>버튼</td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${totalCount eq 0}">
						<tr>
							<td colspan="8">등록된 학생이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${totalCount ne 0}">
						<c:forEach items="${list}" var="student">
							<tr>
								<td>${student.stuNo}</td>
								<td>${student.name}</td>
								<td>${student.kor}</td>
								<td>${student.eng}</td>
								<td>${student.mat}</td>
								<td><fmt:formatNumber value="${student.avg}" pattern="0.00" /></td>
								<td>${student.grade}</td>
								<td>
									<!-- 상세 버튼은 이전 형제에 학번을 저장해 두고 사용 -->
									<input type="hidden" name="stuNo" value="${student.stuNo}">
									<input type="button" value="상세" class="btn_detail">
									
									<!-- 삭제 버튼은 data 속성에 학번을 저장해 두고 사용 -->
									<input type="button" value="삭제" class="btn_delete" data-stuno="${student.stuNo}">
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">전체 평균</td>
						<td><fmt:formatNumber value="${totalAverage}" pattern="0.00" /></td>
						<td></td>
						<td></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>

</body>
</html>