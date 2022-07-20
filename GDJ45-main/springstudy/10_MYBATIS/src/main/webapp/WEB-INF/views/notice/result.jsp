<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script>

	if('${kind}' == 'insert'){
		// if(${res} > 0){     // ${res} 없이 처리된다면 if ( > 0)       컴파일 오류 발생으로 화면 멈춤
		if('${res}' == '1'){   // ${res} 없이 처리된다면 if ('' == '1')  컴파일 오류 없이 실패로 진행
			alert('공지사항이 등록되었습니다.');
			location.href='${contextPath}/notice/list';
		} else {
			alert('공지사항이 등록되지 않았습니다.');
			history.back();
		}
	}
	
	if('${kind}' == 'update'){
		if('${res}' == '1'){
			alert('공지사항이 수정되었습니다.');
			location.href='${contextPath}/notice/list';
		} else {
			alert('공지사항이 수정되지 않았습니다.');
			history.back();
		}
	}
	
	if('${kind}' == 'deleteOne'){
		if('${res}' == '1'){
			alert('공지사항이 삭제되었습니다.');
			location.href='${contextPath}/notice/list';
		} else {
			alert('공지사항이 삭제되지 않았습니다.');
			history.back();
		}
	}
	
	if('${kind}' == 'deleteList'){
		if('${res}' > '0'){
			alert('선택한 공지사항이 모두 삭제되었습니다.');
			location.href='${contextPath}/notice/list';
		} else {
			alert('선택한 공지사항이 삭제되지 않았습니다.');
			history.back();
		}
	}

</script>