package com.goodee.ex16.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex16.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public int insertMember(MemberDTO member);
	public long selectMemberCount();
	public List<MemberDTO> selectMemberList(Map<String, Object> m);
	public MemberDTO selectMemberByNo(Long memberNo);
	public int updateMember(MemberDTO member);
	public int deleteMember(Long memberNo);
}
