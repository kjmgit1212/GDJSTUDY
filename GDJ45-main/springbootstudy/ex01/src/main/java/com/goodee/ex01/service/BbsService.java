package com.goodee.ex01.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex01.domain.BbsDTO;

public interface BbsService {
	public List<BbsDTO> getBbses();
	public BbsDTO getBbsByNo(Long bbsNo);
	public void addBbs(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> uploadSummernoteImage(MultipartHttpServletRequest multipartRequest);
	public void modifyBbs(HttpServletRequest request, HttpServletResponse response);
	public void removeBbs(HttpServletRequest request, HttpServletResponse response);
}
