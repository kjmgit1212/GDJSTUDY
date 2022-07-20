package properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTestMain {

	public static void main(String[] args) {
		
		try {
			
			// 경로가 없는 파일은 프로젝트에 있는 것으로 본다.
			// 12_JDBC\\db.properties
			InputStream in = new FileInputStream("db.properties");
			
			Properties properties = new Properties();
			properties.load(in);  // 프로퍼티 파일을 읽을 수 있도록 입력 스트림 전달
			
			// 프로퍼티 파일
			// key1=값1
			// key2=값2
			// ...
			
			// 프로퍼티 파일 읽기
			// getProperty("key1") 호출하면 "값1"이 반환된다.
			// getProperty("key2") 호출하면 "값2"이 반환된다.
			
			System.out.println(properties.getProperty("url"));
			System.out.println(properties.getProperty("user"));
			System.out.println(properties.getProperty("password"));
			
			in.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
