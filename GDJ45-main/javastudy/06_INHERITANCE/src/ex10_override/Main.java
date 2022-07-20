package ex10_override;

public class Main {

	public static void main(String[] args) {
		
		Regular r = new Regular("이대리", 200);
		System.out.println("직원명 " + r.getName() + ", 급여 " + r.getPay() + "만원");

		Temporary t = new Temporary("정과장", 1);
		t.setTimes(52 * 4);
		System.out.println("직원명 " + t.getName() + ", 급여 " + t.getPay() + "만원");
		
		Sales s = new Sales("최주임", 50);
		s.setSalesVolume(1000);
		s.setIncentive(0.1);
		System.out.println("직원명 " + s.getName() + ", 급여 " + s.getPay() + "만원");
		
	}

}
