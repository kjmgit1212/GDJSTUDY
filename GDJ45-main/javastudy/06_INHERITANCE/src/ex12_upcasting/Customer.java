package ex12_upcasting;

public class Customer {

	private int money;
	private int bonusPoint;
	private Product[] cart;  // Tv, Computer 모두 저장 가능
	private int idx;
	
	public Customer() {
		cart = new Product[5];
	}

	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getBonusPoint() {
		return bonusPoint;
	}
	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	public void purchase(Product product) {
		int price = product.getPrice();
		if(idx == cart.length) {
			System.out.println("더 이상 구매할 수 없습니다.");
			return;
		}
		if(money < price) {
			System.out.println("돈이 " + (price - money) + "만원 부족합니다.");
			return;
		}
		cart[idx++] = product;
		money -= price;
		bonusPoint += price * 0.05;
	}
	public void receipt() {
		if(idx == 0) {
			System.out.println("구매한 제품이 없습니다.");
			return;
		}
		int total = 0;
		for(int i = 0; i < idx; i++) {
			System.out.println(cart[i].getName() + "\t" + cart[i].getPrice() + "만원");
			total += cart[i].getPrice();
		}
		System.out.println("--------------------");
		System.out.println("총 구매금액 " + total + "만원");
		System.out.println("보너스 포인트 " + bonusPoint + "만원");
		System.out.println("남은금액 " + money + "만원");
	}
	
}