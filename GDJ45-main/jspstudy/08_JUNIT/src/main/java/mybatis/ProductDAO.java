package mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ProductDAO {

	private SqlSessionFactory factory;
	private static ProductDAO instance = new ProductDAO();
	
	private ProductDAO() {
		try {
			String resource = "mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ProductDAO getInstance() {
		return instance;
	}
	
	public int insertProduct(ProductDTO product) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert("mybatis.product.insertProduct", product);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	public List<ProductDTO> selectProductList() {
		SqlSession ss = factory.openSession();
		List<ProductDTO> products = ss.selectList("mybatis.product.selectProductList");
		ss.close();
		return products;
	}
	
	public ProductDTO selectProductByNo(Long productNo) {
		SqlSession ss = factory.openSession();
		ProductDTO product = ss.selectOne("mybatis.product.selectProductByNo", productNo);
		ss.close();
		return product;
	}
	
	public int deleteProduct(Long productNo) {
		SqlSession ss = factory.openSession(false);
		int res = ss.delete("mybatis.product.deleteProduct", productNo);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
}
