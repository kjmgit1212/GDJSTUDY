package jdbc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@Builder  // Builder 패턴을 이용해서 ProductDTO 생성
public class ProductDTO {
	private Long product_no;
	private String name;
	private Integer price;
	private String image;
}
