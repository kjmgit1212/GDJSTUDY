package com.goodee.ex16.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageUtils {

	
	/***************************************************************************
	   - 전체 11개 레코드를
	   - 한 페이지에 3개씩 표시한다면
	   - 전체 페이지는 4페이지가 된다.
	*****************************************************************************/
	private long totalRecord;         // DB에서 구해온다.
	private long recordPerPage = 10;  // 여기서 마음대로 정한다.
	private long totalPage;           // totalRecord와 recordPerPage로 계산한다.
	
	
	/***************************************************************************
	   - 전체 11개 레코드를
	   - 한 페이지에 3개씩 표시한다면
	   - 각 페이지에 표시되는 레코드의 번호는 다음과 같다.
	   page = 1, beginRecord = 1, endRecord = 3
	   page = 2, beginRecord = 4, endRecord = 6
	   page = 3, beginRecord = 7, endRecord = 9
	   page = 4, beginRecord = 10, endRecord = 11 (endRecord = 12가 아님을 주의)	   
	*****************************************************************************/
	private long page;          // 파라미터로 받아온다.
	private long beginRecord;   // page와 recordPerPage로 계산한다.
	private long endRecord;     // beginRecord와 recordPerPage와 totalPage로 계산한다.

	
	/***************************************************************************
		- 전체 12개 페이지를
		- 한 블록에 5개씩 표시한다면
		- 각 블록에 표시되는 페이지의 번호는 다음과 같다.
		block = 1,  1  2  3  4  5   ,  page = 1~5,   beginPage = 1,  endPage = 5
		block = 2,  6  7  8  9  10  ,  page = 6~10,  beginPage = 6,  endPage = 10
		block = 3,  11 12           ,  page = 11~15, beginPage = 11, endPage = 12 (endPage = 15가 아님을 주의)
	*****************************************************************************/
	private long pagePerBlock = 10;  // 여기서 마음대로 정한다.
	private long beginPage;          // page와 pagePerBlock으로 계산한다.
	private long endPage;            // beginPage와 pagePerBlock과 totalPage로 계산한다.
	
	
	// 매개변수 2개
	// totalRecord : DB에서 가져온다.
	// page : 파라미터로 가져온다.
	public void setPageEntity(long totalRecord, int page) {
		
		// totalRecord, page 필드 값 저장
		this.totalRecord = totalRecord;
		this.page = page;
		
		// totalPage 필드 값 계산
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		
		// beginRecord, endRecord 필드 값 계산
		beginRecord = (page - 1) * recordPerPage + 1;
		endRecord = beginRecord + recordPerPage - 1;
		if(endRecord > totalRecord) {
			endRecord = totalRecord;
		}
		
		// beginPage, endPage 필드 값 계산
		// beginPage = (pagePerBlock * (page - 1) / pagePerBlock) + 1;
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}

}
