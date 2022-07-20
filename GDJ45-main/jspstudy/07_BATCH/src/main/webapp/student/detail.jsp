<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/css/student.css"/>
</head>
<body>
	
	<div class="wrap">
		<h1 class="title">학생상세조회화면</h1>
		<div class="detail_area">
			<form action="/BATCH/modify.do" method="post">
				<table>
					<thead>
						<tr>
							<td>학번</td>
							<td>이름</td>
							<td>국어</td>
							<td>영어</td>
							<td>수학</td>
							<td>평균</td>
							<td>학점</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${student.stuNo}</td>
							<td><input type="text" name="name" id="name" value="${student.name}"></td>
							<td><input type="text" name="kor" id="kor" value="${student.kor}"></td>
							<td><input type="text" name="eng" id="eng" value="${student.eng}"></td>
							<td><input type="text" name="mat" id="mat" value="${student.mat}"></td>
							<td>${student.avg}</td>
							<td>${student.grade}</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7">
								<input type="hidden" name="stuNo" value="${student.stuNo}">
								<input type="submit" value="수정">
								<input type="reset" value="다시작성">
								<input type="button" value="목록" onclick="location.href='/BATCH/list.do'">
							</td>
						</tr>
					</tfoot>
				</table>
			</form>
		</div>
	</div>
	
</body>
</html>