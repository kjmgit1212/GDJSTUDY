package com.goodee.ex16.service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex16.domain.FileAttachDTO;
import com.goodee.ex16.domain.GalleryDTO;
import com.goodee.ex16.mapper.GalleryMapper;
import com.goodee.ex16.util.MyFileUtils;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	private GalleryMapper galleryMapper;
	
	// 갤러리 삽입
	@Transactional
	@Override
	public Map<String, Object> save(MultipartHttpServletRequest multipartRequest) {
		
		// 전달된 파라미터
		String writer = multipartRequest.getParameter("writer");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// IP
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(multipartRequest.getRemoteAddr());
		
		// GalleryDTO
		GalleryDTO gallery = GalleryDTO.builder()
				.writer(writer)
				.title(title)
				.content(content)
				.ip(ip)
				.build();
		
		// Gallery INSERT 수행
		// System.out.println(gallery);  // INSERT 수행 전에는 gallery에 galleryNo값이 없다.
		int galleryResult = galleryMapper.insertGallery(gallery);  // INSERT 수행
		// System.out.println(gallery);  // INSERT 수행 후에는 selectKey에 의해서 gallery에 galleryNo값이 저장된다.

		// 결론. FILE_ATTACH 테이블에 INSERT할 galleryNo 정보는 gallery에 저장되어 있다.
		
		// 첨부된 모든 파일들
		List<MultipartFile> files = multipartRequest.getFiles("files");  // 파라미터 files
		
		// 썸네일 이름 목록
		List<String> thumbnails = new ArrayList<>();
		
		// 파일 첨부 결과
		int fileAttachResult;
		if(files.get(0).getOriginalFilename().isEmpty()) {  // 첨부가 없으면 files.size() == 1임. [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]] 값을 가짐.
			fileAttachResult = 1;
		} else {  // 첨부가 있으면 "files.size() == 첨부파일갯수"이므로 fileAttachResult = 0으로 시작함.
			fileAttachResult = 0;
		}
		
		// path의 scope 조정
		String path = null;
		
		for (MultipartFile multipartFile : files) {
			
			// 예외 처리는 기본으로 필요함.
			try {
				
				// 첨부가 없을 수 있으므로 점검해야 함.
				if(multipartFile != null && multipartFile.isEmpty() == false) {  // 첨부가 있다.(둘 다 필요함)
					
					// 첨부파일의 본래 이름(origin)
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\") + 1);  // IE는 본래 이름에 전체 경로가 붙어서 파일명만 빼야 함.
					
					// 첨부파일의 저장된 이름(saved)
					String saved = MyFileUtils.getUuidName(origin);
					
					// 첨부파일의 저장 경로(디렉터리)
					path = MyFileUtils.getTodayPath();
					
					// 저장 경로(디렉터리) 없으면 만들기
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					
					// 첨부파일
					File file = new File(dir, saved);
					
					// 첨부파일 확인
					String contentType = Files.probeContentType(file.toPath());  // 이미지의 Content-Type(image/jpeg, image/png, image/gif)
					if(contentType.startsWith("image")) {
						
						// 첨부파일 서버에 저장(업로드)
						multipartFile.transferTo(file);
						
						// 썸네일 서버에 저장(썸네일 정보는 DB에 저장되지 않음)
						Thumbnails.of(file)
							.size(100, 100)
							.toFile(new File(dir, "s_" + saved));
						
						// 썸네일 이름 목록에 추가
						thumbnails.add("s_" + saved);
						// 썸네일에 경로까지 포함하기(이걸 쓰면 map.put("path", path);가 필요 없음)
						// thumbnails.add(path + Matcher.quoteReplacement(File.separator) + "s_" + saved);
						
						// FileAttachDTO
						FileAttachDTO fileAttach = FileAttachDTO.builder()
								.path(path)
								.origin(origin)
								.saved(saved)
								.galleryNo(gallery.getGalleryNo())
								.build();
						
						// FileAttach INSERT 수행
						fileAttachResult += galleryMapper.insertFileAttach(fileAttach);
						
					}

				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		// 응답할 데이터
		Map<String, Object> map = new HashMap<>();
		map.put("galleryResult", galleryResult == 1);  // 갤러리 성공 유무
		map.put("fileAttachResult", fileAttachResult == files.size());  // 첨부 성공 유무
		map.put("thumbnails", thumbnails);  // 썸네일 이름 목록
		map.put("path", path);  // 썸네일이 저장된 경로
		return map;
		
	}
	
	@Override
	public ResponseEntity<byte[]> display(String path, String thumbnail) {
		
		File file = new File(path, thumbnail);
		
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), null, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
