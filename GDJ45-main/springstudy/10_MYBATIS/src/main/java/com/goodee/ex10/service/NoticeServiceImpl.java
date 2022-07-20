package com.goodee.ex10.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex10.domain.NoticeDTO;
import com.goodee.ex10.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDTO> findNotices() {
		return noticeMapper.selectNoticeList();
	}

	@Override
	public NoticeDTO findNoticeByNo(HttpServletRequest request) {

		String requestURI = request.getRequestURI();  // "/ex09/notice/detail"
		String[] arr = requestURI.split("/");         // { "", "ex09", "notice", "detail"}
		
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		
		if(arr[arr.length - 1].equals("detail")) {            // 상세보기 요청이면,
			noticeMapper.updateHit(noticeNo);             // 조회수를 늘리고,
		}
		return noticeMapper.selectNoticeByNo(noticeNo);   // 정보를 조회한다.
		
	}

	@Override
	public int save(HttpServletRequest request) {
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeMapper.insertNotice(notice);
	}

	@Override
	public int change(HttpServletRequest request) {
		NoticeDTO notice = new NoticeDTO();
		notice.setNoticeNo(Long.parseLong(request.getParameter("noticeNo")));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeMapper.updateNotice(notice);
	}

	@Override
	public int removeOne(HttpServletRequest request) {
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		return noticeMapper.deleteNotice(noticeNo);
	}
	
	@Override
	public int removeList(HttpServletRequest request) {
		// 하나씩 여러 번 지우기
		// DELETE FROM NOTICE WHERE NOTICE_NO = 1
		// DELETE FROM NOTICE WHERE NOTICE_NO = 4
		String[] noticeNoList = request.getParameterValues("noticeNoList");  // {"1", "4"}
		Long res = 0L;
		for(int i = 0; i < noticeNoList.length; i++) {
			Long noticeNo = Long.parseLong(noticeNoList[i]);  // Long.parseLong("1") -> Long.parseLong("4")
			res += noticeMapper.deleteNotice(noticeNo);
		}
		return (res == noticeNoList.length) ? 1 : 0;  // 모두 삭제했다면 1 반환 아니면 0 반환
	}

	@Override
	public int removeList2(HttpServletRequest request) {
		// 한 번에 여러 개 지우기
		// DELETE FROM NOTICE WHERE NOTICE_NO IN(1, 4)
		String[] noticeNoList = request.getParameterValues("noticeNoList");  // {"1", "4"}
		List<Long> list = new ArrayList<>();
		for(int i = 0; i < noticeNoList.length; i++) {
			list.add(Long.parseLong(noticeNoList[i]));  // list.add(1) -> list.add(4)
		}
		return noticeMapper.deleteNoticeList(list);
	}
	
	@Override
	public void transactionTest() {
		noticeMapper.insertNotice(new NoticeDTO(null, "테스트제목", "테스트내용", null, null, null));
		noticeMapper.insertNotice(new NoticeDTO());
	}
	
}
