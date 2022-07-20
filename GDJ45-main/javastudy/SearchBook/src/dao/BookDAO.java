package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import dto.Book;

public class BookDAO {

	// 싱글톤
	// 미리 dao를 하나만 만들어 두고 getInstance()를 호출하면 반환해준다.
	// 외부에서는 dao를 만들 수 없다.
	private static BookDAO dao = new BookDAO();
	private BookDAO() { }
	public static BookDAO getInstance() {
		return dao;
	}
	
	// 필드
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// 접속
	private Connection getConnection() {
		try {
			// DB접속정보
			FileInputStream fis = new FileInputStream("db.properties");
			Properties properties = new Properties();
			properties.load(fis);
			// DB접속반환
			Class.forName("oracle.jdbc.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", properties);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("db.properties 파일이 존재하지 않습니다.");
		} catch (IOException e) {
			throw new RuntimeException("db.properties 파일을 읽을 수 없습니다.");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("OracleDriver를 로드할 수 없습니다.");
		} catch (SQLException e) {
			throw new RuntimeException("DB접속 정보를 확인하세요.");
		}
	}
	
	// 접속해제
	private void close() {
		try {
			if(con != null) con.close();
			if(ps != null)  ps.close();
			if(rs != null)  rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Book저장
	public int insertBook(Book book) {
		int res = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO BOOK VALUES(BOOK_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYYMMDD'))";
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getLink());
			ps.setString(3, book.getImage());
			ps.setString(4, book.getAuthor());
			ps.setInt(5, book.getPrice());
			ps.setInt(6, book.getDiscount());
			ps.setString(7, book.getPublisher());
			ps.setString(8, book.getIsbn());
			ps.setString(9, book.getDescription());
			ps.setString(10, book.getPubdate());
			res = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return res;
	}
	
}
