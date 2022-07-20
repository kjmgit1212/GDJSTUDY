package com.goodee.gdlibrary.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.goodee.gdlibrary.domain.QaaDTO;
import com.goodee.gdlibrary.mapper.QaaMapper;
import com.goodee.gdlibrary.util.PageUtils2;
import com.goodee.gdlibrary.util.SecurityUtils;

@Service
public class QaaServiceImpl implements QaaService {
	
	@Autowired
	private QaaMapper qaaMapper;
	

	// qaa 게시판에 작성한 글 qaa 테이블에 넣기
	@Transactional
	@Override
	public void addQaa(HttpServletRequest request, HttpServletResponse response) {
		
		String title = SecurityUtils.xss(request.getParameter("title"));
		String memberId = request.getParameter("memberId");
		String content = SecurityUtils.xss(request.getParameter("content"));
		
		QaaDTO qaa = QaaDTO.builder()
				.qaaTitle(title)
				.memberId(memberId)
				.qaaContent(content)
				.build();
		
		int res1 = qaaMapper.insertQaa(qaa);
		int res2 = qaaMapper.updateQaaGroupNo(qaa);
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res1 > 0 && res2 > 0) {
				out.println("<script>");
				out.println("alert('게시글이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/qaa/qaaPage'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글이 등록되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	

	
	// qaa 페이지로 이동할 때
	// qaa 테이블에 있는 데이터를 가져오고
	// paging 및 page에 관련된 것들을 model에 저장해서 
	// 최종적으로 jsp 페이지에서 보이게 하기.
	@Override
	public void qaaList(HttpServletRequest request, Model model) {
		
		// totalRecord(DB), page(Parameter)
		int totalRecord = qaaMapper.selectQaaCount();
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// pageEntity 계산
		PageUtils2 pageUtils = new PageUtils2();
		pageUtils.setPageEntity(totalRecord, page);
		
		// Map
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("beginRecord", pageUtils.getBeginRecord());
		// map.put("endRecord", pageUtils.getEndRecord());
		map.put("beginRecord", pageUtils.getBeginRecord() - 1);
		map.put("recordPerPage", pageUtils.getRecordPerPage());
		
		// 목록 가져오기
		List<QaaDTO> qaaList = qaaMapper.selectQaaList(map);
		
		// qaa/qaa.jsp로 전달할 데이터
		model.addAttribute("qaaList", qaaList);
		// model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("startNo", totalRecord - (page - 1) * pageUtils.getRecordPerPage());
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/qaa/qaaPage"));
		
	}
	
	
	// 작성자(회원ID) 검색하기
	@Override
	public void search(HttpServletRequest request, Model model) {
		
		// request에서 page 파라미터 꺼내기
		// page 파라미터는 전달되지 않는 경우 page = 1을 사용
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// column, query 파라미터 꺼내기
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		
		// column + query => Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("query", query);
		
		// 검색된 레코드 갯수 가져오기
		long findRecord = qaaMapper.selectFindCount(map);
		
		// findRecord와 page를 알면 PageEntity를 모두 계산할 수 있다.
		PageUtils2 pageUtils = new PageUtils2();
		pageUtils.setPageEntity(findRecord, page);
		
		// beginRecord + endRecord => Map
		// map.put("beginRecord", pageUtils.getBeginRecord());
		// map.put("endRecord", pageUtils.getEndRecord());
		map.put("beginRecord", pageUtils.getBeginRecord() - 1);
		map.put("recordPerPage", pageUtils.getRecordPerPage());
		
		// beginRecord ~ endRecord 사이 검색된 목록 가져오기
		List<QaaDTO> qaaList = qaaMapper.selectFindList(map);
		
		// qaa/qaa.jsp로 전달할 데이터
		model.addAttribute("query", query);
		model.addAttribute("qaaList", qaaList);
		// model.addAttribute("totalRecord", findRecord);
		model.addAttribute("startNo", findRecord - (page - 1) * pageUtils.getRecordPerPage());
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/qaa/search?column=" + column + "&query=" + query));
		
	}
	
	
	// qaa 테이블에 댓글 삽입하기
	@Transactional  // saveReply 메소드 내부에서 update + insert 호출하고 있으므로  
	@Override
	public void saveReply(HttpServletRequest request, HttpServletResponse response) {
		
		String memberId = request.getParameter("memberId");
		String content = SecurityUtils.xss(request.getParameter("content"));
		
		int depth = Integer.parseInt(request.getParameter("depth"));
		Long groupNo = Long.parseLong(request.getParameter("groupNo"));
		int groupOrd = Integer.parseInt(request.getParameter("groupOrd"));
		
		QaaDTO qaa = new QaaDTO();
		qaa.setQaaGroupNo(groupNo);
		qaa.setQaaGroupOrd(groupOrd);
		qaaMapper.updatePreviousReply(qaa);
		
		QaaDTO reply = new QaaDTO();
		reply.setMemberId(memberId);
		reply.setQaaContent(content);
		reply.setQaaDepth(depth + 1);
		reply.setQaaGroupNo(groupNo);
		reply.setQaaGroupOrd(groupOrd + 1);
		
		int res = qaaMapper.insertReply(reply);
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('댓글이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/qaa/qaaPage'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('댓글이 등록되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 원글 상세조회
	@Override
	public QaaDTO detailQaa(Long qaaNo, Model model) {
		QaaDTO qaa = qaaMapper.selectQaa(qaaNo);
		model.addAttribute("qaa", qaa);
		return qaa;
	}
	
	
	// 원글 수정
	@Override
	public void modifyQaa(HttpServletRequest request, HttpServletResponse response) {
		
		Long qaaNo = Long.parseLong(request.getParameter("qaaNo"));
		String title = request.getParameter("qaaTitle");
		String content = request.getParameter("qaaContent");
		
		
		QaaDTO qaa = QaaDTO.builder()
				.qaaNo(qaaNo)
				.qaaTitle(title)
				.qaaContent(content)
				.build();
		
		
		int res = qaaMapper.updateQaa(qaa);
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('게시글이 수정되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/qaa/qaaPage'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글이 수정되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 회원이 작성한 게시글(원본) 삭제
	// 원본을 삭제하면 원본에 달린 댓글들도 삭제해야함.
	@Override
	public void removeQaa(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("qaaNo"));
		Long qaaNo = Long.parseLong(opt.orElse("0"));
		
		// 삭제하려는 원본과 원본에 달린 댓글의 총 갯수
		int getAllCount = qaaMapper.getCountByQaaNo(qaaNo);
		
		// 삭제한 원본 + 원본에 달린 댓글의 총 갯수
		int deleteAllCount = qaaMapper.deleteQaaReply(qaaNo);
		
		
		if(qaaNo == 0) {
			try {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제하려는 게시글을 다시 선택해주세요.')");
				out.println("location.href='" + request.getContextPath() + "/qaa/qaaPage'");
				out.println("</script>");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			if(getAllCount == 0) {
				try {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제하려는 게시글의 정보가 없습니다.')");
					out.println("location.href='" + request.getContextPath() + "/qaa/qaaPage'");
					out.println("</script>");
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(getAllCount == deleteAllCount) {
				try {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('게시글과 댓글이 삭제되었습니다.')");
					out.println("location.href='" + request.getContextPath() + "/qaa/qaaPage'");
					out.println("</script>");
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(getAllCount != deleteAllCount) {
				try {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('게시글과 댓글이 정상적으로 삭제되지 않았습니다.')");
					out.println("location.href='" + request.getContextPath() + "/qaa/qaaPage'");
					out.println("</script>");
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	// 댓글 삭제하기.
	@Transactional
	@Override
	public Map<String, Object> removeReply(QaaDTO qaa) {
		
		int getReplyCountByDepth = qaaMapper.getReplyCountByQaaDepth(qaa);
		
		if(getReplyCountByDepth == 1) {
			qaaMapper.updateReplyByQaaDepth(qaa);
			qaaMapper.updateReplyByQaaGroupOrd(qaa);
		} else if(getReplyCountByDepth > 1) {
			qaaMapper.updateReplyByQaaGroupOrd(qaa);
		}
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("res", qaaMapper.deleteReply(qaa.getQaaNo()));
		
		// 확인해보고 나중에 지우기
		System.out.println(qaaMapper.deleteReply(qaa.getQaaNo()));
		
		return res;
	}
	

	

}
