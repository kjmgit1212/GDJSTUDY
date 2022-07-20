package ex01_network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Main {

	// URL
	// 1. Uniform Resource Locator : 정형화된 자원의 위치
	// 2. 쉽게 말해서 인터넷 주소 체계를 의미한다.
	// 3. 구성
	//    프로토콜://     호스트     /  서버경로  ?파라미터
	//       https://search.naver.com/search.naver?query=날씨
	
	public static void m1() {
		
		// URL 인스턴스 사용시 예외처리 필수
		
		try {
			
			String apiUrl = "https://search.naver.com/search.naver?query=날씨";
			URL url = new URL(apiUrl);
			
			System.out.println("프로토콜 : " + url.getProtocol());
			System.out.println("호스트 : " + url.getHost());
			System.out.println("파일 : " + url.getFile());
			System.out.println("쿼리 : " + url.getQuery());
		
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		}
		
	}
	
	
	// HttpURLConnection : 실제 접속을 담당
	public static void m2() {
		
		try {
			
			String apiUrl = "https://www.naver.com";
			URL url = new URL(apiUrl);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			if(con.getResponseCode() == 200)
				System.out.println("API 접속 성공");
			
			con.disconnect();
			
		} catch (MalformedURLException e) {  // URL
			System.out.println("API 주소 오류");
		} catch (IOException e) {            // HttpURLConnection
			System.out.println("API 접속 오류");
		}
		
	}
	
	
	// 파일 내려 받기
	// 기상청 XML 중 하나 소개
	// http://www.kma.go.kr/XML/weather/sfc_web_map.xml
	public static void m3() {
		
		try {
			
			String apiUrl = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml";
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 입력 스트림 생성(바이트 기반)
			InputStream in = con.getInputStream();
			
			// 문자 기반 입력 스트림 생성
			InputStreamReader isr = new InputStreamReader(in);
			
			// 입력 스트림에 버퍼 추가
			BufferedReader br = new BufferedReader(isr);
			
			// 생성할 파일의 디렉토리 작업
			File dir = new File("C:\\storage");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			// 생성할 파일명 == URL 통해서 확인
			String filename = url.getFile();  // "/XML/weather/sfc_web_map.xml"
			String[] tokens = filename.split("/");
			File file = new File(dir, tokens[tokens.length - 1]);
			
			// 문자 기반 출력 스트림 생성
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			// 내려 받기
			// br에서 내려 받은 내용을 bw로 보냄
			String line = null;
			while((line = br.readLine()) != null)
				bw.write(line + "\n");
			
			// 사용한 스트림/접속 닫기
			bw.close();
			br.close();
			con.disconnect();
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 서버 오류");
		}
		
	}
	
	
	// 인코딩/디코딩
	// 인코딩 : UTF-8 사용하여 암호화
	// 디코딩 : UTF-8 사용하여 복호화
	public static void m4() {
		
		String str1 = "자바 JAVA !@#$";
		String str2 = "자바+JAVA+!@#$";
		
		try {
			
			// 인코딩
			
			String encodedStr1 = URLEncoder.encode(str1, "UTF-8");
			// encodedStr1 = %EC%9E%90%EB%B0%94+JAVA+%21%40%23%24

			String encodedStr2 = URLEncoder.encode(str2, StandardCharsets.UTF_8);
			// encodedStr2 = %EC%9E%90%EB%B0%94%2BJAVA%2B%21%40%23%24
			
			System.out.println(encodedStr1);
			System.out.println(encodedStr2);
			
			
			// 디코딩
			String decodedStr1 = URLDecoder.decode(encodedStr1, "UTF-8");
			// decodedStr1 = "자바 JAVA !@#$"
			
			String decodedStr2 = URLDecoder.decode(encodedStr2, StandardCharsets.UTF_8);
			// decodedStr2 = "자바+JAVA+!@#$"
			
			System.out.println(decodedStr1);
			System.out.println(decodedStr2);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public static void main(String[] args) {
		m4();
	}

}
