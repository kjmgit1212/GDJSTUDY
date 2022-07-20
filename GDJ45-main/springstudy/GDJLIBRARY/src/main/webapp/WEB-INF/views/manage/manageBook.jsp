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
	
	// 페이지 로드 이벤트
	$(function(){
		fnCheck();
		fnAreaChoice();
		fnSearchAll();
		fnSearch();
		fnAutoComplete();
		fnlistCountChange();
		fnMoveManageMain();
		fnInsert();
		fnInsertNaver();
	})
	
	// 함수
	
	function fnInsert(){
		$('#btnInsert').on('click', function(){
			location.href='${contextPath}/admin/saveBookPage?value=' + ${value};
		})
	}
	
	function fnInsertNaver(){
		$('#btnInsertNaver').on('click', function(){
			location.href='${contextPath}/admin/saveNaverBookPage?value=' + ${value};
		})
	}
	
	
	function fnlistCountChange(){
		$('#pageUnit').change(function(){
			location.href="${contextPath}/admin/listBook?value=" + $("#pageUnit option:selected").val();
			
		})
	}
	
	
	
	function fnAutoComplete(){
		// keyup : 한 글자 입력이 끝난 뒤 동작
		$('#query').on('keyup', function(){
			$('#autoComplete').empty();
			$.ajax({  // DB에서 입력한 값으로 시작하는 값을 가져와서 보여 줌
				url: '${contextPath}/admin/autoCompleteBook',
				type: 'get',
				data: 'column=' + $('#column').val() + '&query=' + $('#query').val(),
				dataType: 'json',
				success: function(result){
					if(result.status == 200){
						$.each(result.list, function(i, item){
							$('<option>')
							.val(item[result.column])
							.appendTo('#autoComplete');
						})
					}
				}
			})
		})
	}
	
	
	function fnSearch(){
		
		var column = $('#column');
		var query = $('#query');
		var begin = $('#begin');
		var end = $('#end');
		
		$('#btnSearch').on('click', function(){
			
			
			var regBookIsbn = /^[0-9-.=]{1,16}$/;
			// 책ISBN고유번호 검색
			if( column.val() == 'BOOK_ISBN' && regBookIsbn.test(query.val()) == false) {
				alert('ISBN은 숫자 또는 특수문자(- . =) 1~16자 입니다.');
				query.focus();
				return;
			}
			
			// 책제목 검색
			var regBookTitle = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\s]+$/; // 한글 + 영어 + 숫자 + 공백 가능
			if( column.val() == 'BOOK_TITLE' && regBookTitle.test(query.val()) == false ) {
				alert('제목이 올바르지 않습니다.');
				return;
			}
			
			// 책작가 검색
			var regBookAuthor =  /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\s]+$/; // 한글 + 영어 + 숫자 + 공백 가능
			if( column.val() == 'BOOK_AUTHOR' && regBookAuthor.test(query.val()) == false ) {
				alert('작가 이름이 올바르지 않습니다.');
				return;
			}
			
			// 출판사 검색
			var regBookPublisher = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\s]+$/; // 한글 + 영어 + 숫자 + 공백 가능
			if( column.val() == 'BOOK_PUBLISHER' && regBookPublisher.test(query.val()) == false ) {
				alert('출판사 이름이 올바르지 않습니다.');
				return;
			}
			
			// 검색 실행
			// equalArea 작업은 column, query 파라미터 전송
			
			location.href="${contextPath}/admin/searchBook?column=" + column.val() + "&query=" + query.val() + "&value=" + ${value};
			
			
		})
		
	}
	
	function fnSearchAll(){
		$('#btnSearchAll').on('click', function(){
			location.href="${contextPath}/admin/listBook?value=${value}";
		})
	}
	
	
	function fnAreaChoice(){
		
		// 초기 : 모두 숨김
		$('#equalArea, #rangeArea').css('display', 'none');
	
		// 선택
		$('#column').on('change', function(){
			if( $(this).val() == '' ) {
				$('#equalArea, #rangeArea').css('display', 'none');
			} else if( $(this).val() == 'BOOK_ISBN' || $(this).val() == 'BOOK_TITLE' || $(this).val() == 'BOOK_AUTHOR' || $(this).val() == 'BOOK_PUBLISHER') {
				$('#equalArea').css('display', 'inline');
				$('#rangeArea').css('display', 'none');
				$('#selectSection').css('padding-left', '109px');
			}
		})
		
	}
	
	function fnCheck(){
		// 전체 선택
		$('#checkAll').on('click', function(){
		
			// 전체 선택이 checked    -> 개별 선택 모두 checked
			// 전체 선택이 un-checked -> 개별 선택 모두 un-checked
			
			// 체크 여부 확인 방법 2가지
			// 1) attr('checked'), attr('checked', 'checked')
			// 2) prop('checked'), prop('checked', true)
			
			// 체크 박스 체크 상태 변경
			$('.checkOne').prop('checked', $('#checkAll').prop('checked'));
	
		})
		
		// 개별 선택
		$('.checkOne').on('click', function(){
		
			let checkAll = true;                           // 전체 선택하는 거다.
			
			// 개별 선택이 하나라도 un-checked 상태이면, 전체 선택도 un-checked
			$.each($('.checkOne'), function(i, checkOne){
				if($(checkOne).is(':checked') == false){   // 개별 선택 하나라도 해제되어 있으면,
					$('#checkAll').prop('checked', false);
					$('.items').removeClass('check');
					checkAll = false;                      // 전체 선택이 아니다.
					return false;
				}
			})
			
			if(checkAll){
				$('#checkAll').prop('checked', true);
				$('.items').addClass('check');
			}
			
		})
		
		// 각 체크 박스는 클릭할때마다 check 클래스를 줬다 뺐었다 해야 함.
		$('.item, .items').on('click', function(){
			$(this).toggleClass('check');
		})
	}
	
	function fnMoveManageMain(){
		$('#btnManageMain').on('click', function(){
			location.href="${contextPath}/admin/moveManageMain";
		})
	}
</script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
	
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
   
   * {
   	  color: #4e4c4c;
      font-family: 'Noto Sans KR', sans-serif;
   }
	
	
	table {
	    width: 70%;
	    border-top: 1px solid #444444;
	    border-collapse: collapse;
	    margin: auto;
    }
	th, td {
	    border-bottom: 1px solid #444444;
	    padding: 10px;
	    text-align: center;
    }
	th {
    	background-color: #bbdefb;
    }
	td {
    	background-color: #e3f2fd;
    }
 	thead tr {
	    background-color: #0d47a1;
	    color: #ffffff;
    }
    tbody tr:nth-child(2n) {
    	background-color: #bbdefb;
    }
    tbody tr:nth-child(2n+1) {
    	background-color: #e3f2fd;
    }
 	#tfoot{
 		background-color: #ffffff;
 		border-color: #ffffff;
 	} 
 	
 	#search{
 		width: 70%;
 		margin: auto;
 	}
 	#search_inner{
 		
 		width: 70%;
 		margin: auto;
 	}
 	.form-group{
 		width: 70%;
 		margin: auto;
 	}
 	#column{
 		margin-left: 180px;
 	}
 	#tfoot{
 		padding-right: 21px;
 			
 	}
 	.link, .unlink{
 		padding-left: 8px;
 	}
</style>
</head>
<body>
	
	
	
	<div class="form-group">
    <select id="pageUnit" name="pageUnit">
        <option value="">:::선택:::</option>
        <option value="10">10권씩 보기</option>
        <option value="20">20권씩 보기</option>
        <option value="30">30권씩 보기</option>
        <option value="40">40권씩 보기</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;
    페이지별검색수: ${value}        
	&nbsp;&nbsp;
	책수: ${totalRecord}권
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="전체 책 조회" id="btnSearchAll">&nbsp;&nbsp;&nbsp;<input type="button" value="책 추가하기" id="btnInsert">&nbsp;&nbsp;&nbsp;<input type="button" value="네이버API 책 추가하기" id="btnInsertNaver">&nbsp;&nbsp;&nbsp;<input type="button" value="관리자 메인 페이지" id="btnManageMain">
	</div>
	
	<br>	
				
	<form id="f" action="${contextPath}/admin/removeCheckBook?value=${value}" method="post">
		<table border="1" class="table">
			<thead>
				<tr>
					<th width="1%" height="50%"><input type="checkbox" name="checkAll" class="blind checkAll" id="checkAll"></th>
					<th width="5%">번호</th>
					<th width="5%">이미지</th>
					<th width="10%">ISBN</th>
					<th width="17%">제목</th>
					<th width="17%">작가</th>
					<th width="10%">출판사</th>
					<th width="5%">출판날짜</th>
					<th width="6%">책삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${books}" var="book" varStatus="vs">
					
					<tr>
						<td><input type="checkbox" name="check" class="blind checkOne" value="${book.bookNo}"></td>
						<td>${beginNo - vs.index}</td>
						<td><img alt="" src="${book.bookImage}" width="50px"></td>
						<td><a href="${contextPath}/admin/detailBook?bookNo=${book.bookNo}&value=${value}">${book.bookIsbn}</a></td>
						<td>${book.bookTitle}</td>
						<td>${book.bookAuthor}</td>
						<td>${book.bookPublisher}</td>
						<td>${book.bookPubdateTime}</td>					
						<td><a href="${contextPath}/admin/removeBook?bookNo=${book.bookNo}&value=${value}" onclick="return confirm('정말 삭제하시겠습니까?')"><i class="fa-solid fa-circle-xmark"></i></a></td>					
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr class="tfoot">
					<td colspan="9" id="tfoot">
						<span style="text-decoration:none">${paging}</span>
					</td>
				</tr>
			</tfoot>
		</table>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="return confirm('정말 삭제하시겠습니까?')">책 선택 삭제</button>
	</form>
	
	<div id="search">
		<div id="search_inner">
			<form id="searchForm" method="get">
				<span id="selectSection">
					<select name="column" id="column">
						<option value="">:::선택:::</option>
						<option value="BOOK_ISBN">고유번호</option>
						<option value="BOOK_TITLE">제목</option>
						<option value="BOOK_AUTHOR">작가</option>
						<option value="BOOK_PUBLISHER">출판사</option>
					</select>
				</span>
				<span id="equalArea">
					<input type="text" name="query" id="query" list="autoComplete">
					<datalist id="autoComplete"></datalist>
				</span>
				<span id="rangeArea">
					<input type="text" name="begin" id="begin">
					~
					<input type="text" name="end" id="end">
				</span>
				<span id="btnSearchSection">
					<input type="button" value="검색" id="btnSearch">
				</span>
			</form>
		</div>
	</div>
</body>
</html>