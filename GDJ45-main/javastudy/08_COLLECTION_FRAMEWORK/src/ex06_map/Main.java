package ex06_map;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		// Student 인스턴스를 key로 사용하고,
		// Student의 점수를 value로 사용하는 Map
		
		Map<Student, Integer> scores = new HashMap<Student, Integer>();
		scores.put(new Student("철수", "서울대학교"), 90);
		scores.put(new Student("영희", "고려대학교"), 95);
		scores.put(new Student("영수", "연세대학교"), 100);
		
		// "철수"의 점수를 80점으로 수정하기
		// Student 인스턴스의 동등 비교를 위해서
		// Student 클래스에 hashCode(), equals() 메소드의 오버라이드가 필요함.
		scores.put(new Student("철수", "서울대학교"), 80);
		
		// scores 순회
		// 이름 철수, 학교 서울대학교, 점수 80점
		// 이름 영희, 학교 고려대학교, 점수 95점
		// 이름 영수, 학교 연세대학교, 점수 100점
		for(Map.Entry<Student, Integer> entry : scores.entrySet()) {
			Student key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("이름 " + key.getName() + ", 학교 " + key.getSchool() + ", 점수 " + value + "점");
		}
		
	}

}
