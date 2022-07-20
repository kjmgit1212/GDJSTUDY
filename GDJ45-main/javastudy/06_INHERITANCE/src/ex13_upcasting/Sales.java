package ex13_upcasting;

public class Sales extends Regular {

	private int salesVolume;
	private double incentive;
	
	public Sales(String name, int salary, int salesVolume, double incentive) {
		super(name, salary);
		this.salesVolume = salesVolume;
		this.incentive = incentive;
	}
	
	public int getSalesPay() {
		return (int)(salesVolume * incentive);
	}
	@Override
	public int getPay() {
		// return 기본급 + 판매수당
		return super.getPay() + getSalesPay();
	}
	
}
