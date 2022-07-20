package quiz01;

public class School {

	// field
	private String name;
	private String location;
	
	// constructor
	public School() {
		
	}
	public School(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}
	
	// method
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
