<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
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
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<!-- jquery cookie -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js" integrity="sha512-aUhL2xOCrpLEuGD5f6tgHbLYEXRpYZ8G5yD+WlFrXrPy2IrWBlu6bih5C9H6qGsgqnU6mgx6KtU8TreHpASprw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

  
<script>
	$(function(){
		fnLogin();
		fnRememberId();
		
	})
	
	//로그인
	function fnLogin(){
		$('#f').on('submit', function(event){
			if($('#memberId').val() == '' || $('#memberPw').val() == ''){
				alert('아이디와 비밀번호를 모두 입력해주세요');
				event.preventDefault();
				return false;
			}
			
			
			if($('#rememberId').is(':checked')){
				$.cookie('rememberId', $('#memberId').val(), { expires: 7 });
			} else {
				$.cookie('rememberId','');
			}
			return true;
		})
	}
	
	//아이디 저장
	function fnRememberId() {
		let rememberId = $.cookie('rememberId');
		if(rememberId != '') {
			$('#memberId').val(rememberId);
			$('#rememberId').prop('checked', true);
		} else {
			$('#memberId').val('');
			$('#rememberId').prop('checked', false);
		}
	}


</script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
		* {
		    font-family: 'Noto Sans KR', sans-serif;
		}
        a {
            text-decoration: none;
            color: #333;
        }
        .join_cont {
            width: 390px;
            margin: 110px auto 0;
            padding: 60px 50px 51px;
            border: 1px solid #f2f4f5;
        }
        .tit_bi {
            width: 300px;
            height: 100px;
            margin: 0 auto 30px;
            background-image: url(../resources/images/logo.png);
            background-size: 300px 100px;
            background-repeat: no-repeat;
            background-position: left 0 top 0px; 
           

            text-indent: -9000px;
        }
        .login_ipt_box {
            display: block;
            height: 48px;
            margin-bottom: 6px;
            border: 1px solid #d7d7d7;
            position: relative;
        }
        
        .login_ipt_box > input::placeholder {
            color: #c3c3c3;
        }
        
        .login_ipt_box > input {
            width: 350px;
            height: 17px;
            padding: 16px 19px 15px;
            font-size: 14px;
            font-weight: bold;
            color: #635f5f;
            outline-style: none;
            border: none;
        }
        .btn_login {
            width: 100%;
            display: block;
            height: 50px;
            background-color: #4390de;
            border: #4390de;
            font-size: 14px;
            font-weight: bold;
            color: #FFF;
            letter-spacing: -0.5px;
            text-align: center;
            line-height: 51px;
            cursor: pointer;
        }
        .chk_choice {
            margin: 10px 0 40px;
        }
        .uio_check_box {
            display: inline-block;
            height: 20px;
            padding: 0 22px 0 10px;
            font-size: 12px;
            font-weight: normal;
            color: #555;
            letter-spacing: -0.5px;
            line-height: 22px;
            position: relative;
            
        }
        
        
        
        .social_line {
            margin-top: 8px;
            text-align: center;
        }
        .social_line > span {
            display: inline-block;
            width: 114px;
            height: 21px;
            font-size: 12px;
            color: #aaaaaa;
        }
        
        .btn_sns_login {
            padding-top: 8px;
            margin-bottom: 60px;
            font-size: 0;
            text-align: center;
            
        }
        .btn_sns_login > ul > li {
            display: block;
        }
        .btn_sns_login > ul  {
            padding: 0;
        }
        .ico_naver {
            background-position: left 0 top 0;
        }
        .ico_naver > img {
        	width: 50px;
        	height: 50px;
        }

        .bottom_button_box {
            margin-top: 30px;
            text-align: center;
            border-top: 1px solid #f2f2f5;
            font-size: 12px;
        }
        .bottom_button_box > a {
            display: inline-block;
            padding: 19px 18px 0;
        }

    </style>
</head>
<body>
	
	<div class="join_cont">
        <form id='f' action="${contextPath}/member/login" method="post" >
            <a href="${contextPath}/"><h2 class="tit_bi">로고사진넣기</h2></a>
            <input type="hidden" name="url" value="${url}">
            <label for="memberid" class="login_ipt_box">
                
                <input type="text" id="memberId" name="memberId" placeholder="아이디">
            </label>
            <label for="memberPw" class="login_ipt_box">
                
                <input type="password" name="memberPw" id="memberPw" placeholder="비밀번호">
            </label>
    
            <button class="btn_login">로그인</button>
            <div class="chk_choice">
                <label for="rememberId" class="uio_check_box">
                    <input type="checkbox" id="rememberId" >
                    
                    로그인 상태 유지
                </label>
    
                <label for="keepLogin" class="uio_check_box">
                    <input type="checkbox" name="keepLogin" value="keep" id="keepLogin">
                    
                    자동로그인
                </label>
            </div>
            <div class="social_line">
                <span>간편하게 시작하기</span>
            </div>
            <div class="btn_sns_login">
                <ul>
                    <li><a href="${apiURL}" class="ico_naver"><img src="../resources/images/btnW_아이콘원형.png"/></a></li>
                </ul>
            </div>
            <div class="bottom_button_box">
                <a href="${contextPath}/member/findIdPage">아이디 찾기</a>
               
                <a href="${contextPath}/member/findPwPage">비밀번호 찾기</a>
                
                <a href="${contextPath}/member/agreePage">회원가입</a>
            </div>
        </form>
    </div>


	

</body>

</html>
