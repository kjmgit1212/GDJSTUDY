package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jdbc.ProductDAO;
import jdbc.ProductDTO;


// JUnit 단위 테스트

// 1. DAO의 메소드 단위로 단위 테스트를 실시한다.
// 2. Service 실행 결과가 "특정 값"인 경우 Service를 테스트 할 수도 있다.
// 3. test 패키지 또는 src/main/test 소스 폴더에서 작업한다.
// 4. 프로젝트의 Build Path에서 JUnit 라이브러리를 추가해야 한다.
// 5. 테스트 실행 방법 : 프로젝트 실행 메뉴 Run - JUnit



class ProductTestCase {

	// @BeforeEach  // JUnit 테스트 이전에 실행되는 메소드이다.
	void 제품등록테스트() {
		
		// 등록할 제품 생성
		ProductDTO product = ProductDTO.builder()
				.name("오감자")
				.price(3000)
				.image("오감자.jpg")
				.build();
		
		// DAO의 insertProduct() 메소드 호출
		int res = 0;
		try {
			res = ProductDAO.getInstance().insertProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 결과 res가 1이면 "성공", 0이면 "실패"
		// assertEquals(예상값, 실제발생한값, [실패시메시지])
		// expected : 예상값
		// actual   : 실제발생한값
		assertEquals(1, res, "제품 등록 실패");
		
	}
	
	
	// @Test  // JUnit 테스트 할 때 실행되는 메소드이다.
	void 제품목록테스트() {  // 테스트 메소드명은 주로 "한글"로 만들어서 테스트 파악을 쉽게 한다.

		// List<ProductDTO> products = ProductDAO.getInstance().selectProductList();
		// assertEquals(4, products.size());
		
		int count = ProductDAO.getInstance().getProductCount();
		assertEquals(4, count);		
		
	}
	
	@Test
	void 제품조회테스트() {
		
		// ProductDTO product = ProductDAO.getInstance().selectProductByNo(1L);
		// assertNotNull(product);  // product이 null이 아니면 성공, null이면 실패
		
	}
	
	@AfterEach // JUnit 테스트 다음에 실행되는 메소드이다.
	void 제품삭제테스트() {
		
		// @BeforeEach에서 추가한 오감자 삭제하기
		int res = ProductDAO.getInstance().deleteProduct(14L);
		assertEquals(1, res);
		
	}

}
