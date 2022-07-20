package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Board;

public class BoardDAO {

	// singleton
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() { }
	public static BoardDAO getInstance() {
		return dao;
	}
	
	// DataSource
	private static DataSource dataSource;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle11g");
			// WAS가 톰캣인 경우   java:comp/env/
			// <Resource name>     jdbc/oracle11g
		} catch(NamingException e) {
			System.out.println("Resource name을 찾을 수 없습니다.");
		}
	}
	
	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// method
	
	// 1. 접속 해제하기
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();  // Connection의 반납
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 2. 게시글목록가져오기
	public List<Board> selectBoardList() {
		List<Board> list = new ArrayList<Board>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT * FROM BOARD ORDER BY NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setNo(rs.getLong("NO"));
				board.setWriter(rs.getString("WRITER"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setIp(rs.getString("IP"));
				board.setHit(rs.getLong("HIT"));
				board.setCreated(rs.getDate("CREATED"));
				board.setLastModified(rs.getDate("LASTMODIFIED"));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	// 3. 전체게시글갯수가져오기
	public Long selectBoardCount() {
		Long count = 0L;
		try {
			con = dataSource.getConnection();
			sql = "SELECT COUNT(*) AS 갯수 FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			// rs는 반드시 next()를 호출해야 사용할 수 있다.
			if(rs.next()) {
				count = rs.getLong("갯수");  // count = rs.getLong(1)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return count;
	}
	
	// 4. 게시글삽입하기
	public int insertBoard(Board board) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD(NO, WRITER, TITLE, CONTENT, IP, HIT, CREATED, LASTMODIFIED) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContent());
			ps.setString(4, board.getIp());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	// 5. 게시글조회하기
	public Board selectBoardByNo(Long no) {
		Board board = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT * FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new Board();
				board.setNo(rs.getLong("NO"));
				board.setWriter(rs.getString("WRITER"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setIp(rs.getString("IP"));
				board.setHit(rs.getLong("HIT"));
				board.setCreated(rs.getDate("CREATED"));
				board.setLastModified(rs.getDate("LASTMODIFIED"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return board;
	}
	
	// 6. 조회수늘리기
	public int updateHit(Long no) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	// 7. 게시글수정하기
	public int updateBoard(Board board) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, LASTMODIFIED = SYSDATE WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setLong(3, board.getNo());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	// 8. 게시글삭제하기
	public int deleteBoard(Long no) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
}
