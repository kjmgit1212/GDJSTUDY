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
<style>
   @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
   
   * {
      font-family: 'Noto Sans KR', sans-serif;
   }

   h1 {
      color: #4e4c4c;
   }
   
   .td {
      font-size: 20px;
      font-weight: 700;
      font-family: inherit;
      padding: 8px;
   }
   
   .td1 {
      color: lime;
   }

   .td1:hover {
      color: blue;
      cursor: pointer;
   }
   
   .td2 {
      color: red;
   }
   
   .td2:hover {
      cursor: not-allowed;
   }
   
   .door {
      font-size: 40px;
      text-align: left;
      color: #00ced1;
   }
   .divBody {
      text-align: center;
      padding-top: 170px;
  
   }
   .divSeatTableSmall {
      display: inline-block;
      border: solid #4e4c4c;
      background-color: #ecece5;
      padding: 10px 10px 10px 10px;
   }
   .doorText {
      text-align: left;
   }
   .divForm {
      text-align: left;
      display: inline-block;
      
   }
   
   .divButton {
      text-align: left;
      font-size: 20px;
      font-weight: 600;
   }
   
   tbody, thead {
      background-color:
   }
   
   .back {
      font-size: 30px;
      font-weight: 600;
      color: blue;
   }
   
   .hdoor {
      color: #00ced1;
   }
   
   .formBtn {
      display: none;
   }
   
   .bt:hover {
      cursor: pointer;
   }
   
   input[type=text] {
      width: 20;
      height: 20;
      font-size: 18px;
      border: 0;
   }
   
   caption {
      color: blue;
      font-size:25px;
      font-weight: 600;
   }
   
   table {
      border: 1px;
   }
   
</style>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">

   // 페이지 로드
   $(document).ready(function(){
      fnSeatCheck();
      fnCheckOut();
      fnSeatConfirm();
      fnSeatRenew();
   })
   
   
   // 함수
   
   
   // 4. 좌석갱신
   function fnSeatRenew(){
      $('#btnSeatRenew').on('click', function(){
         $.ajax({
            url: '${contextPath}/seat/seatRenew',
            type: 'GET',
            dataType: 'json',
            success: function(obj){
               location.href='${contextPath}/seat/seatPage';
               alert('갱신되었습니다.');
            },
            error: function(jqXHR){
               alert('실패했습니다.');
            }
         })
         
      })
   }
   
   
   
   // 3. 좌석확인
   function fnSeatConfirm(){
      $('#btnConfirm').on('click', function(){
         $.ajax({
            url: '${contextPath}/seat/seatConfirm',
            type: 'GET',
            data: $('#formConfirm').serialize(),
            dataType: 'json',
            success: function(obj){
               if(obj.res != null) {
                  alert(obj.res.memberName + '님의 좌석번호는 ' + obj.res.seatNo + '번 입니다.');
               } else {
                  alert('일치하는 좌석 정보가 없습니다.');
               }
            },
            error: function(jqxhr){
               alert('아이디를 입력하세요.');
            }
         })
      })
      
   }

   // 2. 퇴실
   function fnCheckOut(){
         $('#btnCheckOut').on('click', function(){

               $.ajax({
                  url: '${contextPath}/seat/seatCheckOut',
                  type: 'GET',
                  data: $('#formCheckOut').serialize(),
                  dataType: 'json',
                  success: function(obj){
                     if(obj.res > 0) {
                        alert('퇴실 처리되었습니다.');
                        location.href='${contextPath}/seat/seatPage';
                     } else {
                        alert('회원 번호를 찾을 수 없습니다.');
                     }
                  },
                  error: function(jqxhr){
                     alert('회원 번호를 입력하세요.');
                  }
               })
            
         })
      
   }
   
   // 1. 좌석 번호 가져와서 seatStatus 파악하기.
   
   function fnSeatCheck(){
      $('.td').on('click', function(){
         $.ajax({
            url: '${contextPath}/seat/seatCheck',
            type: 'GET',
            data: 'seatNo=' + $(this).text(),
            dataType: 'json',
            success: function(obj){
               if(obj.status == 1){
                  alert('이미 이용중인 좌석이 있습니다.');
                  return;
               } else if(obj.status == 0){
                  if(obj.seats.seatStatus == 1) {
                     if(confirm(obj.seats.seatNo + '번 좌석을 사용하시겠습니까?')){
   
                        alert('예약되었습니다. 좌석코드는 ' + obj.code + '입니다. 출력된 번호표를 가져가세요.');
                        location.href='${contextPath}/seat/upSeatStatus?seatNo=' + obj.seats.seatNo;
                     }
                  } else if(obj.seats.seatStatus == 2){
                     alert('이미 이용중인 좌석입니다. 다른 좌석을 선택해주세요.');
                  }                  
               }

            }
            
         })
         
      })
   }
   
</script>

</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>

   <div class="divBody">
   <br>
      <h1>좌석을 선택하세요</h1>
      <br>
      <div class="divSeatTableSmall">
          <table>
             <caption>열람실</caption>
             
             <thead>
                <tr>
                   <td colspan="11" class="door"><i class="fa-solid fa-door-closed door"></i></td>
                </tr>
             </thead>
             <tbody class="tbody">
                
                   <tr >
                      <td class="hdoor"><div class="doorText" style="font-weight:700">출입문</div></td>
                      <td colspan="5"></td>
                      <c:forEach begin="0" end="4" items="${seats}" var="seat">
                      
                            
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                         <td colspan="6"></td>
                      <c:forEach begin="5" end="9" items="${seats}" var="seat">
                      
                            
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                      <c:forEach begin="10" end="14" items="${seats}" var="seat">
                      
                      
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      <c:forEach begin="15" end="19" items="${seats}" var="seat">
                      
                      
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   
                   <tr>
                      <c:forEach begin="20" end="24" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td></td>
                      <c:forEach begin="25" end="29" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                      <c:forEach begin="30" end="34" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td></td>
                      <c:forEach begin="35" end="39" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                      <c:forEach begin="40" end="44" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td></td>
                      <c:forEach begin="45" end="49" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                      <td colspan="11">&nbsp;</td>
                   </tr>
                   <tr>
                      <td colspan="11">&nbsp;</td>
                   </tr>
                   
                   <tr>
                      <c:forEach begin="50" end="54" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td></td>
                      <c:forEach begin="55" end="59" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                      <c:forEach begin="60" end="64" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td></td>
                      <c:forEach begin="65" end="69" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                      <c:forEach begin="70" end="74" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td></td>
                      <c:forEach begin="75" end="79" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                      <c:forEach begin="80" end="84" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td></td>
                      <c:forEach begin="85" end="89" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   
                   <tr>
                      <c:forEach begin="90" end="94" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                      <td></td>
                      <c:forEach begin="95" end="99" items="${seats}" var="seat">
                         <c:if test="${seat.seatStatus == 1}">
                            <td class="td td1"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                         <c:if test="${seat.seatStatus == 2}">
                            <td class="td td2"><i class="fa-solid fa-chair"></i>${seat.seatNo}</td>
                         </c:if>
                      </c:forEach>
                   </tr>
                   <tr>
                      <td colspan="11" class="back">back</td>
                   </tr>
                   <tr>
                      <td colspan="11">&nbsp;</td>
                   </tr>
                   
             </tbody>
             <tfoot>
                <tr>
                   <td colspan="11">   
                      <div class="divButton">
                        퇴실하기
                        <form id="formCheckOut" class="divForm">
                           <input type="text" name="seatCode" placeholder="좌석코드를 입력하세요.">
                           <input type="button" id="btnCheckOut" class="formBtn">
                           <label for="btnCheckOut">&nbsp;<i class="fa-solid fa-magnifying-glass bt"></i>&nbsp;</label>
                        </form>
                        
                        <br>
                        
                        좌석확인
                        <form id="formConfirm" class="divForm">
                           <input type="text" name="memberId" id="id2" placeholder="아이디를 입력하세요.">
                           <input type="button" value="검색" id="btnConfirm" class="formBtn">
                           <label for="btnConfirm">&nbsp;<i class="fa-solid fa-magnifying-glass bt"></i>&nbsp;</label>
                        </form>
                        
                        <br>
                        
                        좌석갱신
                        <input type="button" value="좌석 갱신" id="btnSeatRenew" class="formBtn">
                        <label for="btnSeatRenew">&nbsp;<i class="fa-solid fa-computer-mouse bt"></i>&nbsp;</label>
                     </div>
                  </td>
                </tr>
             </tfoot>
          </table>
       </div>

   
   </div>
</body>
</html>