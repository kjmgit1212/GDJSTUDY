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
<script type="text/javascript">
	$(function(){
		
		
		// 목록
		$('#btnList').on('click', function(){
			location.href='${contextPath}/admin/listSignOutMember?value=${value}';
		})
		
		
	})
	
	
	
	
</script>
<style>
	
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
   
   * {
      color: #4e4c4c;
      font-family: 'Noto Sans KR', sans-serif;
   }
	
	
	/* layout */
	html, body{padding:0;margin:0;width:100%;height:100%;overflow:hidden;}
	 
	#wrap{position:relative;width:100%;height:100%}
	#container{position:absolute;top:10px;right:0;bottom:38px;left:0;overflow-x:hidden;overflow-y:auto}
	#container .inner{width:680px; margin:0 auto; padding:10px 0}
	 
	/* table */
	table.table01 {border-collapse:separate;border-spacing:0;text-align:center;line-height:1.5;border-top:1px solid #ccc;border-left:1px solid #ccc;margin:auto;}
	table.table01 th {padding: 10px;font-weight: bold;vertical-align: middle;text-align:center;border-right:1px solid #ccc;border-bottom:1px solid #ccc;border-top:1px solid #fff;border-left:1px solid #fff;background:#eee;}
	table.table01 td {padding:10px;vertical-align:middle;text-align:center;border-right:1px solid #ccc;border-bottom:1px solid #ccc;}
	 
	table.table02 caption{height:45px;line-height:45px;color:#333;padding-left:35px;border-top:3px solid #464646;border-bottom:1px solid #c9c9c9;background:#ececec}
	table.table02 caption.center{padding-top:6px;height:39px;line-height:130%;text-align:center;color:#333;padding-left:0;border-top:3px solid #464646;border-bottom:1px solid #c9c9c9;background:#ececec}
	table.table02 tbody th{padding:10px;vertical-align:middle;font-family:'malgunbd';color:#333;border-right:1px solid #c9c9c9;border-bottom:1px solid #c9c9c9;background:#ececec}
	table.table02 tbody td{padding:10px;vertical-align:middle;padding-left:15px;background:#fafafa;border-bottom:1px solid #c9c9c9}
	
	
	 /* button_align */
	.btn_left{clear:both;text-align:left}
	.btn_right{clear:both;text-align:right}
	.btn_center{clear:both;text-align:center}
	
	 /* text_color_style */
	.t_blue{color:#004fa8}
	.t_red{color:#f55500}
	 
	/* margin & padding */
	
	.mt15{margin-top:15px}
</style>

</head>
<body>
	
	<div id="wrap">
	    <div id="container">
	        <div class="inner">    
	            <h2>탈퇴 회원 상세 보기</h2>
	            <form id="f" action="${contextPath}/admin/saveDormantMember?memberNo=${member.memberNo}" method="post">        
	                <table width="100%" class="table01">
	                    <thead>
	                    	<tr>
	                    		<td width="80px">회원번호</td><td colspan="12">${member.memberNo}</td>
	                    	</tr>
	                    	<tr>
	                    		<td>아이디</td><td colspan="12">${member.memberId}</td>
	                    	</tr>
	                    	<tr>
	                    		<td>이름</td><td colspan="12">${member.memberName}</td>
	                    	</tr>
	                    	<tr>
	                    		<td>전화번호</td><td colspan="12">${member.memberPhone}</td>
	                    	</tr>
	                    	<tr>
	                    		<td>이메일</td><td colspan="12">${member.memberEmail}</td>
	                    	</tr>
	                    	<tr>
	                    		<td>우편번호</td><td colspan="12">${member.memberPostcode}</td>
	                    	</tr>
	                    	<tr>
	                    		<td>도로명주소</td><td colspan="12">${member.memberRoadAddress}</td>
	                    	</tr>
	                    	<tr>
	                    		<td>상세주소</td><td colspan="12">${member.memberDetailAddress}</td>
	                    	</tr>
	                    	<tr>
	                    		<td>탈퇴일</td><td colspan="12">${member.signOut}</td>
	                    	</tr>
	                  
	                    	
	                    </thead>
	                   
	                </table>        
	              
	            </form>
	            <div class="btn_right mt15">
						<input type="button" value="탈퇴 회원 목록" id="btnList">
	            </div>
	        </div>
	    </div>
	</div>
	
	
</body>
</html>