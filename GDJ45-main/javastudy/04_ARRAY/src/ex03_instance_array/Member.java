package ex03_instance_array;

public class Member {

	// field
	private String userId;
	private String name;
	
	// constructor
	public Member() {
		
	}
	public Member(String userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}
	
	// method
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
