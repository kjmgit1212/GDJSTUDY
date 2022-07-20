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
	
	// 페이지 로드 이벤트
	$(function(){
		fnPwConfirm();	
		fnPwCheck();
		fnMemberChange();
		fnList();
		fnEmailCheck();
		fnPhoneCheck();
	
		// 목록
		$('#btnList').on('click', function(){
			location.href='${contextPath}/admin/listMember?value=${value}';
		})
	
	})


	
	// 함수
	
	// 수정완료
	function fnMemberChange(){
		$('#f').on('submit', function(event){
			if($('#pw').val() == '${member.memberPw}' && $('#name').val() == '${member.memberName}' && $('#phone').val() == '${member.memberPhone}' && $('#email').val() == '${member.memberEmail}' && $('#postcode').val() == '${member.memberPostcode}' && $('#roadAddress').val() == '${member.memberRoadAddress}' && $('#detailAddress').val() == '${member.memberDetailAddress}'){
				alert('변경된 내용이 없습니다.');
				event.preventDefault();
				return false;
			} else if(pwPass == false || rePwPass == false){
				alert('비밀번호를 확인하세요.');
				event.preventDefault();
				return false;
			} else if(emailPass == false){
				alert('이메일 형식을 확인하세요.');
				event.preventDefault();
				return false;
			} else if(emailOverlapPass == false){
				alert('이미 사용중인 이메일입니다.');
				event.preventDefault();
				return false;
			} else if($('#pw').val() == '' || $('#name').val() == '' || $('#phone').val() == '' || $('#email').val() == '' || $('#postcode').val() == '' || $('#roadAddress').val() == '' || $('#detailAddress').val() == ''){
		        alert('내용을 모두 입력해주세요.');
		        event.preventDefault();
				return false;
			} 
			return true;
		})
	}
	
	// 5. 휴대전화 확인
	let PhonePass = false;
	function fnPhoneCheck(){
		$('#phone').on('keyup', function(){
			let regPhone = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
			if(regPhone.test($('#phone').val()) == false) {
				$('#phoneMsg').text('휴대전화 형식 예) 010-1234-5678');
				PhonePass = false;
			} else {
				$('#phoneMsg').text('');
				PhonePass = true;
			}
		})
	}
	
	

	
	let emailPass = false;
	let emailOverlapPass = false;
	// 4. 이메일 중복체크
	function fnEmailCheck(){
		$('#email').on('keyup', function(){
			// 정규식 체크하기
			let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,}){1,2}$/;  
			if(regEmail.test($('#email').val())==false){
				$('#emailMsg').text('이메일 형식이 올바르지 않습니다.').addClass('dont').removeClass('ok');
				emailPass = false;
				return;
			}
			// 이메일 중복 체크
			$.ajax({
				url: '${contextPath}/admin/checkMemberEmail',
				type: 'get',
				data: 'email=' + $('#email').val(),
				dataType: 'json',
				success: function(obj){
					if(obj.res == null){
						$('#emailMsg').text('멋진 이메일네요!').addClass('ok').removeClass('dont');
						emailPass = true;
						emailOverlapPass = true;
					} else {
						$('#emailMsg').text('이미 사용 중인 이메일입니다.').addClass('dont').removeClass('ok');
						emailPass = true;
						emailOverlapPass = false;
					}
				},
				error: function(jqXHR){
					$('#emailMsg').text(jqXHR.responseText).addClass('dont').removeClass('ok');
					emailPass = false;
				}
			})
		})
	}
	
	
	
	
	
	// 비밀번호 입력확인
	let rePwPass = false;
	function fnPwConfirm(){
		$('#pwConfirm').on('keyup', function(){
			if($('#pwConfirm').val() != '' && $('#pw').val() != $('#pwConfirm').val()){
				$('#pwConfirmMsg').text('비밀번호가 일치하지 않습니다').addClass('dont').removeClass('ok');
				rePwPass = false;
			} else {
				$('#pwConfirmMsg').text('');
				rePwPass = true;
			}
		})
	}
	
	let pwPass = false;
	function fnPwCheck(){
		// 비밀번호 정규식
		pwPass = false;
		// 비밀번호 정규식 검사
		$('#pw').on('keyup', function(){
			
			let regPw = /^[a-zA-Z0-9!@#$%^&*]{8,20}$/;
			let pwValid = /[a-z]/.test($('#pw').val()) +  // 소문자 포함이면 1
		  				  /[A-Z]/.test($('#pw').val()) +  // 대문자 포함이면 1
		   				  /[0-9]/.test($('#pw').val()) +  // 숫자 포함이면 1
		  				  /[!@#$%^&*]/.test($('#pw').val());  // 특수문자 포함이면 1
			if(regPw.test($('#pw').val()) && pwValid >= 3){
			   $('#pwMsg').text('사용 가능한 비밀번호 입니다.').addClass('ok').removeClass('dont');
			   pwPass = true;
			} else {
				$('#pwMsg').text('8~20자 영문 대 소문자,숫자,특수문자 중 3개 이상을 사용하세요.').addClass('dont').removeClass('ok');
				pwPass = false;
			}
		})
	}
	

	
	// 목록
	function fnList(){
		$('#btnList').on('click', function(){
			location.href='${contextPath}/admin/listMember?value=${value}';
		})
	}
</script>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
   
   * {
   	  color: #4e4c4c;
      font-family: 'Noto Sans KR', sans-serif;
   }
	
	
	.ok {
		color: limegreen;
	}
	.dont {
		color: crimson;
	}
	
	#phoneMsg {
		color: crimson;
	}
	
	* {
	  margin: 0;
	  padding: 0;
	  box-sizing: border-box;
    }
	ul, li {
	  list-style: none;
	}
	a {
	  text-decoration: none;
	  color: inherit;
	}        
	.register{
	            width: 720px;
	            margin: 140px auto 0;
	            padding: 15px 20px;
            background: white;
            color: #2b2e4a;
            font-size: 14px;
            text-align: left;
            box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.2);
    }
    .register h3{
        font-size: 20px;
        margin-bottom: 20px;
        text-align: center;
    }
    .register input{
        width: 100%;
        height: 40px;
        outline: none;
        padding: 10px;
        border: 1px solid #707070;
        transition: 0.3s;
    }
    .register input:valid, .register input:focus{
        border: 1px solid #2b2e4a;
    }
    .register .center{
        display: flex;
        align-items: center;
    }
    .register .flex{
        display: flex;
        flex-direction: column;
    }
    .register .flex .container{
        display: grid;
        grid-template-columns: 1fr 3fr 1fr;
        margin-bottom: 10px;
    }
    .register .flex .container .item:first-child{
       margin-right: 10px;
    }
    .register .flex .container .item .idcheck{
        height: 100%;
        margin-left: 10px;
        padding: 5px 15px;
        background: #2b2e4a;
        border: 1px solid #2b2e4a;
        color: white;
        font-size: 12px;
        transition: 0.3s;
    }
    .register .flex .container .item .idcheck:hover{
        background: white;
        color: #2b2e4a;
    }
    .register .flex .container .item select{
        height: 40px;
        padding: 10px;
        border: 1px solid #2b2e4a;
    }
    .register .submit{
        width: 100%;
        height: 40px;
        color: white;
        border: none;
        border: 1px solid #2b2e4a;
        background: #2b2e4a;
        transition: 0.3s;
    }
    .register .flex .container:last-child{
        margin: 0;
    }
    .register .submit:hover{
        width: 100%;
        height: 40px;
        border: none;
        border: 1px solid #2b2e4a;
        color: #2b2e4a;
        background: white;
    }
</style>
</head>
<body>
	
	<div class="register">
        <h3>회원수정</h3>
        <form id="f" action="${contextPath}/admin/changeMember?value=${value}" method="post">
            <div class="flex">
                <ul class="container">
                    <li class="item center">
                        회원번호
                    </li>
                    <li class="item">
                        ${member.memberNo}
                    </li>
                    <li class="item">
                        <input type="hidden" name="memberNo" value="${member.memberNo}">
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        아이디
                    </li>
                    <li class="item">
                        ${member.memberId}
                    </li>
                    <li class="item">
                        <input type="hidden" name="id" value="${member.memberId}">
                    </li>
                </ul>
                <ul class="container">
                   
                    <li class="item center">
                        비밀번호
                    </li>
                    <li class="item">
                        <span id="idMsg"></span>
                        <input type="password" name="pw" id="pw" placeholder="비밀번호를 입력하세요.">
                    </li>
                  	<li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                
                    <li class="item center">
                        
                        비밀번호 확인
                    </li>
                    <li class="item">
                        <span id="pwMsg"></span>
                        <input type="password" id="pwConfirm" name="pwConfirm" placeholder="비밀번호를 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    
                    <li class="item center">
                         이름
                    </li>
                    <li class="item">
                        <span id="pwConfirmMsg"></span>
                        <input type="text" name="name" id="name" value="${member.memberName}" placeholder="이름을 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        전화번호
                    </li>
                    <li class="item">
                       <input type="text" name="phone" id="phone" value="${member.memberPhone}" placeholder="전화번호를 입력하세요.">
                    </li>
                     <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    
                    <li class="item center">
                        이메일
                    </li>
                    <li class="item">
                        <span id="phoneMsg"></span>
                        <input type="text" name="email" id="email" placeholder="이메일을 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        우편번호
                    </li>
                    <li class="item">
                        <span id="emailMsg"></span>
                        <input type="text" name="postcode" id="postcode" value="${member.memberPostcode}" placeholder="우편번호를 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                 <ul class="container">
                    <li class="item center">
                        도로명 주소
                    </li>
                    <li class="item">
                        <span id="emailMsg"></span>
                        <input type="text" name="roadAddress" id="roadAddress" value="${member.memberRoadAddress}" placeholder="도로명 주소를 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                 <ul class="container">
                    <li class="item center">
                        상세주소
                    </li>
                    <li class="item">
                        <input type="text" name="detailAddress" id="detailAddress" value="${member.memberDetailAddress}" placeholder="상세주소를 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        
                    </li>
                    <li class="item">
                        <button class="submit">수정하기</button>
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        
                    </li>
                    <li class="item">
                       	<br>
                        <input type="button" class="submit" value="회원 목록 페이지 이동" id="btnList">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
            </div>
        </form>
    </div>
	
	
</body>
</html>