package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyConnection {

	// field
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String USER = "SCOTT";
	private final static String PASSWORD = "1111";
	
	// method
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("OracleDriver 클래스를 찾을 수 없습니다.", e);
		} catch (SQLException e) {
			throw new RuntimeException("DB접속정보를 확인하세요.", e);
		}
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(ps != null)  ps.close();
			if(rs != null)  rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
