package com.goodee.ex09.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodee.ex09.domain.NoticeDTO;

@Repository
public class NoticeRepository {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 각 메소드 이름은 쿼리문의 id와 통일하면 좋다.
	
	public List<NoticeDTO> selectNoticeList() {
		return sqlSessionTemplate.selectList("mybatis.mapper.notice.selectNoticeList");
	}
	
	public int insertNotice(NoticeDTO notice) {
		return sqlSessionTemplate.insert("mybatis.mapper.notice.insertNotice", notice);
	}
	
	public NoticeDTO selectNoticeByNo(Long noticeNo) {
		return sqlSessionTemplate.selectOne("mybatis.mapper.notice.selectNoticeByNo", noticeNo);
	}
	
	public int updateHit(Long noticeNo) {
		return sqlSessionTemplate.update("mybatis.mapper.notice.updateHit", noticeNo);
	}
	
	public int updateNotice(NoticeDTO notice) {
		return sqlSessionTemplate.update("mybatis.mapper.notice.updateNotice", notice);
	}
	
	public int deleteNotice(Long noticeNo) {
		return sqlSessionTemplate.delete("mybatis.mapper.notice.deleteNotice", noticeNo);
	}
	
	public int deleteNoticeList(List<Long> list) {
		return sqlSessionTemplate.delete("mybatis.mapper.notice.deleteNoticeList", list);
	}
	
}
