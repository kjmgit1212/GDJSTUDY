package com.goodee.gdlibrary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.BookDTO;
import com.goodee.gdlibrary.domain.OverdueDTO;
import com.goodee.gdlibrary.domain.RentDTO;

@Mapper
public interface BookManageMapper {
	
	// 책 대여
	public BookDTO selectBookByNo(Long bookNo);
	public int selectRentCheckByNo(Long bookNo);
	public int selectOverdueCheckByNo(Long bookNo);
	public int selectOverdueByMemberId(String memberId);
	public int selectRentCountByMemberId(String memberId);
	public int insertRent(Map<String, Object> map);
	
	// 책 반납
	public List<Map<String, Object>> selectRentBookList(String memberId);
	public RentDTO selectRentBookByRentNo(Long rentNo);
	public int insertReturnedRent(RentDTO rent);
	public int deleteRent(Long rentNo);
	public List<Map<String, Object>> selectOverdueBookList(String memberId);
	
	
	// 추가한거!!
	public OverdueDTO selectOverdueBookByOverdueNo(Long overdueNo);
	public int insertReturnedOverdue(OverdueDTO overdue);
	

	public int deleteOverdue(Long overdueNo);
	

	// 책 연체
	public List<RentDTO> selectRentExpirationDate();
	public int insertOverdue(RentDTO rent);
	
}
