package com.goodee.gdlibrary.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.gdlibrary.domain.BookDTO;
import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.domain.OverdueDTO;
import com.goodee.gdlibrary.domain.RentDTO;
import com.goodee.gdlibrary.mapper.BookManageMapper;

@Service
public class BookManageServiceImpl implements BookManageService {

	@Autowired
	private BookManageMapper bookManageMapper;
	
	
	// 상세페이지에서 책 대여하기
	// 한번에 한권만 가능
	@Override
	public void rentBookByNo(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("bookNo"));
		Long bookNo = Long.parseLong(opt.orElse("0"));
		
		// 대여하기 위해 선택한 책의 존재 여부 확인하기
		BookDTO book = bookManageMapper.selectBookByNo(bookNo);
		
		
		// session에 올라간 loginMember에서 memberId 빼기
		MemberDTO loginMember = (MemberDTO)request.getSession().getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		
		
		
		if(bookNo == 0) {
			
			try {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('대여할 책을 선택해주세요.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			
			if(book != null) {
				
				// 사용자가 대여할려는 책이 다른 사용자가 대여중인지 확인
				int rentCheck = bookManageMapper.selectRentCheckByNo(bookNo);
				
				// 사용자가 대여할려는 책이 다른 사용자가 연체중인지 확인
				int overdueCheck = bookManageMapper.selectOverdueCheckByNo(bookNo);
				
				if(rentCheck + overdueCheck > 0) {
					try {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('현재 이 책은 대여중이므로 선택하신 책을 대여하실 수 없습니다.')");
						out.println("history.back()");
						out.println("</script>");
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					
					// 연체중인 책의 갯수
					int overdueCount = bookManageMapper.selectOverdueByMemberId(memberId);
					
					if(overdueCount > 0) {
						
						try {
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							out.println("<script>");
							out.println("alert('회원님은 연체중인 책이 있으므로 새로운 책의 대여가 불가합니다. 연체중인 책부터 반납해주세요.')");
							out.println("history.back()");
							out.println("</script>");
							out.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					} else {
						
						// 대여중인 책의 갯수
						int rentCount = bookManageMapper.selectRentCountByMemberId(memberId);
						
						if(rentCount < 3) {
							
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("bookNo", bookNo);
							map.put("memberId", memberId);
							
							int rentBook = bookManageMapper.insertRent(map);
							
							if(rentBook == 1) {
								try {
									response.setContentType("text/html");
									PrintWriter out = response.getWriter();
									out.println("<script>");
									out.println("alert('책을 대여하였습니다.')");
									out.println("history.back()");
									out.println("</script>");
									out.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								try {
									response.setContentType("text/html");
									PrintWriter out = response.getWriter();
									out.println("<script>");
									out.println("alert('책을 대여하지 못했습니다.')");
									out.println("history.back()");
									out.println("</script>");
									out.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							
							
							
						} else {
							
							try {
								response.setContentType("text/html");
								PrintWriter out = response.getWriter();
								out.println("<script>");
								out.println("alert('이미 3권의 책을 빌렸기 때문에 추가 대여는 불가합니다.')");
								out.println("history.back()");
								out.println("</script>");
								out.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
						
					}
				}
				
				
			} else {
				try {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('선택한 책이 존재하지 않습니다.')");
					out.println("history.back()");
					out.println("</script>");
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}
		
	}
	
	
	// 대여중인 책 목록
	@Override
	public List<Map<String, Object>> rentBookList(HttpServletRequest request) {
		
		// session에 올라간 loginMember에서 memberId 빼기
		MemberDTO loginMember = (MemberDTO)request.getSession().getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		
		// 사용자가 대여중인 책의 목록
		List<Map<String, Object>> rentBookList = bookManageMapper.selectRentBookList(memberId);
		
		// 대여중인 책 목록 결과
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		if(rentBookList.isEmpty() == false) {
			for(Map<String, Object> map : rentBookList) {
				Map<String, Object> book = new HashMap<String, Object>();
				book.put("rentNo", map.get("RENT_NO"));
				book.put("bookImage", map.get("BOOK_IMAGE"));
				book.put("bookTitle", map.get("BOOK_TITLE"));
				book.put("rentDate", map.get("RENT_DATE"));
				book.put("rentExpirationDate", map.get("RENT_EXPIRATION_DATE"));
				result.add(book);
			}
		}
		
		return result;
	}
	
	
	
	// 대여중인 책 반납하기
	@Transactional
	@Override
	public Map<String, Object> returnedRentBook(HttpServletRequest request) {
		
		// 반납하기 위해 선택한 대여중인 책의 rentNo
		String[] rentNo = request.getParameterValues("rentNo");
		
		List<RentDTO> checkRentBookList = new ArrayList<RentDTO>();
		
		for(int i = 0; i < rentNo.length; i++) {
			RentDTO checkRentBook = bookManageMapper.selectRentBookByRentNo(Long.parseLong(rentNo[i]));
			checkRentBookList.add(checkRentBook);
		}
		
		int insertReturnedResult = 0;
		for(int i = 0; i < checkRentBookList.size(); i++) {
			insertReturnedResult += bookManageMapper.insertReturnedRent(checkRentBookList.get(i)); 
		}
		
		int deleteResult = 0;
		for(int i = 0; i < rentNo.length; i++) {
			deleteResult += bookManageMapper.deleteRent(Long.parseLong(rentNo[i]));
		}
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("res", insertReturnedResult == deleteResult);
		
		return res;
	}
	
	
	
	// 연체중인 책 목록
	@Override
	public List<Map<String, Object>> overdueBookList(HttpServletRequest request) {
		
		// session에 올라간 loginMember에서 memberId 빼기
		MemberDTO loginMember = (MemberDTO)request.getSession().getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		
		// 사용자가 연체중인 책의 목록
		List<Map<String, Object>> overdueBookList = bookManageMapper.selectOverdueBookList(memberId);
		
		// 대여중인 책 목록 결과
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		if(overdueBookList.isEmpty() == false) {
			for(Map<String, Object> map : overdueBookList) {
				Map<String, Object> book = new HashMap<String, Object>();
				book.put("overdueNo", map.get("OVERDUE_NO"));
				book.put("bookImage", map.get("BOOK_IMAGE"));
				book.put("bookTitle", map.get("BOOK_TITLE"));
				book.put("rentExpirationDate", map.get("RENT_EXPIRATION_DATE"));
				result.add(book);
			}
		}
		
		return result;
	}
	
	
	// 연체중인 책 반납하기
	@Transactional
	@Override
	public Map<String, Object> returnedOverdueBook(HttpServletRequest request) {
		
		// 반납하기 위해 선택한 연체된 책의 overdueNo
		String[] overdueNo = request.getParameterValues("overdueNo");
		
		List<OverdueDTO> checkOverdueBookList = new ArrayList<OverdueDTO>();
		
		for(int i = 0; i < overdueNo.length; i++) {
			OverdueDTO checkOverdueBook = bookManageMapper.selectOverdueBookByOverdueNo(Long.parseLong(overdueNo[i]));
			checkOverdueBookList.add(checkOverdueBook);
		}
		
		int insertReturnedResult = 0;
		for(int i = 0; i < checkOverdueBookList.size(); i++) {
			insertReturnedResult += bookManageMapper.insertReturnedOverdue(checkOverdueBookList.get(i)); 
		}
		
		int deleteResult = 0;
		for(int i = 0; i < overdueNo.length; i++) {
			deleteResult += bookManageMapper.deleteOverdue(Long.parseLong(overdueNo[i]));
		}
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("res", insertReturnedResult == deleteResult);
		
		return res;

	}
	
	
	
	
}