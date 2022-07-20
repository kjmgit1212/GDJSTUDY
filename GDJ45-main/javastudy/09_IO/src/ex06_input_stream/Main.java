package ex06_input_stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.List;

import ex05_output_stream.User;

public class Main {

	// FileInputStream : 모든 파일을 읽음
	
	// FileInputStream 입력 단위
	// 1. 1개    : int
	// 2. 여러개 : byte[]
	public static void m1() {
		
		try {
			
			// 입력 스트림 생성
			FileInputStream fis = new FileInputStream("C:\\storage\\b1.txt");
			
			// 사용 변수 및 인스턴스
			StringBuilder sb = new StringBuilder();
			byte[] b = new byte[5];  // 바이트 수는 임의로 조정
			int readCount;
			
			// read 메소드
			// int readCount = fis.read(b);  읽은 내용은 배열 b에 저장, 읽은 바이트 수는 readCount에 저장
			while((readCount = fis.read(b)) != -1) {
				
				// byte[] b를 String으로 변환
				// new String(b, 0, readCount) : 배열 b의 인덱스 0부터 readCount만큼만 문자열로 변환
				
				// b1.txt 파일은 오직 영문(1글자는 1바이트)으로만 구성되므로
				// byte[]에 읽는 것이 문제 없음
				
				sb.append( new String(b, 0, readCount) );
				
			}
			
			// 확인
			System.out.println(sb.toString());
			
			// 입력 스트림 닫기
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// InputStreamReader : 바이트 기반 입력 스트림(InputStream)을 
	//                     문자 기반 입력 스트림(Reader)으로 변환
	public static void m2() {
		
		try {
			
			// 입력 스트림 생성(InputStream)
			InputStream is = new FileInputStream("C:\\storage\\b2.bin");
			
			// 문자 기반 스트림
			InputStreamReader isr = new InputStreamReader(is);
			
			// 버퍼
			BufferedReader br = new BufferedReader(isr);
			
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
	
	
	// 파일 복사하기
	// 소요시간 출력
	public static void quiz() {
		
		try {
			
			// 원본 읽기 스트림
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\storage\\eclipse-jee-2021-03-R-win32-x86_64.zip"));
			
			// 복사본 생성하기 스트림
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\storage\\eclipse.zip"));
			
			byte[] b = new byte[1024];  // 1024바이트 = 1킬로바이트(KB)
			int readCount;
			
			// 시작 시간
			long start = System.currentTimeMillis();
			
			// 복사
			while((readCount = bis.read(b)) != -1)
				bos.write(b, 0, readCount);
			
			// 종료 시간
			long end = System.currentTimeMillis();
			
			// 확인
			System.out.println("복사 완료(" + (end - start) * 0.001 + "초 소요)");
			
			// 스트림 닫기
			bos.close();
			bis.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// DataInputStream : 파일에 저장된 변수 읽는 스트림
	public static void m3() {
		
		try {
			
			DataInputStream dis = new DataInputStream(new FileInputStream("C:\\storage\\b4.dat"));
			
			int age = dis.readInt();
			double height = dis.readDouble();
			String name = dis.readUTF();
			
			System.out.println(age + "," + height + "," + name);
			
			dis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// ObjectInputStream : 객체(인스턴스)를 그대로 입력받는 보조 스트림
	public static void m4() {
		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\storage\\b5.dat"));
			
			List<User> users = (List<User>)ois.readObject();
			for (User user : users) {
				System.out.println(user);
			}
			
			User user = (User)ois.readObject();
			System.out.println(user);
			
			ois.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		m4();
	}

}
