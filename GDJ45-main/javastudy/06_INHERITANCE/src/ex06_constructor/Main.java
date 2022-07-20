package ex06_constructor;

public class Main {

	public static void main(String[] args) {
		
		BulgogiPizza pizza = new BulgogiPizza("오리지널", 200, "국내산");
		
		pizza.info();  // 오리지널 도우, 치즈 200g, 국내산 불고기 사용
		
	}

}
