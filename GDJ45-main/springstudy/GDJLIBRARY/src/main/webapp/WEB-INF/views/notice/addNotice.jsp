<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GDJLibrary</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="../resources/js/jquery-3.6.0.js"></script>
<script src="../resources/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="../resources/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../resources/summernote-0.8.18-dist/summernote-lite.css"/>
<script>

   $(function(){
      fnAddSubmit();
      fnSummernote();
      fnModifySubmit();
      fnFileCheck();
      fnRemoveFileAttach();
      fnCancel();
   });
   
   // 수정페이지에서 취소 버튼 누르면 공지사항 페이지로 가기
   function fnCancel() {
      $('#btnCancel').on('click', function(){
         if(confirm('수정안하시겠습니까?')){
            location.href='${contextPath}/notice/noticePage';
         }
      });
   }

   // 기존 첨부파일 삭제하기(하나씩)
   var removeFileCount = 0;
   function fnRemoveFileAttach() {
      $(document).on('click', '.remove_fileAttach', function(event){
         if(confirm($(this).data('origin') + ' 파일을 삭제하시겠습니까?')){
            $.ajax({
               url: '${contextPath}/notice/removeFileAttach?noticeFileAttachNo=' + $(this).data('file_attach_no') + '&noticeNo=' + $(this).data('notice_no'),
               type: 'get',
               dataType: 'json',
               success: function(obj){
                  if(obj.res > 0){
                     removeFileCount += 1;
                     alert('파일이 삭제되었습니다.');
                     $('.fileAttaches_wrap').empty();
                     if(obj.fileAttaches == ''){
                        $('.fileAttaches_wrap').append($('<div>').text('등록한 첨부 파일이 없습니다.'));
                     } else if(obj.fileAttaches != ''){
                        $.each(obj.fileAttaches, function(i, fileAttach){
                           $('.fileAttaches_wrap').append($('<div class="remove_files">').html('<span class="remove_fileAttach" data-origin="' + fileAttach.noticeFileAttachOrigin + '" data-file_attach_no="' + fileAttach.noticeFileAttachNo + '" data-notice_no="' + fileAttach.noticeNo + '"><i class="fa-solid fa-folder-minus"></i>&nbsp;&nbsp;' + fileAttach.noticeFileAttachOrigin + '</span>'));
                        });                        
                     }
                  } else {
                     alert('파일 삭제에 실패했습니다.');
                  }   
               },
               error: function(jqXHR){
                  console.log(jqXHR.responseText);
               }
            });
         } else {
            event.preventDefault();
            return false;
         }
      });
   }
   
   
   
   
   // 첨부파일 사전점검(확장자, 크기)
   function fnFileCheck(){
      $('.files').on('change', function(){
         // 첨부 규칙
         let regExt = /(.*)\.(pdf|hwp)$/;
         let maxSize = 1024 * 1024 * 10;  // 하나당 최대 크기
         // 첨부 가져오기
         let files = $(this)[0].files;
         // 각 첨부의 순회
         for(let i = 0; i < files.length; i++) {
            // 확장자 체크
            if( regExt.test(files[i].name) == false) {
               alert('pdf, hwp만 첨부할 수 있습니다.');
               $(this).val('');  // 첨부된 파일이 모두 없어짐
               return;
            }
            // 크기 체크
            if(files[i].size > maxSize){
               alert('10MB 이하의 파일만 첨부할 수 있습니다.');
               $(this).val('');  // 첨부된 파일이 모두 없어짐
               return;
            }
         }
      
      });
   }
   
   
   
   function fnSummernote(){
      $('.content').summernote({
         width: 850,
         height: 650,
         lang: 'ko-KR',
         // 툴바 수정
         // https://summernote.org/deep-dive/#custom-toolbar-popover
         toolbar: [
             // [groupName, [list of button]]
             ['fontname', ['fontname']],
             ['fontsize', ['fontsize']],
             ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
             ['color', ['forecolor','color']],
             ['table', ['table']],
             ['para', ['ul', 'ol', 'paragraph']],
             ['height', ['height']],
             ['insert',['picture','link','video']],
             ['view', ['fullscreen', 'help']]
         ],
         fontNames: ['Arial', 'Arial Black', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
         fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','36','48','72'],
         callbacks: {
            onImageUpload: function(files) {
               //uploadSummernoteImageFile(files);
               for(let i = 0; i < files.length; i++) {
                  uploadSummernoteImageFile(files[i]);
               }
             }
         }
      });
   }

   function uploadSummernoteImageFile(files) {
      // 에디터에 첨부된 이미지
      var formData = new FormData();
      formData.append('file', files);  // files이 파라미터 이름이 된다. files는 포함된 이미지 파일을 의미한다.
      // 이미지 저장을 위한 ajax
      $.ajax({
         url: '${contextPath}/notice/uploadSummernoteImage',  
         type: 'POST',
         data: formData,
         contentType: false,
         processData: false,
         dataType: 'json',
         success: function(obj){
            if(obj.src != null){
               $('.content').summernote('insertImage', obj.src);
               // $('#content').summernote('insertImage', '/getImage/abc.jpg')라고 하면,
               // <img src="/getImage/abc.jpg"> 태그가 만들어진다.
               // /getImage/abc.jpg 요청은 WebMvcConfig에 의해서 C:/upload/summernote/abc.jpg을 읽는 것으로 처리된다.                                 
            }
         }
      });
   }
   

   function fnAddSubmit(){
      $('#f1').on('submit', function(event){
         if($('#title1').val() == '' || $('#content1').val() == ''){
            alert('제목과 내용을 전부 입력해주세요.');
            event.preventDefault();
            return false;
         }
         return true;
      });
   }

   
   function fnModifySubmit(){
      $('#f2').on('submit', function(event){
         if($('#title2').val() ==  '${notice.noticeTitle}' && $('#content2').val() == '${notice.noticeContent}' && $('#files').val() == '' && removeFileCount == 0){
            alert('수정된 내용이 없습니다.');
            event.preventDefault();
            return false;
         } else if($('#title2').val() == '' || $('#content2').val() == ''){
            alert('제목과 내용을 전부 입력해주세요.');
            event.preventDefault();
            return false;
         }
         return true;
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
   .addNotice_wrap, .modifyNotice_wrap {
      width: 860px;
/*       margin-top: 30px;
      margin-bottom: 70px; */
      margin: 200px auto;
      padding-left: 15px;
      
   }
   .title_wrap {
      width: 100%;
      padding-left: 10px;
      font-size: 25px;
      height: 40px;
      line-height: 40px;
   }
   .title {
      display: inline-block;
      margin-left: 20px;
      height: 32px;
      padding-left: 10px;
      
      /* line-height: 40px;  */
   }
   .content_wrap {
      width: 100%; 
      margin-top: 30px;
   }
   .btn_wrap {
      width: 100%; 
      text-align: center;
      margin-top: 20px;
      margin-bottom: 50px;
   }
   .btn_wrap button, #btnCancel {
      border: 1px solid #7c7c7c;
      border-radius: 10px;
      width: 100px;
      height: 40px;
      color: white;
      background-color: #4390de;
      font-size: 16px;
      cursor: pointer;
   }
   #btnCancel {
      width: 70px;
   }
   .add_file {
      margin-bottom: 4px;
   }
   .remove_files {
      margin-bottom: 5px;
   }
   .remove_fileAttach {
      cursor: pointer;
   }
</style>
</head>
<body>
   
   <jsp:include page="../layout/header.jsp"></jsp:include><br><br><br>
   
   <c:if test="${res eq 'add'}">
      <div class="addNotice_wrap">
         <form id="f1" action="${contextPath}/notice/addNotice" method="post" enctype="multipart/form-data">
   
            <div class="title_wrap">
               제목 <input type="text" name="title" class="title" id="title1" size="105">
            </div>
            <div class="content_wrap">
               <textarea name="content" class="content" id="content1"></textarea><br><br>
               <input type="file" name="files" class="files" multiple="multiple">
               <div class="btn_wrap">
                  <button>작성완료</button>
               </div>
            </div>
         </form>
      </div>
   </c:if>
   
   
   <c:if test="${res eq 'modify'}">
      <div class="modifyNotice_wrap">
         <form id="f2" action="${contextPath}/notice/modifyNotice" method="post" enctype="multipart/form-data">
         
            <!-- <span class="title_wrap">제목</span><input type="text" name="title" id="title" size="80"><br> -->
   
            <div class="title_wrap">
               제목 <input type="text" name="title" class="title" id="title2" size="105" value="${notice.noticeTitle}">
            </div>
            <div class="content_wrap">
               <textarea name="content" class="content" id="content2">${notice.noticeContent}</textarea><br><br>
               <div class="add_file">첨부 추가</div>
               <input type="file" name="files" class="files" id="files" multiple="multiple">
               <div class="btn_wrap">
                  <input type="hidden" name="noticeNo" value="${notice.noticeNo}">
                  <button>수정완료</button>&nbsp;&nbsp;<input type="button" id="btnCancel" value="취소">
               </div>
            </div>
         </form>
         
         <h3>첨부목록 삭제</h3>
         <div class="fileAttaches_wrap">
            <c:if test="${empty fileAttaches}">
               <div>등록한 첨부 파일이 없습니다.</div>
            </c:if>
            <c:if test="${not empty fileAttaches}">
               <c:forEach items="${fileAttaches}" var="fileAttach">
                  <div class="remove_files"><span class="remove_fileAttach" data-origin="${fileAttach.noticeFileAttachOrigin}" data-file_attach_no="${fileAttach.noticeFileAttachNo}" data-notice_no="${fileAttach.noticeNo}"><i class="fa-solid fa-folder-minus"></i>&nbsp;&nbsp;${fileAttach.noticeFileAttachOrigin}</span></div>
               </c:forEach>
            </c:if>
         </div>
      </div>
   </c:if>
 
</body>
</html>