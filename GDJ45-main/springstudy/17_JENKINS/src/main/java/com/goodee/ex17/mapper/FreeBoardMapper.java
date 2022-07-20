package com.goodee.ex17.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex17.domain.FreeBoardDTO;

@Mapper
public interface FreeBoardMapper {
	
	public int selectFreeBoardCount();
	public List<FreeBoardDTO> selectFreeBoardList(Map<String, Object> map);
	
	public int insertFreeBoard(FreeBoardDTO freeBoard);
	
	public int updatePreviousReply(FreeBoardDTO freeBoard);
	public int insertReply(FreeBoardDTO freeBoard);
	
	public int deleteFreeBoard(Long freeBoardNo);
	
}
