package ex08_override;

public class Coffee {

	private String bean;  // 원두
	
	public Coffee(String bean) {
		this.bean = bean;
	}
	
	public void taste() {
		System.out.println("씹는맛");
	}
	public void info() {
		System.out.println("원두: " + bean);
	}
	
}
