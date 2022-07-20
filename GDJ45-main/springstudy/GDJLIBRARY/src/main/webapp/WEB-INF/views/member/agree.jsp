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

<script>
	$(function() {

		//전체 선택
		$('#checkAll').on('click', function() {
			
			$('.checkOne').prop('checked', $('#checkAll').prop('checked'));

			if ($('#checkAll').is(':checked')) { 
				$('.item, .items').addClass('check');
			} else {
				$('.item, .items').removeClass('check');
			}
		})

		//개별 선택
		$('.checkOne').on('click', function() {

			let checkAll = true; 

			$.each($('.checkOne'), function(i, checkOne) {
				if ($(checkOne).is(':checked') == false) { 
					$('#checkAll').prop('checked', false);
					$('.items').removeClass('check');
					checkAll = false; 
					return;
				}
			})
			if (checkAll) {
				$('#checkAll').prop('checked', true);
				$('.items').addClass('check');
			}

		})

		//체크 박스는 클릭할때마다 check 클래스를 줬다 뺐었다 해야 함
		$('.item, .items').on('click', function() {
			$(this).toggleClass('check');
		})

		//다음 버튼
		$('#f').on(
				'submit',
				function(event) {
					if ($('#service').is(':checked') == false
							|| $('#privacy').is(':checked') == false) {
						alert('필수 약관에 모두 동의하세요.');
						event.preventDefault();
						return false;
					}
					return true;

				})
	})
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
   
 * {
    font-family: 'Noto Sans KR', sans-serif;
 }

.blind {
	display: none;
}

.items, .item {
	padding-left: 20px;
	background-image: url(../resources/images/uncheck.png);
	background-size: 18px 18px;
	background-repeat: no-repeat;
	margin-left:10px;
	color: #635f5f;
	cursor: pointer;
}

.check {
	background-image: url(../resources/images/check.png);
}

.h2 {
	font-size: 20px;
	color: #4a4747;
	text-align: center;
	margin: 0 auto;
	padding: 80px 0 20px 0;
}

.agree {
	border: 1px solid #e6e6e6;
	width: 650px;
	margin: 20px auto;
}
.agree:first-of-type {
	border: 0;
}

.agree > div {
	padding: 23px 19px;
	height: 124px;
	overflow: auto;
	font-size: 13px;
	color: #555;
	line-height: 23px;
}

.agree:last-of-type > div {
	height: 25px;
}

.agree > p {
	background-color: #f8f8f8;
	border-top: 1px solid #e6e6e6;
	height: 43px;
	line-height: 43px;
	margin: 0 0;
	color: #222;
	text-indent: 19px;
	font-size: 13px;
}

.agreeSubmit {
	box-sizing: border-box;
	margin: 0 auto;
	padding: 13px 14px;
	background-color: #4390de;
	font-size: 20px;
	color: #fff;
	line-height: 20px;
	border: #4390de;
	display: block;
	width: 100px;
	cursor: pointer;
	
}

</style>
</head>
<body>

	<div class="container">
		<form id="f" action="${contextPath}/member/signInPage" method="get">
		

		<h2 class="h2">홈페이지 이용약관</h2>
		<div class="agree">
			<input type="checkbox" id="checkAll" class="blind checkAll">
			<label for="checkAll" class="items">모두 동의합니다.</label>
		</div>
		<div class="agree">
			<div>
				제1장 총칙<br>
	            제1조 목적<br>
	            이 약관은 구디아카데미 자바과정 팀 GDJLibrary 도서관 통합홈페이지가 제공하는 통합회원서비스(이하 "서비스"라 한다)를 이용함에 있어 이용자와 도서관간의 권리·의무 및 책임사항과 기타 필요한 사항을 규정함을 목적으로 한다.
	            <br><br>
	            제2조 약관의 효력 및 변경<br>
	            이 약관은 서비스 화면에 게시하거나 기타의 방법으로 이용자에게 공시되며, 이를 동의한 이용자가 서비스에 가입함으로써 효력이 발생한다.<br>
	            GDJLibrary는 필요하다고 인정되는 경우 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 서비스 화면에 이용자가 직접 확인할 수 있도록 공지한다.<br>
	            이용자는 변경된 약관에 동의하지 않을 경우 서비스 이용을 중단하고 본인의 회원등록을 취소할 수 있으며, 계속 사용하는 경우에는 약관 변경에 동의한 것으로 간주되며 변경된 약관은 전항과 같은 방법으로 효력 이 발생한다.<br>
	            이용자가 이 약관의 내용에 동의하는 경우 GDJLibrary의 서비스 제공행위 및 이용자의 서비스 이용행위에는 이 약관이 우선적으로 적용된다.<br>
	           
	            <br>
	            제3조 용어의 정의<br>
	            이 약관에서 사용하는 용어의 정의는 다음과 같다.<br>
	            이용자 : 서비스에 접속하여 도서관 통합홈페이지가 제공하는 서비스를 받는 회원<br>
	            회원 : 서비스에 접속하여 이 약관에 동의하고, ID(아이디)와 PASSWORD(비밀번호)를 발급 받아 확인 절차를 거친 자<br>
	            ID(아이디) : 회원 식별과 회원의 서비스 이용을 위하여 이용자가 선정하고 도서관 통합홈페이지에서 승인하는 영문자와 숫자, 특수문자의 조합(하나의 이메일에 하나의 ID만 발급, 이용 가능) PASSWORD(비밀번호) : 회원의 정보 보호를 위해 이용자 자신이 설정한 문자와 숫자의 조합<br>
	            이용해지 : 도서관 통합홈페이지 또는 회원이 서비스 이용 이후 그 이용계약을 종료시키는 의사표시<br>
	            이 약관에서 사용하는 용어의 정의는 제1항에서 정하는 것을 제외하고는 관계법령 및 서비스별 안내에서 정하는 바에 의한다.<br>
	            <br>
	            제2장 서비스 이용계약<br>
	            제4조 이용계약의 성립<br>
	            이용계약은 이용자의 약관내용에 대한 동의와 이용자의 이용신청에 대한 도서관 통합홈페이지 관리자의 승낙으로 성립 한다.<br>
	            
	            제5조 이용신청<br>
	            이용신청은 서비스의 회원정보 화면에서 이용자가 도서관 통합홈페이지 회원가입 메뉴에서 요구하는 가입신청 양식에 개인의 신상정보를 입력하는 방식으로 신청한다.<br>
	            
	            <br>
	            GDJLibrary은 다음에 해당하는 경우에는 이용신청을 승낙하지 아니할 수 있다.<br>
	            <br>
	            본인의 실명으로 신청하지 않았을 때<br>
	            다른 사람의 명의를 사용하여 신청하였을 때<br>
	            신청서의 내용을 허위로 기재하였을 때<br>
	            사회의 안녕 질서 또는 미풍양속을 저해할 목적으로 신청하였을 때<br>
	            기타 도서관 통합홈페이지가 정한 이용신청 요건이 미비 되었을 때<br>
	            제7조 계약사항의 변경<br>
	            회원은 회원정보관리를 통해 언제든지 자신의 정보를 열람하고 수정할 수 있다. 회원은 이용신청 시 기재 한 사항이 변경된 경우에는 수정을 하여야 하며, 수정하지 아니하여 발생하는 문제의 책임은 회원에게 있다.
			</div>
			<p>
				<input type="checkbox" id="service" class="blind checkOne">
				<label for="service" class="item">이용 약관에 동의합니다.(필수)</label>
			</p>

		</div>

		<div class="agree">
			<div>
				수집하는 개인정보의 항목<br>
				<br>		
	            가. GDJLibrary 도서관에서는 각종 서비스의 제공을 위해 최초 회원가입 당시 아래와 같은 개인정보를 수집하고 있습니다. 기본적인 회원 서비스 제공을 위한 필수항목 정보를 수집하고 있습니다.<br>
	            [일반 회원가입]<br>
	              - 필수항목 : 성명, 아이디, 비밀번호, 연락처(휴대폰 번호), 주소, 이메일<br>
	            <br>
	            개인정보 수집 목적<br>
	            <br>
	            GDJLibrary 도서관은 다음과 같은 이유로 개인정보를 수집합니다.<br>
	            가. 대여 회원관리 - 도서관 대여 회원 서비스 이용 및 제한적 본인 확인제에 따른 본인확인<br>
	            <br>
	            정보주체는 개인정보의 수집·이용목적에 대한 동의를 거부할 수 있으며, 동의 거부 시 도서관 통합홈페이지에 회원가입이 되지 않으며, 도서관 통합홈페이지에서 제공하는 서비스를 이용할 수 없습니다.<br>
			</div>
				<p>
					<input type="checkbox" id="privacy" class="blind checkOne">
					<label for="privacy" class="item">개인 정보 수집에 동의합니다.(필수)</label>
				</p>

		</div>

		<div class="agree">
			<div>서비스와 관련된 소식, 이벤트 안내, 고객 혜택 등 다양한 정보를 제공합니다.</div>
			<p>
				<input type="checkbox" name="promotion" value="promotion" id="promotion" class="blind checkOne"> 
				<label for="promotion" class="item">프로모션 정보 수신에 동의합니다.(선택)</label>
			</p>

		</div>

		<input type="submit" value="다음" class="agreeSubmit">

	</form>
	</div>
	

</body>
</html>