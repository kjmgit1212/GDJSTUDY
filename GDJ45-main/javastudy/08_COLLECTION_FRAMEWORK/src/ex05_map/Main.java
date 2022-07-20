package ex05_map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class Main {

	public static void hashmap1() {
		
		// HashMap 생성
		Map<String, String> dict = new HashMap<String, String>();
		
		// 추가1. 새로운 key 사용 -> 추가
		dict.put("apple", "사과");
		dict.put("banana", "바나나");
		
		// 추가2. 기존 key 사용 -> 덮어쓰기(수정)
		dict.put("apple", "아이폰 만드는 회사");
		
		// 삭제
		String res = dict.remove("apple");  // 삭제할 요소의 '키'를 전달하면 삭제하고 해당 '값'을 반환
		System.out.println(res);
		
		// 확인
		System.out.println(dict);
		
	}

	public static void hashmap2() {
		
		// 국어, 영어, 수학 점수를 가지는 Map 생성하기
		
		Map<String, Integer> sungjuk = new HashMap<String, Integer>();
		
		sungjuk.put("kor", 90);
		sungjuk.put("eng", 80);
		sungjuk.put("math", 100);
		
		// '키'를 이용해서 '값'을 알아내기
		System.out.println("kor 점수 " + sungjuk.get("kor") + "점");
		System.out.println("eng 점수 " + sungjuk.get("eng") + "점");
		System.out.println("math 점수 " + sungjuk.get("math") + "점");
		
	}
	
	public static void hashmap3() {
		
		// Map의 순회-1
		// 1. 키를 모두 알아낸다.
		// 2. 알아낸 키를 이용해 값을 모두 알아낸다.
		
		String name = "민경태";
		int age = 45;
		double height = 178.5;
		
		Map<String, String> person = new HashMap<String, String>();
		person.put("name", name);
		person.put("age", String.valueOf(age));
		person.put("height", height + "");
		
		// 키를 모두 알아내기
		// 키는 Set 자료구조이다.
		Set<String> keys = person.keySet();
		
		// 키를 순회하면서 해당 키의 값을 알아내기
		for(String key : keys) {
			String value = person.get(key);
			System.out.println("키 " + key + ", 값 " + value);
		}
		
		/*
		for(String key : person.keySet())
			System.out.println("키 " + key + ", 값 " + person.get(key));
		*/
		
	}
	
	public static void hashmap4() {
		
		// Map 순회-2
		// 1. '키'-'값'을 하나의 단위로 Entry라고 한다.
		// 2. 모든 Entry를 추출해서 '키', '값'으로 분리한다.
		
		String name = "민경태";
		int age = 45;
		double height = 178.5;
		
		Map<String, Object> person = new HashMap<String, Object>();
		person.put("name", name);
		person.put("age", age);
		person.put("height", height);
		
		// Entry 단위로 순회하기
		Set<Map.Entry<String, Object>> entries = person.entrySet();
		for(Map.Entry<String, Object> entry : entries) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println("키 " + key + ", 값 " + value);
		}
		
		/*
		for(Map.Entry<String, Object> entry : person.entrySet())
			System.out.println("키 " + entry.getKey() + ", 값 " + entry.getValue());
		*/
		
	}
	
	public static void quiz() {
		
		// 사원번호(int empNo), 부서명(String dept), 사원명(String name)을 가지는
		// 사원(employee)을 Map으로 3명 만들고,
		// ArrayList에 사원(employee) 3명을 저장하시오.
		
		Map<String, Object> employee1 = new HashMap<String, Object>();
		employee1.put("empNo", 1000);
		employee1.put("dept", "영업");
		employee1.put("name", "이과장");
		
		Map<String, Object> employee2 = new HashMap<String, Object>();
		employee2.put("empNo", 1001);
		employee2.put("dept", "개발");
		employee2.put("name", "최대리");
		
		Map<String, Object> employee3 = new HashMap<String, Object>();
		employee3.put("empNo", 1002);
		employee3.put("dept", "기획");
		employee3.put("name", "박부장");
		
		List<Map<String, Object>> employees = new ArrayList<Map<String,Object>>();
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
		
		/*
		List<Map<String, Object>> employees = Arrays.asList(employee1, employee2, employee3);
		*/
		
		for(Map<String, Object> employee : employees)  // List
			for(Map.Entry<String, Object> entry : employee.entrySet())  // Map
				System.out.println(entry.getKey() + ":" + entry.getValue());
		
	}
	
	// 이진 트리(binary tree)
	// 1. 모든 노드의 자식은 2개이다. (왼쪽, 오른쪽)
	// 2. 왼쪽은 작은 값, 오른쪽은 큰 값이 저장된다.
	public static void treemap1() {
		
		// TreeMap
		// 1. key를 기준으로 왼쪽은 작은 값, 오른쪽은 큰 값이 저장된다.
		// 2. 범위 연산(이상, 이하, 초과, 미만)이 필요한 경우 사용한다.
		// 3. 자동으로 정렬되면서 저장된다.
		// 4. TreeMap 타입으로 선언하면 범위 연산을 쉽게 호출할 수 있다.
		
		TreeMap<Integer, String> people = new TreeMap<Integer, String>();
		
		people.put(45, "민경태");
		people.put(35, "김두식");
		people.put(55, "최백호");
		people.put(25, "박찬들");
		
		// 확인 -> 자동으로 key의 크기 순으로 저장되어 있음.
		System.out.println(people);
		
		while(people.isEmpty() == false) {  // while( !people.isEmpty() ) {
			Map.Entry<Integer, String> entry = people.pollFirstEntry();  // 작은 값을 Map에서 뺌
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
	}
	
	public static void treemap2() {
		
		// 기본 생성은 key순으로 오름차순 처리됨
		TreeMap<Integer, String> people = new TreeMap<Integer, String>();
		
		people.put(45, "민경태");
		people.put(35, "김두식");
		people.put(55, "최백호");
		people.put(25, "박찬들");
		
		// 정렬할 때 descendingMap 메소드를 사용
		// 사용할 때마다 정렬이 바뀜
		
		// people의 key순으로 내림차순 처리된 map
		NavigableMap<Integer, String> map = people.descendingMap();
		for(Map.Entry<Integer, String> entry : map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
		
		// 다시 오름차순을 하려면? descendingMap 메소드를 다시 사용
		NavigableMap<Integer, String> map2 = map.descendingMap();
		for(Map.Entry<Integer, String> entry : map2.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
		
	}
	
	public static void main(String[] args) {
		treemap2();
	}

}
