<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="add.do" method="post">
		<table border="1">
			<tbody>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" rows="10" cols="30"></textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<button>등록</button>
						<input type="button" value="목록" onclick="location.href='list.do'">
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	
</body>
</html>