package ex01_writer;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	
	// Stream
	// 1. 데이터가 지나다니는 통로 개념으로 이해
	// 2. 일방통행(출력 스트림, 입력 스트림) 개념
	// 3. 항상 IOException 예외 처리가 필요
	
	
	// FileWriter : 텍스트 파일을 만들 때 사용
	public static void m1() {
		// 기본 형식
		// finally 블록에서 close 처리
		FileWriter fw = null;
		try {
			// 자바 경로 : 구분자(separator)로 역슬래시 사용(\\)
			fw = new FileWriter("C:\\storage\\m1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// FileWriter 출력 단위
	// 1. 1글자 : int
	// 2. 여러글자 : char[], String
	public static void m2() {
		
		try {
			
			// 출력 스트림 생성
			FileWriter fw = new FileWriter("C:\\storage\\m2.txt");
			
			// 출력 데이터
			int c = 'A';
			char[] cbuf = {'p', 'p', 'l', 'e'};
			String str = "Mango";
			
			// 출력(데이터를 출력 스트림으로 보냄 -> 파일에 기록됨)
			// write 메소드 사용
			fw.write(c);
			fw.write(cbuf);
			fw.write('\n');
			fw.write(str);
			
			// 출력 스트림 닫기
			fw.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("경로를 확인하세요.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// try catch resources
	// 1. 스트림을 자동으로 close 해 주는 try 문이다.
	// 2. 형식
	// try(스트림 생성) { } catch (IOException e) { }
	public static void m3() {
		
		try(FileWriter fw = new FileWriter("C:\\storage\\m3.txt")) {
			
			fw.write("안녕하세요\n");
			fw.write("내 이름은 민경태입니다.\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	// BufferedWriter : 속도를 높여주는 보조 스트림
	public static void m4() {
		
		try {
			
			// 메인 스트림 생성
			FileWriter fw = new FileWriter("C:\\storage\\m4.txt");
			
			// 보조 스트림 생성
			BufferedWriter bw = new BufferedWriter(fw);
			
			// 출력할 데이터
			char[] cbuf = {'h', 'e', 'l', 'l', 'o'};
			
			// 출력은 보조 스트림을 이용
			bw.write(cbuf, 0, 4);  // 인덱스 0부터 4개 데이터만 보냄
			
			// 출력 스트림 닫기 -> 보조 스트림만 닫으면 됨
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		m4();
	}

}
