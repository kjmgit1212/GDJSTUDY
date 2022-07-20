package com.goodee.gdlibrary.service;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.gdlibrary.domain.NoticeDTO;
import com.goodee.gdlibrary.domain.NoticeFileAttachDTO;
import com.goodee.gdlibrary.mapper.NoticeMapper;
import com.goodee.gdlibrary.util.MyFileUtils;
import com.goodee.gdlibrary.util.PageUtils2;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	// notice 페이지로 이동할 때
	// notice 테이블에 있는 데이터를 가져오고
	// paging 및 page에 관련된 것들을 model에 저장해서 
	// 최종적으로 jsp 페이지에서 보이게 하기.
	@Override
	public void getNotices(HttpServletRequest request, Model model) {
		
		// totalRecord(DB), page(Parameter)
		long totalRecord = noticeMapper.selectNoticeCount();
		
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
		List<NoticeDTO> noticeList = noticeMapper.selectNotices(map);
		
		// notice/notice.jsp로 전달할 데이터
		model.addAttribute("noticeList", noticeList);
		// model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("startNo", totalRecord - (page - 1) * pageUtils.getRecordPerPage());
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/notice/noticePage"));
		
	}
	
	
	
	
	// summernote로 이미지 등록하기
	@Override
	public Map<String, Object> uploadSummernoteImage(MultipartHttpServletRequest multipartRequest) {
		
		// 에디터에 첨부된 파일
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		// 저장할 파일명
		String saved = MyFileUtils.getUuidName(multipartFile.getOriginalFilename());
				
		// 저장할 경로
		// 원본 : String path = "C:" + File.separator + "upload" + File.separator + "summernote";
		// 수정1 : String path = multipartRequest.getServletContext().getRealPath(MyFileUtils.summerNotePath());
		String path = MyFileUtils.summerNotePath();
		
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
		map.put("src", multipartRequest.getContextPath() + "/notice/display?filename=" + saved);
		return map;
	}
	
	
	// summernote로 등록한 이미지 보여주기
	@Override
	public ResponseEntity<byte[]> display(HttpServletRequest request) {
		
		String filename = request.getParameter("filename");
		// 원본 : File file = new File(request.getServletContext().getRealPath(MyFileUtils.summerNotePath()), filename);
		File file = new File(MyFileUtils.summerNotePath(), filename);
		
		ResponseEntity<byte[]> entity = null;
		try {
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	
	
	// 공지사항 게시글 등록하기
	// 첨부파일 있으면 첨부파일도 등록하기
	@Transactional
	@Override
	public void addNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// 제목, 내용
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// IP
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(multipartRequest.getRemoteAddr());
		
		NoticeDTO notice = NoticeDTO.builder()
				.noticeTitle(title)
				.noticeContent(content)
				.noticeIp(ip)
				.build();
		
		// Notice 테이블에 삽입
		int noticeResult = noticeMapper.insertNotice(notice);
		
		// 첨부된 모든 파일들
		List<MultipartFile> files = multipartRequest.getFiles("files");
		
		// 파일 첨부 결과
		int fileAttachResult; 
		if(files.get(0).getOriginalFilename().isEmpty()) {  // 첨부가 없으면 files.size() == 1임. [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]] 값을 가짐.
			fileAttachResult = 1; 
		} else {  // 첨부가 있으면 "files.size() == 첨부파일갯수"이므로 fileAttachResult = 0으로 시작함.
			fileAttachResult = 0;
		}
		
		for(MultipartFile multipartFile : files) {
			
			// 예외 처리는 기본으로 필요함.
			try {
				
				// 첨부가 없을 수 있으므로 점검해야 함.
				if(multipartFile != null && multipartFile.isEmpty() == false) {  // 첨부가 있다.(둘 다 필요함) 
					
					// 첨부파일의 본래 이름(origin)
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\") + 1);  // IE(인터넷 익스플로러)는 본래 이름에 전체 경로가 붙어서 파일명만 빼야 함.
					
					// 첨부파일의 저장된 이름(saved)
					String saved = MyFileUtils.getUuidName(origin);
					
					// 첨부파일의 저장 경로(디렉터리)
					// 원본 : String path = MyFileUtils.getTodayPath();
					// 수정1 : String path = multipartRequest.getServletContext().getRealPath(MyFileUtils.getTodayPath());
					String path = MyFileUtils.getTodayPath();
					
					// 저장 경로(디렉터리) 없으면 만들기
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					
					// 첨부파일
					File file = new File(dir, saved);
					
					// 첨부파일 확인
					// String contentType = Files.probeContentType(file.toPath()); 
					if(saved.endsWith("pdf") || saved.endsWith("hwp")) {
						
						// 첨부파일 서버에 저장(업로드)
						// transferTo() 메소드에 의해 저장 경로에 실질적으로 file이 생성됨
						multipartFile.transferTo(file);
						
						
						// NoticeFileAttachDTO
						NoticeFileAttachDTO noticeFileAttach = NoticeFileAttachDTO.builder()
								.noticeFileAttachPath(path)
								.noticeFileAttachOrigin(origin)
								.noticeFileAttachSaved(saved)
								.noticeNo(notice.getNoticeNo())
								.build();
						
						// NoticeFileAttach INSERT 수행
						fileAttachResult += noticeMapper.insertFileAttach(noticeFileAttach);
						
						
					}
					
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		// 응답
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if((noticeResult == 1 && fileAttachResult == files.size()) || noticeResult == 1) {
				out.println("<script>");
				out.println("alert('게시글이 등록되었습니다.')");
				out.println("location.href='" + multipartRequest.getContextPath() + "/notice/noticePage'");
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
	
	
	
	// 공지사항 게시글 상세보기
	@Override
	public void detailNotice(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("noticeNo"));
		Long noticeNo = Long.parseLong(opt.orElse("0"));
		
		// 공지사항 상세보기 매핑에만 조회수 증가
		if(request.getRequestURI().endsWith("detailNotice")) {
			noticeMapper.updateHit(noticeNo);
		}
		
		// 공지사항 정보 가져와서 model에 저장하기
		model.addAttribute("notice", noticeMapper.selectNoticeByNoticeNo(noticeNo));
		
		// 첨부 파일 정보 가져와서 model에 저장하기
		model.addAttribute("fileAttaches", noticeMapper.selectFileAttachListInTheNotice(noticeNo));
	}
	
	
	// 공지사항 게시글에 올린 첨부파일 다운받기
	@Override
	public ResponseEntity<Resource> download(String userAgent, Long noticeFileAttachNo) {
		
		// 다운로드 해야 할 첨부 파일 정보
		NoticeFileAttachDTO noticeFileAttach = noticeMapper.selectFileAttachByNo(noticeFileAttachNo);
		File file = new File(noticeFileAttach.getNoticeFileAttachPath(), noticeFileAttach.getNoticeFileAttachSaved());
		
		// 반환할 데이터
		Resource resource = new FileSystemResource(file);
		
		// 다운로드 할 파일이 없으면 종료
		if(resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);   // 실패할 경우 주로 상태코드만 넣는다.
		}
		
		// 다운로드 헤더
		HttpHeaders headers = new HttpHeaders();
		
		// 다운로드 되는 파일명(브라우저마다 세팅이 다름)
		String origin = noticeFileAttach.getNoticeFileAttachOrigin();
		try {
			
			// IE(userAgent에 Trident가 포함)
			if(userAgent.contains("Trident")) {
				origin = URLEncoder.encode(origin, "UTF-8").replaceAll("\\+", " ");
			}
			// Micro Edge(userAgent에 Edge가 포함)
			else if(userAgent.contains("Edge")) {
				origin = URLEncoder.encode(origin, "UTF-8");
			}
			// 나머지(Chrome 등)
			else {
				origin = new String(origin.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		headers.add("Content-Disposition", "attachment; filename=" + origin);
		headers.add("Content-Length", file.length() + "");
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	// 공지사항 게시글 삭제하기
	@Override
	public void removeNotice(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("noticeNo"));
		Long noticeNo = Long.parseLong(opt.orElse("0"));
		
		// 저장되어 있는 첨부 파일 목록 가져오기
		List<NoticeFileAttachDTO> fileList = noticeMapper.selectFileAttachListInTheNotice(noticeNo);
		
		// 저장되어 있는 첨부 파일이 있는지 확인
		if(fileList != null && fileList.isEmpty() == false) {
			
			for(NoticeFileAttachDTO file : fileList) {
				
				String path = file.getNoticeFileAttachPath();
				String saved = file.getNoticeFileAttachSaved();
				
				// 첨부 파일 알아내기
				File fileAttach = new File(path, saved);
				
				try {
					
					if(fileAttach.exists()) {
						fileAttach.delete();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	
		int res = noticeMapper.deleteNoticeByNoticeNo(noticeNo);
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res == 1) {
				out.println("<script>");
				out.println("alert('게시글이 삭제되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/notice/noticePage'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글이 삭제되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	// 공지사항 게시글 수정 페이지로 가기
	// 수정 페이지는 따로 없고
	// addNotice.jsp 파일에서 
	// 공지사항 등록과 수정 기능을 같이 넣음.
	// model에 수정할 게시글의 정보를 저장
	@Override
	public void modifyNoticePage(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("noticeNo"));
		Long noticeNo = Long.parseLong(opt.orElse("0"));
				
		// notice 정보 가져와서 model에 저장하기
		model.addAttribute("notice", noticeMapper.selectNoticeByNoticeNo(noticeNo));
		
		// 첨부 파일 정보 가져와서 model에 저장하기
		model.addAttribute("fileAttaches", noticeMapper.selectFileAttachListInTheNotice(noticeNo));
	}
	

	// 공지사항 게시글 수정하기
	@Transactional
	@Override
	public void modifyNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// 번호, 제목, 내용
		Long noticeNo = Long.parseLong(multipartRequest.getParameter("noticeNo"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// IP
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(multipartRequest.getRemoteAddr());
		
		NoticeDTO notice = NoticeDTO.builder()
				.noticeNo(noticeNo)
				.noticeTitle(title)
				.noticeContent(content)
				.noticeIp(ip)
				.build();
		
		// Notice UPDATE 수행
		int noticeResult = noticeMapper.updateNoticeByNoticeNo(notice);
		
		// 첨부된 모든 파일들
		List<MultipartFile> files = multipartRequest.getFiles("files");  // 파라미터 files
		
		// 파일 첨부 결과
		int fileAttachResult;
		if(files.get(0).getOriginalFilename().isEmpty()) {  // 첨부가 없으면 files.size() == 1임. [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]] 값을 가짐.
			fileAttachResult = 1;  
		} else {  // 첨부가 있으면 "files.size() == 첨부파일갯수"이므로 fileAttachResult = 0으로 시작함.
			fileAttachResult = 0;
		}
		
		for(MultipartFile multipartFile : files) {
			
			// 예외 처리는 기본으로 필요함.
			try {
				
				// 첨부가 없을 수 있으므로 점검해야 함.
				if(multipartFile != null && multipartFile.isEmpty() == false) {  // 첨부가 있다.(둘 다 필요함)
					
					// 첨부파일의 본래 이름(origin)
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\") + 1);  // IE(인터넷 익스플로러)는 본래 이름에 전체 경로가 붙어서 파일명만 빼야 함.
					
					// 첨부파일의 저장된 이름(saved)
					String saved = MyFileUtils.getUuidName(origin);
					
					// 첨부파일의 저장 경로(디렉터리)
					// 원본 : String path = MyFileUtils.getTodayPath();
					// 수정1 : String path = multipartRequest.getServletContext().getRealPath(MyFileUtils.getTodayPath());
					String path = MyFileUtils.getTodayPath();
					
					// 저장 경로(디렉터리) 없으면 만들기
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					
					// 첨부파일
					File file = new File(dir, saved);
					
					// 첨부파일 확인
					// String contentType = Files.probeContentType(file.toPath());
					if(saved.endsWith("pdf") || saved.endsWith("hwp")) {
						
						// 첨부파일 서버에 저장(업로드)
						multipartFile.transferTo(file);
						
						// noticeFileAttach 테이블에 추가된 첨부 파일 정보 넣기
						NoticeFileAttachDTO noticeFileAttach = NoticeFileAttachDTO.builder()
								.noticeFileAttachPath(path)
								.noticeFileAttachOrigin(origin)
								.noticeFileAttachSaved(saved)
								.noticeNo(noticeNo)
								.build();
						
						// noticeFileAttach INSERT 수행
						fileAttachResult += noticeMapper.insertFileAttach(noticeFileAttach);
						
					}
					
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		// 응답
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if((noticeResult == 1 && fileAttachResult == files.size()) || noticeResult == 1) {
				out.println("<script>");
				out.println("alert('게시글이 수정되었습니다.')");
				out.println("location.href='" + multipartRequest.getContextPath() + "/notice/noticePage'");
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
	
	
	// 공지사항 게시글 검색하기
	@Override
	public void search(HttpServletRequest request, Model model) {
		
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
		long findRecord = noticeMapper.selectFindCount(map);
		
		// findRecord와 page를 알면 PageEntity를 모두 계산할 수 있다.
		PageUtils2 pageUtils = new PageUtils2();
		pageUtils.setPageEntity(findRecord, page);
		
		// beginRecord + endRecord => Map
		// map.put("beginRecord", pageUtils.getBeginRecord());
		// map.put("endRecord", pageUtils.getEndRecord());
		map.put("beginRecord", pageUtils.getBeginRecord() - 1);
		map.put("recordPerPage", pageUtils.getRecordPerPage());
		
		// beginRecord ~ endRecord 사이 검색된 목록 가져오기
		List<NoticeDTO> noticeList = noticeMapper.selectFindList(map);
		
		// notice/notice.jsp로 전달할 데이터
		model.addAttribute("noticeList", noticeList);
		// model.addAttribute("totalRecord", findRecord);
		model.addAttribute("startNo", findRecord - (page - 1) * pageUtils.getRecordPerPage());
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/notice/search"));
		model.addAttribute("query", query);
		
	}
	
	
	// 기존 공지사항에 첨부된 파일 삭제하기(하나씩)
	@Override
	public Map<String, Object> removeFileAttach(Long noticeFileAttachNo, Long noticeNo) {
		
		// noticeFileAttachNo가 일치하는 noticeFileAttachDTO 정보를 DB에서 가져오면
		// 삭제할 파일의 경로와 이름이 있다.
		NoticeFileAttachDTO noticeFileAttach = noticeMapper.selectFileAttachByNo(noticeFileAttachNo);
		
		
		// 첨부 파일 알아내기
		if(noticeFileAttach != null) {
			File file = new File(noticeFileAttach.getNoticeFileAttachPath(), noticeFileAttach.getNoticeFileAttachSaved());			
			try {
				
				// 첨부 파일이 pdf, hwp가 맞는지 확인
				// String contentType = Files.probeContentType(file.toPath());
				// if(contentType.endsWith("pdf") || contentType.endsWith("hwp")) {
					
					// 첨부 파일 삭제
					if(file.exists()) {
						file.delete();
					}
				// }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// noticeFileAttach 테이블에서 noticeFileAttachNo 에 해당하는 Record 삭제하기
		int fileDeleteResult = noticeMapper.deleteFileAttach(noticeFileAttachNo);
		
		// 첨부 파일 정보 가져와서 map에 저장하기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res", fileDeleteResult);
		map.put("fileAttaches", noticeMapper.selectFileAttachListInTheNotice(noticeNo));
		
		return map;
	}
	
	
	
	
}
