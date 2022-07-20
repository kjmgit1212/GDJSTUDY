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
		fnChangePw();
		fnPwCheck();
		fnPwConfirm();
	})
	
	//6. 비밀번호 입력 확인
	function fnChangePw(){
		$('#f').on('submit', function(event){
			if(pwPass == false || rePwPass == false){
				alert('비밀번호를 확인해주세요');
				event.preventDefault();
				return false;
			}
		})
	}
	
	// 5. 비밀번호 확인
	let rePwPass = false;
	function fnPwConfirm(){
		$('#memberPwConfirm').on('keyup', function(){
			if($('#pwConfirm').val() != '' && $('#memberPwConfirm').val() != $('#memberPw').val()){
				$('#pwConfirmMsg').text('비밀번호가 일치하지 않습니다.').addClass('dont').removeClass('ok');
				rePwPass = false;
			} else {
				$('#pwConfirmMsg').text('');
				rePwPass = true;
			}
		})
	}
	
	// 4. 비밀번호 정규화
	let pwPass = false;
	function fnPwCheck(){
		$('#memberPw').on('keyup', function(){
			let regPw = /^[a-zA-Z0-9!@#$%^&*]{8,20}$/;
			let pwValid = /[a-z]/.test($('#memberPw').val()) +  // 소문자 포함이면 1
		  				  /[A-Z]/.test($('#memberPw').val()) +  // 대문자 포함이면 1
		   				  /[0-9]/.test($('#memberPw').val()) +  // 숫자 포함이면 1
		  				  /[!@#$%^&*]/.test($('#memberPw').val());  // 특수문자 포함이면 1
			if(regPw.test($('#memberPw').val()) && pwValid >= 3){
			   $('#pwMsg').text('사용 가능한 비밀번호 입니다.').addClass('ok').removeClass('dont');
			   pwPass = true;

			} else {
				$('#pwMsg').text('8~20자 영문 대 소문자, 숫자, 특수문자 중 3개 이상을 사용하세요.').addClass('dont').removeClass('ok');
				pwPass = false;
			}
		})
	}
	
	//취소하기
	function fnCancel(){
		location.href='${contextPath}/member/loginPage';
	}
	
</script>
<style>
	
	.ok {
		color: #3253cc;
	}

	.dont {
		color: #666b7d;
	}
	
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	* {
	    
	    
	    font-family: 'Noto Sans KR', sans-serif;
	}
	
	.pwChange_wrap {
	    width: 500px;
	    margin: 150px auto 0;
	}
	
	.pwChange_wrap table {
	    margin: 0 auto;
	    width : 400px;
	    
	}
	
	
	
	.pwChange > h3 {
	    display: block;
	    text-align: center;
	    margin-bottom: 30px;
	    font-size: 15px;
	    color: #4e4c4c;
	}
	
	.pwChange_wrap tr td:nth-of-type(1) {
	    font-size: 13px;
	    padding: 0 10px 0 5px;
	    width: 120px;
	    color: #4e4c4c;
	}
	
	
	.pwChange_wrap tr td:nth-of-type(2) {
	    display: block;
	    height: 35px;
	    margin-bottom: 3px;
	    border: 1px solid #d7d7d7;
	    position: relative;
	    padding-right: 5px;
	}
	
	.pwChange_wrap input[type="password"] {
	    width: 180px;
	    height: 10px;
	    padding: 12px 10px 12px;
	    font-size: 14px;
	    font-weight: bold;
	    color: #635f5f;
	    outline-style: none;
	    border: none;
	}
	

	
	.msg td span{
	    font-size: 12px;
	    padding: 0 10px 0 3px;
	    
	}
    
    .pwChange_wrap input[type="button"], .btnPwChange {
        font-size: 13px;
        border: 1px solid #f5e0c1;
        background-color: #f5e0c1;
        padding: 2px 5px;
        cursor: pointer;
        
    }
    .botton {
        margin-top: 20px;
        text-align: center;
        
    }

    .botton input[type="button"] {
        font-size: 13px;
        border: 1px solid #f5e0c1;
        background-color: #f5e0c1;
        padding: 2px 5px;
        cursor: pointer;
    }
</style>

</head>
<body>

	<div class="pwChange_wrap">
        <div class="pwChange">
            <h3>비밀번호 변경</h3>
            <form id="f" action="${contextPath}/member/changePw" method="post">
                <input type="hidden" name="memberId" id="memberId" value="${memberId}">
                <table>
                    <tbody>
                        <tr>
                            <td><label for="memberPw">변경할 비밀번호</label></td>
                            <td><input type="password" name="memberPw" id="memberPw"></td>
                            
                        </tr>
                        <tr class="msg">
                            <td colspan="2"><span id="pwMsg"></span></td>
                        </tr>
                        <tr>
                            <td><label for="memberPwConfirm">비밀번호 확인</label></td>
                            <td>
                                <input type="password" id="memberPwConfirm">
                                
                            </td>
                        </tr>
                        <tr class="msg">
                            <td colspan="2"><span id="pwConfirmMsg"></span></td>
                        </tr>
                    </tbody>
                </table>
                <div class="botton">
                    <button class="btnPwChange">변경하기</button>
                    <input type="button" value="취소하기" onclick="fnCancel();">
                </div>
                
            </form>
        </div>
    </div>
</body>
</html>