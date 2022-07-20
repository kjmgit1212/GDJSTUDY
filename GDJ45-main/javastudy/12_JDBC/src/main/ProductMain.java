package main;

import service.ProductService;

public class ProductMain {

	public static void main(String[] args) {
		
		ProductService service = new ProductService();
		service.execute();
		
	}

}
