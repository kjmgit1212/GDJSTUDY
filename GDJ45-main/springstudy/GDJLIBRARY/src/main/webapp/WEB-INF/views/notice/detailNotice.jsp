<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GDJLibrary</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

   $(function(){
      fnList();
      fnRemove();
      fnModify();
   });
   
   function fnList() {
      $('#btnList').on('click', function(){
         location.href='${contextPath}/notice/noticePage';
      });
   }
   
   function fnRemove() {
      $('#btnRemove').on('click', function(){
         if(confirm('게시글을 삭제하시겠습니까?')){
            location.href='${contextPath}/notice/removeNotice?noticeNo=' + $(this).data('notice_no');            
         }
      });
   }
   
   function fnModify() {
      $('#btnModify').on('click', function(){
         if(confirm('수정하시겠습니까?')){
            location.href='${contextPath}/notice/modifyNoticePage?noticeNo=' + $(this).next().data('notice_no');   
         }
      });
   }

</script>
<style>
   @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
   
   * {
      box-sizing: border-box;
      font-family: 'Noto Sans KR', sans-serif;
      color: #4e4c4c;
   }
   .notice_wrap {
      width: 860px;
      margin: 200px auto; 
   }
   .notice_detail_wrap {
      width: 850px;
      padding-left: 20px;
   }
   .notice_title_wrap {
      width: 100%;
      height: 55px;
      border-top: 2px solid #7c7c7c;
      padding-left: 20px;
      line-height: 55px;
      
   }
   .created_hit_wrap {
      width: 100%;
      height: 40px;
      padding-left: 20px;
      display: flex;
      border-top: 1px solid #d1d1d1;
      border-bottom: 1px solid #d1d1d1;
      line-height: 40px;
   }
   .hit_wrap {
      margin-left: 30px;
   }
   .notice_content_wrap {
      width: 100%;
      padding-left: 20px;
      padding-top: 20px;
      border-bottom: 1px solid #7c7c7c;
      padding-bottom: 30px;
   }
   .notice_top {
      width: 850px;
      text-align: center;
      font-size: 24px;
      margin-top: 40px;
      margin-bottom: 40px;
   }
   .btn_wrap {
      width: 100%;
      text-align: center;
      margin-top: 20px;
   }
   #btnList, #btnModify, #btnRemove {
/*       border: 1px solid #7c7c7c;
      border-radius: 10px;
      width: 60px;
      height: 40px;
      cursor: pointer; */
      
      border: 1px solid #7c7c7c;
      border-radius: 10px;
      width: 60px;
      height: 30px;
      color: white;
      background-color: #4390de;
      cursor: pointer;
      font-size: 16px;
   }
   #btnModify {
      display: inline-block;
      margin-left: 10px;
      margin-right: 10px;
   }
   .files_wrap {
      display: flex;
      width: 100%;
      border-bottom: 2px solid #7c7c7c;
      

   }
   .files_title {
      text-align: center;
       min-height: 150px;
      line-height: 150px; 
      width: 20%;
      background-color: #f5f5f5;
   }
   .fileList {
      width: 80%;
       padding: 40px 30px; 
   }
   .fileList_wrap {
      margin-top: 10px;
      margin-bottom: 10px;
   }
   .download_link_style {
      text-decoration: none;
   }
   .download_link_style:link, .download_link_style:visited {
      color: black;
   }
   .download_wrap {
      margin-bottom: 3px;
      margin-top: 5px;
   }
   .no_file {
      margin-top: 20px;
   }


</style>
</head>
<body>

   <jsp:include page="../layout/header.jsp"></jsp:include><br><br><br>
   
   <div class="notice_wrap">
      <div class="notice_top">
         - 공지사항 -
      </div>
      <c:if test="${empty notice}">
         <div>
            게시글이 없습니다.   
         </div>   
      </c:if>
      <c:if test="${not empty notice}">
         <div class="notice_detail_wrap">
            <div class="notice_title_wrap">
               ${notice.noticeTitle}
            </div>
            <div class="created_hit_wrap">
               <div>
                  <%-- 작성일 ${notice.noticeCreated}    --%>   
                  <span>작성일ㅣ </span><span><fmt:formatDate value="${notice.noticeCreated}" pattern="yyyy-MM-dd"/></span>   
               </div>
               <div class="hit_wrap">
                  <span>조회수ㅣ </span> ${notice.noticeHit}         
               </div>
            </div>
            <div class="notice_content_wrap">
               ${notice.noticeContent}
            </div>
            <div class="files_wrap">
               <div class="files_title">
                  첨부파일
               </div>
               <div class="fileList">
                  <c:if test="${empty fileAttaches}">
                     <div class="no_file">
                        첨부파일이 없습니다.
                     </div>
                  </c:if>
                  <c:if test="${not empty fileAttaches}">
                     <div>
                        <c:forEach items="${fileAttaches}" var="fileAttach">
                           <div class="download_wrap">
                              <a class="download_link_style" href="${contextPath}/notice/download?noticeFileAttachNo=${fileAttach.noticeFileAttachNo}"><i class="fa-solid fa-download"></i>&nbsp;&nbsp;${fileAttach.noticeFileAttachOrigin}</a>
                           </div>
                        </c:forEach>
                     </div>
                  </c:if>
               </div>
            </div>
            <div class="btn_wrap">
               <input type="button" value="목록" id="btnList">
               <c:if test="${loginMember.memberId eq 'admin'}">
                  <input type="button" value="수정" id="btnModify">
                  <input type="button" value="삭제" id="btnRemove" data-notice_no="${notice.noticeNo}">
               </c:if>
            </div>
         </div>
      </c:if>
   </div>

</body>
</html>