package ex05_constructor;

public class Alba extends Student {

	private String company;
	
	public Alba(String name, String school, String company) {
		// Student 클래스의 생성자를 "먼저" 호출해야 한다.
		super(name, school);
		this.company = company;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void work() {
		System.out.println(company + "에서 일한다.");
		System.out.println(getName() + "가 " + company + "에서 일한다.");
	}
	
}
