package ex04_reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONMain {

	
	// JSON in JAVA 라이브러리
	// 1. 객체 { } -> JSONObject, Map
	// 2. 배열 [ ] -> JSONArray,  List
	
	// 예시
	/*
		JSONObject obj = {
			"name": "민경태", 
			"age": 45,
			"home": {"addr": "seoul", "tel": "010-1111-1111"},
			"hobbies": ["게임", "운동"]
		};
		
		String name = obj.getString("name");
		int age = obj.getInt("age");
		JSONObject home = obj.getJSONObject("home");
		String addr = home.getString("addr");
		String tel = home.getString("tel");
		JSONArray hobbies = obj.getJSONArray("hobbies");
	*/
	
	public static void m1() {
		
		// 코로나.json 파일은
		// [{}, {}, {}](JSON 배열 -> JAVA 리스트로 처리함)
		
		try {
			
			// 코로나.json 읽기
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\코로나.json"));
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			// String 타입의 JSON 데이터 readBody
			String readBody = sb.toString();  // readBody : "[{},{},{}]"
			
			// readBody를 이용해서 JSONArray 생성
			JSONArray array = new JSONArray(readBody);  // array : [{},{},{}]
			
			for(int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);  // obj : {}
				String date = obj.getString("date");
				int infection = obj.getInt("infection");
				int dead = obj.getInt("dead");
				System.out.println(date + "," + infection + "," + dead);
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m2() {
		
		// person.json 파일 읽어서 모든 정보 출력하기
		
		try {
			
			// person.json 읽기
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\person.json"));
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			// String 타입의 JSON 데이터
			String readBody = sb.toString();
			
			// JSONObject
			JSONObject obj = new JSONObject(readBody);
			
			String name = obj.getString("name");
			System.out.println("이름 : " + name);
			
			int age = obj.getInt("age");
			System.out.println("나이 : " + age);
			
			List<Object> friends = obj.getJSONArray("friends").toList();
			for(Object friend : friends) {
				HashMap<String, String> map = (HashMap<String, String>)friend;
				String friendName = map.get("name");
				String contact = map.get("contact");
				System.out.println("친구이름 : " + friendName);
				System.out.println("친구연락처 : " + contact);
			}
			
			JSONObject info = obj.getJSONObject("info");
			
			List<Object> list2 = info.getJSONArray("hobbies").toList();
			System.out.println("취미 : " + list2);
			
			String phone = info.getString("phone");
			System.out.println("전화 : " + phone);
			
			String address = info.getString("address");
			System.out.println("주소 : " + address);
			
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		m2();
	}

}
