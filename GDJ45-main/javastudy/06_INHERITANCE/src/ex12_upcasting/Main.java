package ex12_upcasting;

public class Main {

	public static void main(String[] args) {
		
		Customer customer = new Customer();
		
		customer.setMoney(1000);
		
		customer.purchase(new Tv("QLED75", 300));
		customer.purchase(new Computer("gram", 200));
		
		customer.receipt();  // 영수증 발부

	}

}
