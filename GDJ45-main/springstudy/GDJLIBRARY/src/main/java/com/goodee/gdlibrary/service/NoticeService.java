package com.goodee.gdlibrary.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface NoticeService {
	public void getNotices(HttpServletRequest request, Model model);
	public Map<String, Object> uploadSummernoteImage(MultipartHttpServletRequest multipartRequest);
	public ResponseEntity<byte[]> display(HttpServletRequest request);
	public void addNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void detailNotice(HttpServletRequest request, Model model);
	public ResponseEntity<Resource> download(String userAgent, Long noticeFileAttachNo);
	public void removeNotice(HttpServletRequest request, HttpServletResponse response);
	public void modifyNoticePage(HttpServletRequest request, Model model);
	public void modifyNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void search(HttpServletRequest request, Model model);
	public Map<String, Object> removeFileAttach(Long noticeFileAttachNo, Long noticeNo);
}
