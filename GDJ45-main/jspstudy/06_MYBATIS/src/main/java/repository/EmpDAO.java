package repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Emp;
import mybatis.config.MybatisConfig;

public class EmpDAO {

	// MybatisConfig로부터 SqlSessionFactory를 받아서 사용한다.
	// SqlSessionFactory를 필드로 저장해 두고,
	// 모든 메소드(삽입, 수정, 삭제, 조회)들이 SqlSessionFactory를 사용한다.
	private SqlSessionFactory factory;
	
	// MybatisConfig 인스턴스
	// MybatisConfig.getInstance()
	
	// MybatisConfig 인스턴스가 가지고 있는 SqlSessionFactory
	// MybatisConfig.getInstance().getSqlSessionFactory()
	
	// singleton
	private static EmpDAO empDAO = new EmpDAO();
	private EmpDAO() {
		factory = MybatisConfig.getInstance().getSqlSessionFactory();
	}
	public static EmpDAO getInstance() {
		return empDAO;
	}
	
	// 메소드마다 하나의 쿼리문을 실행한다.
	// 메소드이름과 쿼리문의 id를 통일하면 작업이 수월하다.
	
	
	private final String MAPPER = "mybatis.mapper.emp.";
	
	
	// 1. 전체사원조회하기
	public List<Emp> selectEmpList() {
		
		// 1) SqlSessionFactory로부터 SqlSession 인스턴스를 구한다.
		SqlSession ss = factory.openSession();
		
		// 2) 쿼리문 실행하기
		//    (1) 매퍼 emp.xml
		//        <mapper namespace="mybatis.mapper.emp">
		//    (2) 쿼리문
		//        <select id="selectEmpList">
		//    (3) 메소드
		//        - selectOne()  : 반환 결과가 1개
		//        - selectList() : 반환 결과가 여러 개
		List<Emp> list = ss.selectList(MAPPER + "selectEmpList");
		
		// 3) SqlSession 인스턴스 닫기
		ss.close();
		
		// 4) 결과 반환
		return list;
		
	}
	
	
	// 2. 개별사원조회하기
	public Emp selectEmpByNo(Long no) {
		
		// 1) SqlSession 인스턴스
		SqlSession ss = factory.openSession();
		
		// 2) 쿼리문 실행
		//    - selectOne()  : 반환 결과가 사원 1명
		//    - selectList() : 반환 결과가 사원 여러 명
		Emp emp = ss.selectOne(MAPPER + "selectEmpByNo", no);
		
		// 3) SqlSession 인스턴스 닫기
		ss.close();
		
		// 4) 결과 반환
		return emp;
		
	}
	
	// 3. 사원삽입하기
	public int insertEmp(Emp emp) {
		
		// 1) SqlSession 인스턴스
		//    openSession()      : commit 필요 없는 select
		//    openSession(false) : commit 필요한 insert/update/delete
		SqlSession ss = factory.openSession(false);
		
		// 2) 쿼리문 실행
		int res = ss.insert(MAPPER + "insertEmp", emp);
		
		// 3) 삽입 성공 시 commit
		if(res > 0) {
			ss.commit();
		}
		
		// 4) SqlSession 인스턴스 닫기
		ss.close();
		
		// 5) 결과 반환
		return res;
		
	}
	
	
	// 4. 사원수정하기
	public int updateEmp(Map<String, Object> map) {
		
		// 1) SqlSession 인스턴스
		//    commit 할 수 있도록 openSession(false) 호출
		SqlSession ss = factory.openSession(false);
		
		// 2) 쿼리문 실행
		int res = ss.update(MAPPER + "updateEmp", map);
		
		// 3) 수정 성공 시 commit
		if(res > 0) {
			ss.commit();
		}
		
		// 4) SqlSession 인스턴스 닫기
		ss.close();
		
		// 5) 결과 반환
		return res;
		
	}
	
	
	// 5. 사원삭제하기
	public int deleteEmp(Long no) {
		
		SqlSession ss = factory.openSession(false);
		int res = ss.delete(MAPPER + "deleteEmp", no);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
		
	}

}
