package dto;

import java.sql.Date;

/*
	DTO(Data Transfer Object)
	
	1. 데이터 전송 객체
	2. 자바와 DB 간의 데이터 전송 단위
	3. VO(Value Object)와 같은 의미
	4. 생성
	    1) 테이블 칼럼 - 클래스 필드로 1:1 생성
	    2) getter/setter
*/

public class Board {

	private long no;
	private String title;
	private int hit;
	private Date created;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
