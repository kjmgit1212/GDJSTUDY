package com.goodee.ex05.service;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

import com.goodee.ex05.domain.ReservationDTO;

public class ReservationServiceImpl implements ReservationService {

	
	// ResponseEntity<T>
	// 1. 실제 응답 데이터는 T 타입이다.
	// 2. HttpHeaders 클래스를 이용해서 응답 데이터의 Content-Type을 지정한다.
	//    produces를 사용하지 않는다.
	// 3. 응답 코드(HttpStatus)를 저장한다.
	
	
	@Override
	public ResponseEntity<ReservationDTO> detail1(HttpServletRequest request) {
		
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(optNo.orElse("0"));
		
		// 파라미터 no가 전달되지 않았다면 no의 값은 0이다.
		// no의 값이 0인 경우 조회가 불가능한 경우이다.
		
		ResponseEntity<ReservationDTO> responseEntity = null;
		
		// ResponseEntity로 전달할 결과 데이터
		ReservationDTO reservation = new ReservationDTO(no, "예약자");
		
		// ResponseEntity로 전달할 응답 데이터의 Content-Type
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		
		if(no > 0) {  
			responseEntity = new ResponseEntity<ReservationDTO>(reservation, header, HttpStatus.OK);    // ajax의 success로 전달된다.
		} else {
			responseEntity = new ResponseEntity<ReservationDTO>(null, header, HttpStatus.BAD_REQUEST);  // ajax의 error로 전달된다.
		}
		
		return responseEntity;
		
	}

	@Override
	public ResponseEntity<ReservationDTO> detail2(Long no) {
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		
		return new ResponseEntity<>(new ReservationDTO(no, "예약자"), header, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<ReservationDTO> detail3(ReservationDTO reservation) {
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);  // ("Content-Type", "application/json")
		
		// no가 100을 초과하면 저장할 수 없는 데이터로 가정
		ResponseEntity<ReservationDTO> result = null;
		
		if(reservation.getNo() > 100) {
			result = new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);  // status : 500
		} else {
			result = new ResponseEntity<>(new ReservationDTO(reservation.getNo(), "예약자"), header, HttpStatus.OK);
		}
		
		return result;
	}
	
	@Override
	public ResponseEntity<byte[]> image() {
		File file = new File("D:", "eagle.jpg");  // new File("D:\\eagle.jpg")
		ResponseEntity<byte[]> result = null;
		try {
			// D:\\eagle.jpg 파일을 복사해서 byte[] 배열에 저장하고 해당 byte[] 배열을 반환
			byte[] b = FileCopyUtils.copyToByteArray(file);  // 이걸 반환하면 된다.
			// HttpHeaders : 반환할 데이터의 Content-Type
			// jpg 이미지의 Content-Type은 image/jpeg 이다.
			HttpHeaders header = new HttpHeaders();
			String contentType = Files.probeContentType(file.toPath());
			header.add("Content-Type", contentType);  // ("Content-Type", "image/jpeg")
			// 반환할 ResponseEntity
			result = new ResponseEntity<>(b, header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}