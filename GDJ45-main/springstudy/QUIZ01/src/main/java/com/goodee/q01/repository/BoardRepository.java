package com.goodee.q01.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.goodee.q01.domain.BoardDTO;

@Repository
public class BoardRepository {

	private DataSource dataSource;
	
	public BoardRepository() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardDTO> selectBoards(){
		List<BoardDTO> boards = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, IP, HIT, CREATED, LASTMODIFIED FROM BOARD ORDER BY NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO(
						rs.getLong(1),    // rs.getLong("NO")
						rs.getString(2),  // rs.getString("WRITER")
						rs.getString(3),  // rs.getString("TITLE")
						rs.getString(4),  // rs.getString("CONTENT")
						rs.getString(5),  // rs.getString("IP")
						rs.getLong(6),    // rs.getLong("HIT")
						rs.getDate(7),    // rs.getDate("CREATED")
						rs.getDate(8));   // rs.getString("LASTMODIFIED")
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return boards;
	}
	
	public Long selectBoardCount() {
		Long count = 0L;
		try {
			con = dataSource.getConnection();
			sql = "SELECT COUNT(*) AS 갯수 FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getLong(1);  // rs.getLong("갯수")
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return count;
	}
	
	public BoardDTO selectBoardByNo(Long no) {
		BoardDTO board = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, IP, HIT, CREATED, LASTMODIFIED FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new BoardDTO(
					rs.getLong(1),    // rs.getLong("NO")
					rs.getString(2),  // rs.getString("WRITER")
					rs.getString(3),  // rs.getString("TITLE")
					rs.getString(4),  // rs.getString("CONTENT")
					rs.getString(5),  // rs.getString("IP")
					rs.getLong(6),    // rs.getLong("HIT")
					rs.getDate(7),    // rs.getDate("CREATED")
					rs.getDate(8));   // rs.getString("LASTMODIFIED")
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return board;
	}
	
	public void updateHit(Long no) {
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	public int insertBoard(BoardDTO board) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, SYSDATE)";
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
	
	public int updateBoard(BoardDTO board) {
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
