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
		fnDormantCancle();
		
	})
	function fnDormantCancle(){
		$('#f').on('submit', function(){
			location.href='${contextPath}/member/dormantCancel';
		})
	}
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	* {
	    
	    
	    font-family: 'Noto Sans KR', sans-serif;
	}
	
	.dormant_wrap {
	    width: 500px;
	    margin: 150px auto 0;
	}

	.dormant_wrap > h3 {
	    display: block;
	    text-align: center;
	    margin-bottom: 30px;
	    font-size: 15px;
	    color: #4e4c4c;
	}

      .dormant_wrap button {
		display: block;;
		text-align: center;
		background-color: #f5e0c1;
		width: 100px;
		border-color: #f5d7ac;
		padding: 3px 5px;
		font-size: 15px;
		margin: 0 auto;
		font-weight: 900;
		color: #4e4c4c;
}
</style>
</head>
<body>
	
	<div class="dormant_wrap">
        <h3>휴면 계정입니다. 해제하시려면 휴면해제 버튼을 클릭해주세요.</h3>
        <form id="f" action="${contextPath}/member/dormantCancel" method="post">
            <input type="hidden" name="memberId" value="${member.memberId}">
            <button>휴면해제</button>
        </form>
    </div>
</body>
</html>


