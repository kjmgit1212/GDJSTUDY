package ex07_exception_class;

public class Regular extends Employee {
	
	private int salary;  // 기본급

	public Regular(long empNo, String name, int salary) {
		super(empNo, name);
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Regular " + super.toString() + "[salary=" + salary + "]";
	}
	
}
