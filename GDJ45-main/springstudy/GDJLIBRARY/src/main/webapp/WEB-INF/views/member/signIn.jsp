<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GDJLibrary</title>

<script src="../resources/js/jquery-3.6.0.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>

	/* 페이지 로드 이벤트 */
	$(function(){
		fnIdCheck();
		fnPwCheck();
		fnPwConfirm();
		fnPhoneCheck();
		fnEmailAuth();
		fnSignIn();
	})
	
	/* 함수 */
	// 9. 회원가입
	function fnSignIn(){
		$('#f').on('submit', function(event){
			if(idPass == false){
				alert('아이디를 확인해주세요');
				event.preventDefault();
				return false;
			}
			else if(pwPass == false || rePwPass == false){
				alert('비밀번호를 확인해주세요');
				event.preventDefault();
				return false;
			} 
			else if($('#memberName').val() == ''){
				alert('이름을 확인해주세요.');
				event.preventDefault();
				return false;
			}
			else if(addressPass == false || $('#memberDetailAddress').val() == ''){
				alert('주소를 확인해주세요.');
				event.preventDefault();
				return false;
			}
			else if(PhonePass == false){
				alert('휴대전화를 확인해주세요.');
				event.preventDefault();
				return false;
			}
			else if(authCodePass == false){
				alert('이메일 인증을 받아주세요.');
				event.preventDefault();
				return false;
			}
			return true;
		})
	}
	
	// 8. 이메일 응답코드 확인
	let authCodePass = false;
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
	
	// 7. 이메일 전송
	function fnEmailAuth(){
		$('#btnGetAuthCode').on('click', function(){
			fnEmailCheck().then(
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
					if(code == 1000){
						$('#emailMsg').text('이메일 형식이 올바르지 않습니다.').addClass('dont').removeClass('ok');
						
					} else if(code == 2000){
						$('#emailMsg').text('이미 사용 중인 이메일입니다.').addClass('dont').removeClass('ok');
						
					}
				}
				
			)
		})
	}
	
	// 6. 이메일 확인
	function fnEmailCheck(){
		return new Promise(function(resolve, reject) { 
			
			let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,}){1,2}$/;
			if(regEmail.test($('#memberEmail').val()) == false){
				reject(1000);
				return;
			}
			
			$.ajax({
				url: '${contextPath}/member/emailCheck',
				type: 'get', 
				data: 'memberEmail=' + $('#memberEmail').val(),
				dataType: 'json',
				success: function(obj){
					if(obj.resMember == null && obj.resDormantMember == null){
						resolve();
					} else {
						reject(2000);
					}
				}
			})
		});
	}
	
	// 5. 휴대전화 확인
	let PhonePass = false;
	function fnPhoneCheck(){
		$('#memberPhone').on('keyup', function(){
			let regPhone = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
			if(regPhone.test($('#memberPhone').val()) == false) {
				$('#phoneMsg').text('휴대전화 형식 예) 010-1234-5678');
				PhonePass = false;
			} else {
				$('#phoneMsg').text('');
				PhonePass = true;
			}
		})
	}
	
	// 4. 주소 api
	let addressPass = false;
	function fnPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                
                var roadAddr = data.roadAddress; 

                $('#memberPostcode').val(data.zonecode).prop('readonly', true);
                $('#memberRoadAddress').val(roadAddr).prop('readonly', true);
                
                if($('#memberPostcode').val() != '' && $('#memberRoadAddress').val() != ''){
                	addressPass = true;
                } else {
                	addressPass = false;
                }
            }
        }).open();
    }
	
	// 3. 비밀번호 확인
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
	
	// 2. 비밀번호 정규식
	let pwPass = false;
	function fnPwCheck(){
		$('#memberPw').on('keyup', function(){
			if($('#memberPwConfirm').val() != $('#memberPw').val()){
				$('#pwConfirmMsg').text('비밀번호가 일치하지 않습니다.').addClass('dont').removeClass('ok');
				rePwPass = false;
			} else {
				$('#pwConfirmMsg').text('');
				rePwPass = true;
			}
			
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

	// 1. 아이디 정규식 & 중복체크
	let idPass = false;
	function fnIdCheck(){
		$('#memberId').on('keyup', function(){
			// 정규식 체크하기
			let regId = /^[a-z0-9-_]{5,}$/;  // 아이디 : 소문자, 숫자, 특수문자(-,_)가 들어가는 5글자 이상
			if(regId.test($('#memberId').val())==false){
				$('#idMsg').text('5자의 이상의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.').addClass('dont').removeClass('ok');
				idPass = false;
				return;
			}
			// 아이디 중복 체크
			$.ajax({
				url: '${contextPath}/member/idCheck',
				type: 'get',
				data: 'memberId=' + $('#memberId').val(),
				dataType: 'json',
				success: function(obj){
					if(obj.resMember == null && obj.resDormantMember == null){
						$('#idMsg').text('사용가능한 아이디 입니다.').addClass('ok').removeClass('dont');
						idPass = true;
					} else {
						$('#idMsg').text('이미 사용중인 아이디입니다.').addClass('dont').removeClass('ok');
						idPass = false;
					}
				},
				error: function(jqXHR){
					$('#idMsg').text(jqXHR.responseText).addClass('dont').removeClass('ok');
					idPass = false;
				}
			})
		})
	}
	
	//취소하기
	function fnCancel(){
		location.href='${contextPath}';
	}
	
</script>
<style>
		@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	* {
	    font-family: 'Noto Sans KR', sans-serif;
	}
		.loginForm {
		            width: 800px;
		            margin: 40px auto 0;
		            padding: 50px 50px 51px;
            
            
        }

        .loginForm > h3 {
            text-align: center;
            margin-bottom: 50px;
            color: #4e4c4c;
           
        }
        .loginForm table {
            border-collapse: collapse;
        }
        
        .loginForm table td {
            width: 650px;
            border-top: 1px solid #f2f4f5;
            border-bottom: 1px solid #f2f4f5;
            height: 45px;
        }
        
        .loginForm table td:first-of-type {
            border-right: 1px solid #f2f4f5;
            width: 130px;
        }

        .loginForm label, .loginForm tr:nth-of-type(5) td:first-of-type {
            font-size: 15px;
            padding-left: 10px;
            color:#4a4747;
        }

        .loginForm input[type="text"], .loginForm input[type="password"] {
            margin-left: 10px;
            width: 150px;
            height: 10px;
            padding: 8px 10px 8px 10px;
            font-size: 14px;
            
            color: #635f5f;
            outline-style: none;
            border: 1px solid #d7d7d7
           
        }
        .loginForm tr:nth-of-type(6) td, .loginForm tr:nth-of-type(7) td {
            border-right: 0;
        }
        .loginForm tr:nth-of-type(6) td input[type="text"], .loginForm tr:nth-of-type(7) td input[type="text"] {
            width: 300px;
        }
        .loginForm tr:nth-of-type(5) td input[type="text"] {
            width: 50px;
        }

        .loginForm td span {
            color: #635f5f;
            font-size: 13px;
            padding-left: 5px;
        }

        .loginForm td input::placeholder {
            color: #c6c6c6;
            font-size: 12px;
        }

        .btn_signUp {
            width: 110px;
            display: block;
            height: 40px;
            background-color: #4390de;
            border: #4390de;
            font-size: 15px;
            font-weight: bold;
            color: #FFF;
            letter-spacing: -0.5px;
            text-align: center;
            line-height: 35px;
            margin: 0 auto;
            margin-top: 40px;
        }

        .loginForm input[type="button"] {
            font-size: 13px;
            border: 1px solid #c7c5c5;
            background-color: #e5e5e5;
            padding: 2px 5px;
            cursor: pointer;
            color:#4a4747;
            
        }

.ok {
	color: #3253cc;
}

.dont {
	color: #666b7d;
}
</style>
</head>
<body>
	<div class="loginForm">
		<h3>회원 가입</h3>

		<form id="f" action="${contextPath}/member/signIn" method="post">

			<input type="hidden" name="promotion" value="${promotion}">

			<table>
				<tbody>
					<tr>
						<td><label for="memberId">아이디</label></td>
						<td><input type="text" name="memberId" id="memberId"><span id="idMsg"></span></td>
					</tr>
					<tr>
						<td><label for="memberPw">비밀번호</label></td>
						<td><input type="password" name="memberPw" id="memberPw"><span id="pwMsg"></span></td>
					</tr>
					<tr>
						<td><label for="memberPwConfirm">비밀번호 확인</label></td>
						<td><input type="password" id="memberPwConfirm"><span id="pwConfirmMsg"></span></td>
					</tr>
					<tr>
						<td><label for="memberName">이름</label></td>
						<td><input type="text" name="memberName" id="memberName"></td>
					</tr>
					<tr>
						<td rowspan="3">주소</td>
						<td><input type="text" name="memberPostcode" id="memberPostcode" readonly="readonly"> 
						<input type="button" onclick="fnPostcode()" value="우편번호 찾기"></td>
					</tr>
					<tr>
						<td><input type="text" name="memberRoadAddress" id="memberRoadAddress" readonly="readonly"><span>도로명 주소</span></td>
					</tr>
					<tr>

						<td><input type="text" name="memberDetailAddress" id="memberDetailAddress"><span>상세 주소</span></td>
					</tr>
					<tr>
						<td><label for="memberPhone">휴대전화</label></td>
						<td><input type="text" name="memberPhone" id="memberPhone"><span id="phoneMsg"></span></td>
					</tr>
					<tr>
						<td><label for="memberEmail">이메일</label></td>
						<td><input type="text" name="memberEmail" id="memberEmail">
							<input type="button" value="인증번호 받기" id="btnGetAuthCode"><span id="emailMsg"></span></td>
					</tr>
					<tr>
						<td><label for="authCode">이메일 인증</label></td>
						<td><input type="text" name="authCode" id="authCode" placeholder="인증번호"> <input type="button" value="인증하기" id="btnVerifyAuthCode"></td>
					</tr>
				</tbody>
			</table>
			<button class="btn_signUp">가입하기</button>
		</form>
	</div>
</body>
</html>