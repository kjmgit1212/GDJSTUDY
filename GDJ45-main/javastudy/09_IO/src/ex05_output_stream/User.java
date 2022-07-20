package ex05_output_stream;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.ToString;

/*
	1. ObjectOutputStream 스트림을 통해서 데이터를 보낼 때는 직렬화가 필요하다.
	2. 직렬화
	    1) Serializable 인터페이스를 구현
	    2) 임의의 serialVersionUID 필드 추가
*/

@AllArgsConstructor
@ToString
public class User implements Serializable {
	
	private static final long serialVersionUID = 8893647767234402146L;
	
	private Long userNo;
	private String userId;
	private String userName;
	
}
