package com.goodee.ex18.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.goodee.ex18.domain.NoticeDTO;

public interface NoticeService {

	public void getNotices(HttpServletRequest request, Model model);
	public void getFindNotices(HttpServletRequest request, Model model);
	public NoticeDTO getNoticeByNo(Long noticeNo);
	public void addNotice(HttpServletRequest request, HttpServletResponse response);
	public void modifyNotice(HttpServletRequest request, HttpServletResponse response);
	public void removeNotice(HttpServletRequest request, HttpServletResponse response);
	
}
