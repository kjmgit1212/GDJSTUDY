package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.MemberDTO;

public class MemberDAO {

	private SqlSessionFactory factory;
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public int insertMember(MemberDTO member) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert("mybatis.mapper.member.insertMember", member);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	public List<MemberDTO> selectMemberList() {
		SqlSession ss = factory.openSession();
		List<MemberDTO> members = ss.selectList("mybatis.mapper.member.selectMemberList");
		ss.close();
		return members;
	}
	
	public int getMemberCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne("mybatis.mapper.member.getMemberCount");
		ss.close();
		return count;
	}
	
	public MemberDTO selectMemberByNo(Long no) {
		SqlSession ss = factory.openSession();
		MemberDTO member = ss.selectOne("mybatis.mapper.member.selectMemberByNo", no);
		ss.close();
		return member;
	}
	
	public int updateMember(MemberDTO member) {
		SqlSession ss = factory.openSession(false);
		int res = ss.update("mybatis.mapper.member.updateMember", member);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	public int deleteMember(Long no) {
		SqlSession ss = factory.openSession(false);
		int res = ss.delete("mybatis.mapper.member.deleteMember", no);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
}
