<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	
	// 페이지 로드 이벤트
	$(document).ready(function(){
		fnCovid();
	})
	
	// 함수
	function fnCovid(){
		$.ajax({
			url: '/OPENAPI/covid19InfStateSvc.do',
			type: 'get',
			dataType: 'xml',
			success: function(responseText){
				console.log(responseText);
				var recentItem = $(responseText).find('item')[0];
				console.log(recentItem);
				$('#state_dt').text($(recentItem).find('stateDt').text());
				$('#state_time').text($(recentItem).find('stateTime').text());
				$('#decide_cnt').text($(recentItem).find('decideCnt').text());
				$('#death_cnt').text($(recentItem).find('deathCnt').text());				
			}
		})
	}
	
</script>
</head>
<body>

	<h1>기준일자 <span id="state_dt"></span></h1>
	<h1>기준시간 <span id="state_time"></span></h1>
	<h1>일일확진 <span id="decide_cnt"></span></h1>
	<h1>사망 <span id="death_cnt"></span></h1>

</body>
</html>