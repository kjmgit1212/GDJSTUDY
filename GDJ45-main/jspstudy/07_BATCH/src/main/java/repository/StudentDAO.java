package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Student;

public class StudentDAO {

	// SqlSessionFactory factory 인스턴스를 필드로 생성해 두고,
	// 모든 메소드는 factory로부터
	// SqlSession ss 인스턴스를 받아서 사용한 뒤 닫는다.
	
	// 필드
	private SqlSessionFactory factory;
	
	// singleton
	private static StudentDAO instance = new StudentDAO();
	private StudentDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static StudentDAO getInstance() {
		return instance;
	}
	
	
	// 1. 전체학생조회
	public List<Student> selectStudentList() {
		SqlSession ss = factory.openSession();
		List<Student> list = ss.selectList("mybatis.mapper.student.selectStudentList");
		ss.close();
		return list;
	}
	
	// 2. 전체학생수
	public int getTotalCount() {
		SqlSession ss = factory.openSession();
		int totalCount = ss.selectOne("mybatis.mapper.student.getTotalCount");
		ss.close();
		return totalCount;
	}
	
	// 3. 전체학생평균
	public double getTotalAverage() {
		SqlSession ss = factory.openSession();
		double totalAverage = ss.selectOne("mybatis.mapper.student.getTotalAverage");
		ss.close();
		return totalAverage;
	}
	
	// 4. 학생삽입
	public int insertStudent(Student student) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert("mybatis.mapper.student.insertStudent", student);
		if(res > 0) ss.commit();
		ss.close();
		return res;
	}
	
	// 5. 학생상세조회
	public Student selectStudentByStuNo(Long stuNo) {
		SqlSession ss = factory.openSession();
		Student student = ss.selectOne("mybatis.mapper.student.selectStudentByStuNo", stuNo);
		ss.close();
		return student;
	}
	
	// 6. 학생수정
	public int updateStudent(Map<String, String> map) {
		SqlSession ss = factory.openSession(false);
		int res = ss.update("mybatis.mapper.student.updateStudent", map);
		if(res > 0) ss.commit();
		ss.close();
		return res;
	}
	
	// 7. 학생삭제
	public int deleteStudent(Long stuNo) {
		SqlSession ss = factory.openSession(false);
		int res = ss.delete("mybatis.mapper.student.deleteStudent", stuNo);
		if(res > 0) ss.commit();
		ss.close();
		return res;
	}
	
	// 8. 학생조회(점수)
	public List<Student> selectStudentByAvg(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		List<Student> list = ss.selectList("mybatis.mapper.student.selectStudentByAvg", map);
		ss.close();
		return list;
	}
	
	// 9. 조회된 학생수(점수)
	public int getSearchCount(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		int searchCount = ss.selectOne("mybatis.mapper.student.getSearchCount", map);
		ss.close();
		return searchCount;
	}
	
	// 10. 조회된 학생평균(점수)
	public double getSearchAverage(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		double searchAverage = ss.selectOne("mybatis.mapper.student.getSearchAverage", map);
		ss.close();
		return searchAverage;
	}
	
	// 11. 평균 기준 상위 3명 조회하기
	public List<Student> selectStudentTop3() {
		SqlSession ss = factory.openSession();
		List<Student> list = ss.selectList("mybatis.mapper.student.selectStudentTop3");
		ss.close();
		return list;
	}
	
}
