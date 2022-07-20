package ex02_date_time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex06_LocalDateTime {

	public static void main(String[] args) {
		
		// LocalDateTime 클래스
		// 1. java.time 패키지에 저장된 클래스이다.
		// 2. JDK 1.8 이후에 지원되는 최신 날짜/시간 클래스이다.
		// 3. 특정 요소를 사용할 수 있고, pattern도 지정할 수 있다.
		
		LocalDateTime now = LocalDateTime.now();
		
		int year = now.getYear();
		int month = now.getMonthValue();  // 1 ~ 12
		int day = now.getDayOfMonth();
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		int nanoSecond = now.getNano();
		
		System.out.println(hour);
		System.out.println(minute);
		System.out.println(second);
		System.out.println(nanoSecond);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a h:mm yyyy-MM-dd");
		String res = dtf.format(now);
		System.out.println(res);

	}

}
