package com.goodee.gdlibrary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.gdlibrary.domain.QaaDTO;

@Mapper
public interface QaaMapper {
	
	public int insertQaa(QaaDTO qaa);
	public int updateQaaGroupNo(QaaDTO qaa);
	public int selectQaaCount();
	public List<QaaDTO> selectQaaList(Map<String, Object> map);
	public int updatePreviousReply(QaaDTO qaa);
	public int insertReply(QaaDTO qaa);
	public QaaDTO selectQaa(Long qaaNo);
	public int updateQaa(QaaDTO qaa);
	public int getCountByQaaNo(Long qaaNo);
	public int deleteQaaReply(Long qaaNo);
	
	// 댓글 삭제하기 위한 과정
	public int getReplyCountByQaaDepth(QaaDTO qaa);
	public int updateReplyByQaaDepth(QaaDTO qaa);
	public int updateReplyByQaaGroupOrd(QaaDTO qaa);
	public int deleteReply(Long qaaNo);
	
	
	public long selectFindCount(Map<String, Object> map);
	public List<QaaDTO> selectFindList(Map<String, Object> map);
}
