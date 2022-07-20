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
			
			// 모든 내용 입력확인
			$('#f').on('submit', function(event){
				if($('#title').val() == '' || $('#content').val() == ''){
				        alert('내용을 모두 입력해주세요.');
				        event.preventDefault();
						return false;
			    }
				return true;
			})
			
			// 관리자 메인 페이지 이동 
			$('#btnList').on('click', function(){
			location.href='${contextPath}/admin/moveManageMain';
			})
		
	})
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
	
    * {
		  margin: 0;
		  padding: 0;
		  box-sizing: border-box;
    }
	
	ul,li {
	  list-style: none;
	}
	
	a {
	  text-decoration: none;
	  color: inherit;
	}        
	.register{
	            width: 650px;
	            margin: 130px auto 0;
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
        <h3>FAQ 게시글 추가</h3>
        <form id="f" action="${contextPath}/admin/saveFaq" method="post">
            <div class="flex">
                <ul class="container">
                    <li class="item center">
                        제목
                    </li>
                    <li class="item">
                        <input type="text" name="title" id="title" placeholder="제목을 입력하세요.">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                   
                    <li class="item center">
                        내용
                    </li>
                    <li class="item">
                        <textarea rows="20" cols="50" name="content" id="content"></textarea>
                    </li>
                  	<li class="item">
                        
                    </li>
                </ul>
                
                <ul class="container">
                    <li class="item center">
                        
                    </li>
                    <li class="item">
                        <button class="submit">작성완료</button>
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
                <ul class="container">
                    <li class="item center">
                        
                    </li>
                    <li class="item">
                       	<br>
                        <input type="button" class="submit" value="관리자 메인 페이지 이동" id="btnList">
                    </li>
                    <li class="item">
                        
                    </li>
                </ul>
            </div>
        </form>
    </div>
	
	
</body>
</html>