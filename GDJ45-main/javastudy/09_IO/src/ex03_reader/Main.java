package ex03_reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	
	// FileReader : 텍스트 파일을 읽을 때 사용
	
	// FileReader 입력 단위
	// 1. 1글자 : int
	// 2. 여러글자 : char[]
	public static void m1() {
		
		try {
			
			// 입력 스트림 생성
			FileReader fr = new FileReader("C:\\storage\\m2.txt");
			
			// 문자 저장 변수
			int c;
			
			// read 메소드
			// 1. 문자 반환
			// 2. -1 반환 (모두 읽은 경우)
			/*
			while(true) {
				c = fr.read();
				if(c == -1)
					break;
				System.out.print((char)c);
			}
			*/
			while((c = fr.read()) != -1) {
				System.out.print((char)c);
			}
			
			// 입력 스트림 닫기
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void quiz1() {
		
		try {
			
			// 입력 스트림 생성
			FileReader fr = new FileReader("C:\\storage\\m2.txt");
			
			// 문자 저장 변수
			int c;
			
			// read 메소드
			// 1. 문자 반환
			// 2. -1 반환 (모두 읽은 경우)
		
			StringBuilder sb = new StringBuilder();
			while((c = fr.read()) != -1) {
				sb.append((char)c);
			}
			
			// 확인
			System.out.println(sb.toString());
			
			// 입력 스트림 닫기
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m2() {
		
		try {
			
			// 입력 스트림 생성
			FileReader fr = new FileReader("C:\\storage\\m2.txt");
			
			// 5글자를 저장할 배열
			char[] cbuf = new char[5];
			
			// 읽기
			while(true) {
				
				int readCount = fr.read(cbuf);
				
				// m2.txt 파일 순회 과정
				//          readCount    cbuf
				// 순회1     5           A   p   p   l   e
				// 순회2     5           \n  M   a   n   g
				// 순회3     1           o   M   a   n   g
				//                       --  -------------  
				//             새로 읽은 o   기존에 있던 Mang
				// 순회4    -1
				
				if(readCount == -1)
					break;
				
				// 배열 cbuf 순회는 readCount만큼 처리
				for(int i = 0; i < readCount; i++)
					System.out.print(cbuf[i]);
				
			}
			
			// 입력 스트림 닫기
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void quiz2() {
		
		try {
			
			FileReader fr = new FileReader("C:\\storage\\m2.txt");
			
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[5];
			int readCount;
			
			while((readCount = fr.read(cbuf)) != -1) {
				// cbuf 배열의 인덱스 0부터 readCount만큼만 사용
				sb.append(cbuf, 0, readCount);
			}
			
			System.out.println(sb.toString());
			
			fr.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// BufferedReader : 속도를 높여주는 보조 스트림
	public static void m3() {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"));
			
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[5];
			int readCount;
			
			while((readCount = br.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCount);
			}
			
			System.out.println(sb.toString());
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// BufferedReader 클래스는 readLine 메소드 지원
	// readLine 메소드 : 텍스트 파일의 Line 단위로 읽어 들임
	public static void m4() {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"));

			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			
			System.out.println(sb.toString());
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 텍스트 파일 복사하기
	public static void quiz3() {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"));
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\m3_복사본.txt"));
			bw.write(sb.toString());
			
			bw.close();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// StringBuilder 없이 텍스트 파일 복사하기
	public static void quiz4() {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\m3_복사본.txt"));
			
			String line = null;
			
			while((line = br.readLine()) != null) {
				bw.write(line + "\n");				
			}
			
			bw.close();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		quiz4();
	}

}
