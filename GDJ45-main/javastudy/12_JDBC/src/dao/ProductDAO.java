package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dto.Product;

// Singleton Pattern
// 1. 인스턴스를 하나만 만들 수 있는 패턴이다.
// 2. 방법
//     1) 인스턴스를 하나만 선언한다.
//     2) 인스턴스를 외부에서 못 만들게 한다.
//     3) getInstance() 메소드를 호출하면 선언한 인스턴스를 생성한 다음 반환한다.
//        이미 생성했다면 그냥 반환한다.

public class ProductDAO {
	
	// dao는 필드이므로 기본 null 상태이다.
	// 클래스 메소드인 getInstance() 메소드에서는
	// 클래스 변수만 사용할 수 있다.
	private static ProductDAO dao;

	// private 생성자
	// 클래스 외부에서는 생성자를 호출할 수 없다.(인스턴스 생성을 할 수 없다.)
	private ProductDAO() {
		
	}
	
	// 인스턴스가 없는 상태로 getInstance() 메소드를 호출해야 하므로
	// 클래스 메소드로 처리해야 한다.(static)
	public static ProductDAO getInstance() {
		if(dao == null)
			dao = new ProductDAO();
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
			// db.properties 파일 내용 읽기
			InputStream in = new FileInputStream("db.properties");
			Properties properties = new Properties();
			properties.load(in);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			// OracleDriver 로드
			Class.forName("oracle.jdbc.OracleDriver");
			// 접속 반환
			return DriverManager.getConnection(url, user, password);
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
	
	// 제품등록
	public int insertProduct(String name, int price) {
		int res = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO PRODUCT(NO, NAME, PRICE) VALUES(PRODUCT_SEQ.NEXTVAL, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, price);
			res = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return res;
	}
	
	// 제품조회
	public Product selectProductByNo(long no) {
		Product product = null;
		try {
			con = getConnection();
			sql = "SELECT NO, NAME, PRICE FROM PRODUCT WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				product = new Product();
				product.setNo(rs.getLong("NO"));        // rs.getLong(1)
				product.setName(rs.getString("NAME"));  // rs.getString(2)
				product.setPrice(rs.getInt("PRICE"));   // rs.getInt(3)
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return product;
	}
	
	// 제품삭제
	public int deleteProduct(long no) {
		int res = 0;
		try {
			con = getConnection();
			sql = "DELETE FROM PRODUCT WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return res;
	}
	
	// 제품수정
	public int updateProduct(long no, String name, int price) {
		int res = 0;
		try {
			con = getConnection();
			sql = "UPDATE PRODUCT SET NAME = ?, PRICE = ? WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setLong(3, no);
			res = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return res;
	}
	
	// 전체조회
	public List<Product> selectProductList() {
		List<Product> products = new ArrayList<Product>();
		try {
			con = getConnection();
			sql = "SELECT NO, NAME, PRICE FROM PRODUCT";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product(rs.getLong("NO"), rs.getString("NAME"), rs.getInt("PRICE"));
				products.add(product);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return products;
	}
	
	// 범위조회
	public List<Product> selectProductPartList(int begin, int end) {
		List<Product> products = new ArrayList<Product>();
		try {
			con = getConnection();
			sql = "SELECT P.NO, P.NAME, P.PRICE " +
				    "FROM (SELECT ROW_NUMBER() OVER(ORDER BY NO) AS RN, NO, NAME, PRICE " +
				            "FROM PRODUCT) P " +
				   "WHERE P.RN BETWEEN ? AND ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, begin);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product(rs.getLong("NO"), rs.getString("NAME"), rs.getInt("PRICE"));
				products.add(product);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return products;
	}
	
	
	
	
	
	
	
	
	
	
}
