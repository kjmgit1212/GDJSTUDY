package domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	private String title;
	private String writer;
	private String content;
	private Date date;
}
