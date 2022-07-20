package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BoardDTO;

public class BoardDAO {

	private SqlSessionFactory factory;
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public List<BoardDTO> selectBoardList() {
		SqlSession ss = factory.openSession();
		List<BoardDTO> boards = ss.selectList("mybatis.mapper.board.selectBoardList");
		ss.close();
		return boards;
	}
	
	public Long getBoardCount() {
		SqlSession ss = factory.openSession();
		Long count = ss.selectOne("mybatis.mapper.board.getBoardCount");
		ss.close();
		return count;
	}
	
	public BoardDTO selectBoardByNo(Long no) {
		SqlSession ss = factory.openSession();
		BoardDTO board = ss.selectOne("mybatis.mapper.board.selectBoardByNo", no);
		ss.close();
		return board;
	}
	
	public int insertBoard(BoardDTO board) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert("mybatis.mapper.board.insertBoard", board);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	public int updateBoard(BoardDTO board) {
		SqlSession ss = factory.openSession(false);
		int res = ss.update("mybatis.mapper.board.updateBoard", board);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	public int deleteBoard(Long no) {
		SqlSession ss = factory.openSession(false);
		int res = ss.delete("mybatis.mapper.board.deleteBoard", no);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}

}
