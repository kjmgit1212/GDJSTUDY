package com.goodee.gdlibrary.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.goodee.gdlibrary.domain.QaaDTO;

public interface QaaService {

	public void addQaa(HttpServletRequest request, HttpServletResponse response);
	public void qaaList(HttpServletRequest request, Model model);
	public void search(HttpServletRequest request, Model model);
	public void saveReply(HttpServletRequest request, HttpServletResponse response);
	public QaaDTO detailQaa(Long qaaNo, Model model);
	public void modifyQaa(HttpServletRequest request, HttpServletResponse response);
	public void removeQaa(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> removeReply(QaaDTO qaa);
}
