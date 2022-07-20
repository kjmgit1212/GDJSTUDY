package com.goodee.ex18.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex18.domain.NoticeDTO;

@Mapper
public interface NoticeMapper {

	public int selectNoticeCount();
	public List<NoticeDTO> selectNoticeList(Map<String, Object> map);
	public int selectFindNoticeCount(Map<String, Object> map);
	public List<NoticeDTO> selectFindNoticeList(Map<String, Object> map);
	public NoticeDTO selectNoticeByNo(Long noticeNo);
	public int insertNotice(NoticeDTO notice);
	public int updateNotice(NoticeDTO notice);
	public int deleteNotice(Long noticeNo);
	
}