package com.goodee.ex16.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.goodee.ex16.domain.MemberDTO;
import com.goodee.ex16.mapper.MemberMapper;
import com.goodee.ex16.util.PageUtils;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Map<String, Object> addMember(MemberDTO member, HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("res", memberMapper.insertMember(member));
			return map;
		} catch (DuplicateKeyException e) {  // 아이디 중복으로 인한 예외
			try {
				PrintWriter out = response.getWriter();
				response.setContentType("text/plain");
				response.setStatus(501);
				out.println("이미 사용 중인 아이디입니다.");
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} catch (DataIntegrityViolationException e) {  // 필수 정보 누락으로 인한 예외
			try {
				System.out.println(e.getClass().getName());
				PrintWriter out = response.getWriter();
				response.setContentType("text/plain");
				response.setStatus(502);
				out.println("필수 정보가 누락되었습니다.");
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} catch (Exception e) {  // 기타 예외
			try {
				System.out.println(e.getClass().getName());
				PrintWriter out = response.getWriter();
				response.setContentType("text/plain");
				response.setStatus(503);
				out.println("잘못된 데이터가 전달되었습니다.");
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;  // 사용되지 않는 반환
	}
	
	@Override
	public Map<String, Object> getMembers(int page) {
		
		// page와 totalRecord를 이용해서 페이징 정보를 구한다.
		long totalRecord = memberMapper.selectMemberCount();
		PageUtils p = new PageUtils();
		p.setPageEntity(totalRecord, page);
		
		// 목록은 beginRecord~endRecord 사이값을 가져온다.
		Map<String, Object> m = new HashMap<>();
		m.put("beginRecord", p.getBeginRecord());
		m.put("endRecord", p.getEndRecord());
		
		// 목록과 페이징 정보를 반환한다.
		Map<String, Object> map = new HashMap<>();
		map.put("members", memberMapper.selectMemberList(m));
		map.put("p", p);
		return map;
		
	}
	
	@Override
	public Map<String, Object> getMember(Long memberNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("member", memberMapper.selectMemberByNo(memberNo));
		return map;
	}
	
	@Override
	public Map<String, Object> changeMember(MemberDTO member, HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("res", memberMapper.updateMember(member));
			return map;
		} catch (Exception e) {
			try {
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				response.setStatus(503);  // ajax의 error의 jqXHR의 status가 된다.
				out.println("잘못된 데이터가 전달되었습니다.");  // ajax의 error의 jqXHR의 responseText가 된다.
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> removeMember(Long memberNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", memberMapper.deleteMember(memberNo));
		return map;
	}
	
}
