<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	* {
		box-sizing: border-box;
	}
	.unlink, .link {
		display: inline-block;  /* 같은 줄에 둘 수 있고, width, height 등 크기 지정 속성을 지정할 수 있다. */
		padding: 10px;
		margin: 5px;
		border: 1px solid white;
		text-align: center;
		text-decoration: none;  /* 링크 밑줄 없애기 */
		color: gray;
	}
	.link:hover {
		border: 1px solid orange;
		color: limegreen;
	}
	table {
		border-collapse: collapse;
	}
	td:nth-of-type(1) { width: 80px; }
	td:nth-of-type(2) { width: 160px; }
	td:nth-of-type(3) { width: 240px; }
	td:nth-of-type(4) { width: 240px; }
	td:nth-of-type(5) { width: 120px; }
	td:nth-of-type(6) { width: 160px; }
	td:nth-of-type(7) { width: 160px; }
	td {
		padding: 5px;
		border-top: 1px solid silver;
		border-bottom: 1px solid silver;
		text-align: center;
	}
	tfoot td {
		border-left: 0;
		border-right: 0;
		border-bottom: 0;
	}
</style>
</head>
<body>

	<table>
		
		<thead>
			<tr>
				<td>순번</td>
				<td>사원번호</td>
				<td>사원명</td>
				<td>입사일자</td>
				<td>연봉</td>
				<td>부서번호</td>
				<td>부서이름</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="emp" varStatus="vs">
				<tr>
					<td>${beginNo - vs.index}</td>
					<td>${emp.employeeId}</td>
					<td>${emp.firstName}</td>
					<td>${emp.hireDate}</td>
					<td>${emp.salary}</td>
					<td>${emp.department.departmentId}</td>
					<td>${emp.department.departmentName}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					${paging}
				</td>
			</tr>
		</tfoot>
	</table>
	
</body>
</html>