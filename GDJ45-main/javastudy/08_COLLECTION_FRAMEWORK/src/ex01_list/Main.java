package ex01_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void m1() {
		
		// 리스트 생성
		// List<String> list = new ArrayList<>();  가능
		List<String> list = new ArrayList<String>();
		
		// 요소 추가
		list.add("화");     // 마지막 요소로 추가
		list.add("수");     // 마지막 요소로 추가
		list.add(0, "월");  // 인덱스 0의 요소로 추가
		
		// 리스트 확인
		System.out.println(list);
		
		// 요소 제거
		String element = list.remove(0);
		System.out.println("삭제한 요소 " + element);
		boolean res = list.remove("화");
		System.out.println(res ? "삭제되었다." : "삭제되지않았다.");
		
	}

	public static void m2() {
		
		// 리스트 초기화
		List<String> list = Arrays.asList("월", "화", "수", "금");
		
		// 리스트 길이
		int size = list.size();
		
		// 개별 요소
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(size - 1));  // 마지막 요소
		
		// 수정
		list.set(size - 1, "목");
		
		// 확인
		System.out.println(list);
		
	}
	
	public static void m3() {
		
		// 리스트를 for문으로 순회하기
		
		List<String> list = new ArrayList<String>();
		
		list.add("월");
		list.add("화");
		list.add("수");
		
		// 일반 for문
		for(int i = 0, size = list.size(); i < size; i++)
			System.out.println(list.get(i));
		
		// 향상 for문
		for(String element : list)
			System.out.println(element);
		
	}
	
	public static void m4() {
		
		// 제네릭(generic) 타입은 오직 참조타입만 가능하다.
		// 기본타입의 wrapper(기본타입을 참조타입으로 정의해 놓은 것)
		// boolean    Boolean
		// int        Integer
		// long       Long
		// double     Double
		
		List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
		System.out.println(list);
		
	}
	
	public static void main(String[] args) {
		m4();
	}

}
