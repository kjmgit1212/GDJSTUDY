package ex02_date_time;

public class Ex01_System {

	public static void main(String[] args) {
		
		// 1. timestamp
		//    1970-01-01 0:00부터 1/1000초마다 증가하고 있는 long 타입의 정수값
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		
		// 2. nanotime
		//    1) 어떤 기준점은 없다.
		//    2) 1/1,000,000,000초 단위의 long 타입의 정수 값
		//    3) 경과 시간을 계산하는 용도이다.
		long begin = System.nanoTime();
		int a = 1 + 2 + 3 + 4 + 5;
		long end = System.nanoTime();
		System.out.println((end - begin) + "ns");
		
	}

}
