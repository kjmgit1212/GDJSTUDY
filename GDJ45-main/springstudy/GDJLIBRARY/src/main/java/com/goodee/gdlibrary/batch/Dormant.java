package com.goodee.gdlibrary.batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.gdlibrary.domain.MemberDTO;
import com.goodee.gdlibrary.mapper.MemberMapper;

@Component
public class Dormant {

	@Autowired
	private MemberMapper memberMapper;

	@Transactional
	@Scheduled(cron="0 30 0 * * ?") // 오전 12:30분 마다 -> 언니꺼랑 맞춰둠
	public void execute() throws Exception {

		List<MemberDTO> member = memberMapper.selectMemberLogSignIn();
		
		if(member.isEmpty() == false) {
			for(int i = 0; i < member.size(); i++) {
				memberMapper.insertDormant(member.get(i));
				memberMapper.deleteMemberInDormant(member.get(i).getMemberId());
				
			}
		}
	}
}
