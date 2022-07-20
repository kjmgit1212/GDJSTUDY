<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/css/student.css">
</head>
<body>
	
	<div class="wrap">
		<h1 class="title">학생등록화면</h1>
		<div class="insert_area">
			<form action="/BATCH/insert.do" method="post">
				<table>
					<thead>
						<tr>
							<td>이름</td>
							<td>국어</td>
							<td>영어</td>
							<td>수학</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" name="name" id="name" autofocus></td>
							<td><input type="text" name="kor" id="kor"></td>
							<td><input type="text" name="eng" id="eng"></td>
							<td><input type="text" name="mat" id="mat"></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<input type="submit" value="등록">
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