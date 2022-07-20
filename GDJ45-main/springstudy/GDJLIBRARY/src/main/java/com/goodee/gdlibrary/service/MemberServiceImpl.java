package com.goodee.gdlibrary.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.goodee.gdlibrary.domain.DormantMemberDTO;
import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.domain.MemberLogDTO;
import com.goodee.gdlibrary.domain.SeatDTO;
import com.goodee.gdlibrary.mapper.MemberMapper;
import com.goodee.gdlibrary.util.SecurityUtils;

@PropertySource(value={"classpath:secret/secret.properties"})
@Service
public class MemberServiceImpl implements MemberService {

   private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
   @Value(value="${email.admin}") private String emailAdmin;
   @Value(value="${email.admin.password}") private String emailAdminPassword;
   @Value(value="${naver.clientId}") private String naverClientId;
   @Value(value="${naver.clientSecret}") private String naverClientSecret;
   @Autowired
   private MemberMapper memberMapper;

   //아이디 중복 확인
   @Override
   public Map<String, Object> idCheck(String memberId) {
      Map<String, Object> map = new HashMap<>();
      map.put("resMember", memberMapper.selectMemberById(memberId));
      map.put("resDormantMember", memberMapper.selectDormantMemberById(memberId));

      return map;
   }

   //이메일 중복 확인
   @Override
   public Map<String, Object> emailCheck(String memberEmail) {
      Map<String, Object> map = new HashMap<>();
      map.put("resMember", memberMapper.selectMemberByEmail(memberEmail));
      map.put("resDormantMember", memberMapper.selectDormantMemberByEmail(memberEmail));

      return map;
   }

   //이메일 인증 코드
   @Override
   public Map<String, Object> sendAuthCode(String memberEmail) {
      
      String authCode = SecurityUtils.authCode(6);
      logger.info(authCode);

      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com"); 
      props.put("mail.smtp.port", "587"); 
      props.put("mail.smtp.auth", "true"); 
      props.put("mail.smtp.starttls.enable", "true"); 

      final String USERNAME = emailAdmin;
      final String PASSWORD = emailAdminPassword; 

      Session session = Session.getInstance(props, new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(USERNAME, PASSWORD);
         }
      });

      try {

         Message message = new MimeMessage(session);

         message.setHeader("Content-Type", "text/plain; charset=UTF-8");
         message.setFrom(new InternetAddress(USERNAME, "GDJLibrary"));
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(memberEmail));
         message.setSubject("GDJLibrary 인증메일입니다.");
         message.setText("인증번호는 " + authCode + "입니다.");

         Transport.send(message);

      } catch (Exception e) {
         e.printStackTrace();
      }

      Map<String, Object> map = new HashMap<>();
      map.put("authCode", authCode);
      return map;
   }

   //회원가입
   @Override
   public void signIn(HttpServletRequest request, HttpServletResponse response) {

      String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      String memberPw = SecurityUtils.sha256(request.getParameter("memberPw"));
      String memberName = SecurityUtils.xss(request.getParameter("memberName"));
      String memberPostcode = SecurityUtils.xss(request.getParameter("memberPostcode"));
      String memberRoadAddress = SecurityUtils.xss(request.getParameter("memberRoadAddress"));
      String memberDetailAddress = SecurityUtils.xss(request.getParameter("memberDetailAddress"));
      String memberPhone = SecurityUtils.xss(request.getParameter("memberPhone"));
      String memberEmail = SecurityUtils.xss(request.getParameter("memberEmail"));
      String promotion = request.getParameter("promotion");

      int memberAgreeState = 1; // 필수 동의

      if (promotion.equals("promotion")) {
         memberAgreeState = 2; // 필수 + 프로모션 동의
      }

      MemberDTO member = MemberDTO.builder()
            .memberId(memberId)
            .memberPw(memberPw)
            .memberName(memberName)
            .memberPostcode(memberPostcode)
            .memberRoadAddress(memberRoadAddress)
            .memberDetailAddress(memberDetailAddress)
            .memberAgreeState(memberAgreeState)
            .memberPhone(memberPhone)
            .memberEmail(memberEmail)
            .build();

      int res = memberMapper.insertMemberSignIn(member);
      MemberDTO loginMember = member;
      try {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         if (res == 1) {
            request.getSession().setAttribute("loginMember", loginMember);
            out.println("<script>");
            out.println("alert('회원 가입되었습니다.')");
            out.println("location.href='" + request.getContextPath() + "/'");
            out.println("</script>");
            out.close();
         } else {
            out.println("<script>");
            out.println("alert('회원 가입에 실패했습니다.')");
            out.println("history.back()");
            out.println("</script>");
            out.close();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   //로그인
   @Override
   public MemberDTO login(HttpServletRequest request) {
      String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      String memberPw = SecurityUtils.sha256(request.getParameter("memberPw"));

      MemberDTO member = MemberDTO.builder()
            .memberId(memberId)
            .memberPw(memberPw)
            .build();

      MemberDTO loginMember = memberMapper.selectMemberByIdPw(member);

      if (loginMember != null) {
         request.getSession().setAttribute("loginMember", loginMember);
         MemberLogDTO log = memberMapper.selectMemberLog(memberId);
         if(log == null) {
            memberMapper.insertMemberLog(memberId);
         } else {
            memberMapper.updateMemberLog(memberId);
         }
         
      }

      return loginMember;
   }

   //자동 로그인
   @Override
   public void keepLogin(HttpServletRequest request) {

      Date memberSessionLimit = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7));
      String memberSessionId = request.getSession().getId();
      String memberId = request.getParameter("memberId");

      MemberDTO member = MemberDTO.builder()
            .memberId(memberId)
            .memberSessionId(memberSessionId)
            .memberSessionLimit(memberSessionLimit)
            .build();

      memberMapper.updateSessionInfo(member);

   }

   //자동 로그인 세션 확인
   @Override
   public MemberDTO getMemberBySessionId(String memberSessionId) {
      return memberMapper.selectMemberBySessionId(memberSessionId);
   }

   //아이디 찾기
   @Override
   public Map<String, Object> findId(MemberDTO member) {
      Map<String, Object> map = new HashMap<>();
      MemberDTO m1 = memberMapper.selectMemberFindId(member);
      DormantMemberDTO m2 = memberMapper.selectDormantMemberFindId(member);
      map.put("res1", m1);
      map.put("res2", m2);
      

      return map;
   }

   //비밀번호 찾기 아이디/이메일 확인
   @Override
   public Map<String, Object> findPwCheckIdEmail(HttpServletRequest request) {
      Map<String, Object> map = new HashMap<>();

      String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      String memberEmail = SecurityUtils.xss(request.getParameter("memberEmail"));

      MemberDTO member = MemberDTO.builder()
            .memberId(memberId)
            .memberEmail(memberEmail)
            .build();

      MemberDTO m1 = memberMapper.selectMemberFindPwCheckIdEmail(member);
      DormantMemberDTO m2 = memberMapper.selectDormantMemberFindPwCheckIdEmail(member);
      map.put("res1", m1);
      map.put("res2", m2);

      return map;
   }

   //비밀번호 변경
   @Override
   public void changePw(HttpServletRequest request, HttpServletResponse response) {
      // String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      String memberId = request.getParameter("memberId");
      String memberPw = SecurityUtils.sha256(request.getParameter("memberPw"));

      MemberDTO member = MemberDTO.builder()
            .memberId(memberId)
            .memberPw(memberPw)
            .build();
      
      MemberDTO m1 = memberMapper.selectMemberById(memberId);
      DormantMemberDTO m2 = memberMapper.selectDormantMemberById(memberId);

      if(m1 != null) {
         int res = memberMapper.updateMemberChangePw(member);
         try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (res == 1) {
               out.println("<script>");
               out.println("alert('비밀번호가 수정되었습니다!')");
               out.println("location.href='" + request.getContextPath() + "/member/loginPage'");
               out.println("</script>");
               out.close();
            } else {
               out.println("<script>");
               out.println("alert('비밀번호가 수정되지 않았습니다.')");
               out.println("history.back()");
               out.println("</script>");
               out.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      } 
      
      else if (m2 != null) {
         int res = memberMapper.updateDormantMemberChangePw(member);
         try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (res == 1) {
               out.println("<script>");
               out.println("alert('비밀번호가 수정되었습니다!')");
               out.println("location.href='" + request.getContextPath() + "/member/loginPage'");
               out.println("</script>");
               out.close();
            } else {
               out.println("<script>");
               out.println("alert('비밀번호가 수정되지 않았습니다.')");
               out.println("history.back()");
               out.println("</script>");
               out.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   //회원 정보 수정 페이지 
   @Override
   public void modifyPage(String memberId, Model model) {
      MemberDTO member = memberMapper.selectMemberById(memberId);
      model.addAttribute("member", member);

   }

   //회원 정보 수정
   @Override
   public void modify(HttpServletRequest request, HttpServletResponse response) {
      String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      String memberName = SecurityUtils.xss(request.getParameter("memberName"));
      String memberPostcode = SecurityUtils.xss(request.getParameter("memberPostcode"));
      String memberRoadAddress = SecurityUtils.xss(request.getParameter("memberRoadAddress"));
      String memberDetailAddress = SecurityUtils.xss(request.getParameter("memberDetailAddress"));
      String memberPhone = SecurityUtils.xss(request.getParameter("memberPhone"));

      MemberDTO member = MemberDTO.builder()
            .memberId(memberId)
            .memberName(memberName)
            .memberPostcode(memberPostcode)
            .memberRoadAddress(memberRoadAddress)
            .memberDetailAddress(memberDetailAddress)
            .memberPhone(memberPhone)
            .build();

      int res = memberMapper.updateMemberInfo(member);

      // 응답
      try {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out = response.getWriter();
         if (res == 1) {
            out.println("<script>");
            out.println("alert('정보가 수정되었습니다.')");
            out.println("location.href='" + request.getContextPath() + "/member/myPage'");
            out.println("</script>");
            out.close();
         } else {
            out.println("<script>");
            out.println("alert('정보 수정이 실패했습니다.')");
            out.println("history.back()");
            out.println("</script>");
            out.close();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
   
   //회원 탈퇴
   @Transactional
   @Override
   public void memberDelete(String memberId, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

      MemberDTO member = memberMapper.selectMemberById(memberId);

      response.setContentType("text/html; charset=UTF-8");
      try {
         PrintWriter out = response.getWriter();
         if (member != null) {
            int res1 = memberMapper.insertSignOut(member);
            int res2 = memberMapper.deleteMember(memberId);
            if (res1 == 1 && res2 == 1) {
               MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
               if (loginMember != null) {
                  session.removeAttribute("loginMember");
                  session.invalidate();
               }
               out.println("<script>");
               out.println("alert('탈퇴되었습니다.')");
               out.println("location.href='" + request.getContextPath() + "/'");
               out.println("</script>");
               out.close();

            } else {
               out.println("<script>");
               out.println("alert('탈퇴 실패했습니다.')");
               out.println("history.back()");
               out.println("</script>");
               out.close();
            }

         } else {
            out.println("<script>");
            out.println("alert('비밀번호가 틀렸습니다.')");
            out.println("history.back()");
            out.println("</script>");
            out.close();
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }
   
   //회원 비밀번호 변경을 위한 비밀번호 확인
   @Override
   public void pwChangePwCheck(HttpServletRequest request, HttpServletResponse response) {
      String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      String memberPw = SecurityUtils.sha256(request.getParameter("memberPw"));
      
      MemberDTO m = MemberDTO.builder()
            .memberId(memberId)
            .memberPw(memberPw)
            .build();

      MemberDTO member = memberMapper.selectMemberByIdPw(m);
      
      response.setContentType("text/html; charset=UTF-8");
      try {
         PrintWriter out = response.getWriter();
         if(member != null) {
            out.println("<script>");
            out.println("location.href='" + request.getContextPath() + "/member/pwModifyPage'");
            out.println("</script>");
            out.close();
         } else {
            out.println("<script>");
            out.println("alert('비밀번호가 틀렸습니다.')");
            out.println("history.back()");
            out.println("</script>");
            out.close();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   
   //회원 마이페이지에서 비밀번호 변경
   @Override
   public void pwModify(HttpServletRequest request, HttpServletResponse response) {
      String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      String memberPw = SecurityUtils.sha256(request.getParameter("memberPw"));

      MemberDTO member = MemberDTO.builder()
            .memberId(memberId)
            .memberPw(memberPw)
            .build();

      int res = memberMapper.updateMemberChangePw(member);
      try {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out = response.getWriter();

         if (res == 1) {
            out.println("<script>");
            out.println("alert('비밀번호가 수정되었습니다!')");
            out.println("location.href='" + request.getContextPath() + "/member/myPage'");
            out.println("</script>");
            out.close();
         } else {
            out.println("<script>");
            out.println("alert('비밀번호가 수정되지 않았습니다.')");
            out.println("history.back()");
            out.println("</script>");
            out.close();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   //수정전 비밀번호 확인
   @Override
   public void modifyPwCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
      String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      String memberPw = SecurityUtils.sha256(request.getParameter("memberPw"));

      MemberDTO m = MemberDTO.builder()
            .memberId(memberId)
            .memberPw(memberPw)
            .build();

      MemberDTO member = memberMapper.selectMemberByIdPw(m);
      
      response.setContentType("text/html; charset=UTF-8");
      try {
         PrintWriter out = response.getWriter();
         if(member != null) {
            out.println("<script>");
            out.println("location.href='" + request.getContextPath() + "/member/modifyPage?memberId=" + memberId + "'");
            out.println("</script>");
            out.close();
         } else {
            out.println("<script>");
            out.println("alert('비밀번호가 틀렸습니다.')");
            out.println("history.back()");
            out.println("</script>");
            out.close();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }

   
   //네아로 토큰 받기 위한 URL
   @Override
   public String getNaverURL(HttpServletRequest request) {
      String apiURL = "";
      try {
         String clientId = naverClientId;
         String redirectURI = URLEncoder.encode("http://skykjm1212.cafe24.com/member/callback", "UTF-8");
         SecureRandom random = new SecureRandom();
         String state = new BigInteger(130, random).toString();
         apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
         apiURL += "&client_id=" + clientId;
         apiURL += "&redirect_uri=" + redirectURI;
         apiURL += "&state=" + state;
         request.getSession().setAttribute("state", state);
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }

      return apiURL;
   }
   

   //네아로 토큰 반환
   @Override
   public String getNaverAccessToken(HttpServletRequest request) {

      String clientId = naverClientId;
      String clientSecret = naverClientSecret;
      String code = request.getParameter("code");
      String state = request.getParameter("state");
      String apiURL = "";
      String accessToken = "";
      try {
         String redirectURI = URLEncoder.encode("http://skykjm1212.cafe24.com/", "UTF-8");
         apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
         apiURL += "client_id=" + clientId;
         apiURL += "&client_secret=" + clientSecret;
         apiURL += "&redirect_uri=" + redirectURI;
         apiURL += "&code=" + code;
         apiURL += "&state=" + state;

         try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
               br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { // 에러 발생
               br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String line;
            StringBuffer res = new StringBuffer();
            while ((line = br.readLine()) != null) {
               res.append(line);
            }

            JSONObject result = new JSONObject(res.toString());
            accessToken = result.getString("access_token");
            br.close();


         } catch (Exception e) {
            e.printStackTrace();
         }

      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }

      return accessToken;

   }

   //네아로 토큰전달하고 받은 사용자 정보
   @Override
   public Map<String, Object> getNaverUserInfo(String accessToken) {

      Map<String, Object> naverUserInfo = new HashMap<>();
      String reqURL = "https://openapi.naver.com/v1/nid/me";
      try {
         URL url = new URL(reqURL);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("POST");

         // 요청에 필요한 Header에 포함될 내용
         conn.setRequestProperty("Authorization", "Bearer " + accessToken);

         int responseCode = conn.getResponseCode();
         if (responseCode == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            StringBuffer res = new StringBuffer();

            while ((line = br.readLine()) != null) {
               res.append(line);
            }

            JSONObject result = new JSONObject(res.toString());
            JSONObject response = result.getJSONObject("response");

            String name = response.getString("name");
            String email = response.getString("email");
            String id = response.getString("id");
            String mobile = response.getString("mobile");

            naverUserInfo.put("name", name);
            naverUserInfo.put("email", email);
            naverUserInfo.put("id", id);
            naverUserInfo.put("mobile", mobile);

         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return naverUserInfo;
   }

   //네아로 로그인 세션에 올리기
   @Override
   public MemberDTO naverLogin(HttpServletRequest request, Map<String, Object> map) {
      String memberId = (String) map.get("id");
      String memberEmail = (String) map.get("email");
      String memberName = (String) map.get("name");
      String memberPhone = (String) map.get("mobile");

      MemberDTO member = MemberDTO.builder()
            .memberId(memberId)
            .memberEmail(memberEmail)
            .memberName(memberName)
            .memberPhone(memberPhone)
            .build();
      
      MemberDTO loginMember = new MemberDTO();
      if(memberMapper.selectMemberByEmail(memberEmail) == null) {
         memberMapper.insertNaverLogin(member);
         loginMember = memberMapper.selectMemberByEmail(memberEmail);
      } else {
         
         loginMember = memberMapper.selectMemberByEmail(memberEmail);
      }
      
      if (loginMember != null) {
         request.getSession().setAttribute("loginMember", loginMember);
      }
      
      return loginMember;
   }

   
   //휴면회원 취소
   @Transactional
   @Override
   public void dormantCancle(HttpServletRequest request, HttpServletResponse response) {
      String memberId = SecurityUtils.xss(request.getParameter("memberId"));
      DormantMemberDTO dormant = memberMapper.selectDormantMemberById(memberId);
      
      response.setContentType("text/html; charset=UTF-8");
      try {
         PrintWriter out = response.getWriter();
         if(dormant != null) {
            int res1 = memberMapper.insertMemberDormantCancle(dormant);
            int res2 = memberMapper.deleteDormant(memberId);
            if(res1 == 1 && res2 == 1) {
               out.println("<script>");
               out.println("alert('휴면해제되었습니다! 다시 로그인을 해주세요!')");
               out.println("location.href='" + request.getContextPath() + "/member/loginPage'");
               out.println("</script>");
               out.close();
            } else {
               out.println("<script>");
               out.println("alert('휴면해제가 실패했습니다.')");
               out.println("history.back()");
               out.println("</script>");
               out.close();
            }
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }

   //마이페이지 대여 목록
   @Override
   public List<Map<String, Object>> rentBookList(HttpServletRequest request) {
      
      // session에 올라간 loginMember에서 memberId 빼기
      MemberDTO loginMember = (MemberDTO)request.getSession().getAttribute("loginMember");
      String memberId = loginMember.getMemberId();
      
      // 사용자가 대여중인 책의 목록
      List<Map<String, Object>> rentBookList = memberMapper.selectRentBookList(memberId);
      
      // 대여중인 책 목록 결과
      List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
      
      if(rentBookList.isEmpty() == false) {
         for(Map<String, Object> map : rentBookList) {
            Map<String, Object> book = new HashMap<String, Object>();
            book.put("bookTitle", map.get("BOOK_TITLE"));
            book.put("rentDate", map.get("RENT_DATE"));
            book.put("rentExpirationDate", map.get("RENT_EXPIRATION_DATE"));
            result.add(book);
         }
      }
      
      return result;
   }
   
   
   //마이페이지 연체 목록
   @Override
   public List<Map<String, Object>> overdueBookList(HttpServletRequest request) {
      // session에 올라간 loginMember에서 memberId 빼기
      MemberDTO loginMember = (MemberDTO)request.getSession().getAttribute("loginMember");
      String memberId = loginMember.getMemberId();
      
      // 사용자가 연체중인 책의 목록
      List<Map<String, Object>> overdueBookList = memberMapper.selectOverdueBookList(memberId);
      
      // 대여중인 책 목록 결과
      List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
      
      if(overdueBookList.isEmpty() == false) {
         for(Map<String, Object> map : overdueBookList) {
            Map<String, Object> book = new HashMap<String, Object>();
            book.put("bookTitle", map.get("BOOK_TITLE"));
            book.put("rentExpirationDate", map.get("RENT_EXPIRATION_DATE"));
            result.add(book);
         }
      }
      
      return result;
   }

   //마이페이지 예약 좌석
   @Override
   public Map<String, Object> reservationSeatNo(HttpServletRequest request) {
      MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
      String memberId = loginMember.getMemberId();
      
      SeatDTO seat =  memberMapper.selectReservationSeatNo(memberId);
      
      Map<String, Object> map = new HashMap<>();
      map.put("seat", seat);
      return map;
   }
   
}