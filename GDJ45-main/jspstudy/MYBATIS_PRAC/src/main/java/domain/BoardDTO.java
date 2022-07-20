package domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardDTO {
	private Long no;
	private String writer;
	private String title;
	private String content;
	private Date created; 
}
