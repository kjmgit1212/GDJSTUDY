package ex06_constructor;

public class Pizza {

	private String dough;
	private int cheese;
	
	public Pizza(String dough, int cheese) {
		super();
		this.dough = dough;
		this.cheese = cheese;
	}
	
	public String getDough() {
		return dough;
	}
	public void setDough(String dough) {
		this.dough = dough;
	}
	public int getCheese() {
		return cheese;
	}
	public void setCheese(int cheese) {
		this.cheese = cheese;
	}
	
}
