<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 파라미터 res는 EL로 ${param.res} 표현한다. -->

<script>
	if (${param.res} > 0) {
		alert("게시글이 삽입되었습니다.");
		location.href = '/DBCP_PRAC/list.do';
	} else {
		alert("게시글이 삽입되지 않았습니다.");
		history.back();
	}
</script>