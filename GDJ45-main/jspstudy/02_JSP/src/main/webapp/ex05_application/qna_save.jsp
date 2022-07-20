<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String created = request.getParameter("created");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// 거쳐서 도착한 경우 X-Forwarded-For 헤더 값에 원래 ip가 있다.
	String ip = request.getHeader("X-Forwarded-For");
	if(ip == null) {
		ip = request.getRemoteAddr();
	}
	
	// 파일명 작성
	// IP주소_작성자_작성일자.txt
	
	/* IPv6 */
	// 0:0:0:0:0:0:0:1
	// 0_0_0_0_0_0_0_1  replaceAll(":", "_")

	/* IPv4 */
	// 127.0.0.1
	// 127_0_0_1  replaceAll("\\.", "_")

	String filename = ip.replaceAll(":", "_") + "_" + writer + "_" + created + ".txt";
	
	// 저장 경로
	// 서버 내 저장 경로는 우리가 알고 있는 경로와 다르다.
	// 서버 내 경로를 알기 위해서 getRealPath() 메소드를 사용한다.
	
	String realPath = application.getRealPath("ex05_application");
	
	File dir = new File(realPath);
	if(dir.exists() == false) {
		dir.mkdirs();
	}
	
	out.println(realPath);
	
	// 파일 생성
	File file = new File(dir, filename);
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	bw.write("작성일자 : " + created + "\n");
	bw.write("작성IP : " + ip + "\n");
	bw.write("작성자 : " + writer + "\n");
	bw.write("제목 : " + title + "\n");
	bw.write("내용\n");
	bw.write(content);
	
	if(bw != null)
		bw.close();	
%>

<script>
	var isExist = <%=file.exists()%>;
	if(isExist) {
		alert('<%=filename%> 파일이 생성되었습니다.');
	} else {
		alert('파일이 생성되지 않았습니다.');
	}
	history.back();
</script>
