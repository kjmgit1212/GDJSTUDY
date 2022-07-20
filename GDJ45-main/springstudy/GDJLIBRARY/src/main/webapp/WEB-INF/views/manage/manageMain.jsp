<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GDJLibrary</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
	
	$(function(){
		
	})

</script>
<style>
	
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
   
   * {
      
      font-family: 'Noto Sans KR', sans-serif;
   }
   .img{
  
    background: url("../resources/images/adminLibrary.jpg");                                                              
	background-repeat: no-repeat;
	background-size: cover;
	height: 86vh;
	margin-left: 100px;
	margin-right: 100px;
    }

   

   #divBottomMenu {
		background-color: beige;
		
		text-align: center;
	}
   
   #divBottomText {
		background-color: #efefb8;
   }
   
   .img .content{
     position: absolute;
     top:50%;
     left:50%;
     transform: translate(-50%, -50%);                                                                   
     font-size:4.7rem;
     color: white;
     z-index: 2;
     text-align: center;
   }  

   
   
   
</style>
</head>
<body>
	
	<jsp:include page="../layout/adminHeader.jsp"></jsp:include><br><br><br>
	 <div class="img">
        <div class="content">
            <h1>GDJ 도서관</h1>
            <h2>관리자 시스템</h2>
        </div>
 
    </div>
	
	
	<div id="divBottomText">
		<div style="margin-left: 100px;">
		주소(우)(07988) 서울 양천구 목동서로 113 (목동, 양천도서관)&nbsp;&nbsp;&nbsp;<i class="fa-solid fa-phone"></i> 02-2062-3900(TEL) &nbsp;&nbsp;&nbsp;<i class="fa-solid fa-fax"></i> 02-2062-3919(FAX)<br>
		Copyright © 2019 Yangcheon Public Library. All Rights Reserved.
		</div>
	</div>
																
</body>
</html>