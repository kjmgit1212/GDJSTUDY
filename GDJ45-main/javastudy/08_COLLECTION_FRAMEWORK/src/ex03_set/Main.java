package ex03_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

	public static void m1() {
		
		// 세트 생성
		Set<String> set = new HashSet<String>();
		
		// 추가
		set.add("JAVA");
		set.add("DB");
		set.add("WEB");
		set.add("SPRING");
		set.add("JAVA");  // 중복 저장 시도
		
		// 확인
		System.out.println(set);
		
		// 삭제
		boolean res = set.remove("JAVA");
		System.out.println(res ? "삭제되었다." : "삭제되지 않았다.");
		
		// 크기
		int size = set.size();
		System.out.println("세트 길이 " + size);
		
		// 일반 for문 순회
		// 불가능하다. 인덱스가 없기 때문에
		
		// 향상 for문 순회
		for(String str : set)
			System.out.println(str);
		
	}
	
	public static void m2() {
		
		// 리스트 -> 세트 변환
		
		// 리스트
		List<String> list = new ArrayList<String>();
		list.add("국어");
		list.add("영어");
		list.add("수학");
		list.add("국어");
		list.add("영어");
		list.add("수학");
		
		// 세트
		Set<String> set = new HashSet<String>(list);
		
		System.out.println(set);
		
	}
	
	public static void m3() {
		
		// 반복자 활용하기
		
		// 세트
		Set<String> set = new HashSet<String>(Arrays.asList("국어", "영어", "수학"));
		
		// 반복자 생성
		// 세트(Collection)에 부착
		Iterator<String> itr = set.iterator();
		
		// 반복자 사용
		// hasNext() : 존재하는 데이터 유무 반환
		// next()    : hasNext()로 파악한 데이터 자체 반환
		while(itr.hasNext()) {
			String element = itr.next();
			System.out.println(element);
		}
		
	}

	public static void quiz() {
		
		// 로또 번호(1~45) 6개 랜덤 생성하기
		// 중복된 번호 생성 방지
		
		Set<Integer> lotto = new HashSet<Integer>();
		
		while(lotto.size() != 6)
			lotto.add((int)(Math.random() * 45 + 1));
		
		System.out.println(lotto);
		
	}
	
	public static void main(String[] args) {
		quiz();
	}

}
