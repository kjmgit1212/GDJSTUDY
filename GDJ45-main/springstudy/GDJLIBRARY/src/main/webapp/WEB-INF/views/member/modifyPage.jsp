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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>


	/* 페이지 로드 이벤트 */
	$(function(){
		fnPhoneCheck();
		fnModify();
		fnMemberDelete();
	})
	
	/* 함수 */
	// 3. 수정
	function fnModify(){
		$('#f').on('submit', function(event){
			if($('#memberName').val() == ''){
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
			
			return true;
		})
	}
	
	// 2. 휴대전화 확인
	let PhonePass = true;
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
	
	// 1. 주소 api
	let addressPass = true;
	function fnPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            
	            var roadAddr = data.roadAddress; 
	
	            $('#memberPostcode').val(data.zonecode).prop('readonly', true);
	            $('#memberRoadAddress').val(roadAddr).prop('readonly', true);
	            $('#memberDetailAddress').val('');
	            if($('#memberPostcode').val() != '' && $('#memberRoadAddress').val() != ''){
	            	addressPass = true;
	            } else {
	            	addressPass = false;
	            }
	        }
	    }).open();
	}

	//취소하기
	function fnCancel(){
		location.href='${contextPath}/member/myPage';
	}
	
	function fnMemberDelete() {
		$('#f2').on('submit', function(event){
			if(confirm('탈퇴하시겠습니까?')) {
				return true;
			}else {
				event.preventDefault();
				return false;
			}
		})
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

    .mofidy_warp {
        width: 800px;
        margin: 180px auto 0;
        padding: 50px 50px 51px;
        
        
    }
    .mofidy_warp  h3 {
        text-align: center;
        margin-bottom: 50px;
        color: #4e4c4c;
       
    }
    .mofidy_warp table {
        border-collapse: collapse;
    }
    
    .mofidy_warp table td {
        width: 650px;
        border-top: 1px solid #f2f4f5;
        border-bottom: 1px solid #f2f4f5;
        height: 40px;
    }
    
    .mofidy_warp table td:first-of-type {
        border-right: 1px solid #f2f4f5;
        width: 150px;
    }
    .mofidy_warp label, .mofidy_warp tr:nth-of-type(2) td:first-of-type{
        font-size: 13px;
        padding-left: 10px;
    }
    
    .mofidy_warp input[type="text"] {
        margin-left: 10px;
        width: 150px;
        height: 10px;
        padding: 5px 10px 5px 7px;
        font-size: 14px;
        
        color: #635f5f;
        outline-style: none;
        border: 1px solid #d7d7d7
       
    }
    .mofidy_warp tr:nth-of-type(3) td, .mofidy_warp tr:nth-of-type(4) td, .mofidy_warp tr:last-of-type td {
        border-right: 0;
    }
    .mofidy_warp td span {
        
        font-size: 13px;
        padding-left: 5px;
    }

    .remove_wrap{
       text-align: right;
       margin-bottom: 5px;
    }

    .btnRemove {
        width: 80px;
        height: 30px;
        background-color: #f5e0c1;
        border: #f5e0c1;
        font-size: 13px;
        font-weight: bold;
        color: #4e4c4c;
        letter-spacing: -0.5px;
        text-align: center;
        line-height: 30px;
        cursor: pointer;

    }


    .mofidy_warp input[type="button"], .btnModify {
        font-size: 13px;
        border: 1px solid #f5e0c1;
        background-color: #f5e0c1;
        padding: 2px 5px;
        cursor: pointer;
        
    }
    .modifyBotton {
        margin-top: 20px;
        text-align: center;
        
    }

    .modifyBotton input[type="button"] {
        font-size: 13px;
        border: 1px solid #f5e0c1;
        background-color: #f5e0c1;
        padding: 2px 5px;
        cursor: pointer;
    }
</style>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<div class="mofidy_warp">
        <h3>회원정보 수정</h3>
        <div class="remove_wrap">
            <form id="f2" action="${contextPath}/member/memberDelete" method="post">
                <input type="hidden" name="memberId" id="memberId" value="${member.memberId}">
                <button class="btnRemove">회원탈퇴</button>   
            </form>
        </div>

        <form id="f" action="${contextPath}/member/modify" method="post">
            
            <input type="hidden" name="memberId" id="memberId" value="${member.memberId}">
            <table>
                <tbody>
                    <tr>
                        <td><label for="memberName">이름</label></td>
                        <td><input type="text" name="memberName" id="memberName" value="${member.memberName}"></td>
                    </tr>
                    <tr>
                        <td rowspan="3">주소</td>
                        <td>
                            <input type="text" name="memberPostcode" id="memberPostcode"  readonly="readonly" value="${member.memberPostcode}">
                            <input type="button" onclick="fnPostcode()" value="우편번호 찾기">
                        </td>
                    </tr>
                    <tr>
                        <td><input type="text" name="memberRoadAddress" id="memberRoadAddress"  readonly="readonly" value="${member.memberRoadAddress}"><span>도로명 주소</span></td>
                    </tr>
                    <tr>
                        
                        <td><input type="text" name="memberDetailAddress" id="memberDetailAddress" value="${member.memberDetailAddress}"><span>상세 주소</span></td>
                    </tr>
                    <tr>
                        <td><label for="memberPhone">휴대전화</label></td>
                        <td><input type="text" name="memberPhone" id="memberPhone" value="${member.memberPhone}"><span id="phoneMsg"></span></td>
                    </tr>

                </tbody>
               
            </table>
            <div class="modifyBotton">
                <button class="btnModify">수정하기</button>
                <input type="button" value="취소하기" onclick="fnCancel();">
            </div>
        </form>
        
        
    </div>
	
</body>
</html>