package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDTO {
	private Long no;
	private String id;
	private String name;
	private String gender;
	private String address;
}
