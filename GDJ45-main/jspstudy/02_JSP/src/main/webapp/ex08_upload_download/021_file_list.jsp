<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// 업로드 경로
		String realPath = application.getRealPath("storage");
		File dir = new File(realPath);
		
		// 저장된 파일 목록
		File[] files = dir.listFiles();
	%>
	
	<% for(int i = 0; i < files.length; i++) { %>
		<div>
			<a href="/JSP/ex08_upload_download/022_download.jsp?filename=<%=files[i].getName()%>"><%=files[i].getName()%></a>
		</div>
	<% } %>

</body>
</html>