package com.goodee.ex01.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex01.domain.BbsDTO;
import com.goodee.ex01.mapper.BbsMapper;
import com.goodee.ex01.util.MyFileUtils;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	private BbsMapper bbsMapper;
	
	@Override
	public List<BbsDTO> getBbses() {
		return bbsMapper.selectBbses();
	}

	@Override
	public BbsDTO getBbsByNo(Long bbsNo) {
		return bbsMapper.selectBbsByNo(bbsNo);
	}

	@Override
	public void addBbs(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BbsDTO bbs = BbsDTO.builder()
				.writer(writer)
				.title(title)
				.content(content)
				.build();
		
		int res = bbsMapper.insertBbs(bbs);
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res == 1) {
				out.println("<script>");
				out.println("alert('BBS 추가 성공')");
				out.println("location.href='" + request.getContextPath() + "/bbs/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('BBS 추가 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Map<String, Object> uploadSummernoteImage(MultipartHttpServletRequest multipartRequest) {
		
		// 에디터에 첨부된 파일
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		// 저장할 파일명
		String saved = MyFileUtils.getUuidName(multipartFile.getOriginalFilename());
		
		// 저장할 경로
		String path = "C:" + File.separator + "upload" + File.separator + "summernote";
		
		// 경로가 없으면 만들기
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 저장할 File 객체
		File file = new File(dir, saved);
		
		// File 객체 저장
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			try {
				FileUtils.forceDelete(file);  // 예외 발생하면 삭제
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		// 저장된 파일의 경로를 반환
		Map<String, Object> map = new HashMap<>();
		map.put("src", "/getImage/" + saved);
		return map;
		
	}
	
	@Override
	public void modifyBbs(HttpServletRequest request, HttpServletResponse response) {
		
		Long bbsNo = Long.parseLong(request.getParameter("bbsNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BbsDTO bbs = BbsDTO.builder()
				.bbsNo(bbsNo)
				.title(title)
				.content(content)
				.build();
		
		int res = bbsMapper.updateBbs(bbs);
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res == 1) {
				out.println("<script>");
				out.println("alert('BBS 수정 성공')");
				out.println("location.href='" + request.getContextPath() + "/bbs/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('BBS 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeBbs(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("bbsNo"));
		Long bbsNo = Long.parseLong(opt.orElse("0"));
		
		int res = bbsMapper.deleteBbs(bbsNo);
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res == 1) {
				out.println("<script>");
				out.println("alert('BBS 삭제 성공')");
				out.println("location.href='" + request.getContextPath() + "/bbs/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('BBS 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
