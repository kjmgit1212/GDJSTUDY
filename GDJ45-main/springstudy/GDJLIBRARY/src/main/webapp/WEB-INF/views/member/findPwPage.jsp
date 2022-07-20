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
		
		fnIdEmailAuth();
		fnFindPw();
		
	})
	// 4. 비밀번호 변경 창으로 이동
	function fnFindPw(){
		$('#f').on('submit', function(event){
			if($('#memberId').val() == '' || $('#memberEmail').val() == '') {
				alert('아이디와 이메일 모두 입력해주세요.');
				event.preventDefault();
				return false;
			} else if(authCodePass == false) {
				alert('이메일 인증을 받아주세요.');
				event.preventDefault();
				return false;
			}
			return true;
			
		})
	}
	
	// 3. 이메일 응답코드 확인
	var authCodePass = false;
	function fnVerifyAuthCode(authCode) {
		$('#btnVerifyAuthCode').on('click', function(){
			if($('#authCode').val() == authCode) {
				alert('인증되었습니다.');
				authCodePass = true;
			}else {
				alert('인증에 실패했습니다.');
				authCodePass = false;
			}
		})
	}
	
	// 2. 이메일 전송
	function fnIdEmailAuth(){
		$('#btnGetAuthCode').on('click', function(){
			fnIdEmailCheck().then(
				function(){
					$.ajax({
						url: '${contextPath}/member/sendAuthCode',
						type: 'get',
						data: 'memberEmail=' + $('#memberEmail').val(),
						dataType: 'json',
						success: function(obj){
							$('#emailMsg').text('');
							alert('인증코드를 발송했습니다. 이메일을 확인하세요.');
							fnVerifyAuthCode(obj.authCode);
						},
						error: function(jqXHR){
							alert('인증코드 발송이 실패했습니다.');
						}
						
					})
				}
			).catch(
				function(code){
					if(code == 1001){
						$('#emailMsg').text('이메일 형식이 올바르지 않습니다.').addClass('dont').removeClass('ok');
						
					} else if(code == 2001){
						$('#emailMsg').text('');
						alert('해당하는 회원이 존재하지 않습니다.');
						
					}
				}
				
			)
		})
	}
	
	// 1. 이메일/아이디 맞는 회원 조회
	function fnIdEmailCheck(){
		return new Promise(function(resolve, reject) { 
			if($('#memberId').val() == '' || $('#memberEmail').val() == ''){
				alert('아이디와 이메일 모두 입력해주세요.');
				return;
			}
			
			let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,}){1,2}$/;
			if(regEmail.test($('#memberEmail').val()) == false){
				reject(1001);
				return;
			}

			$.ajax({
				url: '${contextPath}/member/findPwCheckIdEmail',
				type: 'get', 
				data: 'memberId=' + $('#memberId').val() + '&memberEmail=' + $('#memberEmail').val(),
				dataType: 'json',
				success: function(obj){
					if(obj.res1 != null || obj.res2 != null){
						resolve();
					} else {
						reject(2001);
					}
				}
			})
		});
	}
	
</script>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	* {
	    font-family: 'Noto Sans KR', sans-serif;
	}
	
	.ok {
		color: #3253cc;
	}

	.dont {
		color: #666b7d;
	}
	


    .findPwPage_wrap {
        width: 500px;
        margin: 150px auto 0;
    }

    .findPwPage_wrap table {
        margin: 0 auto;
        
    }
    
    .findPwPage_wrap table td {
       height: 40px;
        
    }
    
    .findPwPage_wrap table tr:nth-of-type(3) > td {
        height: 15px;
        text-align: center;
        padding: 0 5px 5px 5px;
        font-size: 13px;
        
    }
    .findPwPage_wrap tr td:nth-of-type(1) {
        font-size: 13px;
        padding: 0 10px 0 5px;
        color: #4e4c4c;
    }
    .findPwPage_wrap tr td:nth-of-type(2) {
        display: block;
        height: 35px;
        margin-bottom: 3px;
        border: 1px solid #d7d7d7;
        position: relative;
        padding-right: 5px;
    }

    .findPwPage_wrap input[type="text"] {
        width: 180px;
        height: 10px;
        padding: 12px 10px 12px;
        font-size: 14px;
        font-weight: bold;
        color: #635f5f;
        outline-style: none;
        border: none;
    }
    .findPwPage  input::placeholder {
        color: #c3c3c3;
        font-size: 12px;
    }
    .findPwPage > h3 {
        display: block;
        text-align: center;
        margin-bottom: 30px;
        font-size: 15px;
        color: #4e4c4c;
    }

    .findPwPage tr input[type="button"] {
        margin-left: 5px;
        background-color: #f5e0c1;
        width: 90px;
        border-color: #f5d7ac;
        padding: 3px 5px;
        font-size: 12px;
        color: #4e4c4c;

    }

    .findPwPage table tr:last-of-type button {
        
        background-color: #f5e0c1;
        width: 150px;
        border-color: #f5d7ac;
        padding: 3px 5px;
        font-size: 12px;
        color: #4e4c4c;
       
    }

    .btn_findPwPage {
       display: block;
       text-align: center;
       margin: 10px auto 0;
       background-color: #f5e0c1;
       width: 150px;
       border-color: #f5d7ac;
       padding: 3px 5px;
       font-size: 12px;
       color: #4e4c4c;
    }
</style>

</head>
<body>
		<div class="findPwPage_wrap">
        <div class="findPwPage">
            <h3>비밀번호 찾기</h3>
            <form id="f" action="${contextPath}/member/findPw" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td><label for="memberId">아이디</label></td>
                            <td><input type="text" name="memberId" id="memberId"></td>
                        </tr>
                        <tr>
                            <td><label for="memberEmail">가입한 이메일</label></td>
                            <td>
                                <input type="text" name="memberEmail" id="memberEmail">
                                
                            </td>
                            
                            <td>
                                <input type="button" value="인증번호받기" id="btnGetAuthCode">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <span id="emailMsg"></span>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="authCode">이메일 인증</label></td>
                            <td><input type="text" name="authCode" id="authCode" placeholder="인증코드"></td>
                            <td><input type="button" value="인증하기" id="btnVerifyAuthCode"></td>
                        </tr>
                        
                    </tbody>
                </table>
                <button class="btn_findPwPage">비밀번호 변경으로 이동</button>
            </form>
        </div>
    </div>
			
	
</body>
</html>