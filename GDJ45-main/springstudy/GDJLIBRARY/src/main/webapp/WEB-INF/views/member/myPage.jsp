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
		fnRentBookList();
		fnoOerdueBookList();
		fnSeatNo();
	})
	
	
	
	// 마이페이지 대여 목록
	function fnRentBookList(){
		$.ajax({
			url: '${contextPath}/member/rentBookList',
			type: 'get',
			dataType: 'json',
			success: function(result){
				$('#myPageRentBookList').empty();
				if(result.rentBookList != ''){
					$.each(result.rentBookList, function(i, rentBook){
						$('<tr>')
						.append($('<td>').html(rentBook.bookTitle))
						.append($('<td>').html(rentBook.rentDate))
						.append($('<td>').html(rentBook.rentExpirationDate))
						.appendTo('#myPageRentBookList');
					});
					
				} else {
					$('<tr>')
					.append($('<td colspan="3">대여중인 책이 없습니다.</td>'))
					.appendTo('#myPageRentBookList');
				}			
			}
		})
	}
	
	// 마이페이지 연체 목록
	function fnoOerdueBookList() {
		$.ajax({
			url: '${contextPath}/member/overdueBookList',
			type: 'get',
			dataType: 'json',
			success: function(result){
				$('#myPageOverdueBookList').empty();			
				if(result.overdueBookList != ''){
					$.each(result.overdueBookList, function(i, overdueBook){
						$('<tr>')
						.append($('<td>').html(overdueBook.bookTitle))
						.append($('<td>').html(overdueBook.rentExpirationDate))
						.appendTo('#myPageOverdueBookList');
					});
				} else {
					$('<tr>')
					.append($('<td colspan="2">연체중인 책이 없습니다.</td>'))
					.appendTo('#myPageOverdueBookList');
				}
			}
		})
	}
	
	// 마이페이지 예약한 좌석
	function fnSeatNo(){
		$.ajax({
			url: '${contextPath}/member/reservationSeatNo',
			type: 'get',
			dataType: 'json',
			success: function(result) {
				if(result.seat != null) {
					$('<tr>')
					.append($('<td>').html(result.seat.seatNo + '번'))
					.append($('<td>').html(result.seat.seatCode))
					.appendTo('#myPageReservationSeatNo');
				} else {
					$('<tr>')
					.append($('<td colspan="2">').html('예약된 좌석이 없습니다.'))
					.appendTo('#myPageReservationSeatNo');
				}
			}
			
		})
	}
</script>
<style>
		@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	* {
	    font-family: 'Noto Sans KR', sans-serif;
	}
       
    .myPage_wrap {
        width: 1000px;
        margin: 100px auto 0;
        padding: 50px 50px 51px;
        
        
    }
    .myPage_wrap table {
		 margin: 0 auto;
		 width : 800px;
	 
	 }
    .myPage_wrap  h3 {
        text-align: center;
        margin-bottom: 50px;
        color: #4e4c4c;
       
    }
    a {
        text-decoration: none;
        color: #4e4c4c;
        
    }
    .myPageTop {
        display: block;
        text-align: right;
    }
    .myPageTop a:first-of-type {
        border-right: #4e4c4c;
        padding: 19px 18px 0;
        
    }
    .myPageList caption {
        width: 150px;
        height: 50px;
        background-color: #4390de;
        color: #fff;
        line-height: 50px;
        font-weight: 500;
        text-align: center;
        margin: 0 auto 20px;
        font-size: 18px;
    }

    .myPageList {
        margin-bottom: 50px;
    }
    .myPageList:nth-of-type(3) {
        margin-bottom: 20px;
    }
    .myPageList:nth-of-type(4) table {
        width: 600px;
       
    }
    .myPageList table {
       color: #4e4c4c;
        border-collapse: collapse;
    }

    .myPageList table td {
        font-size: 15px;
        text-align: center;
        border-top: 1px solid #4e4c4c;
        border-bottom: 1px solid #4e4c4c;
        height: 50px;
    }
    .myPageList table thead td {
        height: 40px;
    }
    .btnReturn {
        display: block;
        font-size: 14px;
        margin: 0 auto 50px;
        width: 100px;
        height: 35px;
        background-color: #f5e0c1;
        border: #f5e0c1;
        cursor: pointer;
        color: #4e4c4c;
    }
</style>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<h2>마이페이지</h2>
	
	<div class="myPage_wrap">
        <h3>MY PAGE</h3>
        <div class="myPageTop">
            <a href="${contextPath}/member/modifyConfirm">회원정보 수정</a>
            <a href="${contextPath}/member/pwModifyConfirm">비밀번호 변경</a>
        </div>
	

        <div class="myPageList">
            <table>
                <caption>대여 목록</caption>
                <thead>
                    <tr>
                        <td>책 제목</td>
                        <td>대여일자</td>
                        <td>대여만료일자</td>
                    </tr>
                </thead>
                <tbody id="myPageRentBookList"></tbody>
            </table>
            
        </div>
        
        <div class="myPageList">
            <table>
                <caption>연체 목록</caption>
                <thead>
                    <tr>
                        <td>책 제목</td>
                        <td>대여만료일자</td>
                    </tr>
                </thead>
                <tbody id="myPageOverdueBookList"></tbody>
            </table>
        </div>
        <input class="btnReturn" type="button" value="반납하기" onclick="location.href='${contextPath}/returned/returnedBookPage'">
        <div class="myPageList">
            <table>
                <caption>예약한 좌석</caption>
                <thead>
                    <tr>
                        <td>예약좌석 번호</td>
                        <td>좌석코드</td>
                    </tr>
                </thead>
                <tbody id="myPageReservationSeatNo"></tbody>
            </table>
        </div>

    </div>
</body>
</html>