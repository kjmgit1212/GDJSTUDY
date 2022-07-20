package com.goodee.ex12.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex12.domain.ReplyDTO;

@Mapper
public interface ReplyMapper {
	
	public List<ReplyDTO> selectReplyList(Long boardNo);
	public int selectReplyCount(Long boardNo);
	
	public int insertReply(ReplyDTO reply);
	public int deleteReply(Long replyNo);
	
}
