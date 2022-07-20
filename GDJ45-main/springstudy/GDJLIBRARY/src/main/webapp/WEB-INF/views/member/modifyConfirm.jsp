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
		fnModifyPwCheck();
	})
	
	function fnModifyPwCheck() {
		$('#f').on('submit', function(){
			if($('#memberPw').val() == '') {
				alert('비밀번호를 입력해주세요.');
				return;
			}
		})

	}

</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	* {
	    font-family: 'Noto Sans KR', sans-serif;
	}

.modifyConfirm {
    width: 500px;
    margin: 230px auto 0;
}

.modifyConfirm table {
    margin: 0 auto;
    
}

.modifyConfirm > h3 {
    display: block;
    text-align: center;
    margin-bottom: 30px;
    font-size: 15px;
    color: #4e4c4c;
}
.modifyConfirm_wrap tr td:nth-of-type(1) {
    font-size: 13px;
    padding: 0 10px 0 5px;
    width: 120px;
	text-align: center;
	color: #4e4c4c;
}

.modifyConfirm_wrap tr td:nth-of-type(2) {
    display: block;
    height: 35px;
    margin-bottom: 3px;
    border: 1px solid #d7d7d7;
    position: relative;
    padding-right: 5px;
}

.modifyConfirm_wrap input[type="password"] {
    width: 180px;
    height: 10px;
    padding: 12px 10px 12px;
    font-size: 14px;
    font-weight: bold;
    color: #635f5f;
    outline-style: none;
    border: none;
}

.modifyConfirm_wrap button {
	display: inline-block;;
	text-align: center;
	background-color: #f5e0c1;
	width: 80px;
	border-color: #f5d7ac;
	padding: 3px 5px;
	font-size: 12px;
	padding: 5px 18px;
	margin: 0 5px 5px 10px;
	cursor: pointer;
}
</style>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<div class="modifyConfirm_wrap">
        <div class="modifyConfirm">
            <h3>회원 정보 수정을 위한 인증 절차</h3>

			<form id="f" action="${contextPath}/member/modifyPwCheck" method="post">
				<input type="hidden" name="memberId" id="memberId" value="${loginMember.memberId}">
				<table>
					<tbody>
						<tr>
							<td colspan="2"><label for="memberPw">비밀번호</label></td>
							<td><input type="password" name="memberPw" id="memberPw"></td>
							<td><button>확인</button></td>
						</tr>	
					</tbody>
				</table>
				
			</form>

        </div>
    </div>
</body>
</html>