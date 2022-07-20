<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GDJLibrary</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function(){
		// 수정페이지
		$('#btnChangePage').on('click', function(){
			location.href='${contextPath}/admin/changeBookPage?bookNo=${book.bookNo}&value=${value}';
		})
		
		// 목록
		$('#btnList').on('click', function(){
			location.href='${contextPath}/admin/listBook?value=${value}';
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
	html, body{padding:0;margin:0;width:100%;height:100%;overflow:hidden;color:#4e4c4c;}
	 
	#wrap{position:relative;width:100%;height:100%}
	#container{position:absolute;top:10px;right:0;bottom:0;left:0;overflow-x:hidden;overflow-y:auto}
	#container .inner{width: 830px; margin:0 auto; padding:10px 0}
	 
	/* table */
	table.table01 {border-collapse:separate;border-spacing:0;line-height:1.5;border-top:1px solid #ccc;border-left:1px solid #ccc;margin:auto;}
	table.table01 th {padding: 10px;font-weight: bold;vertical-align: middle;text-align:center;border-right:1px solid #ccc;border-bottom:1px solid #ccc;border-top:1px solid #fff;border-left:1px solid #fff;background:#eee;}
	table.table01 td {padding:10px;vertical-align:middle;border-right:1px solid #ccc;border-bottom:1px solid #ccc;}
	.a {
		text-align: center;
	}
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
	            <h2>책 상세 보기</h2>
	            <form>        
	                <table width="100%" class="table01">
	                    <thead>
	                    	<tr class="a">
	                    		<td width="70px" class="a">이미지</td><td width="400px"><img alt="" src="${book.bookImage}" width="50px"></td>
	                    	</tr>
	                    	<tr class="a">
	                    		<td>책번호</td><td colspan="12">${book.bookNo}</td>
	                    	</tr>
	                    	<tr class="a">
	                    		<td>ISBN</td><td colspan="12">${book.bookIsbn}</td>
	                    	</tr>
	                    	<tr class="a">
	                    		<td>제목</td><td colspan="12">${book.bookTitle}</td>
	                    	</tr>
	                    	<tr class="a">
	                    		<td>작가</td><td colspan="12">${book.bookAuthor}</td>
	                    	</tr>
	                    	<tr class="a">
	                    		<td>출판사</td><td colspan="12">${book.bookPublisher}</td>
	                    	</tr>
	                    	<tr class="a">
	                    		<td>출판날짜</td><td colspan="16">${book.bookPubdateTime}</td>
	                    	</tr>
	                    	<tr>
	                    		<td class="a">설명</td><td colspan="16">${book.bookDescription}</td>
	                    	</tr>
	                    	
	                    	<tr class="a">
	                    		<td>분야</td><td colspan="12">${book.bookType}</td>
	                    	</tr>
	                  
	                    	
	                    </thead>
	                   
	                </table>        
	              
	            </form>
	            <div class="btn_right mt15">
		            <form id="f" action="${contextPath}/admin/saveDormantToMember?memberNo=${member.memberNo}" method="post">
						<input type="hidden" value="${member.memberNo}" name="memberNo">
						<input type="button" value="수정페이지" id="btnChangePage">
						<input type="button" value="책 목록" id="btnList">
			        </form>
	            </div>
	        </div>
	    </div>
	</div>
	
	<h1>책 상세 보기</h1>
	
	번호 ${book.bookNo}<br>
	ISBN ${book.bookIsbn}<br>
	제목 ${book.bookTitle}<br>
	작가 ${book.bookAuthor}<br>
	출판사 ${book.bookPublisher}<br>
	출판날짜 ${book.bookPubdateTime}<br>
	설명 ${book.bookDescription}<br>
	이미지주소 ${book.bookImage}<br>
	분야 ${book.bookType}<br>
	
	<input type="button" value="수정페이지" id="btnChangePage">
	<input type="button" value="목록" id="btnList">
</body>
</html>