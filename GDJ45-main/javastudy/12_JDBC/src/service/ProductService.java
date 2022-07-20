package service;

import java.util.List;
import java.util.Scanner;

import dao.ProductDAO;
import dto.Product;

public class ProductService {

	// 필드
	private Scanner sc;
	private ProductDAO dao;
	
	// 생성자
	public ProductService() {
		sc = new Scanner(System.in);
		dao = ProductDAO.getInstance();
	}
	
	// 제품등록
	public void addProduct() {
		System.out.println("===제품등록===");
		System.out.print("제품명 >>> ");
		String name = sc.next();
		System.out.print("제품가격 >>> ");
		int price = sc.nextInt();
		int res = dao.insertProduct(name, price);
		if(res > 0)
			System.out.println("===" + name + " 등록 성공===");
		else
			System.out.println("===" + name + " 등록 실패===");
	}
	// 제품삭제
	public void removeProduct() {
		System.out.println("=== 제품삭제 ===");
		System.out.print("삭제할 제품번호 >>> ");
		long no = sc.nextLong();
		Product product = dao.selectProductByNo(no);
		if(product == null) {
			System.out.println("해당 제품이 없습니다.");
			return;
		}
		String name = product.getName();
		System.out.print(name + " 제품을 삭제할까요(y/n)? ");
		String yn = sc.next();
		if(yn.equalsIgnoreCase("y")) {
			int res = dao.deleteProduct(no);
			if(res > 0)
				System.out.println("===" + name + " 삭제 성공===");
			else
				System.out.println("===" + name + " 삭제 실패===");
		} else {
			System.out.println(name + " 제품 삭제가 취소되었습니다.");
		}
	}
	// 제품수정
	public void modifyProduct() {
		System.out.println("=== 제품수정 ===");
		System.out.print("수정할 제품번호 >>> ");
		long no = sc.nextLong();
		Product product = dao.selectProductByNo(no);
		if(product == null) {
			System.out.println("해당 제품이 없습니다.");
			return;
		}
		System.out.println("현재 제품번호 " + no + "번 제품은 " + product.getName() + "입니다.");
		System.out.print("수정할 제품명 >>> ");
		String name = sc.next();
		System.out.print("수정할 제품가격 >>> ");
		int price = sc.nextInt();
		int res = dao.updateProduct(no, name, price);
		if(res > 0)
			System.out.println("=== 수정 성공 ===");
		else
			System.out.println("=== 수정 실패 ===");
	}
	// 제품조회
	public void findProduct() {
		System.out.println("=== 제품조회 ===");
		System.out.print("조회할 제품번호 >>> ");
		long no = sc.nextLong();
		Product product = dao.selectProductByNo(no);
		if(product == null)
			System.out.println("제품번호 " + no + "인 제품이 없습니다.");
		else
			System.out.println("조회결과 " + product);
	}
	// 전체조회
	public void findAllProducts() {
		System.out.println("=== 전체조회 ===");
		List<Product> products = dao.selectProductList();
		if(products.isEmpty()) {  // if(products.size() == 0)
			System.out.println("저장된 제품이 없습니다.");
			return;
		}
		for(Product product : products)
			System.out.println(product);
	}
	// 범위조회
	public void findPartProducts() {
		System.out.println("=== 범위조회 ===");
		System.out.print("시작 >>> ");
		int begin = sc.nextInt();
		System.out.print("끝 >>> ");
		int end = sc.nextInt();
		List<Product> products = dao.selectProductPartList(begin, end);
		if(products.isEmpty()) {
			System.out.println("지정된 범위에 저장된 제품이 없습니다.");
			return;
		}
		for(Product product : products)
			System.out.println(product);
	}
	// 실행
	public void execute() {
		while(true) {
			System.out.print("1.추가 2.삭제 3.수정 4.조회 5.전체 6.범위 0.종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();  // 1~0 숫자 입력 후 누른 Enter키 제거
			switch(choice) {
			case 1 : addProduct(); break;
			case 2 : removeProduct(); break;
			case 3 : modifyProduct(); break;
			case 4 : findProduct(); break;
			case 5 : findAllProducts(); break;
			case 6 : findPartProducts(); break;
			case 0 : System.out.println("프로그램을 종료합니다."); return;
			default : System.out.println("선택을 다시 하세요.");
			}
		}
	}
	
}
