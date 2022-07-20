package ex07_file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

	// File 클래스 : 파일/디렉토리 정보 확인
	public static void m1() {
		
		// File 인스턴스 생성
		// 1. new File(경로, 파일)
		// 2. new File(경로\\파일)
		
		File file =  new File("C:\\storage", "b1.txt");
		
		System.out.println("파일명 : " + file.getName());
		System.out.println("경로 : " + file.getParent());
		System.out.println("전체경로(경로+파일) : " + file.getPath());
		System.out.println("수정한날짜 : " + file.lastModified());  // timestamp
		String lastModified = new SimpleDateFormat("a h:mm yyyy-MM-dd").format(file.lastModified());
		System.out.println("수정한날짜 : " + lastModified);
		System.out.println("파일크기 : " + file.length() + "byte");
		System.out.println("파일인가? " + file.isFile());
		System.out.println("폴더인가? " + file.isDirectory());
		System.out.println("존재하는가? " + file.exists());
		
	}
	
	// 디렉토리 조작
	public static void m2() {
		
		// 디렉터리를 지정
		File dir = new File("C:\\upload");
		
		// 존재하지 않으면 디렉터리 생성하기
		if( !dir.exists() ) {  // if(dir.exists() == false) {
			dir.mkdirs();  // 디렉터리 생성하기
		}
		// 존재하면 디렉터리 삭제하기
		else {
			// dir.delete();  // 바로 지우기
			dir.deleteOnExit();  // 자바 실행 끝나면 지우기
		}
		
	}
	
	// 파일 조작하기
	public static void m3() throws IOException {  // createNewFile() 메소드의 예외 처리
		
		File file = new File("C:\\storage", "my.txt");
		
		// 존재하지 않으면 my.txt 파일 생성
		if(file.exists() == false) {
			file.createNewFile();
		}
		// 존재하면 my.txt 파일 삭제
		else {
			file.delete();
		}
		
	}
	
	public static void quiz1() {
		
		// C:\\storage 디렉터리
		// 명령 프롬프트로 확인할 수 있는 정보 목록 만들기
		
		File dir = new File("C:\\storage");
		
		File[] files = dir.listFiles();  // 디렉터리(dir)에 저장된 모든 File 정보를 배열로 저장하기
		
		// 수정한날짜    <DIR>    디렉터리명
		// 수정한날짜    파일크기 파일명
		for(File file : files) {
			System.out.print(new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(file.lastModified()));
			System.out.print("    ");
			if(file.isDirectory())
				System.out.print("<DIR>");
			else
				System.out.print(new DecimalFormat("#,##0").format(file.length()));
			System.out.print("    ");
			System.out.println(file.getName());
		}
		
	}
	
	public static void quiz2() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("삭제할 파일명 입력 >>> ");
		String filename = sc.next();
		
		File file = new File("C:\\storage", filename);
		
		if(file.exists()) {
			System.out.println(filename + " 파일이 삭제되었습니다.");
			file.delete();
		} else {
			System.out.println(filename + " 파일이 존재하지 않습니다.");
		}
		
		sc.close();
		
	}
	
	// File + 입출력 스트림 함께 사용하기
	public static void m4() throws IOException {
		
		File dir = new File("C:\\storage");
		
		if ( !dir.exists() )
			dir.mkdirs();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(dir.getPath() + File.separator + "m.txt"));
		
		bw.write("안녕하세요. 반갑습니다.");
		
		bw.close();
		
	}
	
	public static void quiz3() throws IOException {
		
		// C:\\storage\\m.txt 파일을
		// C:\\upload 디렉토리로 이동하기
		
		// 원본 파일
		// C:\\storage\\m.txt
		File source = new File("C:\\storage\\m.txt");
		
		// 이동할 디렉터리
		File dir = new File("C:\\upload");
		if(!dir.exists())
			dir.mkdirs();
		
		// 복사 파일
		File copy = new File(dir, source.getName());
		
		// 복사하기
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copy));
		
		byte[] b = new byte[1024];
		int readCount;
		
		while((readCount = bis.read(b)) != -1)
			bos.write(b, 0, readCount);
		
		// 스트림 닫기
		bos.close();
		bis.close();
		
		// 원본 파일 삭제
		// 원본 파일과 복사본 파일의 크기가 동일하면 삭제
		if(source.length() == copy.length()) {
			source.deleteOnExit();
		}
		
	}
	
	public static void main(String[] args) throws IOException {  // main 메소드를 호출하는 곳으로 예외 처리를 넘김. 자바가 처리함.
		quiz3();
	}

}
