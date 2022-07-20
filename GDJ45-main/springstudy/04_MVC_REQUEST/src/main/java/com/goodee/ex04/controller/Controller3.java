package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex04.domain.Product;

@Controller
public class Controller3 {


	@PostMapping("/remove1")
	public String remove1(HttpServletRequest request,  // <input> 태그의 name 속성을 파라미터로 인식해서 request가 받아줍니다.
						Model model) {
		
		String modelName = request.getParameter("model");
		int price = Integer.parseInt(request.getParameter("price"));
		
		Product product = new Product(modelName, price);
		
		model.addAttribute("product", product);  // 이동할 JSP로 전달할 값을 보관하는 곳이 Model입니다.
		
		return "product";  // product.jsp로 forward 이동합니다.
		
	}
	
	
	@PostMapping("/remove2")
	public String remove2(@RequestParam(value="model") String modelName,  // <input name="model">에 입력된 값을 String modelName에 저장합니다.
						@RequestParam(value="price") int price,           // <input name="price">에 입력된 값을 int price에 저장합니다.
						Model model) {
		
		Product product = new Product(modelName, price);
		
		model.addAttribute("product", product);  // 이동할 JSP로 전달할 값을 보관하는 곳이 Model입니다.
		
		return "product";  // product.jsp로 forward 이동합니다.
		
	}
	
	
	@PostMapping("/remove3")
	public String remove3(Product product,  // Product 클래스의 setModel과 setPrice 메소드가 <input name="model">과 <input name="price">에 입력된 값을 받아줍니다.
						Model model) {
		
		model.addAttribute("product", product);  // 이동할 JSP로 전달할 값을 보관하는 곳이 Model입니다.
		
		return "product";  // product.jsp로 forward 이동합니다.
		
	}
	
	
}
