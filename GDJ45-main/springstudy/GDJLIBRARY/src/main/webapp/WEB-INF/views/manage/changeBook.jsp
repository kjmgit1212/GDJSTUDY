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
		fnBookChange();
		fnList();
		fnIsbnCheck();
	
		// 목록
		$('#btnList').on('click', function(){
			location.href='${contextPath}/admin/listBook?value=${value}';
		})
	
	})


	
	// 함수
	
	// 수정완료
	function fnBookChange(){
		$('#f').on('submit', function(event){
			
			if($('#isbn').val() == '${book.bookIsbn}' && $('#title').val() == '${book.bookTitle}' && $('#author').val() == '${book.bookAuthor}' && $('#publisher').val() == '${book.bookPublisher}' && $('#pubdate').val() == '${book.bookPubdateTime}' && $('#description').val() == '${book.bookDescription}' && $('#image').val() == '${book.bookImage}' && $('#field').val() == '${book.bookType}'){
				alert('변경된 내용이 없습니다.');
				event.preventDefault();
				return false;
			} else if(isbnPass == false){
				alert('형식에 맞지 않는 ISBN입니다.');
				event.preventDefault();
				return false;
			} else if($('#title').val() == '' || $('#author').val() == '' || $('#publisher').val() == '' || $('#pubdate').val() == '' || $('#description').val() == '' || $('#image').val() == '' || $('#field').val() == ''){
				alert('내용을 모두 입력해주세요.');
				event.preventDefault();
				return false;
			} 
			return true;
		})
	}
	
	
	// 1. ISBN 정규식
	
	let isbnPass = false;
	function fnIsbnCheck(){
		$('#isbn').on('keyup', function(){
			// 정규식 체크하기
			let regIsbn = /^[0-9--.=]{1,20}$/; 
			if(regIsbn.test($('#isbn').val())==false){
				$('#isbnMsg').text('ISBN은 숫자 또는 특수문자(- . =) 1~20자 입니다.').addClass('dont').removeClass('ok');
				isbnPass = false;
				return false;
			} else {
				isbnPass = true;
				$('#isbnMsg').text('멋진 ISBN입니다.').addClass('ok').removeClass('dont');
				return true;
			}
			
		})
	}
	
	
	
	
	
	
	// 목록
	function fnList(){
		$('#btnList').on('click', function(){
			location.href='${contextPath}/admin/listBook?value=${value}';
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
	            margin: 20px auto 0;
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
    .register textarea {
    	outline: none;
        padding: 10px;
        border: 1px solid #707070;
        transition: 0.3s;
    }
    .register input:valid, .register input:focus, .register textarea:valid, .register textarea:focus{
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
        <h3>책수정</h3>
        <form id="f" action="${contextPath}/admin/changeBook?value=${value}" method="post">
            <div class="flex">
                <ul class="container">
                    <li class="item center">
                        책번호
                    </li>
                    <li class="item">
                        ${book.bookNo}
                    </li>
                    <li class="item">
                        <input type="hidden" name="bookNo" value="${book.bookNo}">
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        ISBN
                    </li>
                    <li class="item">
                        <input type="text" name="isbn" id="isbn" placeholder="ISBN을 입력하세요.">
                    </li>
                    <li class="item">
                   
                    </li>
                </ul>
                <ul class="container">
                   
                    <li class="item center">
                        제목
                    </li>
                    <li class="item">
                        <span id="isbnMsg"></span>
                        <input type="text" name="title" id="title" value="${book.bookTitle}" placeholder="제목을 입력하세요.">
                    </li>
                  	<li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                
                    <li class="item center">
						작가                        
                    </li>
                    <li class="item">
                        <input type="text" id="author" name="author" value="${book.bookAuthor}" placeholder="작가를 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    
                    <li class="item center">
                         출판사
                    </li>
                    <li class="item">
                        <input type="text" name="publisher" id="publisher" value="${book.bookPublisher}" placeholder="출판사를 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        출판날짜
                    </li>
                    <li class="item">
                       <input type="text" name="pubdate" id="pubdate" value="${book.bookPubdateTime}" placeholder="출판날짜를 입력하세요.">
                    </li>
                     <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    
                    <li class="item center">
                        내용
                    </li>
                    <li class="item">
                        <textarea rows="15" cols="53" name="description" id="description" placeholder="내용을 입력하세요.">${book.bookDescription}</textarea>
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        이미지주소
                    </li>
                    <li class="item">
                        <input type="text" name="image" id="image" value="${book.bookImage}" placeholder="이미지 주소를 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                 <ul class="container">
                    <li class="item center">
                        분야
                    </li>
                    <li class="item">
                        <input type="text" name="field" id="field" value="${book.bookType}" placeholder="분야를 입력하세요.">
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
                        <input type="button" class="submit" value="도서 목록 페이지 이동" id="btnList">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
            </div>
        </form>
    </div>
   
	
	
	
</body>
</html>