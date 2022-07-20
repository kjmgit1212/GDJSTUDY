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

import domain.Emp;

public class EmpDAO {

	// singleton
	private static EmpDAO empDAO = new EmpDAO();
	private EmpDAO() { }
	public static EmpDAO getInstance() {
		return empDAO;
	}
	
	// context.xml 내용을 읽어서 DataSource 인스턴스 생성
	// JNDI : Java Naming and Directory Interface API
	// 특정 리소스에 이름(name)을 부여하고 해당 이름(name)을 찾는(lookup) 방식
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
	
	// 2. 사원 추가하기
	// 1) 매개변수 : Emp emp (사원 1명의 정보)
	// 2) 반환     : int     (성공하면 1, 실패하면 0)
	public int insertEmp(Emp emp) {
		int res = 0;
		try {
			con = dataSource.getConnection();  // Connection 대여
			sql = "INSERT INTO EMP(EMPNO, NAME, DEPT, HIRED) VALUES (EMP_SEQ.NEXTVAL, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getDept());
			res = ps.executeUpdate();  // DML(INSERT, UPDATE, DELETE)은 executeUpdate() 메소드 사용
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	// 3. 전체사원목록 가져오기
	// 1) 매개변수 : 없음
	// 2) 반환     : List<Emp> (여러 사원의 정보)
	public List<Emp> selectEmpList() {
		List<Emp> list = new ArrayList<Emp>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT EMPNO, NAME, DEPT, HIRED FROM EMP ORDER BY EMPNO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();  // SELECT문은 executeQuery() 메소드 실행
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpNo(rs.getLong("EMPNO"));
				emp.setName(rs.getString("NAME"));
				emp.setDept(rs.getString("DEPT"));
				emp.setHired(rs.getDate("HIRED"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	// 4. 사원상세정보 가져오기
	// 1) 매개변수 : Long empNo (사원번호)
	// 2) 반환     : Emp (사원 1명의 정보)
	public Emp selectEmpByEmpNo(Long empNo) {
		Emp emp = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT EMPNO, NAME, DEPT, HIRED FROM EMP WHERE EMPNO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, empNo);
			rs = ps.executeQuery();
			if(rs.next()) {
				emp = new Emp();
				emp.setEmpNo(rs.getLong("EMPNO"));
				emp.setName(rs.getString("NAME"));
				emp.setDept(rs.getString("DEPT"));
				emp.setHired(rs.getDate("HIRED"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return emp;
	}
	
	// 5. 사원정보 수정하기
	// 1) 매개변수 : Emp emp (사원 1명의 정보)
	// 2) 반환     : int     (성공하면 1, 실패하면 0)
	public int updateEmp(Emp emp) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE EMP SET NAME = ?, DEPT = ? WHERE EMPNO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getDept());
			ps.setLong(3, emp.getEmpNo());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	// 6. 사원정보 삭제하기
	// 1) 매개변수 : Long empNo (삭제할 사원번호)
	// 2) 반환     : int        (성공하면 1, 실패하면 0)
	public int deleteEmp(Long empNo) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM EMP WHERE EMPNO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, empNo);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
}
