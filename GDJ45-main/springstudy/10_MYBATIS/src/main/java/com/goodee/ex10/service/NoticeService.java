package com.goodee.ex10.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.goodee.ex10.domain.NoticeDTO;

public interface NoticeService {
	public List<NoticeDTO> findNotices();
	public NoticeDTO findNoticeByNo(HttpServletRequest request);
	public int save(HttpServletRequest request);
	public int change(HttpServletRequest request);
	public int removeOne(HttpServletRequest request);
	
	public int removeList(HttpServletRequest request);
	public int removeList2(HttpServletRequest request);
	
	public void transactionTest();
}
