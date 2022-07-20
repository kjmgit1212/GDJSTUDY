package com.goodee.ex17.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.goodee.ex17.domain.FreeBoardDTO;
import com.goodee.ex17.mapper.FreeBoardMapper;
import com.goodee.ex17.util.PageUtils;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardMapper freeBoardMapper;
	
	@Override
	public void findFreeBoards(HttpServletRequest request, Model model) {
		
		// totalRecord(DB), page(Parameter)
		int totalRecord = freeBoardMapper.selectFreeBoardCount();
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// PageEntity 계산
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		// Map
		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		
		// 목록 가져오기
		List<FreeBoardDTO> freeBoards = freeBoardMapper.selectFreeBoardList(map);
		
		// free/list.jsp로 전달할 데이터
		model.addAttribute("freeBoards", freeBoards);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/freeBoard/list"));

	}

	@Override
	public int saveFreeBoard(HttpServletRequest request) {
		
		// 게시글 작성자와 내용
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// 작성 IP
		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());
		
		// 게시글 DTO
		FreeBoardDTO freeBoard = new FreeBoardDTO();
		freeBoard.setWriter(writer);
		freeBoard.setContent(content);
		freeBoard.setIp(ip);
		
		return freeBoardMapper.insertFreeBoard(freeBoard);
		
	}

	@Transactional  // saveReply 메소드 내부에서 update + insert 호출하고 있으므로
	@Override
	public int saveReply(HttpServletRequest request) {
		
		// 댓글 작성자와 댓글 내용
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// 원글의 depth, groupNo, groupOrd
		int depth = Integer.parseInt(request.getParameter("depth"));
		Long groupNo = Long.parseLong(request.getParameter("groupNo"));
		int groupOrd = Integer.parseInt(request.getParameter("groupOrd"));
		
		// 추가로 작성 IP
		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());
		
		// 원글의 depth, groupNo, groupOrd를 이용해서 댓글의 depth, groupNo, groupOrd를 계산
		// 댓글 depth    : 원글 depth + 1
		// 댓글 groupNo  : 원글 groupNo
		// 댓글 groupOrd : 같은 그룹의 기존 댓글 모두 groupOrd + 1 처리 후 원글 groupOrd + 1
		
		// 같은 그룹의 기존 댓글 모두 groupOrd + 1 처리를 위해서는
		// FreeBoardMapper에 원글의 정보를 넘겨야 한다.
		
		// 원글 DTO
		FreeBoardDTO freeBoard = new FreeBoardDTO();
		freeBoard.setGroupNo(groupNo);
		freeBoard.setGroupOrd(groupOrd);
		freeBoardMapper.updatePreviousReply(freeBoard);
		
		// 삽입할 댓글 DTO
		FreeBoardDTO reply = new FreeBoardDTO();
		reply.setWriter(writer);
		reply.setContent(content);
		reply.setDepth(depth + 1);
		reply.setGroupNo(groupNo);
		reply.setGroupOrd(groupOrd + 1);
		reply.setIp(ip);
		
		return freeBoardMapper.insertReply(reply);
		
	}

	@Override
	public int removeFreeBoard(Long freeBoardNo) {
		return freeBoardMapper.deleteFreeBoard(freeBoardNo);
	}

}
