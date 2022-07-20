package ex02_api;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONObject;


public class PapagoMain {

	public static void main(String[] args) {
		
		
		// 번역할 언어 선택
		Scanner sc = new Scanner(System.in);
		
		System.out.println("번역할 언어 선택");
		System.out.print("1.한국어  2.영어  >>> ");
		int choice = sc.nextInt();
		sc.nextLine();  // 숫자 입력 후 누른 엔터 제거
		
		String source = null;  // 원본 언어 코드
		String target = null;  // 목적 언어 코드
		switch(choice) {
		case 1:
			source = "ko"; target = "en";
			break;
		case 2:
			source = "en"; target = "ko";
			break;
		default:
			System.out.println("잘못된 선택입니다.");
			return;  // main 메소드 종료
		}
		
		
		// 번역할 텍스트 입력
		System.out.print("번역할 텍스트 입력 >>> ");
		String text = sc.nextLine();
		try {
			text = URLEncoder.encode(text, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			System.out.println("인코딩 오류");
		}
		
		
		// 요청 URL 접속
		String apiUrl = "https://openapi.naver.com/v1/papago/n2mt";
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiUrl);
			con = (HttpURLConnection)url.openConnection();
		} catch(MalformedURLException e) {
			System.out.println("API 주소가 잘못되었습니다.");
		} catch (IOException e) {
			System.out.println("API 연결이 실패했습니다.");
		}
		
		
		// 요청
		// request : 클라이언트 -> 서버로 데이터 보내기
		// 보낼 데이터 : 파라미터(source, target, text), clientId, clientSecret
		String clientId = "XvHSzjB4Bj49XBbfspdN";
		String clientSecret = "ARhr4OmF6r";
		String params = "source=" + source + "&target=" + target + "&text=" + text;
		try {
			// 요청 메소드
			con.setRequestMethod("POST");
			// 요청 헤더
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			// con으로 데이터 보내는가? true
			con.setDoOutput(true);
			// 파라미터 보내기
			BufferedOutputStream bos = new BufferedOutputStream(con.getOutputStream());  // 바이트 기반
			bos.write(params.getBytes());  // getBytes() : String -> byte[]
			bos.flush();  // 혹시 스트림에 남은 게 있으면 모두 보낸다.
			bos.close();
		} catch (IOException e) {
			System.out.println("API 요청이 실패했습니다.");
		}
		
		
		// 응답
		// response : 서버 -> 클라이언트에게 데이터 보내기
		// 응답 데이터 형식 : JSON
		
		try {
			
			// 응답확인
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {  // 성공
				System.out.println("응답 성공");
			} else {
				System.out.println("응답 실패");
				System.out.println("응답 코드 : " + responseCode);
				return;  // main 메소드 종료
			}
			
			// 응답 받아오기
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null)
				sb.append(line);
			String responseText = sb.toString();
			// System.out.println(responseText);
			
			// JSONObject 처리
			JSONObject obj = new JSONObject(responseText);
			JSONObject message = obj.getJSONObject("message");
			JSONObject result = message.getJSONObject("result");
			System.out.println("번역 결과 : " + result.getString("translatedText"));
			
		} catch (IOException e) {
			System.out.println("API 응답이 실패했습니다.");
		}
		
		
	}

}
