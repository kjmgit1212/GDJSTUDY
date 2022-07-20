package mybatis.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 목적
// SqlSessionFactory를 1개 만들어서 DAO에게 반환한다.

public class MybatisConfig {

	// DAO에게 SqlSessionFactory 반환
	private SqlSessionFactory sqlSessionFactory;
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	// singleton
	private static MybatisConfig instance = new MybatisConfig();
	private MybatisConfig() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MybatisConfig getInstance() {
		return instance;
	}

}
