package com.goodee.ex05.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goodee.ex05.domain.ProductDTO;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<ProductDTO> list1() {
		// 제품번호, 제품명, 제조사, 가격 (과자 3개 만들기)
		ProductDTO product1 = new ProductDTO(1L, "오징어땅콩", "오리온", 1000);
		ProductDTO product2 = new ProductDTO(2L, "홈런볼", "해태", 2000);
		ProductDTO product3 = new ProductDTO(3L, "웨하스", "롯데", 3000);
		List<ProductDTO> products = Arrays.asList(product1, product2, product3);
		return products;
	}

	@Override
	public List<Map<String, Object>> list2() {
		// 제품번호, 제품명, 제조사, 가격 (라면 3개 만들기)
		Map<String, Object> product1 = new HashMap<String, Object>();
		product1.put("no", 10L);
		product1.put("name", "신라면");
		product1.put("maker", "농심");
		product1.put("price", 500);
		Map<String, Object> product2 = new HashMap<String, Object>();
		product2.put("no", 20L);
		product2.put("name", "진라면");
		product2.put("maker", "오뚜기");
		product2.put("price", 600);
		Map<String, Object> product3 = new HashMap<String, Object>();
		product3.put("no", 30L);
		product3.put("name", "왕뚜껑");
		product3.put("maker", "팔도");
		product3.put("price", 1000);
		return Arrays.asList(product1, product2, product3);
	}
	
	@Override
	public Map<String, Object> list3() {
		ProductDTO product1 = new ProductDTO(100L, "하이트", "하이트진로", 2000);
		ProductDTO product2 = new ProductDTO(200L, "카스", "OB맥주", 3000);
		ProductDTO product3 = new ProductDTO(300L, "테라", "하이트진로", 4000);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("products", Arrays.asList(product1, product2, product3));
		return result;
	}

}
