package com.goodee.ex18.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.goodee.ex18.domain.MemberDTO;
import com.goodee.ex18.domain.NoticeDTO;
import com.goodee.ex18.mapper.NoticeMapper;
import com.goodee.ex18.util.PageUtils;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public void getNotices(HttpServletRequest request, Model model) {
		
		int totalRecord = noticeMapper.selectNoticeCount();
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginRecord", pageUtils.getBeginRecord() - 1);
		map.put("recordPerPage", pageUtils.getRecordPerPage());
		
		model.addAttribute("notices", noticeMapper.selectNoticeList(map));
		model.addAttribute("startNo", totalRecord - (page - 1) * pageUtils.getRecordPerPage());
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/notice/list"));
	
	}
	
	@Override
	public void getFindNotices(HttpServletRequest request, Model model) {
		
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", request.getParameter("column"));
		map.put("query", request.getParameter("query"));
		
		int totalRecord = noticeMapper.selectFindNoticeCount(map);
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		map.put("beginRecord", pageUtils.getBeginRecord() - 1);
		map.put("recordPerPage", pageUtils.getRecordPerPage());
		
		model.addAttribute("notices", noticeMapper.selectFindNoticeList(map));
		model.addAttribute("startNo", totalRecord - (page - 1) * pageUtils.getRecordPerPage());
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/notice/find?column=" + column + "&query=" + query));
		
	}
	
	@Override
	public NoticeDTO getNoticeByNo(Long noticeNo) {
		return noticeMapper.selectNoticeByNo(noticeNo);
	}
	
	@Override
	public void addNotice(HttpServletRequest request, HttpServletResponse response) {
		
		Long memberNo = Long.parseLong(request.getParameter("memberNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeDTO notice = NoticeDTO.builder()
				.member(MemberDTO.builder().memberNo(memberNo).build())
				.title(title)
				.content(content)
				.build();
		
		int result = noticeMapper.insertNotice(notice);
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<script>");
				out.println("alert('공지사항 등록 성공')");
				out.println("location.href='" + request.getContextPath() + "/notice/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('공지사항 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void modifyNotice(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("noticeNo"));
		Long noticeNo = Long.parseLong(opt.orElse("0"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeDTO notice = NoticeDTO.builder()
				.noticeNo(noticeNo)
				.title(title)
				.content(content)
				.build();
		
		int result = noticeMapper.updateNotice(notice);
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<script>");
				out.println("alert('공지사항 수정 성공')");
				out.println("location.href='" + request.getContextPath() + "/notice/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('공지사항 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeNotice(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("noticeNo"));
		Long noticeNo = Long.parseLong(opt.orElse("0"));
		
		int result = noticeMapper.deleteNotice(noticeNo);
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<script>");
				out.println("alert('공지사항 삭제 성공')");
				out.println("location.href='" + request.getContextPath() + "/notice/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('공지사항 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
