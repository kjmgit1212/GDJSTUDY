package com.goodee.ex01.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex01.domain.BbsDTO;

@Mapper
public interface BbsMapper {
	public List<BbsDTO> selectBbses();
	public BbsDTO selectBbsByNo(Long bbsNo);
	public int insertBbs(BbsDTO bbs);
	public int updateBbs(BbsDTO bbs);
	public int deleteBbs(Long bbsNo);
}
