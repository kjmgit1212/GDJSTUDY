package com.goodee.ex05.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex05.domain.ProductDTO;
import com.goodee.ex05.service.ProductService;

@Controller
public class ProductController {

	private ProductService productService;

	@Autowired
	public void setService(ProductService productService) {
		this.productService = productService;
	}
	
	@ResponseBody
	@GetMapping(value="/product/list1", produces="application/json; charset=UTF-8")
	public List<ProductDTO> list1() {
		return productService.list1();  // List 반환 값이 produces에 의해서 JSON 데이터로 변환되어야 함을 나타내고,
		                                // List -> JSON 변환을 위해 jackson이 자동으로 개입한다.
		                                // 반환 값이 JSP이름이 아니라 값임을 나타내기 위해서 @ResponseBody 애너테이션이 필요하다.
	}
	
	@ResponseBody
	@GetMapping(value="/product/list2", produces="application/json; charset=UTF-8")
	public List<Map<String, Object>> list2() {
		return productService.list2();
	}
	
	@ResponseBody
	@GetMapping(value="/product/list3", produces="application/json; charset=UTF-8")
	public Map<String, Object> list3() {
		return productService.list3();
	}
	
}
