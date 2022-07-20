package ex02_date_time;

import java.sql.Date;

public class Ex04_java_sql_Date {

	public static void main(String[] args) {
		
		// java.sql 패키지에 저장된 Date 클래스
		// Oracle Database에 저장할 때 사용
		
		// 참고
		// DB는 날짜 형식은 "-", "/"로 나타냄
		
		Date now = new Date(System.currentTimeMillis());
		System.out.println(now);

	}

}
