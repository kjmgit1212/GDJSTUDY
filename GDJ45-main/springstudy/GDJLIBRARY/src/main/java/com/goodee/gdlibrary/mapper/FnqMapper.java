package com.goodee.gdlibrary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.FnqDTO;

@Mapper
public interface FnqMapper {
	
	public long selectFnqCount();
	public List<FnqDTO> selectFnqList(Map<String, Object> m);
	public long selectFindCount(Map<String, Object> map);
	public List<FnqDTO> selectFindList(Map<String, Object> map);

}
