package ex02_writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CSVMain {

	public static void main(String[] args) {
		
		// CSV 파일
		// 1. Comma Separate Values
		// 2. 콤마로 분리된 값들
		// 3. 기본 연결프로그램 : 스프레드시트(엑셀)
		
		// C:\storage\코로나.csv
		// 날짜,확진자,사망자\n
		// 2022-02-14,500,0\n
		// 2022-02-15,600,1\n
		// 2022-02-16,700,2\n
		
		List<String> header = Arrays.asList("날짜", "확진자", "사망자");
		List<String> content1 = Arrays.asList("2022-02-14", "500", "0");
		List<String> content2 = Arrays.asList("2022-02-15", "600", "1");
		List<String> content3 = Arrays.asList("2022-02-16", "700", "2");
		
		List<List<String>> list = Arrays.asList(header, content1, content2, content3);
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\코로나.csv", StandardCharsets.UTF_8));
			
			for(List<String> line : list) {
				
				for(int i = 0, size = line.size(); i < size; i++) {
					
					bw.write(line.get(i));
					
					if(i == size - 1)
						bw.write("\n");
					else
						bw.write(",");
					
				}
				
			}
			
			System.out.println("코로나.csv 파일이 생성되었습니다.");
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
