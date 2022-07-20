package com.goodee.gdlibrary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.gdlibrary.domain.FnqDTO;
import com.goodee.gdlibrary.mapper.FnqMapper;
import com.goodee.gdlibrary.util.PageUtils2;

@Service
public class FnqServiceImpl implements FnqService {

	@Autowired
	private FnqMapper fnqMapper;
	
	
	// FNQ 테이블 목록 + page 전역변수
	@Override
	public Map<String, Object> getFnqList(int page) {
		
		// page와 totalRecord를 이용해서 페이징 정보를 구한다.
		long totalRecord = fnqMapper.selectFnqCount();
		PageUtils2 p = new PageUtils2();
		p.setPageEntity(totalRecord, page);
		
		// 목록은 beginRecord~endRecord 사이값을 가져온다.
		Map<String, Object> m = new HashMap<>();
		// m.put("beginRecord", p.getBeginRecord());
		// m.put("endRecord", p.getEndRecord());
		m.put("beginRecord", p.getBeginRecord() - 1);
		m.put("recordPerPage", p.getRecordPerPage());
		
		// 목록과 페이징 정보를 반환한다.
		Map<String, Object> map = new HashMap<>();
		map.put("fnqList", fnqMapper.selectFnqList(m));
		map.put("p", p);
		return map;
		
	}
	
	
	// 제목 또는 내용 검색한 결과 
	@Override
	public Map<String, Object> search(HttpServletRequest request) {
			
		// request에서 page 파라미터 꺼내기
		// page 파라미터는 전달되지 않는 경우 page = 1을 사용
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// column, query 파라미터 꺼내기
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		
		// column + query => Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("query", query);
		
		// 검색된 레코드 갯수 가져오기
		long findRecord = fnqMapper.selectFindCount(map);
		
		// findRecord와 page를 알면 PageEntity를 모두 계산할 수 있다.
		PageUtils2 pageUtils = new PageUtils2();
		pageUtils.setPageEntity(findRecord, page);
		
		// beginRecord + endRecord => Map
		// map.put("beginRecord", pageUtils.getBeginRecord());
		// map.put("endRecord", pageUtils.getEndRecord());
		map.put("beginRecord", pageUtils.getBeginRecord() - 1);
		map.put("recordPerPage", pageUtils.getRecordPerPage());
		
		// beginRecord ~ endRecord 사이 검색된 목록 가져오기
		List<FnqDTO> fnqList = fnqMapper.selectFindList(map);
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("fnqList", fnqList);
		res.put("paging", pageUtils);
		
		
		return res;
	}
	
	
	
	
	
	
	
	
	
}
