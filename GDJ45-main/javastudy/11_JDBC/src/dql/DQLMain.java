package dql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.MyConnection;
import dto.Board;

public class DQLMain {

	// 결과 집합(ResultSet)이 1개인 경우
	// 결과 행 1개 -> Board board
	public static void selectOne() {
		
		/* DB접속     */  Connection con = null;
		/* 쿼리문실행 */  PreparedStatement ps = null;
		/* 실행결과   */  ResultSet rs = null;
		/* 쿼리문자체 */  String sql = null;
		
		try {
			
			// 1. DB접속
			con = MyConnection.getConnection();
			
			// 2. 쿼리문작성
			//    WHERE절의 조건으로 PK/UNIQUE 칼럼의 동등비교(=)를 진행하면 결과 집합은 1개이다.
			sql = "SELECT NO, TITLE, HIT, CREATED FROM BOARDS WHERE NO = ?";
			
			// 3. ps 생성
			ps = con.prepareStatement(sql);
			
			// 4. ? 표시에 값을 전달하기
			long findNo = 2;
			ps.setLong(1, findNo);  // 1번째 ?에 findNo 전달하기
			
			// 5. 쿼리문실행
			//    SELECT문 실행은 executeQuery() 메소드를 이용한다.
			//    executeQuery() 메소드의 반환 값은 ResultSet 이다.
			//    ResultSet의 각 행(ROW)은 next() 메소드를 호출해서 확인한다.
			//    결과 집합이 1개인 경우에는 분기문 if문을 이용해서 결과 집합의 유무를 확인한다.
			rs = ps.executeQuery();
			
			if(rs.next()) {  // next() 메소드는 결과 행(ROW)이 있으면 true, 없으면 false를 반환한다.
				
				/*
					| NO | TITLE | HIT | CREATED  |
					| 2  | 제목  | 10  | 22/03/10 |  <= rs.next() 메소드로 가져올 수 있다.
				
					rs에 저장된 행(RWO) 전체 데이터를 칼럼(column)으로 분리하기
					1. 칼럼의 이름
						1) rs.getLong("NO")
						2) rs.getString("TITLE")
						3) rs.getInt("HIT")
						4) rs.getDate("CREATED")
					2. 칼럼의 번호
						1) rs.getLong(1)
						2) rs.getString(2)
						3) rs.getInt(3)
						4) rs.getDate(4)
				*/
				
				long no = rs.getLong("NO");
				String title = rs.getString("TITLE");
				int hit = rs.getInt("HIT");
				Date created = rs.getDate("CREATED");
				
				Board board = new Board();
				board.setNo(no);
				board.setTitle(title);
				board.setHit(hit);
				board.setCreated(created);
				
				System.out.println("글번호 " + board.getNo());
				System.out.println("제목 " + board.getTitle());
				System.out.println("조회수 " + board.getHit());
				System.out.println("작성일 " + board.getCreated());
				
			} else {
				System.out.println("조회된 행(ROW)이 없습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, rs);
		}
		
	}
	
	// 결과 집합(ResultSet)이 여러 개인 경우
	// 결과 행 여러 개 -> List<Board> boards
	public static void selectList() {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			
			con = MyConnection.getConnection();
			sql = "SELECT NO, TITLE, HIT, CREATED FROM BOARDS";
			ps = con.prepareStatement(sql);
			
			// 쿼리문실행
		    //    ResultSet의 각 행(ROW)은 next() 메소드를 호출해서 확인한다.
			//    결과 집합이 여러 개인 경우에는 반복문 while문을 이용해서 결과 집합의 각 행을 하나씩 호출해서 확인한다.
			rs = ps.executeQuery();
			
			List<Board> boards = new ArrayList<Board>();
			
			while(rs.next()) {
				
				// Board board 생성(결과 행 1개가 board가 된다.)
				Board board = new Board();
				board.setNo(rs.getLong(1));
				board.setTitle(rs.getString(2));
				board.setHit(rs.getInt(3));
				board.setCreated(rs.getDate(4));
				
				// ArrayList에 board 저장(결과 행 1개를 boards 리스트에 add한다.)
				boards.add(board);
				
			}
			
			// 결과 집합 확인
			for(Board board : boards) {
				System.out.println("글번호 " + board.getNo());
				System.out.println("제목 " + board.getTitle());
				System.out.println("조회수 " + board.getHit());
				System.out.println("작성일 " + board.getCreated());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, rs);
		}
		
	}
	
	public static void main(String[] args) {
		selectList();
	}

}
