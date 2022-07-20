<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Date today = new Date();
	String strToday = new SimpleDateFormat("yyyy-MM-dd a h:mm:ss").format(today);
%>

<script>
	alert('<%=strToday%>');
</script>