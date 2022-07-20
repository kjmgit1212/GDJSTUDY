package ex02_date_time;

import java.util.Calendar;

// 자바의 모든 클래스들은 특정 패키지 소속이다.
// java.lang 패키지(자바랭)는 기본 클래스를 저장한다.
// java.lang 패키지에 소속된 클래스들은 import가 필요 없다.
// java.lang 패키지 소속 클래스
// : System, String, Integer, Long, Double, Math 등 

public class Ex02_Calendar {

	public static void main(String[] args) {
		
		// java.util 패키지에 저장된 Calendar 클래스
		// calendar 인스턴스는 현재 날짜와 시간을 저장하고 있다.
		Calendar calendar = Calendar.getInstance();
		
		// Calendar는 특정 날짜/시간 요소가 필요할 때 사용한다.
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;     // 월: 0 ~ 11
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int weekNo = calendar.get(Calendar.DAY_OF_WEEK);  // 일:1, 월:2, ..., 토:7
		
		String[] weekNames = {"", "일", "월", "화", "수", "목", "금", "토"};
		System.out.println(year + "년 " + month + "월 " + day + "일 " + weekNames[weekNo] + "요일");
		
		int ap = calendar.get(Calendar.AM_PM);            // 오전:0, 오후:1
		int hour12 = calendar.get(Calendar.HOUR);         // 1 ~ 12
		int hour24 = calendar.get(Calendar.HOUR_OF_DAY);  // 0 ~ 23
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		String[] ampm = {"오전", "오후"};
		System.out.println(ampm[ap] + " " + hour12 + "시 " + minute + "분 " + second + "초");
		System.out.println(hour24 + "시 " + minute + "분 " + second + "초");
		
		// timestamp
		long timestamp = calendar.getTimeInMillis();
		System.out.println(timestamp);
		
	}

}
