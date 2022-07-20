package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	private Connection con;
	private static MyConnection instance = new MyConnection();
	private MyConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "1111");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MyConnection getInstance() {
		return instance;
	}
	public Connection getConnection() {
		return con;
	}
	
}
