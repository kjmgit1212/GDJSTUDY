<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
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
	})
	
	// 함수
	function fnInsert(){
		$('#btnInsert').on('click', function(){
			location.href='${contextPath}/admin/saveMemberPage?value=' + ${value};
		})
	}
	// 삭제
		

	
	
	function fnlistCountChange(){
		$('#pageUnit').change(function(){
			location.href="${contextPath}/admin/listMember?value=" + $("#pageUnit option:selected").val();
			
		})
	}
	
	
	
	function fnAutoComplete(){
		// keyup : 한 글자 입력이 끝난 뒤 동작
		$('#query').on('keyup', function(){
			$('#autoComplete').empty();
			$.ajax({  // DB에서 입력한 값으로 시작하는 값을 가져와서 보여 줌
				url: '${contextPath}/admin/autoCompleteMember',
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
			
			// 사원이름 검색
			var regMemberName = /^[가-힣]+$/;  // 한글만가능.
			if( column.val() == 'MEMBER_NAME' && regMemberName.test(query.val()) == false) {
				alert('회원이름이 올바르지 않습니다.');
				query.focus();
				return;
			}
			
			// 전화번호 검색
			var regMemberPhone = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;     
			if( column.val() == 'MEMBER_PHONE' && regMemberPhone.test(query.val()) == false ) {
				alert('휴대전화 형식 예) 010-1234-5678');
				return;
			}
			
			// 주소 검색
			var regMemberRoadAddress = /^[가-힣\s]+$/;      // (한글 + 띄어쓰기)가능.
			if( column.val() == 'MEMBER_ROAD_ADDRESS' && regMemberRoadAddress.test(query.val()) == false ) {
				alert('주소가 올바르지 않습니다.');
				return;
			}
			
			// 가입일자 검색
			var regMemberSingUp = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;  // 2022-05-25
			if( column.val() == 'MEMBER_SIGN_UP' && (!regMemberSingUp.test(begin.val()) || !regMemberSingUp.test(end.val())) ){
				alert('가입일자 형식 예) 2022-05-25');
				return;
			}
			
			// 검색 실행
			// equalArea 작업은 column, query 파라미터 전송
			// rangeArea 작업은 column, begin, end 파라미터 전송
			if( column.val() == 'MEMBER_NAME' || column.val() == 'MEMBER_PHONE' || column.val() == 'MEMBER_ROAD_ADDRESS') {
				location.href="${contextPath}/admin/searchMember?column=" + column.val() + "&query=" + query.val() + "&value=" + ${value};
			} else {
				location.href="${contextPath}/admin/searchMember?column=" + column.val() + "&begin=" + begin.val() + "&end=" + end.val() + "&value=" + ${value};
			}
			
		})
		
	}
	
	function fnSearchAll(){
		$('#btnSearchAll').on('click', function(){
			location.href="${contextPath}/admin/listMember?value=${value}";
		})
	}
	
	
	function fnAreaChoice(){
		
		// 초기 : 모두 숨김
		$('#equalArea, #rangeArea').css('display', 'none');
	
		// 선택
		$('#column').on('change', function(){
			if( $(this).val() == '' ) {
				$('#equalArea, #rangeArea').css('display', 'none');
			} else if( $(this).val() == 'MEMBER_NAME' || $(this).val() == 'MEMBER_PHONE' || $(this).val() == 'MEMBER_ROAD_ADDRESS' ) {
				$('#btnSearchSection').css('padding-right', '-75px');
				$('#equalArea').css('display', 'inline');
				$('#rangeArea').css('display', 'none');
				$('#selectSection').css('padding-left', '122px');
			} else if ( $(this).val() == 'MEMBER_SIGN_UP'){
				$('#selectSection').css('padding-left', '-122px');
				$('#equalArea').css('display', 'none');
				$('#rangeArea').css('display', 'inline');
				$('#btnSearchSection').css('padding-right', '74px');
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
 		padding-right: 20px;
 			
 	}
 	.link, .unlink{
 		padding-left: 8px;
 	}
 	
</style>
</head>
<body>
	
	
	
	<div class="form-group">
    <select id="pageUnit" name="pageUnit" onchange="Change(1)">
        <option value="">:::선택:::</option>
        <option value="10">10명씩 보기</option>			
        <option value="20">20명씩 보기</option>
        <option value="30">30명씩 보기</option>
        <option value="40">40명씩 보기</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;
    페이지별검색수: ${value}        
	&nbsp;&nbsp;
	활동회원수: ${totalRecord}명
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="전체 활동 회원 조회" id="btnSearchAll">&nbsp;&nbsp;&nbsp;<input type="button" value="회원 추가" id="btnInsert">&nbsp;&nbsp;&nbsp;<input type="button" value="관리자 메인 페이지" id="btnManageMain">
	</div>
	
	<br>	
				
	<form id="f" action="${contextPath}/admin/removeCheckMember?value=${value}" method="post">
		<table border="1" class="table">
			<thead>
				<tr>
					<th width="1%" height="50%"><input type="checkbox" name="checkAll" class="blind checkAll" id="checkAll"></th>
					<th width="5%">번호</th>
					<th width="9%">이름</th>
					<th width="15%">전화번호</th>
					<th width="20%">이메일</th>
					<th width="30%">주소</th>
					<th width="">회원가입일</th>
					<th width="8%">회원추방</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${members}" var="member" varStatus="vs">
					<tr>
						<td><input type="checkbox" name="check" class="blind checkOne" value="${member.memberNo}"></td>
						<td>${beginNo - vs.index}</td>
						<td><a href="${contextPath}/admin/detailMember?memberNo=${member.memberNo}&value=${value}">${member.memberName}</a></td>
						<td>${member.memberPhone}</td>
						<td>${member.memberEmail}</td>
						<td>${member.memberRoadAddress}</td>
						<td><fmt:formatDate value="${member.memberSignUp}" pattern="yyyy-MM-dd" /></td>					
						<td><a href="${contextPath}/admin/removeMember?memberNo=${member.memberNo}&value=${value}" onclick="return confirm('정말 추방하시겠습니까?')"><i class="fa-solid fa-circle-xmark"></i></a></td>					
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr class="tfoot">
					<td colspan="8" id="tfoot">
						${paging}
					</td>
				</tr>
			</tfoot>
		</table>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="return confirm('정말 추방하시겠습니까?')">회원 선택 추방</button>
	</form>
	
	<div id="search">
		<div id="search_inner">
			<form id="searchForm" method="get">
				<span id="selectSection">
					<select name="column" id="column">
						<option value="">:::선택:::</option>
						<option value="MEMBER_NAME">이름</option>
						<option value="MEMBER_PHONE">전화번호</option>
						<option value="MEMBER_ROAD_ADDRESS">주소</option>
						<option value="MEMBER_SIGN_UP">가입일자</option>
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