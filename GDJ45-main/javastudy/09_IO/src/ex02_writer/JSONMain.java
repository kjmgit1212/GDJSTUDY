package ex02_writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONMain {


	// JSON 파일
	// 1. JavaScript Object Notation
	// 2. 자바스크립트 객체 표현법
	
	
	// JSON in JAVA 라이브러리
	// 1. JSONObject 클래스 : { } -> Map
	// 2. JSONArray  클래스 : [ ] -> List
	
	
	public static void m1() {
		
		/*
		[
			{
				"date": "2022-02-14",
				"infection": 500,
				"dead": 0
			},
			{
				"date": "2022-02-15",
				"infection": 600,
				"dead": 1
			},
			{
				"date": "2022-02-16",
				"infection": 700,
				"dead": 2
			}
		]
		*/
		
		List<Object> content1 = Arrays.asList("2022-02-14", 500, 0);
		List<Object> content2 = Arrays.asList("2022-02-15", 600, 1);
		List<Object> content3 = Arrays.asList("2022-02-16", 700, 2);
		
		List<List<Object>> list = Arrays.asList(content1, content2, content3);
		
		
		JSONArray array = new JSONArray();
		
		for(List<Object> data : list) {
				
			JSONObject obj = new JSONObject();
			obj.put("date", data.get(0));
			obj.put("infection", data.get(1));
			obj.put("dead", data.get(2));
			
			array.put(obj);
			
		}
		
		// 코로나.json 만들기
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\코로나.json"))) {
			bw.write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m2() {
		
		/*
			{
				"name": "민경태",
				"age": 45,
				"info": {
					"hobbies": [
						"넷플릭스",
						"디즈니플러스",
						"티빙"
					],
					"address": "서울시",
					"phone": "010-1111-1111"
				},
				"friends": [
					{
						"name": "철수",
						"contact": "010-2222-2222"
					},
					{
						"name": "영희",
						"contact": "010-3333-3333"
					}
				]
			}
		*/
		
		Map<String, Object> person = new HashMap<String, Object>();
		
		person.put("name", "민경태");   //
		person.put("age", 45);          //
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("hobbies", Arrays.asList("넷플릭스", "디즈니플러스", "티빙"));
		info.put("address", "서울시");
		info.put("phone", "010-1111-1111");
		
		person.put("info", info);       //
		
		List<Map<String, String>> friends = new ArrayList<Map<String,String>>();
		
		Map<String, String> friend1 = new HashMap<String, String>();
		friend1.put("name", "철수");
		friend1.put("contact", "010-2222-2222");
		
		Map<String, String> friend2 = new HashMap<String, String>();
		friend2.put("name", "영희");
		friend2.put("contact", "010-3333-3333");
		
		friends.add(friend1);
		friends.add(friend2);
		person.put("friends", friends);  //
		
		JSONObject obj = new JSONObject(person);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\person.json"))) {
			// bw.write(obj.toString());  // 한 줄로 파일 생성
			obj.write(bw, 4, 0);  // 들여쓰기(4칸) 형식 갖춰서 파일 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		m2();
	}

}
