package com.goodee.ex07;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.goodee.ex07.config.BoardConfig;
import com.goodee.ex07.domain.BoardDTO;
import com.goodee.ex07.service.BoardService;

// 안녕. 난 JUnit4 단위 테스트를 할 거야.
// @RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MySpringJUnit4ClassRunner.class)

// 안녕. 단위 테스트를 할 때 여기 있는 bean을 만들어서 수행해줘.
@ContextConfiguration(classes= {BoardConfig.class})

// root-context.xml로 bean을 만들었으면 아래처럼 하면 되지.
// @ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardTestCase {

	
	// BoardService가 필요해요.(DI)
	// BoardService가 동작하면 BoardRepository도 동작한다고 볼 수 있어요.	
	@Autowired
	private BoardService boardService;
	
	
	// 단위 테스트의 메소드 이름은 "한글"로 해도 좋아요.
	
	
	@Test
	public void 목록테스트() {
		List<BoardDTO> boards = boardService.findBoards();
		assertEquals(0, boards.size());
	}

}
