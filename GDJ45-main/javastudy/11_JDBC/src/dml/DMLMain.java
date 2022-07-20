package dml;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connect.MyConnection;

public class DMLMain {

	// DML : INSERT, UPDATE, DELETE
	// COMMIT과 ROLLBACK을 사용할 필요가 있으나,
	// JDBC는 Auto Commit을 사용하므로 코드를 작성할 필요가 없다.
	
	// INSERT, UPDATE, DELETE문을 처리하는 자바 코드는 동일하다.
	
	// INSERT
	public static void insert1() {
		
		/* DB접속     */  Connection con = null;
		/* 쿼리문실행 */  PreparedStatement ps = null;
		/* 실행결과   */  int res = 0;
		/* 쿼리문자체 */  String sql = null;
		
		try {

			// 1. DB접속
			// MyConnection 클래스의 getConnection 메소드를 호출하면 된다.
			con = MyConnection.getConnection();
			
			// 2. 쿼리문작성
			//    쿼리문자체에는 세미콜론이 마지막에 포함되지 않는다.
			sql = "INSERT INTO BOARDS(NO, TITLE, HIT, CREATED) VALUES(BOARDS_SEQ.NEXTVAL, '긴급공지', 0, TO_DATE('22/03/07', 'YY/MM/DD'))";
			
			// 3. 쿼리문실행 인스턴스 ps 생성
			//    쿼리문을 미리 준비해서 전달해야 한다.
			ps = con.prepareStatement(sql);
			
			// 4. 쿼리문실행
			//    1) DML(INSERT, UPDATE, DELETE)의 실행 메소드는 executeUpdate() 이다.
			//    2) executeUpdate() 메소드의 반환 값(실행 결과)
			//        - 의미 : 작업이 수행된 행(row)의 갯수가 반환된다.
			//        (1) 0 : 작업이 수행되지 않았다.
			//        (2) 1 : 작업이 수행되었다.
			res = ps.executeUpdate();
			
			if(res > 0) {
				// con.commit();  // 커밋하겠다. 생략한다.
				System.out.println("게시글이 추가되었습니다.");
			} else {
				System.out.println("게시글이 추가되지 않았습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, null);
		}
		
	}
	
	// INSERT
	public static void insert2() {
		
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		String sql = null;
		
		try {
			
			// 1. DB접속
			con = MyConnection.getConnection();
			
			// 2. 쿼리문작성
			//    ? 자리에 값을 전달해야 실행할 수 있다.
			sql = "INSERT INTO BOARDS(NO, TITLE, HIT, CREATED) VALUES(BOARDS_SEQ.NEXTVAL, ?, ?, SYSDATE)";
			
			// 3. ps 생성
			ps = con.prepareStatement(sql);
			
			// 4. ? 표시에 값을 전달하기
			String title = "필독";  // String을 전달할 때는 setString()
			int hit = 10;           // int를 전달할 때는 setInt()
			ps.setString(1, title);  // 1번째 ?에 title 전달
			ps.setInt(2, hit);       // 2번째 ?에 hit 전달
			
			// 5. 쿼리문실행
			res = ps.executeUpdate();
			
			if(res > 0) {
				System.out.println("게시글이 추가되었습니다.");
			} else {
				System.out.println("게시글이 추가되지 않았습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, null);
		}
		
	}

	// UPDATE
	public static void update() {
		
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		String sql = null;
		
		try {
			con = MyConnection.getConnection();
			sql = "UPDATE BOARDS SET TITLE = ?, HIT = ? WHERE NO = ?";
			ps = con.prepareStatement(sql);
			String title = "수정제목";
			int hit = 99;
			long no = 2;
			ps.setString(1, title);
			ps.setInt(2, hit);
			ps.setLong(3, no);
			res = ps.executeUpdate();
			if(res > 0) {
				System.out.println("게시글이 수정되었습니다.");
			} else {
				System.out.println("게시글이 수정되지 않았습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, null);
		}
		
	}
	
	// DELETE
	public static void delete() {
		
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		String sql = null;
		
		try {
			con = MyConnection.getConnection();
			sql = "DELETE FROM BOARDS WHERE NO = ?";
			ps = con.prepareStatement(sql);
			long no = 1;
			ps.setLong(1, no);
			res = ps.executeUpdate();
			if(res > 0)
				System.out.println("게시글이 삭제되었습니다.");
			else
				System.out.println("게시글이 삭제되지 않았습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, null);
		}
		
	}
	
	public static void main(String[] args) {
		delete();
	}

}
