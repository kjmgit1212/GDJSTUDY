package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTestMain {

	public static void main(String[] args) {
		
		// Oracle JDBC(ojdbc6.jar)에 있는 OracleDriver 클래스 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");  // 패키지를 포함한 전체 이름
		} catch (ClassNotFoundException e) {
			System.out.println("OracleDriver 클래스를 찾을 수 없습니다.");
		}
		
		// Connection에서 사용할 데이터
		final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		final String USER = "SCOTT";
		final String PASSWORD = "1111";
		
		// Connection 생성
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("접속되었습니다.");
		} catch (SQLException e) {
			System.out.println("DB접속정보를 확인하세요.");
		}
		
		// Connection 닫기
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
