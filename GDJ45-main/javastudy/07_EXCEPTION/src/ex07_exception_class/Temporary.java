package ex07_exception_class;

public class Temporary extends Employee {

	private int payPerHour;

	public Temporary(long empNo, String name, int payPerHour) {
		super(empNo, name);
		this.payPerHour = payPerHour;
	}

	@Override
	public String toString() {
		return "Temporary " + super.toString() + "[payPerHour=" + payPerHour + "]";
	}
	
}
