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
		fnFindId();
		fnFindIdEmailCheck();
	})
	
	//이메일 정규식
	function fnFindIdEmailCheck(){
		$('#memberEmail').on('keyup', function(){
			let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,}){1,2}$/;
			if(regEmail.test($('#memberEmail').val()) == false){
				$('#emailMsg').text('사용할 수 없는 이메일 형식입니다.').addClass('dont').removeClass('ok');
				return;
			}
			else {
				$('#emailMsg').text('');
			}
		})
	}

	//아이디 찾기(아이디/이메일 확인) = 회원 아이디 반환
	function fnFindId(){
		$('#btnFindId').on('click', function(event){
			if($('#memberName').val() == '' || $('#memberEmail').val() == ''){
				alert('이름과 이메일을 모두 입력해주세요');
				event.preventDefault();
				return;
			}
		
			$.ajax({
				url: '${contextPath}/member/findId',
				type: 'post',
				data: 'memberName=' + $('#memberName').val() + '&memberEmail=' + $('#memberEmail').val(),
				dataType: 'json',
				success: function(obj){
					if(obj.res1 != null) {
						$('#findIdMsg').text(obj.res1.memberName + '님의 아이디는 ' + obj.res1.memberId + '입니다.').addClass('ok').removeClass('dont');
					} else if (obj.res2 != null) {
						$('#findIdMsg').text(obj.res2.memberName + '님의 아이디는 ' + obj.res2.memberId + '입니다.').addClass('ok').removeClass('dont');
					} 
					
					else {
						$('#findIdMsg').text('해당하는 아이디가 존재하지 않습니다.').addClass('dont').removeClass('ok');
					}
				}
			})
			
		})
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


     .findId_wrap {
         width: 500px;
         margin: 150px auto 0;
     }

     a {
         text-decoration: none;
         color: #333;
     }

     .findId_wrap table {
         margin: 0 auto;
         
     }
     
     .findId_wrap table td {
        height: 40px;
         
     }
     .findId_wrap tr td:nth-of-type(2) {
         display: block;
         height: 35px;
         margin-bottom: 3px;
         border: 1px solid #d7d7d7;
         position: relative;
         
     }

     .findId_wrap tr:nth-of-type(3) td {
         
         height: 25px;
         
     }

     .findId_wrap tr td:nth-of-type(1) {
         font-size: 13px;
         padding: 0 10px 0 5px;
         color: #4e4c4c;
     }

     .findId_wrap  input[type="text"] {
         width: 180px;
         height: 10px;
         padding: 12px 10px 12px;
         font-size: 14px;
         font-weight: bold;
         color: #635f5f;
         outline-style: none;
         border: none;
     }

     .findId tr input[type="button"] {
         margin-left: 5px;
         background-color: #f5e0c1;
         width: 80px;
         border-color: #f5d7ac;
         padding: 3px 5px;
         font-size: 12px;
		color: #333;
     }

     .findId > h3 {
         display: block;
         text-align: center;
         margin-bottom: 30px;
         font-size: 15px;
         color: #4e4c4c;
     }
     #emailMsg {
         display: block;
         text-align: center;
     }

     .bottom_button_box {
         margin-top: 30px;
         text-align: center;
         
         font-size: 12px;
     }
     .bottom_button_box > a {
         display: inline-block;
         padding: 19px 18px 0;
     }
     .findId + div {
     	text-align: center;
     }
</style>
</head>
<body>
	<div class="findId_wrap">
        <div class="findId">
            <h3>아이디 찾기</h3>
            <table>
                <tbody>
                    <tr>
                        <td><label for="memberName">이름</label></td>
                        <td><input type="text" name="memberName" id="memberName"></td>
                    </tr>
                    <tr>
                        <td><label for="memberEmail">가입한 이메일</label></td>
                        <td>
                            <input type="text" name="memberEmail" id="memberEmail">
                            
                        </td>
                        <td>
                            <input type="button" id="btnFindId" value="아이디찾기">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3"><span id="emailMsg"></span></td>
                    </tr>
                </tbody>
            </table>
            
        </div>
        <div>
            <span id="findIdMsg"></span>
        </div>
        <div class="bottom_button_box">
            <a href="${contextPath}/member/loginPage">로그인</a>
           
            <a href="${contextPath}/member/findPwPage">비밀번호 찾기</a>
            
            <a href="${contextPath}/member/agreePage">회원가입</a>
        </div>
    </div>
	
</body>
</html>